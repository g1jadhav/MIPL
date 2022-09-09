package mahyco.iqc.nxg.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import mahyco.iqc.nxg.MainActivity;
import mahyco.iqc.nxg.R;
import mahyco.iqc.nxg.util.Preferences;

public class Login extends AppCompatActivity implements LoginAPIListener {
    EditText et_usercode, et_password;
    Button btn_login;
    Context context;
    String str_usercode, str_password;
    LoginAPI loginAPI;

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
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_usercode = et_usercode.getText().toString().trim();
                str_password = et_password.getText().toString().trim();
                ValidateLogin(str_usercode, str_password);
            }
        });
       // Toast.makeText(context, ""+Preferences.get(context,Preferences.USER_ID), Toast.LENGTH_SHORT).show();
      if( Preferences.get(context,Preferences.USER_ID)!=null&&!Preferences.get(context,Preferences.USER_ID).trim().equals(""))
        {

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void ValidateLogin(String str_usercode, String str_password) {
        if (validateUI()) {
         
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("username", str_usercode);
            jsonObject.addProperty("password", str_password);
            loginAPI.validateLogin(jsonObject);
        }

    }

    private boolean validateUI() {
        try {
            int cnt = 0;

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
                        Preferences.save(context, Preferences.USER_ID, jsonTokenDetails.getString("EmpCode"));
                        Preferences.save(context, Preferences.USER_NAME, jsonTokenDetails.getString("EmpName"));
                        Preferences.save(context, Preferences.ROLE_ID, jsonTokenDetails.getString("RoleId"));
                        Preferences.save(context, Preferences.ROLE_NAME, jsonTokenDetails.getString("Role"));
                        Preferences.save(context, Preferences.USER_EMAIL, jsonTokenDetails.getString("EmpEmail"));
                        Preferences.save(context, Preferences.IQCPlantId, jsonTokenDetails.getString("IQCPlantId"));

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
               //     } else {
                 //       Toast.makeText(context, "Access Denied.", Toast.LENGTH_SHORT).show();
                //    }
                } catch (Exception e) {
                    Toast.makeText(context, "Error is"+e.getMessage(), Toast.LENGTH_SHORT).show();
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

    }
}