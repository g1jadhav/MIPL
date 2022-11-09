package mahyco.mipl.nxg.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONObject;

import java.util.List;

import mahyco.mipl.nxg.MainActivity;
import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.view.registration.Registration;

public class Login extends AppCompatActivity implements LoginAPIListener {
    EditText et_usercode, et_password;
    Button btn_login;
    Context context;
    String str_usercode, str_password;
    LoginAPI loginAPI;
    SearchableSpinner country_drop_down;
    JsonObject categoryJson;
    ArrayAdapter adapter;
    int countrycode = 0;
    String countryname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        context = Login.this;
        loginAPI = new LoginAPI(context, this);
        et_password = findViewById(R.id.et_password);
        et_usercode = findViewById(R.id.et_usercode);
        btn_login = findViewById(R.id.btn_login);
        country_drop_down = findViewById(R.id.country_drop_down);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_usercode = et_usercode.getText().toString().trim();
                str_password = et_password.getText().toString().trim();
                ValidateLogin(str_usercode, str_password);
            }
        });
        // Toast.makeText(context, ""+Preferences.get(context,Preferences.USER_ID), Toast.LENGTH_SHORT).show();
        if (Preferences.get(context, Preferences.USER_ID) != null && !Preferences.get(context, Preferences.USER_ID).trim().equals("")) {

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            categoryJson = new JsonObject();
            categoryJson.addProperty("filterValue", "0");
            categoryJson.addProperty("FilterOption", "GetCountryList");
//            loginAPI.getCategory(categoryJson);
            loginAPI.getCountry(categoryJson);
        }
    }

    private void ValidateLogin(String str_usercode, String str_password) {
        if (validateUI()) {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("username", str_usercode);
            jsonObject.addProperty("password", str_password);
            jsonObject.addProperty("CountryId", countrycode);
            loginAPI.validateLogin(jsonObject);
        }
    }

    private boolean validateUI() {
        try {
            int cnt = 0;
            if (country_drop_down.getSelectedItemPosition() == -1) {
                Toast.makeText(this, "Please select country", Toast.LENGTH_SHORT).show();
                cnt++;
            }
            if (str_usercode.isEmpty()) {
                et_usercode.setError("Please Enter User Code");
                cnt++;
            }
            if (str_password.isEmpty()) {
                et_password.setError("Please enter Password");
                cnt++;
            }

            if (cnt == 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onResult(String result) {
        // Toast.makeText(context, "" + result, Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObject = new JSONObject(result.trim());
            boolean success = jsonObject.getBoolean("success");
            if (success) {
                try {

                    //  JSONObject jsonObject1 = jsonObject.getJSONObject("Token");
                    JSONObject jsonTokenDetails = jsonObject.getJSONObject("Token");
                    //     Toast.makeText(context, "" + jsonTokenDetails.getInt("IQCPlantId"), Toast.LENGTH_SHORT).show();
                    // Allow Only TMB and RBM Means User Role id Shouldbe 3 and 4
                    //   if (jsonTokenDetails.getInt("RoleId") == 3 || jsonTokenDetails.getInt("RoleId") == 4) {
                    Preferences.save(context, Preferences.TOKEN, jsonTokenDetails.getString("access_token"));
                    Preferences.save(context, Preferences.USER_ID, jsonTokenDetails.getString("UserCode"));
                    Preferences.save(context, Preferences.USER_NAME, jsonTokenDetails.getString("UserName"));
                    Preferences.save(context, Preferences.ROLE_ID, jsonTokenDetails.getString("RoleId"));
                    Preferences.save(context, Preferences.ROLE_NAME, jsonTokenDetails.getString("Role"));
                    Preferences.save(context, Preferences.USER_EMAIL, jsonTokenDetails.getString("EmailId"));
                    Preferences.save(context, Preferences.LOGINID, jsonTokenDetails.getString("LoginId"));
                    Preferences.save(context, Preferences.COUNTRYCODE, "" + countrycode);
                    Preferences.save(context, Preferences.COUNTRYNAME, "" + countryname);

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //     } else {
                    //       Toast.makeText(context, "Access Denied.", Toast.LENGTH_SHORT).show();
                    //    }
                } catch (Exception e) {
                    Toast.makeText(context, "Error is" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Username and Password.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "JsonParsing Error .", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListResponce(List result) {
        try {
            adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, result);
            country_drop_down.setAdapter(adapter);
            country_drop_down.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CategoryModel categoryModel = (CategoryModel) parent.getItemAtPosition(position);
                    Toast.makeText(context, "" + categoryModel.getCountryName(), Toast.LENGTH_SHORT).show();
                    countrycode = categoryModel.getCategoryId();
                    countryname = categoryModel.getCountryName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    public void onCountryListResponce(List result) {
        try {
            adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, result);
            country_drop_down.setAdapter(adapter);
            country_drop_down.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CategoryChildModel categoryModel = (CategoryChildModel) parent.getItemAtPosition(position);
                    //Toast.makeText(context, "" + categoryModel.getCountryName(), Toast.LENGTH_SHORT).show();
                    countrycode = categoryModel.getCountryMasterId();
                    countryname = categoryModel.getCountryName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (Exception e) {
        }
    }

    public void register(View c) {
        try {

            Intent intent = new Intent(context, Registration.class);
            startActivity(intent);

        } catch (Exception e) {

        }
    }


}