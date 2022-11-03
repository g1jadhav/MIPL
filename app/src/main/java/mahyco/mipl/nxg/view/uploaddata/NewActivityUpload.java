package mahyco.mipl.nxg.view.uploaddata;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mahyco.mipl.nxg.BuildConfig;
import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.GrowerModel;
import mahyco.mipl.nxg.model.SuccessModel;
import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.util.Constants;
import mahyco.mipl.nxg.util.MultipartUtility;
import mahyco.mipl.nxg.util.SqlightDatabase;
import mahyco.mipl.nxg.view.growerregistration.GrowerRegistrationAPI;
import mahyco.mipl.nxg.view.growerregistration.Listener;

public class NewActivityUpload extends BaseActivity implements View.OnClickListener, Listener {

    private AppCompatButton mGrowerRegistrationBtn;
    private AppCompatButton mOrganizerRegistrationBtn;
    private AppCompatButton mDistributionUploadBtn;

    private Context mContext;
    private List<GrowerModel> mGrowerList;
    private List<GrowerModel> mOrganizerList;

    private TextView mGrowerRecords;
    private TextView mOrganizerRecords;
    private TextView mSeedDistributionRecords;

    private int stid = 0;
    private GrowerRegistrationAPI registrationAPI;
    private boolean mGrowerClicked;
    private String mResponseString = "";

    private int mGrowerListSize;
    private int mOrganizerSize;

    @Override
    protected int getLayout() {
        return R.layout.activity_upload_layout;
    }

