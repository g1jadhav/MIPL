package mahyco.mipl.nxg.view.registration;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.List;

import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryModel;

public class Registration extends AppCompatActivity implements RegistrationListener {

    EditText et_staffname, et_staffid, et_mobileno, et_password, et_confirmpassword, et_email;
    SearchableSpinner sp_country;
    Context context;

    String str_staffname, str_staffid, str_mobile, str_password, str_confirmpassword, str_countrycode;

    JsonObject jsonObject;
    RegisrationAPI regisrationAPI;
    ArrayAdapter adapter;
    JsonObject categoryJson;
    int ccode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = Registration.this;

        init();
    }

    public void init() {
        regisrationAPI = new RegisrationAPI(context, this);
        et_staffname = findViewById(R.id.staff_name);
        et_staffid = findViewById(R.id.staff_id);
        et_mobileno = findViewById(R.id.mobile_no);
        et_password = findViewById(R.id.registration_password);
        et_confirmpassword = findViewById(R.id.registration_confirm_password);
        sp_country = findViewById(R.id.registration_country_drop_down);
        et_email = findViewById(R.id.emailid);

        categoryJson = new JsonObject();
        categoryJson.addProperty("filterValue", "0");
        categoryJson.addProperty("FilterOption", "Position");
        regisrationAPI.getCategory(categoryJson);
    }

    public void submit(View view) {
        try {

            register();

        } catch (Exception e) {

        }
    }

    public void register() {

        if (TextUtils.isEmpty(et_staffname.getText().toString().trim())) {
            Toast.makeText(this, "Please enter staff name", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(et_staffid.getText().toString().trim())) {
            Toast.makeText(this, "Please enter staff id", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(et_mobileno.getText().toString().trim())) {
            Toast.makeText(this, "Please enter mobile no", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(et_email.getText().toString().trim())) {
            Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString().trim()).matches()) {
            Toast.makeText(this, "Please enter valid email id", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(et_confirmpassword.getText().toString().trim())) {
            Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            return;
        } else if (!et_password.getText().toString().trim().equalsIgnoreCase(et_confirmpassword.getText().toString().trim())) {
            Toast.makeText(this, "Password and confirm password not match", Toast.LENGTH_SHORT).show();
            return;
        }
        str_staffname = et_staffname.getText().toString().trim();
        str_staffid = et_staffid.getText().toString().trim();
        str_mobile = et_mobileno.getText().toString().trim();
        str_password = et_password.getText().toString().trim();
        str_confirmpassword = et_confirmpassword.getText().toString().trim();
        str_countrycode = sp_country.getSelectedItem().toString();

        Toast.makeText(context, "" + str_countrycode, Toast.LENGTH_SHORT).show();

   /*     if(str_countrycode.trim().equals("Zimbabwe"))
        {
            ccode=19;
        }else if(str_countrycode.trim().equals("Malawi"))
        {
            ccode=20;
        }
        else if(str_countrycode.trim().equals("India"))
        {
            ccode=21;
        }else
        {
            ccode=21;
        }*/

        jsonObject = new JsonObject();

        jsonObject.addProperty("LoginId", 0);
        jsonObject.addProperty("CountryId", ccode);
        jsonObject.addProperty("RoleId", 3);
        jsonObject.addProperty("Role", "MDO");
        jsonObject.addProperty("UserType", "OnRole");
        jsonObject.addProperty("UserName", str_staffname);
        jsonObject.addProperty("UserCode", str_staffid);
        jsonObject.addProperty("EmailId", et_email.getText().toString().trim());
        jsonObject.addProperty("MobileNo", str_mobile);
        jsonObject.addProperty("Password", str_password);
        jsonObject.addProperty("ConfirmPassword", str_confirmpassword);
        jsonObject.addProperty("ParentId", 0);
        jsonObject.addProperty("ParentCode", "");
        jsonObject.addProperty("ParentUserName", "");
        jsonObject.addProperty("IsActive", true);
        jsonObject.addProperty("IsDelete", false);
        jsonObject.addProperty("CreatedBy", str_staffid);
        jsonObject.addProperty("CreatedDt", "2022-10-07T04:10:27.070Z");
        jsonObject.addProperty("ModifiedBy", "");
        jsonObject.addProperty("ModifiedDt", "2022-10-07T04:10:27.070Z");
        jsonObject.addProperty("CountryName", str_countrycode);
        regisrationAPI.createObservation(jsonObject);

    }


    @Override
    public void onResult(String result) {
        Toast.makeText(context, "" + result, Toast.LENGTH_SHORT).show();

        finish();

    }

    @Override
    public void onListResponce(List<CategoryModel> result) {
        try {

            adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, result);
            sp_country.setAdapter(adapter);
            sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    CategoryModel categoryModel = (CategoryModel) parent.getItemAtPosition(position);
                    Toast.makeText(context, "" + categoryModel.getCategoryName(), Toast.LENGTH_SHORT).show();
                    ccode = categoryModel.getCategoryId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (Exception e) {

        }
    }
}