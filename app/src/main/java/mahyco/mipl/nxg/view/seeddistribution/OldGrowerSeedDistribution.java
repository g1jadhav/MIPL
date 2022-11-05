package mahyco.mipl.nxg.view.seeddistribution;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.ScanMode;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.adapter.CropAdapter;
import mahyco.mipl.nxg.adapter.GrowerAdapter;
import mahyco.mipl.nxg.adapter.OrganizerAdapter;
import mahyco.mipl.nxg.adapter.ProductCodeAdapter;
import mahyco.mipl.nxg.adapter.ProductionClusterAdapter;
import mahyco.mipl.nxg.adapter.SeasonAdapter;
import mahyco.mipl.nxg.adapter.SeedBatchNoFemaleAdapter;
import mahyco.mipl.nxg.adapter.SeedBatchNoMaleAdapter;
import mahyco.mipl.nxg.adapter.SeedDistrPlantingYearAdapter;
import mahyco.mipl.nxg.model.CropModel;
import mahyco.mipl.nxg.model.DownloadGrowerModel;
import mahyco.mipl.nxg.model.OldGrowerSeedDistributionModel;
import mahyco.mipl.nxg.model.ProductionClusterModel;
import mahyco.mipl.nxg.model.SeasonModel;
import mahyco.mipl.nxg.model.SeedBatchNoModel;
import mahyco.mipl.nxg.model.SeedReceiptModel;
import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.util.SqlightDatabase;

public class OldGrowerSeedDistribution extends BaseActivity implements View.OnClickListener {

    private Context mContext;

    private AppCompatSpinner mPlantingYearSpinner;
    private SearchableSpinner mSeasonSpinner;
    private SearchableSpinner mCropSpinner;
    private SearchableSpinner mProductionCodeSpinner;
    private SearchableSpinner mClusterSpinner;
    private SearchableSpinner mMaleBatchNoSpinner;
    private SearchableSpinner mFemaleBatchNoSpinner;
    private SearchableSpinner mOrganizerNameSpinner;
    private SearchableSpinner mSearchByIdNameSpinner;
    // private SearchableSpinner mCropTypeSpinner;

    private ArrayList<CropModel> mCropList = new ArrayList<>();
    private ArrayList<DownloadGrowerModel> mGrowerList = new ArrayList<>();
    private ArrayList<DownloadGrowerModel> mOrganizerNameList = new ArrayList<>();
    private ArrayList<SeasonModel> mSeasonList = new ArrayList<>();
    private ArrayList<ProductionClusterModel> mProdClusterList = new ArrayList<>();
    // private ArrayList<ProductCodeModel> mProdCodeList = new ArrayList<>();
    private ArrayList<SeedBatchNoModel> mMaleBatchNoList = new ArrayList<>();
    private ArrayList<SeedBatchNoModel> mFemaleBatchNoList = new ArrayList<>();
    //private ArrayList<CropTypeModel> mCropTypeList = new ArrayList<>();
    private ArrayList<SeedReceiptModel> mSeedProductionCodeList = new ArrayList<>();

    private AppCompatEditText mAreaEditText;

    private AppCompatTextView mGrowerName;
    private AppCompatTextView mUniqueCode;
    private AppCompatTextView mAddressTextView;
    private AppCompatTextView mParentSeedIssueDate;
    private AppCompatTextView mStaffTextView;
    private AppCompatTextView mOrganizerNameTextView;

    private CodeScanner mCodeScanner;
    private CodeScannerView mCodeScannerView;
    private ScrollView mScrollView;

    private Button mSubmitButton;
    private Button mScanQRCodeButton;
    private String mCountryId = "0";
    private String mCountryName = "";
    private RadioGroup mRadioGroup;
    private OldGrowerSeedDistributionModel mOldGrowerSeedDistributionModel = new OldGrowerSeedDistributionModel();

    private boolean mFirstTimeSelectedCrop = false;
    private boolean mProductFirstTimeSelected = false;
    private boolean mGrowerRadioBtnSelected = true;

    @Override
    protected int getLayout() {
        return R.layout.old_grower_seed_distribution;
    }