    @Override
    protected void init() {

        setTitle("Data Upload");

        mContext = this;

        registrationAPI = new GrowerRegistrationAPI(mContext, this);
        mGrowerRegistrationBtn = findViewById(R.id.grower_registration_upload);
        mGrowerRegistrationBtn.setOnClickListener(this);

        mOrganizerRegistrationBtn = findViewById(R.id.organizer_registration_upload);
        mOrganizerRegistrationBtn.setOnClickListener(this);

        mDistributionUploadBtn = findViewById(R.id.seed_distribution_upload);
        mDistributionUploadBtn.setOnClickListener(this);

        mGrowerRecords = findViewById(R.id.grower_registration_no_of_records);
        mOrganizerRecords = findViewById(R.id.organizer_registration_no_of_records);
        mSeedDistributionRecords = findViewById(R.id.seed_distribution_no_of_records);

        mGrowerRecords.setText(getString(R.string.no_of_records_for_upload, 0));
        mOrganizerRecords.setText(getString(R.string.no_of_records_for_upload, 0));
        mSeedDistributionRecords.setText(getString(R.string.no_of_records_for_upload, 0));

        AppCompatTextView mVersionTextView = findViewById(R.id.upload_data_version_code);
        mVersionTextView.setText(getString(R.string.version_code, BuildConfig.VERSION_CODE));

        mGrowerList = new ArrayList<>();
        mOrganizerList = new ArrayList<>();

        new GetRegistrationAsyncTaskList().execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.grower_registration_upload: {
                if (checkInternetConnection(mContext)) {
                    if (mGrowerList.size() > 0) {
                        mGrowerClicked = true;
//                        Log.e("temporary", "on cllick  mGrowerList.get(0).getGrowerImageUpload() " +  mGrowerList.get(0).getGrowerImageUpload() +
//                                " mGrowerList.get(0).FrontImageUpload() " + mGrowerList.get(0).getFrontImageUpload() +
//                                " mGrowerList.get(0).BackImageUpload() " + mGrowerList.get(0).getBackImageUpload() +
//                                " mGrowerList.get(0).getUploadPhoto() " + mGrowerList.get(0).getUploadPhoto() +
//                                " mGrowerList.get(0).getIdProofFrontCopy() " + mGrowerList.get(0).getIdProofFrontCopy()+
//                                " mGrowerList.get(0).getIdProofBackCopy() " + mGrowerList.get(0).getIdProofBackCopy());
                        if (mGrowerList.get(0).getGrowerImageUpload() == 0) {
                            stid = 1;
                            new UploadFile().execute(mGrowerList.get(0).getUploadPhoto());
                        } else if (mGrowerList.get(0).getFrontImageUpload() == 0) {
                            stid = 2;
                            new UploadFile().execute(mGrowerList.get(0).getIdProofFrontCopy());
                        } else if (mGrowerList.get(0).getBackImageUpload() == 0) {
                            stid = 3;
                            new UploadFile().execute(mGrowerList.get(0).getIdProofBackCopy());
                        } else {
                            uploadDataAfterThreeImagesUpload(/*mGrowerList.get(0).getUploadPhoto(), mGrowerList.get(0).getIdProofBackCopy(), mGrowerList.get(0).getIdProofFrontCopy()*/);
                        }
                    } else {
                        showNoInternetDialog(mContext, "No data available to upload");
                    }
                } else {
                    showNoInternetDialog(mContext, "Please check your internet connection");
                }
            }
            break;
            case R.id.organizer_registration_upload: {
                if (checkInternetConnection(mContext)) {
                    if (mOrganizerList.size() > 0) {
                        mGrowerClicked = false;
//                        Log.e("temporary", "on cllick  mOrganizerList.get(0).getGrowerImageUpload() " +  mOrganizerList.get(0).getGrowerImageUpload() +
//                                " mOrganizerList.get(0).getUploadPhoto() " + mOrganizerList.get(0).getUploadPhoto() +
//                                " mOrganizerList.get(0).getIdProofFrontCopy() " + mOrganizerList.get(0).getIdProofFrontCopy()+
//                                " mOrganizerList.get(0).getIdProofBackCopy() " + mOrganizerList.get(0).getIdProofBackCopy());
                       /* stid = 1;
                        new UploadFile().execute(mOrganizerList.get(0).getUploadPhoto());*/
                        if (mOrganizerList.get(0).getGrowerImageUpload() == 0) {
                            stid = 1;
                            new UploadFile().execute(mOrganizerList.get(0).getUploadPhoto());
                        } else if (mOrganizerList.get(0).getFrontImageUpload() == 0) {
                            stid = 2;
                            new UploadFile().execute(mOrganizerList.get(0).getIdProofFrontCopy());
                        } else if (mOrganizerList.get(0).getBackImageUpload() == 0) {
                            stid = 3;
                            new UploadFile().execute(mOrganizerList.get(0).getIdProofBackCopy());
                        } else {
                            uploadDataAfterThreeImagesUpload(/*mOrganizerList.get(0).getUploadPhoto(), mOrganizerList.get(0).getIdProofBackCopy(), mOrganizerList.get(0).getIdProofFrontCopy()*/);
                        }
                    } else {
                        showNoInternetDialog(mContext, "No data available to upload");
                    }
                } else {
                    showNoInternetDialog(mContext, "Please check your internet connection");
                }
            }
            break;
            case R.id.seed_distribution_upload: {
            }
            break;
        }
    }


    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List result) {

    }

    @Override
    public void onSpinnerClick(int id, String categoryId) {

    }

    @Override
    public void loadChildSpinner(List<CategoryChildModel> result, SearchableSpinner spinner) {

    }

    @Override
    public void onGrowerRegister(SuccessModel result) {
        if (result.getStatus().equalsIgnoreCase("Success")) {
            /*if (mGrowerClicked) {*/
//            Log.e("temporary", "result.getStatus().equalsIgnoreCase(\"Success\")");
            mResponseString = result.getComment();
            new DeleteIfSyncSuccessfully().execute();
        }
    }

    private class GetRegistrationAsyncTaskList extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                List<GrowerModel> list = database.getAllRegistration();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserType().equalsIgnoreCase("Grower")) {
                        mGrowerList.add(list.get(i));
                    } else {
                        mOrganizerList.add(list.get(i));
                    }
