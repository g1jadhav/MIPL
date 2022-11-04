package mahyco.mipl.nxg.view.seeddistribution;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import mahyco.mipl.nxg.model.OldGrowerSeedDistributionModel;
import mahyco.mipl.nxg.model.SuccessModel;
import mahyco.mipl.nxg.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OldGrowerSeedDistrAPI {

    Context context;
    ProgressDialog progressDialog;
    DistributionListener resultOutput;

    public OldGrowerSeedDistrAPI(Context context, DistributionListener resultOutput) {
        this.context = context;
        this.resultOutput = resultOutput;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait..");
    }

    public void createDistribution(ArrayList<OldGrowerSeedDistributionModel> jsonObject) {
        try {
            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<SuccessModel> call = null;
            call = RetrofitClient.getInstance().getMyApi().seedDistribution(jsonObject);
            call.enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response.body() != null) {
                        SuccessModel result = response.body();
                        try {
                            resultOutput.onSeedDistributionResult(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {
        }
    }
}
