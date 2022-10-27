package mahyco.mipl.nxg.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {

    private Dialog mDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        init();
    }

    protected abstract int getLayout();

    protected abstract void init();

    protected boolean checkInternetConnection(Context context) {
        boolean result = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (cm != null) {
                    NetworkCapabilities ne = cm.getNetworkCapabilities(cm.getActiveNetwork());
                    if (ne != null) {
                        result = ne.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                ne.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                                ne.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
                    }
                }
            } else {
                if (cm != null) {
                    NetworkInfo activeInfo = cm.getActiveNetworkInfo();
                    if (activeInfo != null && activeInfo.isConnected()) {
                        result = activeInfo.getType() == ConnectivityManager.TYPE_WIFI || activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void showNoInternetDialog(Context context, String message) {
        mDialog = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("MIPL");
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Ok", (dialogInterface, i) -> dialogInterface.dismiss());
        mDialog = alertDialog.create();
        mDialog.show();
    }

    protected void dismissNoInternetDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        String myFormat = "dd/MM/yyyy";

        SimpleDateFormat df = new SimpleDateFormat(myFormat, Locale.getDefault());
        return df.format(date);
    }

    protected boolean checkAutoTimeEnabledOrNot() {
        return Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
    }

    protected void showAutomaticTimeMessage(Context context, String message) {
        mDialog = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("MIPL");
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Settings", (dialogInterface, i) -> startActivity(new Intent(Settings.ACTION_DATE_SETTINGS)));
        mDialog = alertDialog.create();
        mDialog.show();
    }

    protected void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(context);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showToast(String desc) {
        Toast toast = Toast.makeText(this, desc, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    protected File createImageFile(String type) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = Preferences.get(this,Preferences.USER_ID) + "_" + type + "_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        );
    }
}
