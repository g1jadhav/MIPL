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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import mahyco.iqc.nxg.R;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_capa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("RCA/CAPA");
        context=AddCapaActivity.this;

        btn_choosefile=findViewById(R.id.btn_choosefile);
                btn_uplaodfile=findViewById(R.id.btn_uploadfile);
        txt_uploadstatus=findViewById(R.id.txt_uploadstatus);
                txt_filepath=findViewById(R.id.txt_selectedfilepath);


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
            new UploadFile().execute(file_path);
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
                                txt_filepath.setText(filePath);
                                Log.i("File path",filePath);

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
                Log.v("rht", "SERVER REPLIED:");
                for (String line : response) {
                    Log.v("rht", "Line : " + line);
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
                String requestURL = Constants.BASE_URL + "rCA_CAPA/create";
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);
//            multipart.addHeaderField("User-Agent", "CodeJava");
//            multipart.addHeaderField("Test-Header", "Header-Value");
                // multipart.addFormField("friend_id", "Cool Pictures");
                String UplaodType = "PDF";

                multipart.addFormField("FileType", UplaodType);
                multipart.addFormField("PlantInspectionId", inspectionid);
                multipart.addFormField("CreatedBy", userCode);
                multipart.addFilePart("UploadFile", uploadFile1);
                multipart.addFormField("Status", "");
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