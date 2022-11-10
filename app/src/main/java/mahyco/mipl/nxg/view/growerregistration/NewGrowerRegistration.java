package mahyco.mipl.nxg.view.growerregistration;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.ScanMode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import mahyco.mipl.nxg.BuildConfig;
import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.adapter.CategoryLoadingAdapter;
import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.model.GrowerModel;
import mahyco.mipl.nxg.model.SuccessModel;
import mahyco.mipl.nxg.spinner.CCFSerachSpinner;
import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.util.Constants;
import mahyco.mipl.nxg.util.MultipartUtility;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.util.SqlightDatabase;

public class NewGrowerRegistration extends BaseActivity implements Listener, View.OnClickListener {

    JsonObject categoryJson;
    GrowerRegistrationAPI registrationAPI;
    Context mContext;
    LinearLayoutManager mManager;
    RecyclerView rc_list;
    CategoryLoadingAdapter adapter;
    EditText et_landmark, et_fullname, /*et_gender,*/ /*et_dob,*/
            et_mobile, et_uniqcode /*et_regdate,*/
            /*,et_satffname*/;
    String str_et_landmark, str_et_fullname, str_et_gender, str_et_dob, str_et_mobile, str_et_uniqcode, str_et_regdate, str_et_satffname;
    Button grower_registration_submit_btn, scan_qr_code_btn;
    CircleImageView iv_dp;
    ImageView imageView_front, imageView_back;
    String str_Lable = "";
    TextView txt_name, et_dob, et_regdate, et_satffname;
    TextView txt_registration_country;
    String dp_path, front_path, back_path;
    static int stid = 0;
    GrowerModel growerModel = new GrowerModel();
    String counrtyId = "0", countryName = "";

    private CCFSerachSpinner mSpinnerArray[];
    private int[] mSpinnerHeadingTextView;

    private CCFSerachSpinner mSearchableSpinner1;
    private CCFSerachSpinner mSearchableSpinner2;
    private CCFSerachSpinner mSearchableSpinner3;
    private CCFSerachSpinner mSearchableSpinner4;
    private CCFSerachSpinner mSearchableSpinner5;
    private CCFSerachSpinner mSearchableSpinner6;
    private CCFSerachSpinner mSearchableSpinner7;
    private CCFSerachSpinner mSearchableSpinner8;
    private CCFSerachSpinner mSearchableSpinner9;
    private CCFSerachSpinner mSearchableSpinner10;

    private DatePickerDialog mDatePickerDialog = null;

    private ArrayList<CategoryChildModel> mSpinner1List;
    private ArrayList<CategoryChildModel> mSpinner2List;
    private ArrayList<CategoryChildModel> mSpinner3List;
    private ArrayList<CategoryChildModel> mSpinner4List;
    private ArrayList<CategoryChildModel> mSpinner5List;
    private ArrayList<CategoryChildModel> mSpinner6List;
    private ArrayList<CategoryChildModel> mSpinner7List;
    private ArrayList<CategoryChildModel> mSpinner8List;
    private ArrayList<CategoryChildModel> mSpinner9List;
    private ArrayList<CategoryChildModel> mSpinner10List;

    private CodeScanner mCodeScanner;
    private CodeScannerView mCodeScannerView;
    private ScrollView mScrollView;

    private int mCountryMasterIdAsPerSelection = 0;
    private int mSpinnerPosition = 1;

    private File mDocFrontPhotoFile = null;
    private File mGrowerPhotoFile = null;
    private File mDocBackPhotoFile = null;