//                    Log.e("temporary", "\n at starting" +
//                            "\nfarmer photo " + list.get(i).getUploadPhoto() + "\n country id " + list.get(i).getCountryId() +
//                            "\n CountryMasterId() " + list.get(i).getCountryMasterId() +
//                            "\nLandMark()" + list.get(i).getLandMark() +
//                            "\nLandFullName()" + list.get(i).getFullName() +
//                            "\nLandGender()" + list.get(i).getGender() +
//                            "\nLandDOB()()" + list.get(i).getDOB() +
//                            "\nLandMobileNo()" + list.get(i).getMobileNo() +
//                            "\nLandUniqueCode()" + list.get(i).getUniqueCode() +
//                            "\nLandRegDt()" + list.get(i).getRegDt() +
//                            "\nLandStaffNameAndI()" + list.get(i).getStaffNameAndId() +
//                            "\nLandFrontCopy()" + list.get(i).getIdProofFrontCopy() +
//                            "\nIsSync()" + list.get(i).getIsSync() +
//                            "\nreatedBy()" + list.get(i).getCreatedBy() +
//                            "\nUserType()" + list.get(i).getUserType() +
//                            "\nBackCopy()" + list.get(i).getIdProofBackCopy() +
//                            "\ntempid ()" + list.get(i).getTempId() +
//                            "\nloginId ()" + list.get(i).getLoginId() +
//                            "\ngetGrowerImageUpload ()" + list.get(i).getGrowerImageUpload() +
//                            "\ngetFrontImageUpload ()" + list.get(i).getFrontImageUpload() +
//                            "\ngetBackImageUpload ()" + list.get(i).getBackImageUpload());
                }
                mOrganizerSize = mOrganizerList.size();
                mGrowerListSize = mGrowerList.size();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mGrowerRecords.setText(getString(R.string.no_of_records_for_upload, mGrowerList.size()));
            mOrganizerRecords.setText(getString(R.string.no_of_records_for_upload, mOrganizerList.size()));
            super.onPostExecute(unused);
        }
    }

    public class UploadFile extends AsyncTask {
        ProgressDialog progressDialog;

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            try {
                List<String> response = (List) o;
//                Log.e("temporary", "SERVER REPLIED: " + response.size());
                for (String line : response) {
//                    Log.e("temporary", "Line : " + line);
                    try {
                        JSONObject job = new JSONObject(line.toString());
                        new UpdateAsyncTaskList().execute(job.getString("FileName"));
                    } catch (Exception e) {

                    }
                }
            } catch (Exception e) {
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("File Uploading ....");
            progressDialog.setCancelable(false);
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

                if (mGrowerClicked) {
                    multipart.addFormField("UniqueCode", mGrowerList.get(0).getUniqueCode());
                    multipart.addFormField("FarmerName", mGrowerList.get(0).getFullName());
                } else {
                    multipart.addFormField("UniqueCode", mOrganizerList.get(0).getUniqueCode());
                    multipart.addFormField("FarmerName", mOrganizerList.get(0).getFullName());
                }
                multipart.addFilePart("UploadFile", uploadFile1);

                List<String> response = multipart.finish();
                return response;
            } catch (Exception e) {

            }
            return null;
        }
    }

