package mahyco.iqc.nxg.view.observationlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.iqc.nxg.model.ProcessInspectionModel;
import mahyco.iqc.nxg.model.ProcessModel;
import mahyco.iqc.nxg.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObservationListAPI {


    Context context;
    String result = "";
    ProgressDialog progressDialog;
    ObservationListAPIListener resultOutput;

    public ObservationListAPI(Context context, ObservationListAPIListener resultOutput) {
        this.context = context;
        this.resultOutput = resultOutput;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..");
    }
    public void getAllObservation(JsonObject jsonObject) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<ProcessInspectionModel>> call = null;
            call = RetrofitClient.getInstance().getMyApi().getUserObservationAll(jsonObject);
            call.enqueue(new Callback<List<ProcessInspectionModel>>() {
                @Override
                public void onResponse(Call<List<ProcessInspectionModel>> call, Response<List<ProcessInspectionModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response.body() != null) {
                        List<ProcessInspectionModel> result = response.body();
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
                public void onFailure(Call<List<ProcessInspectionModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }

}