    private RadioButton mMaleRadioButton;
    private RadioButton mFemaleRadioButton;

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_grower_registration;
    }


    @Override
    protected void init() {
        try {
            AppCompatTextView mVersionTextView = findViewById(R.id.registration_version_code);
            mVersionTextView.setText(getString(R.string.version_code, BuildConfig.VERSION_CODE));

            mContext = this;
            str_Lable = getIntent().getExtras().getString("title");
            counrtyId = Preferences.get(mContext, Preferences.COUNTRYCODE);
            countryName = Preferences.get(mContext, Preferences.COUNTRYNAME);

            //setTitle("New " + str_Lable + " Registration");

            toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("New " + str_Lable + " Registration");
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCodeScannerView != null && mCodeScannerView.getVisibility() == View.VISIBLE) {
                        mCodeScanner.releaseResources();
                        hideScannerLayout();
                    } else {
                        finish();
                    }
                }
            });

            dp_path = front_path = back_path = "";

            new GetCategoriesAsyncTask().execute();

            mMaleRadioButton = findViewById(R.id.male_radio_btn);
            mFemaleRadioButton = findViewById(R.id.female_radio_btn);

            mSearchableSpinner1 = findViewById(R.id.sp1);
            mSearchableSpinner2 = findViewById(R.id.sp2);
            mSearchableSpinner3 = findViewById(R.id.sp3);
            mSearchableSpinner4 = findViewById(R.id.sp4);
            mSearchableSpinner5 = findViewById(R.id.sp5);
            mSearchableSpinner6 = findViewById(R.id.sp6);
            mSearchableSpinner7 = findViewById(R.id.sp7);
            mSearchableSpinner8 = findViewById(R.id.sp8);
            mSearchableSpinner9 = findViewById(R.id.sp9);
            mSearchableSpinner10 = findViewById(R.id.sp10);

            mSearchableSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (mSpinner1List != null && mSpinner1List.size() > 0) {
                        mSpinnerPosition = 2;
                        mCountryMasterIdAsPerSelection = mSpinner1List.get(i).getCountryMasterId();
                        new GetLocationMasterAsyncTask().execute();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            mSpinnerArray = new CCFSerachSpinner[]{mSearchableSpinner1, mSearchableSpinner2, mSearchableSpinner3, mSearchableSpinner4, mSearchableSpinner5, mSearchableSpinner6, mSearchableSpinner7, mSearchableSpinner8, mSearchableSpinner9, mSearchableSpinner10};
            mSpinnerHeadingTextView = new int[]{R.id.textview1, R.id.textview2, R.id.textview3, R.id.textview4, R.id.textview5, R.id.textview6, R.id.textview7, R.id.textview8, R.id.textview9, R.id.textview10};

            et_landmark = (EditText) findViewById(R.id.landmark_edittext);
            et_fullname = (EditText) findViewById(R.id.farmer_name_edittext);
            // et_gender = (EditText) findViewById(R.id.gender_edittext);
            et_dob = findViewById(R.id.date_of_birth_textview);
            et_dob.setOnClickListener(this);
            et_mobile = (EditText) findViewById(R.id.mobile_no_edittext);
            et_uniqcode = (EditText) findViewById(R.id.unique_code_edittext);
            et_regdate = findViewById(R.id.date_of_registration_textview);
            et_regdate.setText(getCurrentDate());
            et_satffname = findViewById(R.id.staff_name_and_id_textview);
            txt_name = (TextView) findViewById(R.id.txt_name);
            txt_registration_country = (TextView) findViewById(R.id.registration_country_textview);
            txt_name.setText(str_Lable + " Full Name :");
            txt_registration_country.setText("" + countryName);
            et_satffname.setText("" + Preferences.get(mContext, Preferences.USER_NAME));
            iv_dp = findViewById(R.id.farmer_photo);
            imageView_front = findViewById(R.id.national_id_photo_front_side_image_view);
            imageView_back = findViewById(R.id.national_id_photo_back_side_image_view);

            scan_qr_code_btn = findViewById(R.id.scan_qr_code);
            mCodeScannerView = findViewById(R.id.scanner_view);
            mCodeScanner = new CodeScanner(this, mCodeScannerView);
            mScrollView = findViewById(R.id.main_scrollview);

            if (countryName.equalsIgnoreCase("Malawi")) {
                scan_qr_code_btn.setVisibility(View.VISIBLE);
                scan_qr_code_btn.setOnClickListener(this);
            }
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
            Log.e("temporary", "exception init " + e.getCause());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_of_birth_textview:

                Calendar mCalendar = Calendar.getInstance();
                hideKeyboard(mContext);

                mCalendar.add(Calendar.YEAR, -18);

                DatePickerDialog.OnDateSetListener onDateSetListener = (datePicker, i, i1, i2) -> {
                    mCalendar.set(Calendar.YEAR, i);
                    mCalendar.set(Calendar.MONTH, i1);
                    mCalendar.set(Calendar.DAY_OF_MONTH, i2);

//                    String myFormat = "yyyy-MM-dd";
                    String myFormat = "dd-MM-yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
                    et_dob.setText(dateFormat.format(mCalendar.getTime()));
                };

                mDatePickerDialog = new DatePickerDialog(
                        mContext,
                        onDateSetListener,
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)
                );
                mDatePickerDialog.getDatePicker().setMaxDate(mCalendar.getTimeInMillis());
                mDatePickerDialog.show();
                break;
            case R.id.scan_qr_code: {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    showToast("Please capture the photo first");
                    return;
                }

                try {
                    // open scanner
                    hideKeyboard(mContext);
                    mCodeScanner.startPreview();
                    visibleScannerLayout();

                    mCodeScanner.setCamera(CodeScanner.CAMERA_BACK);
                    // or CAMERA_FRONT or specific camera id
                    mCodeScanner.setFormats(CodeScanner.ALL_FORMATS); // list of type BarcodeFormat,
                    mCodeScanner.setAutoFocusMode(AutoFocusMode.SAFE); // or CONTINUOUS
                    mCodeScanner.setScanMode(ScanMode.SINGLE);// or CONTINUOUS or PREVIEW
                    mCodeScanner.setAutoFocusEnabled(true);// Whether to enable auto focus or not
                    mCodeScanner.setFlashEnabled(false);// Whether to enable flash or not
                    mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
                        if (!result.getText().isEmpty()) {
                            hideScannerLayout();
                            String[] results = result.getText().split("~");

                            if (results.length > 10 && results[1].contains("MWI")
                            ) {
                                showToast("Scanner successfully !!");
                                et_dob.setText(results[9]);
                                // et_gender.setText(results[8]);
                                if (results[8].equalsIgnoreCase("male")) {
                                    mFemaleRadioButton.setChecked(false);
                                    mMaleRadioButton.setChecked(true);
                                } else {
                                    mFemaleRadioButton.setChecked(true);
                                    mMaleRadioButton.setChecked(false);
                                }
                                et_fullname.setText(results[4] + " " + results[6]);
//                                    val result = string[2].dropLast(1)
                                et_uniqcode.setText(
                                        results[5]
                                        /*result.replace("<", "")*/
                                );
                            } else {
                                showToast("SCANNER ERROR !! INVALID DATA");
                            }
                        } else {
                            showToast("SCANNER ERROR !! INVALID DATA");
                        }
                    }));
                    mCodeScanner.setErrorCallback(thrown -> runOnUiThread(() -> {
                        hideScannerLayout();
                        showToast("Camera initialization error: ${it.message}");
                    }));
                } catch (Exception e) {
                    Log.e("temporary", " e " + e.getCause());
                }
            }
            break;
        }
    }

    private void visibleScannerLayout() {
        mCodeScannerView.setVisibility(View.VISIBLE);
        grower_registration_submit_btn.setVisibility(View.GONE);
        mScrollView.setVisibility(View.GONE);
    }

    private void hideScannerLayout() {
        mCodeScannerView.setVisibility(View.GONE);
        grower_registration_submit_btn.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(View.VISIBLE);
    }

    public void submit(View v) {
        validation();
//        if (validation()) {
//            try {
//
//                str_et_landmark = et_landmark.getText().toString();
//                str_et_fullname = et_fullname.getText().toString();
//
//                if (mMaleRadioButton.isChecked()) {
//                    str_et_gender = mMaleRadioButton.getText().toString();
//                } else {
//                    str_et_gender = mFemaleRadioButton.getText().toString();
//                }
//                str_et_dob = et_dob.getText().toString();
//                str_et_mobile = et_mobile.getText().toString();
//                str_et_uniqcode = et_uniqcode.getText().toString();
//                str_et_regdate = et_regdate.getText().toString();
//                str_et_satffname = et_satffname.getText().toString();
//
//
//                growerModel.setLoginId(Integer.parseInt(Preferences.get(mContext, Preferences.LOGINID).trim()));//,
//                growerModel.setCountryId(Integer.parseInt(counrtyId.trim()));//,
//                //village id
//                growerModel.setCountryMasterId(/*26*/mSpinner5List.get(mSearchableSpinner5.getSelectedItemPosition()).getCountryMasterId());//,
//                growerModel.setUniqueId("");//,
//                growerModel.setUserType(str_Lable);//,
//                growerModel.setLandMark(str_et_landmark);//,
//                growerModel.setFullName(str_et_fullname);//,
//                growerModel.setDOB(str_et_dob);//,
//                growerModel.setGender(str_et_gender);//,
//                growerModel.setMobileNo(str_et_mobile);//,
//                growerModel.setUniqueCode(str_et_uniqcode);//,
//                growerModel.setIdProofFrontCopy(front_path);//,
//                growerModel.setIdProofBackCopy(back_path);//,
//                growerModel.setUploadPhoto(dp_path);//,
//                growerModel.setRegDt(str_et_regdate);//,
//                growerModel.setIsSync(0);
//                growerModel.setGrowerImageUpload(0);
//                growerModel.setFrontImageUpload(0);
//                growerModel.setBackImageUpload(0);
//                growerModel.setStaffNameAndId(str_et_satffname);
//                growerModel.setCreatedBy(Preferences.get(mContext, Preferences.USER_NAME));//
//
//                new AddRegistrationAsyncTask().execute();
//            } catch (Exception e) {
//                Log.e("temporary ", "Error is " + e.getMessage());
//            }
//        }
    }

    /*private class GetRegistrationAsyncTaskList extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                List<GrowerModel> list = database.getAllRegistration();
                for (int i = 0; i < list.size(); i++) {
                    Log.e("temporary", "farmer photo " + list.get(i).getUploadPhoto() + "\n country id " + growerModel.getCountryId() +
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
                            "\ntempid ()" + list.get(i).getTempId());
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
            finish();
            super.onPostExecute(unused);
        }
    }*/

    private class AddRegistrationAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                database.addRegistration(growerModel);
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            /*new GetRegistrationAsyncTaskList().execute();*/
            Dialog mDialog = null;
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("MIPL");
            alertDialog.setMessage("Registration data stored successfully");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            mDialog = alertDialog.create();
            mDialog.show();
            super.onPostExecute(unused);
        }
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List result) {
        Toast.makeText(mContext, "" + result.size(), Toast.LENGTH_SHORT).show();

        adapter = new CategoryLoadingAdapter((ArrayList) result, mContext, this);
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

        ArrayAdapter adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, result);
        spinner.setAdapter(adapter);


    }

    @Override
    public void onGrowerRegister(SuccessModel result) {
        try {
            if (result.isResultFlag()) {
                Toast.makeText(mContext, "" + result.getComment(), Toast.LENGTH_SHORT).show();
                //   finish();
            } else {
                Toast.makeText(mContext, "" + result.getComment(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Something went wrong...", Toast.LENGTH_SHORT).show();
        }


    }

    public void front(View v) {
        try {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            imageView_front.setVisibility(View.VISIBLE);
                            imageView_front.setImageBitmap(r.getBitmap());
                            // front_path = r.getPath();
                            try {
                                mDocFrontPhotoFile = createImageFile("front_photo");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (mDocFrontPhotoFile != null && r.getBitmap() != null) {
                                try {
                                    front_path = mDocFrontPhotoFile.getAbsolutePath();
                                    //Z Log.e("temporary", " front_path " + front_path);
                                    FileOutputStream out = new FileOutputStream(front_path);
                                    r.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);
                                    out.flush();
                                    out.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                showToast(getString(R.string.went_wrong));
                            }
                        }
                    })
                    .setOnPickCancel(new IPickCancel() {
                        @Override
                        public void onCancelClick() {
                            //TODO: do what you have to if user clicked cancel
                        }
                    }).show(getSupportFragmentManager());
        } catch (Exception e) {

        }
    }

    public void back(View v) {
        try {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            imageView_back.setVisibility(View.VISIBLE);
                            imageView_back.setImageBitmap(r.getBitmap());
                            // back_path = r.getPath();
                           /* str_chk_img1 = r.getPath();
                            uploadstatus = 11;*/
                            try {
                                mDocBackPhotoFile = createImageFile("back_photo");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (mDocBackPhotoFile != null && r.getBitmap() != null) {
                                try {
                                    back_path = mDocBackPhotoFile.getAbsolutePath();
                                    //   Log.e("temporary", " back_path " + back_path);
                                    FileOutputStream out = new FileOutputStream(back_path);
                                    r.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);
                                    out.flush();
                                    out.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                showToast(getString(R.string.went_wrong));
                            }
                        }
                    })
                    .setOnPickCancel(new IPickCancel() {
                        @Override
                        public void onCancelClick() {
                            //TODO: do what you have to if user clicked cancel
                        }
                    }).show(getSupportFragmentManager());
        } catch (Exception e) {

        }
    }

    public void dp(View v) {
        try {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            iv_dp.setImageBitmap(r.getBitmap());
                            //  dp_path = r.getPath();
                          /*  str_chk_img1 = r.getPath();
                            uploadstatus = 11;*/
                            try {
                                mGrowerPhotoFile = createImageFile("profile_photo");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (mGrowerPhotoFile != null && r.getBitmap() != null) {
                                try {
                                    dp_path = mGrowerPhotoFile.getAbsolutePath();
                                    //  Log.e("temporary", " dp " + dp_path);
                                    FileOutputStream out = new FileOutputStream(dp_path);
                                    r.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);
                                    out.flush();
                                    out.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                showToast(getString(R.string.went_wrong));
                            }
                        }
                    })
                    .setOnPickCancel(new IPickCancel() {
                        @Override
                        public void onCancelClick() {
                            //TODO: do what you have to if user clicked cancel
                        }
                    }).show(getSupportFragmentManager());
        } catch (Exception e) {

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

                        if (stid == 1) {
                            JSONObject job = new JSONObject(line.toString());
                            dp_path = job.getString("FileName").toString();
                            growerModel.setUploadPhoto(job.getString("FileName").toString());
                            Toast.makeText(mContext, "Dp Uploaded", Toast.LENGTH_SHORT).show();
                            stid = 2;
                            new UploadFile().execute(front_path);

                        } else if (stid == 2) {
                            stid = 3;
                            JSONObject job = new JSONObject(line.toString());
                            front_path = job.getString("FileName").toString();
                            growerModel.setIdProofFrontCopy(job.getString("FileName").toString());
                            new UploadFile().execute(back_path);
                            Toast.makeText(mContext, "front Uploaded", Toast.LENGTH_SHORT).show();

                        } else if (stid == 3) {
                            JSONObject job = new JSONObject(line.toString());
                            back_path = job.getString("FileName").toString();

                            growerModel.setIdProofBackCopy(job.getString("FileName").toString());

                            Log.i("Local Json Data :", growerModel.toString().trim());
                            Log.i("Local Json Data :", new Gson().toJson(growerModel));
                            JsonObject jsonObject = new JsonParser().parse(new Gson().toJson(growerModel)).getAsJsonObject();
                            registrationAPI.createGrower(jsonObject);
                            Toast.makeText(mContext, "back Uploaded", Toast.LENGTH_SHORT).show();

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


    private class GetCategoriesAsyncTask extends AsyncTask<Void, Void, ArrayList<CategoryModel>> {
        @Override
        protected final ArrayList<CategoryModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<CategoryModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                actionModels = database.getAllCategories();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<CategoryModel> result) {

            if (result != null && result.size() > 0) {

                for (int i = 0; i < result.size(); i++) {
//                    SearchableSpinner searchableSpinner = findViewById(mSpinnerArray[i]);
                    mSpinnerArray[i].setVisibility(View.VISIBLE);
                    mSpinnerArray[i].setTitle("Select " + result.get(i).getDisplayTitle());

                    TextView textView = findViewById(mSpinnerHeadingTextView[i]);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(Html.fromHtml(result.get(i).getDisplayTitle() + "<font color='#FF0000'>*</font>"));
                }
                mSpinnerPosition = 1;
                mCountryMasterIdAsPerSelection = Integer.parseInt(Preferences.get(mContext, Preferences.COUNTRY_MASTER_ID));
                new GetLocationMasterAsyncTask().execute();
            } /*else {
                Log.e("temporary", "onPostExecute result null ");
            }*/
            super.onPostExecute(result);
        }
    }


    private class GetLocationMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<CategoryChildModel>> {
        @Override
        protected final ArrayList<CategoryChildModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<CategoryChildModel> mCategoryChildModelsList = null;
            try {
                database = new SqlightDatabase(mContext);
                mCategoryChildModelsList = database.getLocationCategories(/*Integer.parseInt(Preferences.get(mContext, Preferences.COUNTRY_MASTER_ID))*/mCountryMasterIdAsPerSelection);
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return mCategoryChildModelsList;
        }

        @Override
        protected void onPostExecute(ArrayList<CategoryChildModel> result) {
            if (result != null && result.size() > 0) {
                callLocationAdapter(result);
            } else {
                clearSpinnerData(mSpinnerPosition);
            }
            super.onPostExecute(result);
        }
    }

    private void callLocationAdapter(ArrayList<CategoryChildModel> result) {
        switch (mSpinnerPosition) {
            case 1: {
                mSpinner1List = result;
//                Spinner1Adapter adapter = new Spinner1Adapter(mContext, R.layout.spinner_rows,
//                        result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner1.setAdapter(adapter);
            }
            break;
            case 2: {
                mSpinner2List = result;
                //Spinner2Adapter adapter = new Spinner2Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner2.setAdapter(adapter);
                mSearchableSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner2List != null && mSpinner2List.size() > 0) {
                            mSpinnerPosition = 3;
                            mCountryMasterIdAsPerSelection = mSpinner2List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 3: {
                mSpinner3List = result;
                // Spinner3Adapter adapter = new Spinner3Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner3.setAdapter(adapter);

                mSearchableSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner3List != null && mSpinner3List.size() > 0) {
                            mSpinnerPosition = 4;
                            mCountryMasterIdAsPerSelection = mSpinner3List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 4: {
                mSpinner4List = result;
                //Spinner4Adapter adapter = new Spinner4Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner4.setAdapter(adapter);

                mSearchableSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner4List != null && mSpinner4List.size() > 0) {
                            mSpinnerPosition = 5;
                            mCountryMasterIdAsPerSelection = mSpinner4List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 5: {
                mSpinner5List = result;
                //Spinner5Adapter adapter = new Spinner5Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner5.setAdapter(adapter);

                mSearchableSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner5List != null && mSpinner5List.size() > 0) {
                            mSpinnerPosition = 6;
                            mCountryMasterIdAsPerSelection = mSpinner5List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 6: {
                mSpinner6List = result;
                // Spinner6Adapter adapter = new Spinner6Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner6.setAdapter(adapter);

                mSearchableSpinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner6List != null && mSpinner6List.size() > 0) {
                            mSpinnerPosition = 7;
                            mCountryMasterIdAsPerSelection = mSpinner6List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 7: {
                mSpinner7List = result;
                //Spinner7Adapter adapter = new Spinner7Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner7.setAdapter(adapter);

                mSearchableSpinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner7List != null && mSpinner7List.size() > 0) {
                            mSpinnerPosition = 8;
                            mCountryMasterIdAsPerSelection = mSpinner7List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 8: {
                mSpinner8List = result;
                // Spinner8Adapter adapter = new Spinner8Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner8.setAdapter(adapter);

                mSearchableSpinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner8List != null && mSpinner8List.size() > 0) {
                            mSpinnerPosition = 9;
                            mCountryMasterIdAsPerSelection = mSpinner8List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 9: {
                mSpinner9List = result;
                // Spinner9Adapter adapter = new Spinner9Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner9.setAdapter(adapter);

                mSearchableSpinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner9List != null && mSpinner9List.size() > 0) {
                            mSpinnerPosition = 10;
                            mCountryMasterIdAsPerSelection = mSpinner9List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
            case 10: {
                mSpinner10List = result;
                // Spinner10Adapter adapter = new Spinner10Adapter(mContext, R.layout.spinner_rows, result);
                ArrayAdapter<CategoryChildModel> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_rows,
                        result);
                mSearchableSpinner10.setAdapter(adapter);
                mSearchableSpinner10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mSpinner10List != null && mSpinner10List.size() > 0) {
                            mSpinnerPosition = 10;
                            mCountryMasterIdAsPerSelection = mSpinner10List.get(i).getCountryMasterId();
                            new GetLocationMasterAsyncTask().execute();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            break;
        }
    }

    private void clearSpinnerData(int mSpinnerPosition) {
        switch (mSpinnerPosition) {
            case 2: {
                if (mSpinner2List != null) {
                    mSpinner2List.clear();
                }
                if (mSpinner3List != null) {
                    mSpinner3List.clear();
                }
                if (mSpinner4List != null) {
                    mSpinner4List.clear();
                }
                if (mSpinner5List != null) {
                    mSpinner5List.clear();
                }
                if (mSpinner6List != null) {
                    mSpinner6List.clear();
                }
                if (mSpinner7List != null) {
                    mSpinner7List.clear();
                }
                if (mSpinner8List != null) {
                    mSpinner8List.clear();
                }
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }

                mSearchableSpinner2.setAdapter(null);
                mSearchableSpinner3.setAdapter(null);
                mSearchableSpinner4.setAdapter(null);
                mSearchableSpinner5.setAdapter(null);
                mSearchableSpinner6.setAdapter(null);
                mSearchableSpinner7.setAdapter(null);
                mSearchableSpinner8.setAdapter(null);
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 3: {
                if (mSpinner3List != null) {
                    mSpinner3List.clear();
                }
                if (mSpinner4List != null) {
                    mSpinner4List.clear();
                }
                if (mSpinner5List != null) {
                    mSpinner5List.clear();
                }
                if (mSpinner6List != null) {
                    mSpinner6List.clear();
                }
                if (mSpinner7List != null) {
                    mSpinner7List.clear();
                }
                if (mSpinner8List != null) {
                    mSpinner8List.clear();
                }
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }

                mSearchableSpinner3.setAdapter(null);
                mSearchableSpinner4.setAdapter(null);
                mSearchableSpinner5.setAdapter(null);
                mSearchableSpinner6.setAdapter(null);
                mSearchableSpinner7.setAdapter(null);
                mSearchableSpinner8.setAdapter(null);
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 4: {
                if (mSpinner4List != null) {
                    mSpinner4List.clear();
                }
                if (mSpinner5List != null) {
                    mSpinner5List.clear();
                }
                if (mSpinner6List != null) {
                    mSpinner6List.clear();
                }
                if (mSpinner7List != null) {
                    mSpinner7List.clear();
                }
                if (mSpinner8List != null) {
                    mSpinner8List.clear();
                }
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }

                mSearchableSpinner4.setAdapter(null);
                mSearchableSpinner5.setAdapter(null);
                mSearchableSpinner6.setAdapter(null);
                mSearchableSpinner7.setAdapter(null);
                mSearchableSpinner8.setAdapter(null);
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 5: {
                if (mSpinner5List != null) {
                    mSpinner5List.clear();
                }
                if (mSpinner6List != null) {
                    mSpinner6List.clear();
                }
                if (mSpinner7List != null) {
                    mSpinner7List.clear();
                }
                if (mSpinner8List != null) {
                    mSpinner8List.clear();
                }
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }

                mSearchableSpinner5.setAdapter(null);
                mSearchableSpinner6.setAdapter(null);
                mSearchableSpinner7.setAdapter(null);
                mSearchableSpinner8.setAdapter(null);
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 6: {
                if (mSpinner6List != null) {
                    mSpinner6List.clear();
                }
                if (mSpinner7List != null) {
                    mSpinner7List.clear();
                }
                if (mSpinner8List != null) {
                    mSpinner8List.clear();
                }
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }

                mSearchableSpinner6.setAdapter(null);
                mSearchableSpinner7.setAdapter(null);
                mSearchableSpinner8.setAdapter(null);
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 7: {
                if (mSpinner7List != null) {
                    mSpinner7List.clear();
                }
                if (mSpinner8List != null) {
                    mSpinner8List.clear();
                }
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }
                mSearchableSpinner7.setAdapter(null);
                mSearchableSpinner8.setAdapter(null);
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 8: {

                if (mSpinner8List != null) {
                    mSpinner8List.clear();
                }
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }
                mSearchableSpinner8.setAdapter(null);
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 9: {
                if (mSpinner9List != null) {
                    mSpinner9List.clear();
                }
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }
                mSearchableSpinner9.setAdapter(null);
                mSearchableSpinner10.setAdapter(null);
            }
            break;
            case 10: {
                if (mSpinner10List != null) {
                    mSpinner10List.clear();
                }
                mSearchableSpinner10.setAdapter(null);
            }
            break;
        }
    }

    /*private boolean validation() {
        if (mGrowerPhotoFile == null) {
            showToast(getString(R.string.please_grower_photo));
            return false;
        } else if (mSearchableSpinner5.getSelectedItemPosition() == -1) {
            showToast("Data not found");
            return false;
        } else if (TextUtils.isEmpty(et_landmark.getText().toString())) {
            showToast(getString(R.string.Please_enter_landmark));
            return false;
        } else if (TextUtils.isEmpty(et_fullname.getText().toString())) {
            if (str_Lable.equalsIgnoreCase("Grower")) {
                showToast(getString(R.string.Please_enter_farmer_name));
            } else {
                showToast(getString(R.string.Please_enter_organizer_name));
            }
            return false;
        } else if (*//*TextUtils.isEmpty(et_gender.getText().toString())*//*
                !mMaleRadioButton.isChecked() && !mFemaleRadioButton.isChecked()) {
            showToast(getString(R.string.Please_select_gender));
            return false;
        } else if (TextUtils.isEmpty(et_dob.getText().toString().trim())) {
            showToast(getString(R.string.Please_enter_date_of_birth));
            return false;
        } else if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            showToast(getString(R.string.Please_enter_mobile_no));
            return false;
        } else if (mDocFrontPhotoFile == null) {
            showToast(getString(R.string.please_capture_national_id_photo_front));
            return false;
        } else if (mDocBackPhotoFile == null) {
            showToast(getString(R.string.please_capture_national_id_photo_back));
            return false;
        } else if (TextUtils.isEmpty(et_uniqcode.getText().toString().trim())) {
            showToast(getString(R.string.Please_enter_unique_code));
            return false;
        } else {
            *//*return if (checkInternetConnection()) {*//*
            return true;
            *//*} else {
                setEnableOrDisable(true)
                showToast(getString(R.string.err_internet))
                false
            }*//*
        }
    }*/
    private /*boolean*/void validation() {
        if (mGrowerPhotoFile == null) {
            showToast(getString(R.string.please_grower_photo));
            //  return false;
        } else if (mSearchableSpinner5.getSelectedItemPosition() == -1) {
            showToast("Data not found");
            // return false;
        } else if (TextUtils.isEmpty(et_landmark.getText().toString())) {
            showToast(getString(R.string.Please_enter_landmark));
            // return false;
        } else if (TextUtils.isEmpty(et_fullname.getText().toString())) {
            if (str_Lable.equalsIgnoreCase("Grower")) {
                showToast(getString(R.string.Please_enter_farmer_name));
            } else {
                showToast(getString(R.string.Please_enter_organizer_name));
            }
            //  return false;
        } else if (/*TextUtils.isEmpty(et_gender.getText().toString())*/
                !mMaleRadioButton.isChecked() && !mFemaleRadioButton.isChecked()) {
            showToast(getString(R.string.Please_select_gender));
            // return false;
        } else if (TextUtils.isEmpty(et_dob.getText().toString().trim())) {
            showToast(getString(R.string.Please_enter_date_of_birth));
            //  return false;
        } else if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            showToast(getString(R.string.Please_enter_mobile_no));
            //  return false;
        } else if (mDocFrontPhotoFile == null) {
            showToast(getString(R.string.please_capture_national_id_photo_front));
            // return false;
        } else if (mDocBackPhotoFile == null) {
            showToast(getString(R.string.please_capture_national_id_photo_back));
            // return false;
        } else if (TextUtils.isEmpty(et_uniqcode.getText().toString().trim())) {
            showToast(getString(R.string.Please_enter_unique_code));
            // return false;
        } /*else if (new SqlightDatabase(mContext).isGrowerRegister(et_uniqcode.getText().toString().trim())) {
            Dialog mDialog = null;
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("MIPL");
            alertDialog.setMessage("Record Already Exists.");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // finish();
                    dialogInterface.dismiss();
                }
            });
            mDialog = alertDialog.create();
            mDialog.show();
            return false;
        } else if (new SqlightDatabase(mContext).isGrowerDownloaded(et_uniqcode.getText().toString().trim())) {
            Dialog mDialog = null;
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("MIPL");
            alertDialog.setMessage("Record Already Exists.");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // finish();
                    dialogInterface.dismiss();
                }
            });
            mDialog = alertDialog.create();
            mDialog.show();
            return false;
        } */ else {
            // Log.e("temporary", " validation true");
            new CheckWithLocalRegistrationAsyncTask().execute();
            /*return if (checkInternetConnection()) {*/
            //  return true;
        /*} else {
            setEnableOrDisable(true)
            showToast(getString(R.string.err_internet))
            false
        }*/
        }
    }

    private class CheckWithLocalRegistrationAsyncTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected final Boolean doInBackground(Void... voids) {
            SqlightDatabase database = null;
            boolean b = false;
            try {
                database = new SqlightDatabase(mContext);
                b = database.isGrowerRegister(et_uniqcode.getText().toString().trim());
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return b;
        }

        @Override
        protected void onPostExecute(Boolean unused) {
            if (unused) {
                Dialog mDialog = null;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                alertDialog.setCancelable(false);
                alertDialog.setTitle("MIPL");
                alertDialog.setMessage("Record Already Exists.");
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // finish();
                        dialogInterface.dismiss();
                    }
                });
                mDialog = alertDialog.create();
                mDialog.show();
            } else {
                new CheckWithServerRegistrationAsyncTask().execute();
            }
            super.onPostExecute(unused);
        }
    }

    private class CheckWithServerRegistrationAsyncTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected final Boolean doInBackground(Void... voids) {
            SqlightDatabase database = null;
            boolean b = false;
            try {
                database = new SqlightDatabase(mContext);
                b = database.isGrowerDownloaded(et_uniqcode.getText().toString().trim());
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return b;
        }

        @Override
        protected void onPostExecute(Boolean unused) {
            if (unused) {
                Dialog mDialog = null;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                alertDialog.setCancelable(false);
                alertDialog.setTitle("MIPL");
                alertDialog.setMessage("Record Already Exists.");
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mDialog = alertDialog.create();
                mDialog.show();
            } else {
                saveData();
            }
            super.onPostExecute(unused);
        }
    }

    private void saveData() {
        try {
            str_et_landmark = et_landmark.getText().toString();
            str_et_fullname = et_fullname.getText().toString();

            if (mMaleRadioButton.isChecked()) {
                str_et_gender = mMaleRadioButton.getText().toString();
            } else {
                str_et_gender = mFemaleRadioButton.getText().toString();
            }
            str_et_dob = et_dob.getText().toString();
            str_et_mobile = et_mobile.getText().toString();
            str_et_uniqcode = et_uniqcode.getText().toString();
            str_et_regdate = et_regdate.getText().toString();
            str_et_satffname = et_satffname.getText().toString();


            growerModel.setLoginId(Integer.parseInt(Preferences.get(mContext, Preferences.LOGINID).trim()));//,
            growerModel.setCountryId(Integer.parseInt(counrtyId.trim()));//,
            //village id
            growerModel.setCountryMasterId(/*26*/mSpinner5List.get(mSearchableSpinner5.getSelectedItemPosition()).getCountryMasterId());//,
            growerModel.setUniqueId("");//,
            growerModel.setUserType(str_Lable);//,
            growerModel.setLandMark(str_et_landmark);//,
            growerModel.setFullName(str_et_fullname);//,
            growerModel.setDOB(str_et_dob/*getDateToSendServer(mBirthDateToSendServer)*/);//,
            growerModel.setGender(str_et_gender);//,
            growerModel.setMobileNo(str_et_mobile);//,
            growerModel.setUniqueCode(str_et_uniqcode);//,
            growerModel.setIdProofFrontCopy(front_path);//,
            growerModel.setIdProofBackCopy(back_path);//,
            growerModel.setUploadPhoto(dp_path);//,
            growerModel.setRegDt(str_et_regdate/*getCurrentDateToSendServer()*/);//,
            growerModel.setIsSync(0);
            growerModel.setGrowerImageUpload(0);
            growerModel.setFrontImageUpload(0);
            growerModel.setBackImageUpload(0);
            growerModel.setStaffNameAndId(str_et_satffname);
            growerModel.setCreatedBy(Preferences.get(mContext, Preferences.USER_NAME));//

            new AddRegistrationAsyncTask().execute();
        } catch (Exception e) {
            Log.e("temporary ", "Error is " + e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        if (mCodeScannerView != null) {
            mCodeScanner.releaseResources();
        }
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (mCodeScannerView != null && mCodeScannerView.getVisibility() == View.VISIBLE) {
            mCodeScanner.releaseResources();
            hideScannerLayout();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        hideKeyboard(mContext);
        dismissNoInternetDialog();
        super.onDestroy();
    }
}
