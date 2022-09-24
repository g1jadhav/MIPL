package mahyco.iqc.nxg.view.rca_capa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mahyco.iqc.nxg.R;
import mahyco.iqc.nxg.model.ActionTypeModel;
import mahyco.iqc.nxg.model.CheckPoint;
import mahyco.iqc.nxg.model.FileModel;
import mahyco.iqc.nxg.util.Constants;
import mahyco.iqc.nxg.util.FileHelper;
import mahyco.iqc.nxg.util.MultipartUtility;
import mahyco.iqc.nxg.util.Preferences;
import mahyco.iqc.nxg.view.investigation.CreateInvestigation;

public class AddCapaActivity extends AppCompatActivity implements View.OnClickListener, IPickResult {
 Button btn_choosefile,btn_uplaodfile;
 TextView txt_uploadstatus,txt_filepath;
 Intent intent;
     Context context;
     String file_path="";
String inspectionid="0";
String userCode;
String str_remark="",str_actiontype_value="";
int str_actiontype_id=0;
ArrayList<ActionTypeModel> action_type;
EditText et_remark;
SearchableSpinner spinner_action_type;
ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_capa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("RCA/CAPA");
        context=AddCapaActivity.this;
        action_type=new ArrayList<>();
        ActionTypeModel actionTypeModel0=new ActionTypeModel();
        actionTypeModel0.setId(0);
        actionTypeModel0.setValue("Select");

        ActionTypeModel actionTypeModel1=new ActionTypeModel();
        actionTypeModel1.setId(1);
        actionTypeModel1.setValue("RCA");

        ActionTypeModel actionTypeModel2=new ActionTypeModel();
        actionTypeModel2.setId(2);
        actionTypeModel2.setValue("CAPA");
 action_type.add(actionTypeModel0);
 action_type.add(actionTypeModel1);
 action_type.add(actionTypeModel2);

        btn_choosefile=findViewById(R.id.btn_choosefile);
                btn_uplaodfile=findViewById(R.id.btn_uploadfile);
        txt_uploadstatus=findViewById(R.id.txt_uploadstatus);
                txt_filepath=findViewById(R.id.txt_selectedfilepath);
                et_remark=findViewById(R.id.et_remark);
                spinner_action_type=findViewById(R.id.spinner_actiontype);
                adapter=  new ArrayAdapter(context, android.R.layout.simple_list_item_1, action_type);
spinner_action_type.setTitle("Select Action Type");
spinner_action_type.setAdapter(adapter);
spinner_action_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ActionTypeModel actionTypeModel = (ActionTypeModel) adapterView.getSelectedItem();
        Toast.makeText(context, actionTypeModel.getId()+"-"+actionTypeModel.getValue(), Toast.LENGTH_SHORT).show();
    str_actiontype_id=actionTypeModel.getId();
    str_actiontype_value=""+actionTypeModel.getValue();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});


        btn_choosefile.setOnClickListener(this);
         btn_uplaodfile.setOnClickListener(this);

        inspectionid= Preferences.get(context,Preferences.PROCESSINSPECTIONID).toString().trim();
        userCode= Preferences.get(context,Preferences.USER_ID).toString().trim();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // app icon in action bar clicked; goto parent activity.
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn_choosefile:
                  chooseFile();
                break;
            case R.id.btn_uploadfile:
                  uploadFile();
                break;
        }
    }

    void uploadFile() {

        try {


            str_remark=et_remark.getText().toString().trim();

            if(str_actiontype_id==0)
            {
                Toast.makeText(context, "Choose Action type.", Toast.LENGTH_SHORT).show();
            }else if(str_remark.equals(""))
            {
                et_remark.setError("Please enter something...");
            }
            else if(file_path.trim().equals(""))
            {
                Toast.makeText(context, "Choose File..", Toast.LENGTH_SHORT).show();
            }
            else {
                new UploadFile().execute(file_path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void chooseFile() {
        try{
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("application/pdf");
            intent=Intent.createChooser(intent,"Choose Files");
            intentActivityResultLauncher.launch(intent);
        }catch(Exception e)
        {

        }
    }
    ActivityResultLauncher<Intent> intentActivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Toast.makeText(context, ""+(result.getResultCode()==RESULT_OK), Toast.LENGTH_SHORT).show();
                    if(result.getResultCode()==RESULT_OK)
                    {
                        try {
                            //      Toast.makeText(context, ""+(result.getData()), Toast.LENGTH_SHORT).show();
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            //    Toast.makeText(context, ""+ FileHelper.getRealPathFromURI(context,uri), Toast.LENGTH_SHORT).show();
                            if (!Uri.EMPTY.equals(uri)) {

                                String filePath= FileHelper.getRealPathFromURI(context,uri);
                                file_path = filePath;
                                File file=new File(filePath);

                                txt_filepath.setText(filePath);
                                Log.i("File path",filePath+" Size"+file.exists());

                            }

//                            Toast.makeText(context, "FilePath : " + filePath, Toast.LENGTH_SHORT).show();
                        }catch(Exception e)
                        {
                            Toast.makeText(context, "ERROR: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
    );
    @Override
    public void onPickResult(PickResult r) {

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
                Log.v("rht", "SERVER REPLIED:"+response.size());
                for (String line : response) {
                    Log.i("rht", "Line : " + line);
                  /*  try {
                        JSONArray jsonArray = new JSONArray(line);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            JsonObject jsonObjectuploadImageModels = new JsonObject();
                            jsonObjectuploadImageModels.addProperty("FileType", jsonObject.getString("FileType"));
                            jsonObjectuploadImageModels.addProperty("InspectionCheckPointId", checkpointid);
                            jsonObjectuploadImageModels.addProperty("FileName", jsonObject.getString("FileName"));
                            jsonObjectuploadImageModels.addProperty("FilePath", jsonObject.getString("FilePath"));
                            uploadImageModels.add(jsonObjectuploadImageModels);
                            Log.i("Iagemodel", uploadImageModels.toString());
                        }
                    } catch (Exception e) {

                    }*/

                }

                    txt_uploadstatus.setText("File Uploaded Successfully..");

            } catch (Exception e) {
Log.i("Post Execute",e.getMessage());
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
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
                String requestURL = "http://10.80.50.153/IQCTest/api/rCA_CAPA/create";
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);
//            multipart.addHeaderField("User-Agent", "CodeJava");
//            multipart.addHeaderField("Test-Header", "Header-Value");
                // multipart.addFormField("friend_id", "Cool Pictures");
                String UplaodType = "PDF";
Log.i("Added",uploadFile1.exists()+" "+UplaodType+" "+inspectionid+" "+userCode+" "+str_remark+" "+str_actiontype_value );
                multipart.addFormField("FileType", UplaodType);
                multipart.addFormField("PlantInspectionId", inspectionid);
                multipart.addFormField("CreatedBy", userCode);
                multipart.addFormField("Remark", str_remark);
                multipart.addFormField("CategoryType", str_actiontype_value);
                multipart.addFilePart("UploadFile", uploadFile1);
                multipart.addFormField("Status", "True");





                List<String> response = multipart.finish();
               // Log.i("Response",response);
                return response;
            } catch (Exception e) {
                Log.i("Error",e.getMessage());
            }

            return null;
        }
    }

}