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

public class ActivityUpload extends BaseActivity implements View.OnClickListener, Listener {

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
    private int mIndex = 0;
    private boolean mGrowerClicked;

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
                if (checkInternetConnection(mContext) && mGrowerList.size() > 0) {
                    mGrowerClicked = true;
                    mIndex = 0;
                    stid = 1;
                    new UploadFile().execute(mGrowerList.get(mIndex).getUploadPhoto());
                } else {
                    showNoInternetDialog(mContext, "Please check your internet connection");
                }
            }
            break;
            case R.id.organizer_registration_upload: {
                if (checkInternetConnection(mContext) && mOrganizerList.size() > 0) {
                    mGrowerClicked = false;
                    mIndex = 0;
                    stid = 1;
                    new UploadFile().execute(mOrganizerList.get(mIndex).getUploadPhoto());
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
            Log.e("temporary", "result.getStatus().equalsIgnoreCase(\"Success\")");
            new DeleteIfSyncSuccessfully().execute();
            /*} else {
                Log.e("temporary", "onGrowerRegister mGrowerUpload before " + mIndex);
                mIndex++;
                Log.e("temporary", "onGrowerRegister mGrowerUpload after " + mIndex + " mOrganizerList size " +
                        mOrganizerList.size());
                if (mIndex < mOrganizerList.size()) {
                    Log.e("temporary", "UploadFile() called");
                    stid = 1;
                    new UploadFile().execute(mOrganizerList.get(mIndex).getUploadPhoto());
                } else {
                    Log.e("temporary", "onGrowerRegister mGrowerUpload all data upload");
                }
            }*/
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
                    Log.e("temporary", "farmer photo " + list.get(i).getUploadPhoto() + "\n country id " + list.get(i).getCountryId() +
                            "\n CountryMasterId() " + list.get(i).getCountryMasterId() +
                            "\nLandMark()" + list.get(i).getLandMark() +
                            "\nLandFullName()" + list.get(i).getFullName() +
                            "\nLandGender()" + list.get(i).getGender() +
                            "\nLandDOB()()" + list.get(i).getDOB() +
                            "\nLandMobileNo()" + list.get(i).getMobileNo() +
                            "\nLandUniqueCode()" + list.get(i).getUniqueCode() +
                            "\nLandRegDt()" + list.get(i).getRegDt() +
                            "\nLandStaffNameAndI()" + list.get(i).getStaffNameAndId() +
                            "\nLandFrontCopy()" + list.get(i).getIdProofFrontCopy() +
                            "\nIsSync()" + list.get(i).getIsSync() +
                            "\nreatedBy()" + list.get(i).getCreatedBy() +
                            "\nUserType()" + list.get(i).getUserType() +
                            "\nBackCopy()" + list.get(i).getIdProofBackCopy() +
                            "\ntempid ()" + list.get(i).getTempId() +
                            "\nloginId ()" + list.get(i).getLoginId());
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
                Log.e("temporary", "SERVER REPLIED: " + response.size());
                for (String line : response) {
                    Log.e("temporary", "Line : " + line);
                    try {
                        JSONObject job = new JSONObject(line.toString());
                        new UpdateAsyncTaskList().execute(job.getString("FileName"));
                        /*if (stid == 1) {
                            JSONObject job = new JSONObject(line.toString());
                            new UpdateAsyncTaskList().execute();
                            SqlightDatabase database = null;
                            try {
                                database = new SqlightDatabase(mContext);
                                if (mGrowerClicked) {
                                    database.updateRegistrationImagePath(mGrowerList.get(mIndex).getMobileNo(), "growerPhoto", job.getString("FileName"));
                                } else {
                                    database.updateRegistrationImagePath(mOrganizerList.get(mIndex).getMobileNo(), "growerPhoto", job.getString("FileName"));
                                }
                            } finally {
                                if (database != null) {
                                    database.close();
                                }
                            }
                            Toast.makeText(mContext, "Dp Uploaded", Toast.LENGTH_SHORT).show();
                            stid = 2;
                            new UploadFile().execute(mGrowerList.get(mIndex).getIdProofFrontCopy());

                        } else if (stid == 2) {
                          //  stid = 3;
                            JSONObject job = new JSONObject(line.toString());
                            SqlightDatabase database = null;
                            try {
                                database = new SqlightDatabase(mContext);
                                if (mGrowerClicked) {
                                    database.updateRegistrationImagePath(mGrowerList.get(mIndex).getMobileNo(), "frontPhoto", job.getString("FileName"));
                                } else {
                                    database.updateRegistrationImagePath(mOrganizerList.get(mIndex).getMobileNo(), "frontPhoto", job.getString("FileName"));
                                }
                            } finally {
                                if (database != null) {
                                    database.close();
                                }
                            }
                            new UploadFile().execute(mGrowerList.get(mIndex).getIdProofBackCopy());
                            Toast.makeText(mContext, "front Uploaded", Toast.LENGTH_SHORT).show();
                        } else if (stid == 3) {
                            JSONObject job = new JSONObject(line.toString());
                            SqlightDatabase database = null;
                            try {
                                database = new SqlightDatabase(mContext);
                                if (mGrowerClicked) {
                                    database.updateRegistrationImagePath(mGrowerList.get(mIndex).getMobileNo(), "docBackPhoto", job.getString("FileName"));
                                } else {
                                    database.updateRegistrationImagePath(mOrganizerList.get(mIndex).getMobileNo(), "docBackPhoto", job.getString("FileName"));
                                }
                            } finally {
                                if (database != null) {
                                    database.close();
                                }
                            }
                            Log.e("temporary", " database farmer photo " +
                                    mGrowerList.get(mIndex).getUploadPhoto());
                            JsonObject jsonObject = new JsonObject();
                            if (mGrowerClicked) {
                                jsonObject.addProperty("CountryId", mGrowerList.get(mIndex).getCountryId());
                                jsonObject.addProperty("CountryMasterId", mGrowerList.get(mIndex).getCountryMasterId());
                                jsonObject.addProperty("CreatedBy", mGrowerList.get(mIndex).getCreatedBy());
                                jsonObject.addProperty("DOB", mGrowerList.get(mIndex).getDOB());
                                jsonObject.addProperty("FullName", mGrowerList.get(mIndex).getFullName());
                                jsonObject.addProperty("Gender", mGrowerList.get(mIndex).getGender());
                                jsonObject.addProperty("IdProofBackCopy", mGrowerList.get(mIndex).getIdProofBackCopy());
                                jsonObject.addProperty("IdProofFrontCopy", mGrowerList.get(mIndex).getIdProofFrontCopy());
                                jsonObject.addProperty("LandMark", mGrowerList.get(mIndex).getLandMark());
                                jsonObject.addProperty("LoginId", mGrowerList.get(mIndex).getLoginId());
                                jsonObject.addProperty("MobileNo", mGrowerList.get(mIndex).getMobileNo());
                                jsonObject.addProperty("RegDt", mGrowerList.get(mIndex).getRegDt());
                                jsonObject.addProperty("StaffNameAndId", mGrowerList.get(mIndex).getStaffNameAndId());
                                jsonObject.addProperty("UniqueCode", mGrowerList.get(mIndex).getUniqueCode());
                                jsonObject.addProperty("UploadPhoto", mGrowerList.get(mIndex).getUploadPhoto());
                                jsonObject.addProperty("UserType", mGrowerList.get(mIndex).getUserType());
                                jsonObject.addProperty("UniqueId", mGrowerList.get(mIndex).getUniqueId());

                                // Log.e("temporary", "mGrowerList.get(mIndex).toString().trim() :" + growerModel.toString().trim());
                                // Log.e("temporary", "Local Json Data :" + new Gson().toJson(growerModel));
                                // JsonObject jsonObject = new JsonParser().parse(new Gson().toJson(growerModel)).getAsJsonObject();
                                registrationAPI.createGrower(jsonObject);
                            } else {
                                jsonObject.addProperty("CountryId", mOrganizerList.get(mIndex).getCountryId());
                                jsonObject.addProperty("CountryMasterId", mOrganizerList.get(mIndex).getCountryMasterId());
                                jsonObject.addProperty("CreatedBy", mOrganizerList.get(mIndex).getCreatedBy());
                                jsonObject.addProperty("DOB", mOrganizerList.get(mIndex).getDOB());
                                jsonObject.addProperty("FullName", mOrganizerList.get(mIndex).getFullName());
                                jsonObject.addProperty("Gender", mOrganizerList.get(mIndex).getGender());
                                jsonObject.addProperty("IdProofBackCopy", mOrganizerList.get(mIndex).getIdProofBackCopy());
                                jsonObject.addProperty("IdProofFrontCopy", mOrganizerList.get(mIndex).getIdProofFrontCopy());
                                jsonObject.addProperty("LandMark", mOrganizerList.get(mIndex).getLandMark());
                                jsonObject.addProperty("LoginId", mOrganizerList.get(mIndex).getLoginId());
                                jsonObject.addProperty("MobileNo", mOrganizerList.get(mIndex).getMobileNo());
                                jsonObject.addProperty("RegDt", mOrganizerList.get(mIndex).getRegDt());
                                jsonObject.addProperty("StaffNameAndId", mOrganizerList.get(mIndex).getStaffNameAndId());
                                jsonObject.addProperty("UniqueCode", mOrganizerList.get(mIndex).getUniqueCode());
                                jsonObject.addProperty("UploadPhoto", mOrganizerList.get(mIndex).getUploadPhoto());
                                jsonObject.addProperty("UserType", mOrganizerList.get(mIndex).getUserType());
                                jsonObject.addProperty("UniqueId", mOrganizerList.get(mIndex).getUniqueId());
                                // Log.e("temporary", "mGrowerList.get(mIndex).toString().trim() :" + growerModel.toString().trim());
                                // Log.e("temporary", "Local Json Data :" + new Gson().toJson(growerModel));
                                //JsonObject jsonObject = new JsonParser().parse(new Gson().toJson(growerModel)).getAsJsonObject();
                                registrationAPI.createGrower(jsonObject);
                            }
                            Toast.makeText(mContext, "back Uploaded", Toast.LENGTH_SHORT).show();
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
            progressDialog = new ProgressDialog(mContext);
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

                if (mGrowerClicked) {
                    multipart.addFormField("UniqueCode", mGrowerList.get(mIndex).getUniqueCode());
                    multipart.addFormField("FarmerName", mGrowerList.get(mIndex).getFullName());
                } else {
                    multipart.addFormField("UniqueCode", mOrganizerList.get(mIndex).getUniqueCode());
                    multipart.addFormField("FarmerName", mOrganizerList.get(mIndex).getFullName());
                }
                multipart.addFilePart("UploadFile", uploadFile1);

                List<String> response = multipart.finish();
                return response;
            } catch (Exception e) {

            }
            return null;
        }
    }
private String mGrowerPath;
private String mDocBackPath;
private String mDocFrontPath;

    private class UpdateAsyncTaskList extends AsyncTask<String, String, Void> {

        private String paths[];

        @Override
        protected final Void doInBackground(String... path) {
            SqlightDatabase database = null;
            try {
                paths = path;
                database = new SqlightDatabase(mContext);
                Log.e("temporary", "UpdateAsyncTaskList stid " + stid +" paths " + paths[0]);
                if (stid == 1) {
                    mGrowerPath = paths[0];
                    if (mGrowerClicked) {
                        database.updateRegistrationImagePath(mGrowerList.get(mIndex).getMobileNo(), "growerPhoto", path[0]);
                    } else {
                        database.updateRegistrationImagePath(mOrganizerList.get(mIndex).getMobileNo(), "growerPhoto", path[0]);
                    }
                    runOnUiThread(() -> Toast.makeText(mContext, "Dp Uploaded", Toast.LENGTH_SHORT).show());
                    stid = 2;
                } else if (stid == 2) {
                    stid = 3;
                    mDocFrontPath = paths[0];
                    if (mGrowerClicked) {
                        database.updateRegistrationImagePath(mGrowerList.get(mIndex).getMobileNo(), "frontPhoto", path[0]);
                    } else {
                        database.updateRegistrationImagePath(mOrganizerList.get(mIndex).getMobileNo(), "frontPhoto", path[0]);
                    }
                    runOnUiThread(() -> Toast.makeText(mContext, "front Uploaded", Toast.LENGTH_SHORT).show());
                } else if (stid == 3) {
                    mDocBackPath = paths[0];
                    stid = 4;
                    if (mGrowerClicked) {
                        database.updateRegistrationImagePath(mGrowerList.get(mIndex).getMobileNo(), "docBackPhoto", path[0]);
                    } else {
                        database.updateRegistrationImagePath(mOrganizerList.get(mIndex).getMobileNo(), "docBackPhoto", path[0]);
                    }
                    runOnUiThread(() -> Toast.makeText(mContext, "back Uploaded", Toast.LENGTH_SHORT).show());
                } /*else if (stid == 4) {
                    JsonObject jsonObject = new JsonObject();
                    if (mGrowerClicked) {
                        jsonObject.addProperty("CountryId", mGrowerList.get(mIndex).getCountryId());
                        jsonObject.addProperty("CountryMasterId", mGrowerList.get(mIndex).getCountryMasterId());
                        jsonObject.addProperty("CreatedBy", mGrowerList.get(mIndex).getCreatedBy());
                        jsonObject.addProperty("DOB", mGrowerList.get(mIndex).getDOB());
                        jsonObject.addProperty("FullName", mGrowerList.get(mIndex).getFullName());
                        jsonObject.addProperty("Gender", mGrowerList.get(mIndex).getGender());
                        jsonObject.addProperty("IdProofBackCopy", mGrowerList.get(mIndex).getIdProofBackCopy());
                        jsonObject.addProperty("IdProofFrontCopy", mGrowerList.get(mIndex).getIdProofFrontCopy());
                        jsonObject.addProperty("LandMark", mGrowerList.get(mIndex).getLandMark());
                        jsonObject.addProperty("LoginId", mGrowerList.get(mIndex).getLoginId());
                        jsonObject.addProperty("MobileNo", mGrowerList.get(mIndex).getMobileNo());
                        jsonObject.addProperty("RegDt", mGrowerList.get(mIndex).getRegDt());
                        jsonObject.addProperty("StaffNameAndId", mGrowerList.get(mIndex).getStaffNameAndId());
                        jsonObject.addProperty("UniqueCode", mGrowerList.get(mIndex).getUniqueCode());
                        jsonObject.addProperty("UploadPhoto", mGrowerList.get(mIndex).getUploadPhoto());
                        jsonObject.addProperty("UserType", mGrowerList.get(mIndex).getUserType());
                        jsonObject.addProperty("UniqueId", mGrowerList.get(mIndex).getUniqueId());
                    } else {
                        jsonObject.addProperty("CountryId", mOrganizerList.get(mIndex).getCountryId());
                        jsonObject.addProperty("CountryMasterId", mOrganizerList.get(mIndex).getCountryMasterId());
                        jsonObject.addProperty("CreatedBy", mOrganizerList.get(mIndex).getCreatedBy());
                        jsonObject.addProperty("DOB", mOrganizerList.get(mIndex).getDOB());
                        jsonObject.addProperty("FullName", mOrganizerList.get(mIndex).getFullName());
                        jsonObject.addProperty("Gender", mOrganizerList.get(mIndex).getGender());
                        jsonObject.addProperty("IdProofBackCopy", mOrganizerList.get(mIndex).getIdProofBackCopy());
                        jsonObject.addProperty("IdProofFrontCopy", mOrganizerList.get(mIndex).getIdProofFrontCopy());
                        jsonObject.addProperty("LandMark", mOrganizerList.get(mIndex).getLandMark());
                        jsonObject.addProperty("LoginId", mOrganizerList.get(mIndex).getLoginId());
                        jsonObject.addProperty("MobileNo", mOrganizerList.get(mIndex).getMobileNo());
                        jsonObject.addProperty("RegDt", mOrganizerList.get(mIndex).getRegDt());
                        jsonObject.addProperty("StaffNameAndId", mOrganizerList.get(mIndex).getStaffNameAndId());
                        jsonObject.addProperty("UniqueCode", mOrganizerList.get(mIndex).getUniqueCode());
                        jsonObject.addProperty("UploadPhoto", mOrganizerList.get(mIndex).getUploadPhoto());
                        jsonObject.addProperty("UserType", mOrganizerList.get(mIndex).getUserType());
                        jsonObject.addProperty("UniqueId", mOrganizerList.get(mIndex).getUniqueId());
                    }
                    registrationAPI.createGrower(jsonObject);*/
                /*}*/
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Log.e("temporary", "onPostExecute mGrowerClicked " + mGrowerClicked + " mGrowerList " + mGrowerList + " mGrowerList size " + mGrowerList.size()
                    + " mGrowerPath " + mGrowerPath + " mDocBack " + mDocBackPath  + " mDocFrontPath " + mDocFrontPath);
            if (stid < 4) {
                if (mGrowerClicked && mGrowerList != null && mGrowerList.size() > 0) {
                    new UploadFile().execute(mGrowerList.get(mIndex).getIdProofFrontCopy());
                } else if (mOrganizerList != null && mOrganizerList.size() > 0) {
                    new UploadFile().execute(mOrganizerList.get(mIndex).getIdProofFrontCopy());
                }
            } else {
                JsonObject jsonObject = new JsonObject();
                if (mGrowerClicked) {
                    jsonObject.addProperty("CountryId", mGrowerList.get(mIndex).getCountryId());
                    jsonObject.addProperty("CountryMasterId", mGrowerList.get(mIndex).getCountryMasterId());
                    jsonObject.addProperty("CreatedBy", mGrowerList.get(mIndex).getCreatedBy());
                    jsonObject.addProperty("DOB", mGrowerList.get(mIndex).getDOB());
                    jsonObject.addProperty("FullName", mGrowerList.get(mIndex).getFullName());
                    jsonObject.addProperty("Gender", mGrowerList.get(mIndex).getGender());
                    jsonObject.addProperty("IdProofBackCopy", /*mGrowerList.get(mIndex).getIdProofBackCopy()*/mDocBackPath);
                    jsonObject.addProperty("IdProofFrontCopy", /*mGrowerList.get(mIndex).getIdProofFrontCopy()*/mDocFrontPath);
                    jsonObject.addProperty("LandMark", mGrowerList.get(mIndex).getLandMark());
                    jsonObject.addProperty("LoginId", mGrowerList.get(mIndex).getLoginId());
                    jsonObject.addProperty("MobileNo", mGrowerList.get(mIndex).getMobileNo());
                    jsonObject.addProperty("RegDt", mGrowerList.get(mIndex).getRegDt());
                    jsonObject.addProperty("StaffNameAndId", mGrowerList.get(mIndex).getStaffNameAndId());
                    jsonObject.addProperty("UniqueCode", mGrowerList.get(mIndex).getUniqueCode());
                    jsonObject.addProperty("UploadPhoto", /*mGrowerList.get(mIndex).getUploadPhoto()*/mGrowerPath);
                    jsonObject.addProperty("UserType", mGrowerList.get(mIndex).getUserType());
                    jsonObject.addProperty("UniqueId", mGrowerList.get(mIndex).getUniqueId());
                } else {
                    jsonObject.addProperty("CountryId", mOrganizerList.get(mIndex).getCountryId());
                    jsonObject.addProperty("CountryMasterId", mOrganizerList.get(mIndex).getCountryMasterId());
                    jsonObject.addProperty("CreatedBy", mOrganizerList.get(mIndex).getCreatedBy());
                    jsonObject.addProperty("DOB", mOrganizerList.get(mIndex).getDOB());
                    jsonObject.addProperty("FullName", mOrganizerList.get(mIndex).getFullName());
                    jsonObject.addProperty("Gender", mOrganizerList.get(mIndex).getGender());
                    jsonObject.addProperty("IdProofBackCopy",/* mOrganizerList.get(mIndex).getIdProofBackCopy()*/mDocBackPath);
                    jsonObject.addProperty("IdProofFrontCopy", /*mOrganizerList.get(mIndex).getIdProofFrontCopy()*/mDocFrontPath);
                    jsonObject.addProperty("LandMark", mOrganizerList.get(mIndex).getLandMark());
                    jsonObject.addProperty("LoginId", mOrganizerList.get(mIndex).getLoginId());
                    jsonObject.addProperty("MobileNo", mOrganizerList.get(mIndex).getMobileNo());
                    jsonObject.addProperty("RegDt", mOrganizerList.get(mIndex).getRegDt());
                    jsonObject.addProperty("StaffNameAndId", mOrganizerList.get(mIndex).getStaffNameAndId());
                    jsonObject.addProperty("UniqueCode", mOrganizerList.get(mIndex).getUniqueCode());
                    jsonObject.addProperty("UploadPhoto", /*mOrganizerList.get(mIndex).getUploadPhoto()*/mGrowerPath);
                    jsonObject.addProperty("UserType", mOrganizerList.get(mIndex).getUserType());
                    jsonObject.addProperty("UniqueId", mOrganizerList.get(mIndex).getUniqueId());
                }
                registrationAPI.createGrower(jsonObject);
            }
            super.onPostExecute(unused);
        }
    }

    private class DeleteIfSyncSuccessfully extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                if (mGrowerClicked) {
                    mGrowerList = database.deleteRegistration(mGrowerList.get(mIndex).getMobileNo());
                } else {
                    mOrganizerList = database.deleteRegistration(mOrganizerList.get(mIndex).getMobileNo());
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
                Log.e("temporary", "mGrowerList list after delete " + mOrganizerList.size());
                Log.e("temporary", "onGrowerRegister mGrowerUpload before " + mIndex);
                for (int i = 0; i < mGrowerList.size(); i++) {
                    Log.e("temporary", " i " + i + " mGrowerList.get(i).getIsSync() " + mGrowerList.get(i).getIsSync());
                    if (mGrowerList.get(i).getIsSync() == 0) {
                        mIndex++;
                        break;
                    }
                }
                Log.e("temporary", "onGrowerRegister mGrowerUpload after " + mIndex + " growerlist size " +
                        mGrowerList.size());
                if (mIndex < mGrowerList.size()) {
                    Log.e("temporary", "UploadFile() called");
                    stid = 1;
                    new UploadFile().execute(mGrowerList.get(mIndex).getUploadPhoto());
                } else {
                    Log.e("temporary", "onGrowerRegister mGrowerUpload all data upload");
                }
                mGrowerRecords.setText(getString(R.string.no_of_records_for_upload, mGrowerList.size()));
            } else {
                Log.e("temporary", "mOrganizerList list after delete " + mOrganizerList.size());
                Log.e("temporary", "onGrowerRegister mGrowerUpload before " + mIndex);
                for (int i = 0; i < mOrganizerList.size(); i++) {
                    Log.e("temporary", " i " + i + " mOrganizerList.get(i).getIsSync() " + mOrganizerList.get(i).getIsSync());
                    if (mOrganizerList.get(i).getIsSync() == 0) {
                        mIndex++;
                        break;
                    }
                }
                Log.e("temporary", "onGrowerRegister mGrowerUpload after " + mIndex + " mOrganizerList size " +
                        mOrganizerList.size());
                if (mIndex < mOrganizerList.size()) {
                    Log.e("temporary", "UploadFile() called");
                    stid = 1;
                    new UploadFile().execute(mOrganizerList.get(mIndex).getUploadPhoto());
                } else {
                    Log.e("temporary", "onGrowerRegister mGrowerUpload all data upload");
                }
                mOrganizerRecords.setText(getString(R.string.no_of_records_for_upload, mOrganizerList.size()));
            }
            super.onPostExecute(unused);
        }
    }
}
