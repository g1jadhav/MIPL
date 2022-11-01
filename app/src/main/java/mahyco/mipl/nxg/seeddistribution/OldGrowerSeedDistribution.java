package mahyco.mipl.nxg.seeddistribution;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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
import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.util.Preferences;

public class OldGrowerSeedDistribution extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private AppCompatSpinner mPlantingYearSpinner;

    private ArrayList<String> mYearList;

    private AppCompatEditText mAreaEditText;
    private AppCompatTextView mParentSeedIssueDate;
    private AppCompatTextView mStaffTextView;

    private CodeScanner mCodeScanner;
    private CodeScannerView mCodeScannerView;
    private ScrollView mScrollView;

    private  Button mSubmitButton;
    private  Button mScanQRCodeButton;
    private  String mCountryId = "0";
    private String mCountryName = "";
    private RadioGroup mRadioGroup;

    @Override
    protected int getLayout() {
        return R.layout.old_grower_seed_distribution;
    }

    @Override
    protected void init() {

        setTitle("Parent Seed Distribution");

        mYearList = new ArrayList<>();

        mContext = this;

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

        mPlantingYearSpinner = findViewById(R.id.planting_year_drop_down);

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
                    showToast("Please give camera permission first");
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
                break;
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
