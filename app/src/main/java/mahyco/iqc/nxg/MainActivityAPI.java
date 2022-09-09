package mahyco.iqc.nxg;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.iqc.nxg.model.ActionModel;
import mahyco.iqc.nxg.model.IQCPlantModel;
import mahyco.iqc.nxg.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityAPI {


        Context context;
        String result = "";
        ProgressDialog progressDialog;
        MainActivityListListener resultOutput;

        public MainActivityAPI(Context context, MainActivityListListener resultOutput) {
            this.context = context;
            this.resultOutput = resultOutput;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait..");
        }
        public void getIqcPlant(JsonObject jsonObject)
        {
            try {


                if (!progressDialog.isShowing())
                    progressDialog.show();

                Call<List<IQCPlantModel>> call = null;
                call = RetrofitClient.getInstance().getMyApi().getIQCPlant(jsonObject);
                call.enqueue(new Callback<List<IQCPlantModel>>() {
                    @Override
                    public void onResponse(Call<List<IQCPlantModel>> call, Response<List<IQCPlantModel>> response) {

                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                        if (response.body() != null) {
                            List<IQCPlantModel> result = response.body();
                            try {
                                resultOutput.onListResponce(result);
                            } catch (NullPointerException e) {
                                Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<IQCPlantModel>> call, Throwable t) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Error is", t.getMessage());
                    }
                });
            } catch (Exception e) {

            }
        }
    public void clearIMIE(String Usercode)
    {
        try {

                if (!progressDialog.isShowing())
                    progressDialog.show();

            Call<String> call = RetrofitClient.getInstance().getMyApi().clearData(Usercode);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        String result = response.body();
                        try {
                            resultOutput.onDataReset(result);
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
