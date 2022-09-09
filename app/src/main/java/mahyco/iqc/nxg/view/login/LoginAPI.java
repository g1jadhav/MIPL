package mahyco.iqc.nxg.view.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import mahyco.iqc.nxg.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAPI {


    Context context;
    String result = "";
    ProgressDialog progressDialog;
    LoginAPIListener resultOutput;

    public LoginAPI(Context context, LoginAPIListener resultOutput) {
        this.context = context;
        this.resultOutput = resultOutput;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..");
    }
    public void validateLogin(JsonObject jsonObject) {
        try {

            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<String> call = RetrofitClient.getInstance().getMyApi().checkLogin(jsonObject);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response.body() != null) {
                        String result = response.body();
                        try {
                            resultOutput.onResult(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
   
}
