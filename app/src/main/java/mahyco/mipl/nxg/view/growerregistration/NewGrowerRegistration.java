package mahyco.mipl.nxg.view.growerregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.adapter.CategoryLoadingAdapter;
import mahyco.mipl.nxg.model.GrowerModel;
import mahyco.mipl.nxg.model.SuccessModel;
import mahyco.mipl.nxg.util.Constants;
import mahyco.mipl.nxg.util.MultipartUtility;
import mahyco.mipl.nxg.util.Preferences;

public class NewGrowerRegistration extends AppCompatActivity implements Listener {

    JsonObject categoryJson;
    GrowerRegistrationAPI registrationAPI;
    Context context;
    LinearLayoutManager mManager;
    RecyclerView rc_list;
    CategoryLoadingAdapter adapter;
    EditText et_landmark, et_fullname, et_gender, et_dob, et_mobile, et_uniqcode, et_regdate, et_satffname;
    String str_et_landmark, str_et_fullname, str_et_gender, str_et_dob, str_et_mobile, str_et_uniqcode, str_et_regdate, str_et_satffname;
    Button grower_registration_submit_btn;
    CircleImageView iv_dp;
    ImageView imageView_front,imageView_back;
    String str_Lable="";
    TextView txt_name;
    TextView txt_registration_country;
    String dp_path,front_path,back_path;
    static int stid=0;
    GrowerModel growerModel = new GrowerModel();
    String counrtyId="0",countryName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_grower_registration);
        context = NewGrowerRegistration.this;
        str_Lable=getIntent().getExtras().getString("title");
        counrtyId=Preferences.get(context,Preferences.COUNTRYCODE);
                countryName=Preferences.get(context,Preferences.COUNTRYNAME);

        setTitle("New "+str_Lable+" Registration");
        dp_path=front_path=back_path="";
        init();
        registrationAPI = new GrowerRegistrationAPI(context, this);
        categoryJson = new JsonObject();
        categoryJson.addProperty("filterValue", countryName);
        categoryJson.addProperty("FilterOption", "GetCountry");
        registrationAPI.getCategory(categoryJson);

    }

    public void init() {
        try {
            rc_list = findViewById(R.id.rc_list);
            mManager = new LinearLayoutManager(context);
            rc_list.setLayoutManager(mManager);

            et_landmark = (EditText) findViewById(R.id.landmark_edittext);
            et_fullname = (EditText) findViewById(R.id.farmer_name_edittext);
            et_gender = (EditText) findViewById(R.id.gender_edittext);
            et_dob = (EditText) findViewById(R.id.date_of_birth_textview);
            et_mobile = (EditText) findViewById(R.id.mobile_no_edittext);
            et_uniqcode = (EditText) findViewById(R.id.unique_code_edittext);
            et_regdate = (EditText) findViewById(R.id.date_of_registration_textview);
            et_satffname = (EditText) findViewById(R.id.staff_name_and_id_textview);
            txt_name = (TextView) findViewById(R.id.txt_name);
            txt_registration_country = (TextView) findViewById(R.id.registration_country_textview);
            txt_name.setText(str_Lable+" Full Name :");
            txt_registration_country.setText(""+countryName);
            et_satffname.setText(""+Preferences.get(context,Preferences.USER_NAME));
            iv_dp=findViewById(R.id.farmer_photo);
            imageView_front=findViewById(R.id.national_id_photo_front_side_image_view);
            imageView_back=findViewById(R.id.national_id_photo_back_side_image_view);

            grower_registration_submit_btn = (Button) findViewById(R.id.grower_registration_submit_btn);
            /*

            grower_registration_submit_btn.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                            Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
                            //submit();
                }
            });

            */
        } catch (Exception e) {

        }
    }

    public void submit(View v) {
        try {

            str_et_landmark = et_landmark.getText().toString();
            str_et_fullname = et_fullname.getText().toString();
            str_et_gender = et_gender.getText().toString();
            str_et_dob = et_dob.getText().toString();
            str_et_mobile = et_mobile.getText().toString();
            str_et_uniqcode = et_uniqcode.getText().toString();
            str_et_regdate = et_regdate.getText().toString();
            str_et_satffname = et_satffname.getText().toString();


            growerModel.setLoginId(Integer.parseInt(Preferences.get(context,Preferences.LOGINID).trim()));//,
            growerModel.setCountryId(Integer.parseInt(counrtyId.trim()));//,
            growerModel.setCountryMasterId(26);//,
            growerModel.setUniqueId("");//,
            growerModel.setUserType(str_Lable);//,
            growerModel.setLandMark(str_et_landmark);//,
            growerModel.setFullName(str_et_fullname);//,
            growerModel.setDOB("2022-10-11T04:44:57.051Z");//,
            growerModel.setGender(str_et_gender);//,
            growerModel.setMobileNo(str_et_mobile);//,
            growerModel.setUniqueCode(str_et_uniqcode);//,
            growerModel.setIdProofFrontCopy("one.jpg");//,
            growerModel.setIdProofBackCopy("two.jpg");//,
            growerModel.setUploadPhoto("three.jpg");//,
            growerModel.setRegDt("2022-10-11T04:44:57.051Z");//,
            growerModel.setCreatedBy(Preferences.get(context,Preferences.USER_NAME));//


            stid=1;
            new UploadFile().execute(dp_path);




        } catch (Exception e) {
            Log.i("Error is ", e.getMessage());
        }


    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List result) {
        Toast.makeText(context, "" + result.size(), Toast.LENGTH_SHORT).show();

        adapter = new CategoryLoadingAdapter((ArrayList) result, context, this);
        rc_list.setAdapter(adapter);
    }

    @Override
    public void onSpinnerClick(int id, String categoryId) {

        try {
            SearchableSpinner spinner = (SearchableSpinner) findViewById(id);


            //  Toast.makeText(context, ""+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

         /*   if(id==1)
            {
                categoryJson=new JsonObject();
                categoryJson.addProperty("filterValue","1");
                categoryJson.addProperty("FilterOption","ParentId");
                registrationAPI.getCategoryByParent(categoryJson,spinner);
            }else
            {*/
            categoryJson = new JsonObject();
            categoryJson.addProperty("filterValue", categoryId);
            categoryJson.addProperty("FilterOption", "ParentId");
            registrationAPI.getCategoryByParent(categoryJson, spinner);
            //  }


        } catch (Exception e) {

        }

    }

    @Override
    public void loadChildSpinner(List<CategoryChildModel> result, SearchableSpinner spinner) {

        ArrayAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, result);
        spinner.setAdapter(adapter);


    }

    @Override
    public void onGrowerRegister(SuccessModel result) {
        try {
            if (result.isResultFlag()) {
                Toast.makeText(context, "" + result.getComment(), Toast.LENGTH_SHORT).show();
             //   finish();
            } else {
                Toast.makeText(context, "" + result.getComment(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show();
        }


    }

    public void front(View v)
    {
        try{
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            imageView_front.setVisibility(View.VISIBLE);
                            imageView_front.setImageBitmap(r.getBitmap());
front_path=r.getPath();
                        }
                    })
                    .setOnPickCancel(new IPickCancel() {
                        @Override
                        public void onCancelClick() {
                            //TODO: do what you have to if user clicked cancel
                        }
                    }).show(getSupportFragmentManager());
        }catch(Exception e)
        {

        }
    }
    public void back(View v)
    {
        try{
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            imageView_back.setVisibility(View.VISIBLE);
                            imageView_back.setImageBitmap(r.getBitmap());
back_path=r.getPath();
                           /* str_chk_img1 = r.getPath();
                            uploadstatus = 11;*/
                        }
                    })
                    .setOnPickCancel(new IPickCancel() {
                        @Override
                        public void onCancelClick() {
                            //TODO: do what you have to if user clicked cancel
                        }
                    }).show(getSupportFragmentManager());
        }catch(Exception e)
        {

        }
    }
    public void dp(View v)
    {
        try{
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            iv_dp.setImageBitmap(r.getBitmap());
                            dp_path=r.getPath();
                          /*  str_chk_img1 = r.getPath();
                            uploadstatus = 11;*/
                        }
                    })
                    .setOnPickCancel(new IPickCancel() {
                        @Override
                        public void onCancelClick() {
                            //TODO: do what you have to if user clicked cancel
                        }
                    }).show(getSupportFragmentManager());
        }catch(Exception e)
        {

        }
    }


    public class UploadFile extends AsyncTask {
        ProgressDialog progressDialog;
        int checkpointid = 0;

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            try {
                List<String> response = (List) o;
                Log.v("rht", "SERVER REPLIED:");
                for (String line : response) {
                    Log.v("rht", "Line : " + line);
                    try {

                        if(stid==1)
                        {
                            JSONObject job=new JSONObject(line.toString());
                            dp_path=job.getString("FileName").toString();
                            growerModel.setUploadPhoto(job.getString("FileName").toString());
                            Toast.makeText(context, "Dp Uploaded", Toast.LENGTH_SHORT).show();
                            stid=2;
                            new UploadFile().execute(front_path);

                        }else if(stid==2)
                        {
                            stid=3;
                            JSONObject job=new JSONObject(line.toString());
                            front_path=job.getString("FileName").toString();
                            growerModel.setIdProofFrontCopy(job.getString("FileName").toString());
                            new UploadFile().execute(back_path);
                            Toast.makeText(context, "front Uploaded", Toast.LENGTH_SHORT).show();

                        }else if(stid==3)
                        {
                            JSONObject job=new JSONObject(line.toString());
                            back_path=job.getString("FileName").toString();

                            growerModel.setIdProofBackCopy(job.getString("FileName").toString());

                            Log.i("Local Json Data :", growerModel.toString().trim());
                            Log.i("Local Json Data :", new Gson().toJson(growerModel));
                            JsonObject jsonObject=new JsonParser().parse(new Gson().toJson(growerModel)).getAsJsonObject();
                            registrationAPI.createGrower(jsonObject);
                            Toast.makeText(context, "back Uploaded", Toast.LENGTH_SHORT).show();

                        }


                       /* JSONArray jsonArray = new JSONArray(line);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            JsonObject jsonObjectuploadImageModels = new JsonObject();
                            jsonObjectuploadImageModels.addProperty("FileType", jsonObject.getString("FileType"));
                            jsonObjectuploadImageModels.addProperty("InspectionCheckPointId", checkpointid);
                            jsonObjectuploadImageModels.addProperty("FileName", jsonObject.getString("FileName"));
                            jsonObjectuploadImageModels.addProperty("FilePath", jsonObject.getString("FilePath"));
                            uploadImageModels.add(jsonObjectuploadImageModels);
                            Log.i("Iagemodel", uploadImageModels.toString());
                        }*/
                    } catch (Exception e) {

                    }

                }


            } catch (Exception e) {

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("File Uploading ....");
            progressDialog.show();
        }


        @Override
        protected List<String> doInBackground(Object[] objects) {


            try {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String charset = "UTF-8";
                File uploadFile1 = new File(objects[0].toString().trim());
                String requestURL = Constants.BASE_URL + "users/uploadFile";
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);

                multipart.addFormField("UniqueCode", str_et_uniqcode);
                multipart.addFormField("FarmerName", str_et_fullname);
                multipart.addFilePart("UploadFile", uploadFile1);
/*

                for(FileModel fileModel:files) {
                    multipart.addFormField("FileType", fileModel.getType());
                    multipart.addFilePart("UploadFile", fileModel.getFile());
                }

 */


                List<String> response = multipart.finish();
                return response;
            } catch (Exception e) {

            }

            return null;
        }
    }





}