//    private String mGrowerPath;
//    private String mDocBackPath;
//    private String mDocFrontPath;

    private class UpdateAsyncTaskList extends AsyncTask<String, String, Void> {

        private String paths[];

        @Override
        protected final Void doInBackground(String... path) {
            SqlightDatabase database = null;
            try {
                paths = path;
                database = new SqlightDatabase(mContext);
//                Log.e("temporary", "UpdateAsyncTaskList stid " + stid + " paths " + paths[0]);
                if (stid == 1) {
                    // mGrowerPath = paths[0];
                    if (mGrowerClicked) {
                        database.updateRegistrationImagePath(mGrowerList.get(0).getUniqueCode(), "growerPhoto", path[0], 1);
                    } else {
                        database.updateRegistrationImagePath(mOrganizerList.get(0).getUniqueCode(), "growerPhoto", path[0], 1);
                    }
                    runOnUiThread(() -> Toast.makeText(mContext, "Dp Uploaded", Toast.LENGTH_SHORT).show());
                    stid = 2;
                } else if (stid == 2) {
                    stid = 3;
                    //  mDocFrontPath = paths[0];
                    if (mGrowerClicked) {
                        database.updateRegistrationImagePath(mGrowerList.get(0).getUniqueCode(), "frontPhoto", path[0], 1);
                    } else {
                        database.updateRegistrationImagePath(mOrganizerList.get(0).getUniqueCode(), "frontPhoto", path[0], 1);
                    }
                    runOnUiThread(() -> Toast.makeText(mContext, "front Uploaded", Toast.LENGTH_SHORT).show());
                } else if (stid == 3) {
                    //  mDocBackPath = paths[0];
                    stid = 4;
                    if (mGrowerClicked) {
                        database.updateRegistrationImagePath(mGrowerList.get(0).getUniqueCode(), "docBackPhoto", path[0], 1);
                    } else {
                        database.updateRegistrationImagePath(mOrganizerList.get(0).getUniqueCode(), "docBackPhoto", path[0], 1);
                    }
                    runOnUiThread(() -> Toast.makeText(mContext, "back Uploaded", Toast.LENGTH_SHORT).show());
                }
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            /*Log.e("temporary", "onPostExecute mGrowerClicked " + mGrowerClicked + " mGrowerList " + mGrowerList + " mGrowerList size " + mGrowerList.size()
                    + " mGrowerPath " + mGrowerPath + " mDocBack " + mDocBackPath + " mDocFrontPath " + mDocFrontPath);
            if (stid < 4) {
                if (mGrowerClicked && mGrowerList != null && mGrowerList.size() > 0) {
                    new UploadFile().execute(mGrowerList.get(0).getIdProofFrontCopy());
                } else if (mOrganizerList != null && mOrganizerList.size() > 0) {
                    new UploadFile().execute(mOrganizerList.get(0).getIdProofFrontCopy());
                }
            } else {
                JsonObject jsonObject = new JsonObject();
                if (mGrowerClicked) {
                    jsonObject.addProperty("CountryId", mGrowerList.get(0).getCountryId());
                    jsonObject.addProperty("CountryMasterId", mGrowerList.get(0).getCountryMasterId());
                    jsonObject.addProperty("CreatedBy", mGrowerList.get(0).getCreatedBy());
                    jsonObject.addProperty("DOB", mGrowerList.get(0).getDOB());
                    jsonObject.addProperty("FullName", mGrowerList.get(0).getFullName());
                    jsonObject.addProperty("Gender", mGrowerList.get(0).getGender());
                    jsonObject.addProperty("IdProofBackCopy", *//*mGrowerList.get(mIndex).getIdProofBackCopy()*//*mDocBackPath);
                    jsonObject.addProperty("IdProofFrontCopy", *//*mGrowerList.get(mIndex).getIdProofFrontCopy()*//*mDocFrontPath);
                    jsonObject.addProperty("LandMark", mGrowerList.get(0).getLandMark());
                    jsonObject.addProperty("LoginId", mGrowerList.get(0).getLoginId());
                    jsonObject.addProperty("MobileNo", mGrowerList.get(0).getMobileNo());
                    jsonObject.addProperty("RegDt", mGrowerList.get(0).getRegDt());
                    jsonObject.addProperty("StaffNameAndId", mGrowerList.get(0).getStaffNameAndId());
                    jsonObject.addProperty("UniqueCode", mGrowerList.get(0).getUniqueCode());
                    jsonObject.addProperty("UploadPhoto", *//*mGrowerList.get(mIndex).getUploadPhoto()*//*mGrowerPath);
                    jsonObject.addProperty("UserType", mGrowerList.get(0).getUserType());
                    jsonObject.addProperty("UniqueId", mGrowerList.get(0).getUniqueId());
                } else {
                    jsonObject.addProperty("CountryId", mOrganizerList.get(0).getCountryId());
                    jsonObject.addProperty("CountryMasterId", mOrganizerList.get(0).getCountryMasterId());
                    jsonObject.addProperty("CreatedBy", mOrganizerList.get(0).getCreatedBy());
                    jsonObject.addProperty("DOB", mOrganizerList.get(0).getDOB());
                    jsonObject.addProperty("FullName", mOrganizerList.get(0).getFullName());
                    jsonObject.addProperty("Gender", mOrganizerList.get(0).getGender());
                    jsonObject.addProperty("IdProofBackCopy",*//* mOrganizerList.get(mIndex).getIdProofBackCopy()*//*mDocBackPath);
                    jsonObject.addProperty("IdProofFrontCopy", *//*mOrganizerList.get(mIndex).getIdProofFrontCopy()*//*mDocFrontPath);
                    jsonObject.addProperty("LandMark", mOrganizerList.get(0).getLandMark());
                    jsonObject.addProperty("LoginId", mOrganizerList.get(0).getLoginId());
                    jsonObject.addProperty("MobileNo", mOrganizerList.get(0).getMobileNo());
                    jsonObject.addProperty("RegDt", mOrganizerList.get(0).getRegDt());
                    jsonObject.addProperty("StaffNameAndId", mOrganizerList.get(0).getStaffNameAndId());
                    jsonObject.addProperty("UniqueCode", mOrganizerList.get(0).getUniqueCode());
                    jsonObject.addProperty("UploadPhoto", *//*mOrganizerList.get(mIndex).getUploadPhoto()*//*mGrowerPath);
                    jsonObject.addProperty("UserType", mOrganizerList.get(0).getUserType());
                    jsonObject.addProperty("UniqueId", mOrganizerList.get(0).getUniqueId());
                }
                registrationAPI.createGrower(jsonObject);
            }*/
            new GetUpdatedListAfterUpdateAsyncTaskList().execute();
            super.onPostExecute(unused);
        }
    }

    private class DeleteIfSyncSuccessfully extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected final Boolean doInBackground(Void... voids) {
            SqlightDatabase database = null;
            boolean b;
            try {
                database = new SqlightDatabase(mContext);
                if (mGrowerClicked) {
                    b = database.deleteRegistration(mGrowerList.get(0).getUniqueCode());
                } else {
                    b = database.deleteRegistration(mOrganizerList.get(0).getUniqueCode());
                }
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return b;
        }

        @Override
        protected void onPostExecute(Boolean deleted) {
//            Log.e("temporary", " onPostExecute deleted " + deleted);
            new GetUpdatedListAfterDeleteAsyncTaskList().execute();
            super.onPostExecute(deleted);
        }
    }

    private class GetUpdatedListAfterDeleteAsyncTaskList extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                List<GrowerModel> list = database.getAllRegistration();
                if (mGrowerList != null && mGrowerList.size() > 0) {
                    mGrowerList.clear();
                }
                if (mOrganizerList != null && mOrganizerList.size() > 0) {
                    mOrganizerList.clear();
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserType().equalsIgnoreCase("Grower")) {
                        mGrowerList.add(list.get(i));
                    } else {
                        mOrganizerList.add(list.get(i));
                    }
//                    Log.e("temporary", "\nGetUpdatedListAfterDeleteAsyncTaskList" +
//                            "\nnew refresh list\n i "+ i +"\nfarmer photo " + list.get(i).getUploadPhoto() + "\n country id " + list.get(i).getCountryId() +
//                            "\n i "+ i+"\n CountryMasterId() " + list.get(i).getCountryMasterId() +
//                            "\ni "+ i+"\nLandMark()" + list.get(i).getLandMark() +
//                            "\ni "+ i+"\nLandFullName()" + list.get(i).getFullName() +
//                            "\ni "+ i+"\nLandGender()" + list.get(i).getGender() +
//                            "\ni "+ i+"\nLandDOB()()" + list.get(i).getDOB() +
//                            "\ni "+ i+"\nLandMobileNo()" + list.get(i).getMobileNo() +
//                            "\ni "+ i+"\nLandUniqueCode()" + list.get(i).getUniqueCode() +
//                            "\ni "+ i+"\nLandRegDt()" + list.get(i).getRegDt() +
//                            "\ni "+ i+"\nLandStaffNameAndI()" + list.get(i).getStaffNameAndId() +
//                            "\ni "+ i+"\nLandFrontCopy()" + list.get(i).getIdProofFrontCopy() +
//                            "\ni "+ i+"\nIsSync()" + list.get(i).getIsSync() +
//                            "\ni "+ i+"\nreatedBy()" + list.get(i).getCreatedBy() +
//                            "\ni "+ i+"\nUserType()" + list.get(i).getUserType() +
//                            "\ni "+ i+"\nBackCopy()" + list.get(i).getIdProofBackCopy() +
//                            "\ni "+ i+"\ntempid ()" + list.get(i).getTempId() +
//                            "\ni "+ i+"\nloginId ()" + list.get(i).getLoginId() +
//                            "\ni "+ i+"\ngetGrowerImageUpload ()" + list.get(i).getGrowerImageUpload() +
//                            "\ni "+ i+"\ngetFrontImageUpload ()" + list.get(i).getFrontImageUpload() +
//                            "\ni "+ i+"\ngetBackImageUpload ()" + list.get(i).getBackImageUpload());
                }
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            if (mGrowerClicked) {
//                Log.e("temporary", "onGrowerRegister mGrowerUpload after growerlist size " +
//                        mGrowerList.size());
                if (mGrowerList.size() > 0) {
//                    Log.e("temporary", "UploadFile() called");
                    stid = 1;
                    new UploadFile().execute(mGrowerList.get(0).getUploadPhoto());
                } else {
                    if (mResponseString.contains("Error")) {
                        showNoInternetDialog(mContext, mResponseString);
                    } else {
                        showNoInternetDialog(mContext, "New Grower Registration "+ mGrowerListSize +" Record/s Uploaded Successfully");
                    }
//                    Log.e("temporary", "onGrowerRegister mGrowerUpload all data upload");
                }
                mGrowerRecords.setText(getString(R.string.no_of_records_for_upload, mGrowerList.size()));
            } else {
//                Log.e("temporary", "onOrganizerRegister mOrganizerUpload after " + 0 + " mOrganizerList size " +
//                        mOrganizerList.size());
                if (mOrganizerList.size() > 0) {
//                    Log.e("temporary", "UploadFile() called");
                    stid = 1;
                    new UploadFile().execute(mOrganizerList.get(0).getUploadPhoto());
                } else {
                    if (mResponseString.contains("Error")) {
                        showNoInternetDialog(mContext, mResponseString);
                    } else {
                        showNoInternetDialog(mContext, "New Organizer Registration "+ mOrganizerSize +"  Record/s Uploaded Successfully");
                    }//                    Log.e("temporary", "onGrowerRegister mGrowerUpload all data upload");
                }
                mOrganizerRecords.setText(getString(R.string.no_of_records_for_upload, mOrganizerList.size()));
            }
            super.onPostExecute(unused);
        }
    }

    private class GetUpdatedListAfterUpdateAsyncTaskList extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                List<GrowerModel> list = database.getAllRegistration();
                if (mGrowerList != null && mGrowerList.size() > 0) {
                    mGrowerList.clear();
                }
                if (mOrganizerList != null && mOrganizerList.size() > 0) {
                    mOrganizerList.clear();
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserType().equalsIgnoreCase("Grower")) {
                        mGrowerList.add(list.get(i));
                    } else {
                        mOrganizerList.add(list.get(i));
                    }
                    /*Log.e("temporary", "\nGetUpdatedListAfterUpdateAsyncTaskList new refresh list" +
                            " \n i "+ i +"\nfarmer photo " + list.get(i).getUploadPhoto() + "\n country id " + list.get(i).getCountryId() +
                            "\n i "+ i+"\n CountryMasterId() " + list.get(i).getCountryMasterId() +
                            "\ni "+ i+"\nLandMark()" + list.get(i).getLandMark() +
                            "\ni "+ i+"\nLandFullName()" + list.get(i).getFullName() +
                            "\ni "+ i+"\nLandGender()" + list.get(i).getGender() +
                            "\ni "+ i+"\nLandDOB()()" + list.get(i).getDOB() +
                            "\ni "+ i+"\nLandMobileNo()" + list.get(i).getMobileNo() +
                            "\ni "+ i+"\nLandUniqueCode()" + list.get(i).getUniqueCode() +
                            "\ni "+ i+"\nLandRegDt()" + list.get(i).getRegDt() +
                            "\ni "+ i+"\nLandStaffNameAndI()" + list.get(i).getStaffNameAndId() +
                            "\ni "+ i+"\nLandFrontCopy()" + list.get(i).getIdProofFrontCopy() +
                            "\ni "+ i+"\nIsSync()" + list.get(i).getIsSync() +
                            "\ni "+ i+"\nreatedBy()" + list.get(i).getCreatedBy() +
                            "\ni "+ i+"\nUserType()" + list.get(i).getUserType() +
                            "\ni "+ i+"\nBackCopy()" + list.get(i).getIdProofBackCopy() +
                            "\ni "+ i+"\ntempid ()" + list.get(i).getTempId() +
                            "\ni "+ i+"\nloginId ()" + list.get(i).getLoginId() +
                            "\ni "+ i+"\ngetGrowerImageUpload ()" + list.get(i).getGrowerImageUpload() +
                            "\ni "+ i+"\ngetFrontImageUpload ()" + list.get(i).getFrontImageUpload() +
                            "\ni "+ i+"\ngetBackImageUpload ()" + list.get(i).getBackImageUpload());*/
                }
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            if (stid < 4) {
                if (mGrowerClicked && mGrowerList != null && mGrowerList.size() > 0) {
                 /*   Log.e("temporary", "GetUpdatedListAfterUpdateAsyncTaskList onPostExecute mGrowerClicked " + mGrowerClicked + " mGrowerList " + mGrowerList + " mGrowerList size " + mGrowerList.size()
                            + "stid " + stid+" mGrowerPath " + mGrowerList.get(0).getUploadPhoto() + " mDocBack " + mGrowerList.get(0).getIdProofBackCopy() + " mDocFrontPath " + mGrowerList.get(0).getIdProofFrontCopy());*/

                    if (stid == 2) {
                        new UploadFile().execute(mGrowerList.get(0).getIdProofFrontCopy());
                    } else {
                        new UploadFile().execute(mGrowerList.get(0).getIdProofBackCopy());
                    }
                } else if (mOrganizerList != null && mOrganizerList.size() > 0) {
//                    Log.e("temporary", "GetUpdatedListAfterUpdateAsyncTaskList onPostExecute mGrowerClicked " + mGrowerClicked + " mGrowerList " + mOrganizerList + " mOrganizerList size " + mOrganizerList.size()
//                            + "stid " + stid+" mGrowerPath " + mOrganizerList.get(0).getUploadPhoto() + " mDocBack " + mOrganizerList.get(0).getIdProofBackCopy() + " mDocFrontPath " + mOrganizerList.get(0).getIdProofFrontCopy());

                    if (stid == 2) {
                        new UploadFile().execute(mOrganizerList.get(0).getIdProofFrontCopy());
                    } else {
                        new UploadFile().execute(mOrganizerList.get(0).getIdProofBackCopy());
                    }
                }
            } else {
                /*JsonObject jsonObject = new JsonObject();
                if (mGrowerClicked) {
                    jsonObject.addProperty("CountryId", mGrowerList.get(0).getCountryId());
                    jsonObject.addProperty("CountryMasterId", mGrowerList.get(0).getCountryMasterId());
                    jsonObject.addProperty("CreatedBy", mGrowerList.get(0).getCreatedBy());
                    jsonObject.addProperty("DOB", mGrowerList.get(0).getDOB());
                    jsonObject.addProperty("FullName", mGrowerList.get(0).getFullName());
                    jsonObject.addProperty("Gender", mGrowerList.get(0).getGender());
                    jsonObject.addProperty("IdProofBackCopy", *//*mGrowerList.get(mIndex).getIdProofBackCopy()*//*mDocBackPath);
                    jsonObject.addProperty("IdProofFrontCopy", *//*mGrowerList.get(mIndex).getIdProofFrontCopy()*//*mDocFrontPath);
                    jsonObject.addProperty("LandMark", mGrowerList.get(0).getLandMark());
                    jsonObject.addProperty("LoginId", mGrowerList.get(0).getLoginId());
                    jsonObject.addProperty("MobileNo", mGrowerList.get(0).getMobileNo());
                    jsonObject.addProperty("RegDt", mGrowerList.get(0).getRegDt());
                    jsonObject.addProperty("StaffNameAndId", mGrowerList.get(0).getStaffNameAndId());
                    jsonObject.addProperty("UniqueCode", mGrowerList.get(0).getUniqueCode());
                    jsonObject.addProperty("UploadPhoto", *//*mGrowerList.get(mIndex).getUploadPhoto()*//*mGrowerPath);
                    jsonObject.addProperty("UserType", mGrowerList.get(0).getUserType());
                    jsonObject.addProperty("UniqueId", mGrowerList.get(0).getUniqueId());
                } else {
                    jsonObject.addProperty("CountryId", mOrganizerList.get(0).getCountryId());
                    jsonObject.addProperty("CountryMasterId", mOrganizerList.get(0).getCountryMasterId());
                    jsonObject.addProperty("CreatedBy", mOrganizerList.get(0).getCreatedBy());
                    jsonObject.addProperty("DOB", mOrganizerList.get(0).getDOB());
                    jsonObject.addProperty("FullName", mOrganizerList.get(0).getFullName());
                    jsonObject.addProperty("Gender", mOrganizerList.get(0).getGender());
                    jsonObject.addProperty("IdProofBackCopy",*//* mOrganizerList.get(mIndex).getIdProofBackCopy()*//*mDocBackPath);
                    jsonObject.addProperty("IdProofFrontCopy", *//*mOrganizerList.get(mIndex).getIdProofFrontCopy()*//*mDocFrontPath);
                    jsonObject.addProperty("LandMark", mOrganizerList.get(0).getLandMark());
                    jsonObject.addProperty("LoginId", mOrganizerList.get(0).getLoginId());
                    jsonObject.addProperty("MobileNo", mOrganizerList.get(0).getMobileNo());
                    jsonObject.addProperty("RegDt", mOrganizerList.get(0).getRegDt());
                    jsonObject.addProperty("StaffNameAndId", mOrganizerList.get(0).getStaffNameAndId());
                    jsonObject.addProperty("UniqueCode", mOrganizerList.get(0).getUniqueCode());
                    jsonObject.addProperty("UploadPhoto", *//*mOrganizerList.get(mIndex).getUploadPhoto()*//*mGrowerPath);
                    jsonObject.addProperty("UserType", mOrganizerList.get(0).getUserType());
                    jsonObject.addProperty("UniqueId", mOrganizerList.get(0).getUniqueId());
                }
                registrationAPI.createGrower(jsonObject);*/
                uploadDataAfterThreeImagesUpload(/*mGrowerPath, mDocBackPath, mDocFrontPath*/);
            }
            super.onPostExecute(unused);
        }
    }

    private void uploadDataAfterThreeImagesUpload(/*String dpPhotoPath, String docBackPath, String docFrontPath*/) {
        JsonObject jsonObject = new JsonObject();
        if (mGrowerClicked) {
            jsonObject.addProperty("CountryId", mGrowerList.get(0).getCountryId());
            jsonObject.addProperty("CountryMasterId", mGrowerList.get(0).getCountryMasterId());
            jsonObject.addProperty("CreatedBy", mGrowerList.get(0).getCreatedBy());
            jsonObject.addProperty("DOB", mGrowerList.get(0).getDOB());
            jsonObject.addProperty("FullName", mGrowerList.get(0).getFullName());
            jsonObject.addProperty("Gender", mGrowerList.get(0).getGender());
            jsonObject.addProperty("IdProofBackCopy", mGrowerList.get(0).getIdProofBackCopy());
            jsonObject.addProperty("IdProofFrontCopy", mGrowerList.get(0).getIdProofFrontCopy());
            jsonObject.addProperty("LandMark", mGrowerList.get(0).getLandMark());
            jsonObject.addProperty("LoginId", mGrowerList.get(0).getLoginId());
            jsonObject.addProperty("MobileNo", mGrowerList.get(0).getMobileNo());
            jsonObject.addProperty("RegDt", mGrowerList.get(0).getRegDt());
            jsonObject.addProperty("StaffNameAndId", mGrowerList.get(0).getStaffNameAndId());
            jsonObject.addProperty("UniqueCode", mGrowerList.get(0).getUniqueCode());
            jsonObject.addProperty("UploadPhoto", mGrowerList.get(0).getUploadPhoto());
            jsonObject.addProperty("UserType", mGrowerList.get(0).getUserType());
            jsonObject.addProperty("UniqueId", mGrowerList.get(0).getUniqueId());
        } else {
            jsonObject.addProperty("CountryId", mOrganizerList.get(0).getCountryId());
            jsonObject.addProperty("CountryMasterId", mOrganizerList.get(0).getCountryMasterId());
            jsonObject.addProperty("CreatedBy", mOrganizerList.get(0).getCreatedBy());
            jsonObject.addProperty("DOB", mOrganizerList.get(0).getDOB());
            jsonObject.addProperty("FullName", mOrganizerList.get(0).getFullName());
            jsonObject.addProperty("Gender", mOrganizerList.get(0).getGender());
            jsonObject.addProperty("IdProofBackCopy", mOrganizerList.get(0).getIdProofBackCopy());
            jsonObject.addProperty("IdProofFrontCopy", mOrganizerList.get(0).getIdProofFrontCopy());
            jsonObject.addProperty("LandMark", mOrganizerList.get(0).getLandMark());
            jsonObject.addProperty("LoginId", mOrganizerList.get(0).getLoginId());
            jsonObject.addProperty("MobileNo", mOrganizerList.get(0).getMobileNo());
            jsonObject.addProperty("RegDt", mOrganizerList.get(0).getRegDt());
            jsonObject.addProperty("StaffNameAndId", mOrganizerList.get(0).getStaffNameAndId());
            jsonObject.addProperty("UniqueCode", mOrganizerList.get(0).getUniqueCode());
            jsonObject.addProperty("UploadPhoto", mOrganizerList.get(0).getUploadPhoto());
            jsonObject.addProperty("UserType", mOrganizerList.get(0).getUserType());
            jsonObject.addProperty("UniqueId", mOrganizerList.get(0).getUniqueId());
        }
        registrationAPI.createGrower(jsonObject);
    }
}
