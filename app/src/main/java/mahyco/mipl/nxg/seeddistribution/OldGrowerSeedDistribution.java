package mahyco.mipl.nxg.seeddistribution;

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
import android.view.View;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.adapter.SeedDistrPlantingYearAdapter;
import mahyco.mipl.nxg.model.OldGrowerSeedDistributionModel;
import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.util.SqlightDatabase;

public class OldGrowerSeedDistribution extends BaseActivity implements View.OnClickListener {

    private Context mContext;

    private AppCompatSpinner mPlantingYearSpinner;
    private AppCompatSpinner mSeasonSpinner;
    private AppCompatSpinner mCropSpinner;
    private AppCompatSpinner mProductionCodeSpinner;
    private AppCompatSpinner mClusterSpinner;
    private AppCompatSpinner mMaleBatchNoSpinner;
    private AppCompatSpinner mFemaleBatchNoSpinner;
    private AppCompatSpinner mOrganizerNameSpinner;

    private ArrayList<String> mYearList;

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

    @Override
    protected int getLayout() {
        return R.layout.old_grower_seed_distribution;
    }

    @Override
    protected void init() {

        setTitle("Parent Seed Distribution");

        mYearList = new ArrayList<>();

        mContext = this;

        mRadioGroup = findViewById(R.id.direct_or_organizer_radio_group);
        mRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.direct_to_grower_radio_btn: {
                    mOrganizerNameSpinner.setVisibility(View.GONE);
                    mOrganizerNameTextView.setVisibility(View.GONE);
                }
                break;
                case R.id.direct_to_orgnizer_radio_btn: {
                    mOrganizerNameSpinner.setVisibility(View.VISIBLE);
                    mOrganizerNameTextView.setVisibility(View.VISIBLE);
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
                        String[] results = result.getText().split("~");

                        if (results.length > 10 && results[1].contains("MWI")
                        ) {
                            showToast("Scanner successfully !!");
                            /*et_dob.setText(results[9]);
                            et_fullname.setText(results[4] + " " + results[6]);
                            et_uniqcode.setText(  results[5]  );*/
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
            }
            break;

            case R.id.seed_distribution_submit_btn:
                mOldGrowerSeedDistributionModel.setLoginId(Integer.parseInt(Preferences.get(mContext, Preferences.LOGINID).trim()));
                mOldGrowerSeedDistributionModel.setCountryId(Integer.parseInt(Preferences.get(mContext, Preferences.COUNTRYCODE)));
                mOldGrowerSeedDistributionModel.setGrowerName(mGrowerName.getText().toString());
                mOldGrowerSeedDistributionModel.setUniqueCode(mUniqueCode.getText().toString());
                mOldGrowerSeedDistributionModel.setGrowerAddress(mAddressTextView.getText().toString());
                mOldGrowerSeedDistributionModel.setPlantingYear(mPlantingYearSpinner.getSelectedItem().toString());
                mOldGrowerSeedDistributionModel.setSeason(mSeasonSpinner.getSelectedItem().toString());
                mOldGrowerSeedDistributionModel.setCrop(mCropSpinner.getSelectedItem().toString());
                mOldGrowerSeedDistributionModel.setProductionCode(mProductionCodeSpinner.getSelectedItem().toString());
                mOldGrowerSeedDistributionModel.setSeedProductionArea(mAreaEditText.getText().toString());
                mOldGrowerSeedDistributionModel.setSeedIssueLocation(mClusterSpinner.getSelectedItem().toString());
                mOldGrowerSeedDistributionModel.setSeedBatchNoFemale(mFemaleBatchNoSpinner.getSelectedItem().toString());
                mOldGrowerSeedDistributionModel.setSeedBatchNoMale(mMaleBatchNoSpinner.getSelectedItem().toString());
                mOldGrowerSeedDistributionModel.setIssueDate(mParentSeedIssueDate.getText().toString());
                mOldGrowerSeedDistributionModel.setStaffName(mStaffTextView.getText().toString());

                new AddDataAsyncTask().execute();
                break;
        }
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
        hideKeyboard(mContext);
        dismissNoInternetDialog();
        super.onDestroy();
    }
}