    @Override
    protected void init() {

        setTitle("Parent Seed Distribution");

        ArrayList<String> mYearList = new ArrayList<>();

        mContext = this;

        mRadioGroup = findViewById(R.id.direct_or_organizer_radio_group);
        mRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.direct_to_grower_radio_btn: {
                    // Log.e("temporary", "is check called grower");
                    mGrowerRadioBtnSelected = true;
                    mOrganizerNameSpinner.setVisibility(View.GONE);
                    mOrganizerNameTextView.setVisibility(View.GONE);
                    mProductionCodeSpinner.setAdapter(null);
                    new GetProductionCodeMasterAsyncTask().execute();
                }
                break;
                case R.id.direct_to_orgnizer_radio_btn: {
                    //Log.e("temporary", "is check called orgnizer");
                    mGrowerRadioBtnSelected = false;
                    mOrganizerNameSpinner.setVisibility(View.VISIBLE);
                    mOrganizerNameTextView.setVisibility(View.VISIBLE);
                    mProductionCodeSpinner.setAdapter(null);
                    new GetProductionCodeMasterAsyncTask().execute();
                }
                break;
            }
        });

        mPlantingYearSpinner = findViewById(R.id.planting_year_drop_down);
        mOrganizerNameSpinner = findViewById(R.id.organizer_name_drop_down);
        mSeasonSpinner = findViewById(R.id.season_drop_down);
        mCropSpinner = findViewById(R.id.crop_drop_down);
        mProductionCodeSpinner = findViewById(R.id.production_code_drop_down);
        mClusterSpinner = findViewById(R.id.parent_seed_issue_location_drop_down);
        mMaleBatchNoSpinner = findViewById(R.id.parent_seed_batch_no_male_drop_down);
        mFemaleBatchNoSpinner = findViewById(R.id.parent_seed_batch_no_female_drop_down);
        mGrowerName = findViewById(R.id.grower_name_textview);
        mUniqueCode = findViewById(R.id.unique_id_textview);
        mAddressTextView = findViewById(R.id.address_textview);
        mOrganizerNameTextView = findViewById(R.id.organizer_name_textview);
        mSearchByIdNameSpinner = findViewById(R.id.search_grower_by_id_name);
        // mCropTypeSpinner = findViewById(R.id.crop_type_down);

        mSearchByIdNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //   Log.e("temporary", " mSearchByIdNameSpinner.setOnItemSelectedListener");
                if (l != 0) {
                    mGrowerName.setText(mGrowerList.get(i).getFullName());
                    mUniqueCode.setText(mGrowerList.get(i).getUniqueCode());
                    mAddressTextView.setText(mGrowerList.get(i).getLandMark() + ", " +
                            mGrowerList.get(i).getCountryName());
                } else {
                    mGrowerName.setText("");
                    mUniqueCode.setText("");
                    mAddressTextView.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mCropSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Log.e("temporary", "selected item called mFirstTimeSelectedCrop " + mFirstTimeSelectedCrop);
                if (mFirstTimeSelectedCrop) {
                    mProductionCodeSpinner.setAdapter(null);
                    new GetProductionCodeMasterAsyncTask().execute();
                } else {
                    mFirstTimeSelectedCrop = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mPlantingYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Log.e("temporary", "selected item called mProductFirstTimeSelected " + mProductFirstTimeSelected);
                if (mProductFirstTimeSelected) {
                    new GetProductionCodeMasterAsyncTask().execute();
                } else {
                    mProductFirstTimeSelected = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mScanQRCodeButton = findViewById(R.id.seed_scan_qr_code);
        mCodeScannerView = findViewById(R.id.seed_distribution_scanner_view);
        mCodeScanner = new CodeScanner(this, mCodeScannerView);
        mScrollView = findViewById(R.id.seed_distribution_main_scrollview);

        mSubmitButton = findViewById(R.id.seed_distribution_submit_btn);
        mSubmitButton.setOnClickListener(this);

        mParentSeedIssueDate = findViewById(R.id.parent_seed_issue_date_textview);
        mParentSeedIssueDate.setText(getCurrentDate());

        mStaffTextView = findViewById(R.id.parent_seed_distribution_by_staff_textview);
        mStaffTextView.setText(Preferences.get(mContext, Preferences.USER_NAME));

        mAreaEditText = findViewById(R.id.seed_production_area_edittext);
        mAreaEditText.setFilters(new InputFilter[]{new DecimalDigitsInputFilters(100, 2)});

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, +1);
        mYearList.add(String.valueOf(calendar.get(Calendar.YEAR)));

        calendar.add(Calendar.YEAR, -1);
        mYearList.add(String.valueOf(calendar.get(Calendar.YEAR)));

        calendar.add(Calendar.YEAR, -1);
        mYearList.add(String.valueOf(calendar.get(Calendar.YEAR)));

        SeedDistrPlantingYearAdapter adapter = new SeedDistrPlantingYearAdapter(mContext, R.layout.spinner_rows, mYearList);
        mPlantingYearSpinner.setAdapter(adapter);
        mPlantingYearSpinner.setSelection(1);

        mCountryName = Preferences.get(mContext, Preferences.COUNTRYNAME);

        if (mCountryName.equalsIgnoreCase("Malawi")) {
            mScanQRCodeButton.setVisibility(View.VISIBLE);
            mScanQRCodeButton.setOnClickListener(this);
        }

        new GetGrowerMasterAsyncTask().execute();
        new GetClusterMasterAsyncTask().execute();
        // new GetCropTypeMasterAsyncTask().execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.seed_scan_qr_code: {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    showToast("Camera Permission is Required.");
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    return;
                }
                try {
                    // open scanner
                    hideKeyboard(mContext);
                    mCodeScanner.startPreview();
                    visibleScannerLayout();

                    mCodeScanner.setCamera(CodeScanner.CAMERA_BACK);
                    mCodeScanner.setFormats(CodeScanner.ALL_FORMATS); // list of type BarcodeFormat,
                    mCodeScanner.setAutoFocusMode(AutoFocusMode.SAFE); // or CONTINUOUS
                    mCodeScanner.setScanMode(ScanMode.SINGLE);// or CONTINUOUS or PREVIEW
                    mCodeScanner.setAutoFocusEnabled(true);// Whether to enable auto focus or not
                    mCodeScanner.setFlashEnabled(false);// Whether to enable flash or not
                    mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
                        if (!result.getText().isEmpty()) {
                            hideScannerLayout();
                            // Log.e("temporary", " result " + result + " length " + result.getText().length());
                            String[] results = result.getText().split("~");

                            //  Log.e("temporary", " results " + results.length + " results " + results/*+" 1 " + results[1]
                            // +" 5 " + results[5]*/);
                            if (results.length > 10 && results[1].contains("MWI")
                            ) {
                                showToast("Scanner successfully !!");
                            /*et_dob.setText(results[9]);
                            et_fullname.setText(results[4] + " " + results[6]);
                            et_uniqcode.setText(  results[5]  );*/
                                for (int i = 0; i < mGrowerList.size(); i++) {
                                    if (mGrowerList.get(i).getUniqueCode().equals(results[5])) {
                                        mSearchByIdNameSpinner.setSelection(i);
                                        mGrowerName.setText(mGrowerList.get(i).getFullName());
                                        mUniqueCode.setText(mGrowerList.get(i).getUniqueCode());
                                        mAddressTextView.setText(mGrowerList.get(i).getLandMark() + ", " +
                                                mGrowerList.get(i).getCountryName());
                                        break;
                                    }
                                }
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
                    //   Log.e("temporary", " e " + e.getCause());
                }
            }
            break;

            case R.id.seed_distribution_submit_btn:
                if (validation()) {
                    try {
                        //   Log.e("temporary", " selection value " + mGrowerList.get(mSearchByIdNameSpinner.getSelectedItemPosition()).getUniqueId());
                        mOldGrowerSeedDistributionModel.setCountryId(Integer.parseInt(Preferences.get(mContext, Preferences.COUNTRYCODE)));
                        mOldGrowerSeedDistributionModel.setPlantingYear(mPlantingYearSpinner.getSelectedItem().toString());
                        mOldGrowerSeedDistributionModel.setSeasonId(mSeasonList.get(mSeasonSpinner.getSelectedItemPosition()).getSeasonId());
                        mOldGrowerSeedDistributionModel.setCropId(mCropList.get(mCropSpinner.getSelectedItemPosition()).getCropId());
                        mOldGrowerSeedDistributionModel.setProductionClusterId(mProdClusterList.get(mClusterSpinner.getSelectedItemPosition()).getProductionClusterId());

                        if (!mGrowerRadioBtnSelected) {
                            mOldGrowerSeedDistributionModel.setOrganizerId(mOrganizerNameList.get(mOrganizerNameSpinner.getSelectedItemPosition()).getUserId());
                        } else {
                            mOldGrowerSeedDistributionModel.setOrganizerId(0);
                        }
                        mOldGrowerSeedDistributionModel.setGrowerId(mOrganizerNameList.get(mOrganizerNameSpinner.getSelectedItemPosition()).getUserId());
                        mOldGrowerSeedDistributionModel.setProductionCode(mSeedProductionCodeList.get(mProductionCodeSpinner.getSelectedItemPosition()).getParentSeedReceiptId());
                        mOldGrowerSeedDistributionModel.setCreatedBy(Preferences.get(mContext, Preferences.USER_ID));
                        mOldGrowerSeedDistributionModel.setFemaleParentSeedBatchId(mFemaleBatchNoList.get(mFemaleBatchNoSpinner.getSelectedItemPosition()).getParentSeedBatchId());
                        mOldGrowerSeedDistributionModel.setMaleParentSeedBatchId(mMaleBatchNoList.get(mMaleBatchNoSpinner.getSelectedItemPosition()).getParentSeedBatchId());
                        mOldGrowerSeedDistributionModel.setIssueDt(mParentSeedIssueDate.getText().toString());
                        mOldGrowerSeedDistributionModel.setSeedParentArea(Float.parseFloat(mAreaEditText.getText().toString()));

                        new AddDataAsyncTask().execute();
                    } catch (Exception e) {
                        showToast("Data not found");
                    }
                }
                break;
        }
    }

    private boolean validation() {
        if (mSearchByIdNameSpinner.getSelectedItemPosition() == -1 || mSearchByIdNameSpinner.getSelectedItemPosition() == 0) {
            showToast("Please search grower first by name or id");
            return false;
        } else if (mSeasonSpinner.getSelectedItemPosition() == -1) {
            showToast("Please select season");
            return false;
        } else if (mCropSpinner.getSelectedItemPosition() == -1) {
            showToast("Please select crop");
            return false;
        } else if (mProductionCodeSpinner.getSelectedItemPosition() == -1) {
            showToast("Please select production code");
            return false;
        } else if (TextUtils.isEmpty(mAreaEditText.getText().toString().trim()) ||
                mAreaEditText.getText().toString().trim().equalsIgnoreCase(".")) {
            showToast("Please enter seed production area");
            return false;
        } else if (mClusterSpinner.getSelectedItemPosition() == -1) {
            showToast("Please select parent seed issue cluster");
            return false;
        } else if (mFemaleBatchNoSpinner.getSelectedItemPosition() == -1) {
            showToast("Please select parent seed batch no. female");
            return false;
        } else if (mMaleBatchNoSpinner.getSelectedItemPosition() == -1) {
            showToast("Please select parent seed batch no. male");
            return false;
        }
        return true;
    }

    private class AddDataAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                database.parentSeedDistribution(mOldGrowerSeedDistributionModel);
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
    }

    private void visibleScannerLayout() {
        mCodeScannerView.setVisibility(View.VISIBLE);
        mSubmitButton.setVisibility(View.GONE);
        mScrollView.setVisibility(View.GONE);
    }

    private void hideScannerLayout() {
        mCodeScannerView.setVisibility(View.GONE);
        mSubmitButton.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(View.VISIBLE);
    }

    class DecimalDigitsInputFilters implements InputFilter {

        private int mDigitsBeforeZero;
        private int mDigitsAfterZero;
        private Pattern mPattern;

        private static final int DIGITS_BEFORE_ZERO_DEFAULT = 100;
        private static final int DIGITS_AFTER_ZERO_DEFAULT = 100;

        public DecimalDigitsInputFilters(Integer digitsBeforeZero, Integer digitsAfterZero) {
            this.mDigitsBeforeZero = (digitsBeforeZero != null ? digitsBeforeZero : DIGITS_BEFORE_ZERO_DEFAULT);
            this.mDigitsAfterZero = (digitsAfterZero != null ? digitsAfterZero : DIGITS_AFTER_ZERO_DEFAULT);
            mPattern = Pattern.compile("-?[0-9]{0," + (mDigitsBeforeZero) + "}+((\\.[0-9]{0," + (mDigitsAfterZero)
                    + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String replacement = source.subSequence(start, end).toString();
            String newVal = dest.subSequence(0, dstart) + replacement
                    + dest.subSequence(dend, dest.length());

            Matcher matcher = mPattern.matcher(newVal);

            if (matcher.matches())
                return null;

            if (TextUtils.isEmpty(source))
                return dest.subSequence(dstart, dend);
            else
                return "";
        }
    }

    private class GetGrowerMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<DownloadGrowerModel>> {

        @Override
        protected void onPreExecute() {
            //  Log.e("temporary", "GetGrowerMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<DownloadGrowerModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<DownloadGrowerModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                actionModels = database.getDownloadedGrowerMaster();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<DownloadGrowerModel> result) {
            hideProgressDialog();
            if (mGrowerList != null) {
                mGrowerList.clear();
            }
            if (mOrganizerNameList != null) {
                mOrganizerNameList.clear();
            }

            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getUserType().equalsIgnoreCase("Organizer")) {
                        mOrganizerNameList.add(result.get(i));
                    } else {
                        mGrowerList.add(result.get(i));
                    }
                }
                if (mGrowerList.size() > 0) {
                    GrowerAdapter adapter = new GrowerAdapter(mContext, R.layout.spinner_rows, mGrowerList);
                    mSearchByIdNameSpinner.setAdapter(adapter);
                }

                if (mOrganizerNameList.size() > 0) {
                    OrganizerAdapter adapter1 = new OrganizerAdapter(mContext, R.layout.spinner_rows, mOrganizerNameList);
                    mOrganizerNameSpinner.setAdapter(adapter1);
                }
                super.onPostExecute(result);
            }
            new GetSeasonMasterAsyncTask().execute();
        }
    }

    private class GetSeasonMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<SeasonModel>> {

        @Override
        protected void onPreExecute() {
            // Log.e("temporary", "GetSeasonMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<SeasonModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<SeasonModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                actionModels = database.getSeasonMaster();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<SeasonModel> result) {
            hideProgressDialog();
            if (mSeasonList != null) {
                mSeasonList.clear();
            }
            if (result != null && result.size() > 0) {
                mSeasonList = result;
                SeasonAdapter adapter = new SeasonAdapter(mContext, R.layout.spinner_rows, mSeasonList);
                mSeasonSpinner.setAdapter(adapter);
                super.onPostExecute(result);
            }

            new GetCropMasterAsyncTask().execute();
        }
    }

    private class GetCropMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<CropModel>> {

        @Override
        protected void onPreExecute() {
            //   Log.e("temporary", "GetCropMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<CropModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<CropModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                actionModels = database.getCropMaster();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<CropModel> result) {
            hideProgressDialog();
            if (mCropList != null) {
                mCropList.clear();
            }
            if (result != null && result.size() > 0) {
                mCropList = result;
                CropAdapter adapter = new CropAdapter(mContext, R.layout.spinner_rows, mCropList);
                mCropSpinner.setAdapter(adapter);
                new GetProductionCodeMasterAsyncTask().execute();
                super.onPostExecute(result);
                // new GetCodeMasterAsyncTask().execute();
            }
        }
    }

