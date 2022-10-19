package mahyco.mipl.nxg;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.util.ULocale;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.util.RetrofitClient;
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
        public void getCategory(JsonObject jsonObject)
        {
            try {
                if (!progressDialog.isShowing())
                    progressDialog.show();

                Call<List<CategoryModel>> call = null;
                call = RetrofitClient.getInstance().getMyApi().getCategory(jsonObject);
                call.enqueue(new Callback<List<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {

                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                        if (response.body() != null) {
                            List<CategoryModel> result = response.body();
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
                    public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Error is", t.getMessage());
                    }
                });
            } catch (Exception e) {

            }
        }
        public void getLocation(JsonObject jsonObject)
    {
        try {
            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<CategoryModel>> call = null;
            call = RetrofitClient.getInstance().getMyApi().getCategory(jsonObject);
            call.enqueue(new Callback<List<CategoryModel>>() {
                @Override
                public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<CategoryModel> result = response.body();
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
                public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }

}