/*
    private class GetCodeMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<ProductCodeModel>> {

        @Override
        protected void onPreExecute() {
           // Log.e("temporary", "GetCodeMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<ProductCodeModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<ProductCodeModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
             //   Log.e("temporary", "asynctask crop code " + mCropList.get(mCropSpinner.getSelectedItemPosition()).getCropCode());
                actionModels = database.getProdCodeMaster(mCropList.get(mCropSpinner.getSelectedItemPosition()).getCropCode());
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<ProductCodeModel> result) {
            hideProgressDialog();
            if (mProdCodeList != null) {
                mProdCodeList.clear();
            }
            if (result != null && result.size() > 0) {
                //  Log.e("temporary", "result " + result.size());
                mProdCodeList = result;
                ProductCodeAdapter adapter = new ProductCodeAdapter(mContext, R.layout.spinner_rows, mProdCodeList);
                mProductionCodeSpinner.setAdapter(adapter);
                super.onPostExecute(result);

                // new GetBatchNoMasterAsyncTask().execute();
                new GetSeedReceiptMasterAsyncTask().execute();
            } else {
                mMaleBatchNoSpinner.setAdapter(null);
                mFemaleBatchNoSpinner.setAdapter(null);
            //    Log.e("temporary", "Product master onPostExecute " + result.size());
            }
        }
    }
*/


    private class GetClusterMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<ProductionClusterModel>> {

        @Override
        protected void onPreExecute() {
            //  Log.e("temporary", "GetClusterMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<ProductionClusterModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<ProductionClusterModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                actionModels = database.getProdClusterMaster();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<ProductionClusterModel> result) {
            hideProgressDialog();
            if (mProdClusterList != null) {
                mProdClusterList.clear();
            }
            if (result != null && result.size() > 0) {
                mProdClusterList = result;
                ProductionClusterAdapter adapter = new ProductionClusterAdapter(mContext, R.layout.spinner_rows, mProdClusterList);
                mClusterSpinner.setAdapter(adapter);
                super.onPostExecute(result);
            }
        }
    }

    private class GetProductionCodeMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<SeedReceiptModel>> {

        @Override
        protected void onPreExecute() {
            Log.e("temporary", "GetSeedReceiptMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<SeedReceiptModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<SeedReceiptModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                //  Log.e("temporary", "mProdCodeList crop code " + mProdCodeList.get(mProductionCodeSpinner.getSelectedItemPosition()).getCropCode());
                actionModels = database.getSeedReceiptMaster();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<SeedReceiptModel> result) {
            hideProgressDialog();
            if (mSeedProductionCodeList != null) {
                mSeedProductionCodeList.clear();
            }
//             Log.e("temporary", " result " + result);
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    /*Log.e("temporary", " result.get(i).getPlantingYear() " + result.get(i).getPlantingYear()
                            + " year " + mPlantingYearSpinner.getSelectedItem().toString() +
                            "\n result.get(i).getCropName() " + result.get(i).getCropName() + " crop name " + mCropList.get(mCropSpinner.getSelectedItemPosition()).getCropName() +
                            "\nmGrowerRadioBtnSelected " + mGrowerRadioBtnSelected);*/
                    if (result.get(i).getPlantingYear().equalsIgnoreCase(mPlantingYearSpinner.getSelectedItem().toString()) &&
                            result.get(i).getCropName().equalsIgnoreCase(mCropList.get(mCropSpinner.getSelectedItemPosition()).getCropName())) {
                        if (mGrowerRadioBtnSelected) {
                          // Log.e("temporary", "receipt " + result.get(i).getParentSeedReceiptType());
                            if (result.get(i).getParentSeedReceiptType().equalsIgnoreCase("Country Level")) {
                              // Log.e("temporary", "country level");
                                mSeedProductionCodeList.add(result.get(i));
                            }
                        } else {
                            if (result.get(i).getParentSeedReceiptType().equalsIgnoreCase("Production Organizer Level")) {
                              // Log.e("temporary", "Production Organizer Level");
                                mSeedProductionCodeList.add(result.get(i));
                            }
                        }
                    }
                }
                ProductCodeAdapter adapter = new ProductCodeAdapter(mContext, R.layout.spinner_rows, mSeedProductionCodeList);
                mProductionCodeSpinner.setAdapter(adapter);
                if (mSeedProductionCodeList.size() > 0) {
                    new GetBatchNoMasterAsyncTask().execute();
                } else {
                    mMaleBatchNoSpinner.setAdapter(null);
                    mFemaleBatchNoSpinner.setAdapter(null);
                }
            }
        }
    }

    private class GetBatchNoMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<SeedBatchNoModel>> {
        @Override
        protected void onPreExecute() {
            //  Log.e("temporary", "GetBatchNoMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<SeedBatchNoModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<SeedBatchNoModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                //   Log.e("temporary", "mProdCodeList crop code " + mSeedReceiptList.get(0).getProductionCode());
                actionModels = database.getSeedBatchNo(mSeedProductionCodeList.get(0).getProductionCode());
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<SeedBatchNoModel> result) {
            hideProgressDialog();
            if (mMaleBatchNoList != null) {
                mMaleBatchNoList.clear();
            }
            if (mFemaleBatchNoList != null) {
                mFemaleBatchNoList.clear();
            }
            //  Log.e("temporary", "result " + result);
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getParentType().equalsIgnoreCase("Female Batch No")) {
                        if (mGrowerRadioBtnSelected) {
                            if (result.get(i).getParentSeedReceiptType().equalsIgnoreCase("Country Level")) {
                                mFemaleBatchNoList.add(result.get(i));
                            }
                        } else {
                            if (result.get(i).getParentSeedReceiptType().equalsIgnoreCase("Production Organizer Level")) {
                                mFemaleBatchNoList.add(result.get(i));
                            }
                        }
                    } else {
                        if (mGrowerRadioBtnSelected) {
                            if (result.get(i).getParentSeedReceiptType().equalsIgnoreCase("Country Level")) {
                                mMaleBatchNoList.add(result.get(i));
                            }
                        } else {
                            if (result.get(i).getParentSeedReceiptType().equalsIgnoreCase("Production Organizer Level")) {
                                mMaleBatchNoList.add(result.get(i));
                            }
                        }
                    }
                }
                SeedBatchNoMaleAdapter adapter = new SeedBatchNoMaleAdapter(mContext, R.layout.spinner_rows, mMaleBatchNoList);
                mMaleBatchNoSpinner.setAdapter(adapter);

                SeedBatchNoFemaleAdapter adapter1 = new SeedBatchNoFemaleAdapter(mContext, R.layout.spinner_rows, mFemaleBatchNoList);
                mFemaleBatchNoSpinner.setAdapter(adapter1);
                super.onPostExecute(result);
            }
        }
    }

    /*private class GetCropTypeMasterAsyncTask extends AsyncTask<Void, Void, ArrayList<CropTypeModel>> {

        @Override
        protected void onPreExecute() {
          //  Log.e("temporary", "GetCropTypeMasterAsyncTask onPreExecute called");
            showProgressDialog(mContext);
            super.onPreExecute();
        }

        @Override
        protected final ArrayList<CropTypeModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<CropTypeModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                actionModels = database.getCropType();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<CropTypeModel> result) {
            hideProgressDialog();
           // Log.e("temporary", " result " + result);
            if (mCropTypeList != null) {
                mCropTypeList.clear();
            }
            if (result != null && result.size() > 0) {
             //   Log.e("temporary", " result.size() " + result.size());
                mCropTypeList = result;
                CropTypeAdapter adapter = new CropTypeAdapter(mContext, R.layout.spinner_rows, mCropTypeList);
                mCropTypeSpinner.setAdapter(adapter);
                super.onPostExecute(result);
            }
        }
    }*/


    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (mCodeScannerView.getVisibility() == View.VISIBLE) {
            mCodeScanner.releaseResources();
            hideScannerLayout();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        hideProgressDialog();
        hideKeyboard(mContext);
        dismissNoInternetDialog();
        super.onDestroy();
    }
}
