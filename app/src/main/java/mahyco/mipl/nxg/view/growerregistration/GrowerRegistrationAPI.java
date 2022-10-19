  package mahyco.mipl.nxg.view.growerregistration;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.model.SuccessModel;
import mahyco.mipl.nxg.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrowerRegistrationAPI {


        Context context;
        String result = "";
        ProgressDialog progressDialog;
        Listener resultOutput;

        public GrowerRegistrationAPI(Context context, Listener resultOutput) {
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

    public void getCategoryByParent(JsonObject jsonObject, SearchableSpinner spinner)
    {
        try {
          /*  if (!progressDialog.isShowing())
                progressDialog.show();*/

            Call<List<CategoryChildModel>> call = null;
            call = RetrofitClient.getInstance().getMyApi().getCategoryByParent(jsonObject);
            call.enqueue(new Callback<List<CategoryChildModel>>() {
                @Override
                public void onResponse(Call<List<CategoryChildModel>> call, Response<List<CategoryChildModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<CategoryChildModel> result = response.body();
                        try {
                            resultOutput.loadChildSpinner(result,spinner);
                        } catch (NullPointerException e) {
                           // Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                           // Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CategoryChildModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }


    public void createGrower(JsonObject jsonObject)
    {
        try {
            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<SuccessModel> call = null;
            call = RetrofitClient.getInstance().getMyApi().submitGrowerDetails(jsonObject);
            call.enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        SuccessModel result = response.body();
                        try {
                            resultOutput.onGrowerRegister(result);
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



  /*      public void getPendingActions(JsonObject jsonObject)
        {
            *//*try {

                if (!progressDialog.isShowing())
                    progressDialog.show();

                Call<ActionModel> call = RetrofitClient.getInstance().getMyApi().getPendingActions("");
                call.enqueue(new Callback<ActionModel>() {
                    @Override
                    public void onResponse(Call<ActionModel> call, Response<ActionModel> response) {

                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                        if (response.body() != null) {
                            ActionModel result = response.body();
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
                    public void onFailure(Call<ActionModel> call, Throwable t) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Error is", t.getMessage());
                    }
                });
            } catch (Exception e) {

            }*//*
        }

    public void getProcessList(JsonObject jsonObject_process) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<ProcessModel>> call = null;
            call = RetrofitClient.getInstance().getMyApi().getProcessList(jsonObject_process);
            call.enqueue(new Callback<List<ProcessModel>>() {
                @Override
                public void onResponse(Call<List<ProcessModel>> call, Response<List<ProcessModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<ProcessModel> result = response.body();
                        try {
                            resultOutput.onListResponce_ProcessList(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ProcessModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
        }
    public void getCropList(JsonObject jsonObject_crop) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<CropModel>> call = null;

            call = RetrofitClient.getInstance().getMyApi().getCropList(jsonObject_crop);
            call.enqueue(new Callback<List<CropModel>>() {
                @Override
                public void onResponse(Call<List<CropModel>> call, Response<List<CropModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<CropModel> result = response.body();
                        try {

                            resultOutput.onListResponce_CropList(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CropModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
    public void getHybridList(JsonObject jsonObject_hybrid) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<HybridModel>> call = null;

            call = RetrofitClient.getInstance().getMyApi().getHybridList(jsonObject_hybrid);
            call.enqueue(new Callback<List<HybridModel>>() {
                @Override
                public void onResponse(Call<List<HybridModel>> call, Response<List<HybridModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<HybridModel> result = response.body();
                        try {
                            resultOutput.onListResponce_HybridList(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<HybridModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
    public void getCheckPointList(JsonObject jsonObject_CheckPoint) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<CheckPoint>> call = null;

            call = RetrofitClient.getInstance().getMyApi().getCheckPoint(jsonObject_CheckPoint);
            call.enqueue(new Callback<List<CheckPoint>>() {
                @Override
                public void onResponse(Call<List<CheckPoint>> call, Response<List<CheckPoint>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<CheckPoint> result = response.body();
                        try {
                            resultOutput.onListResponce_CheckPointList(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CheckPoint>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }

    public void getNCType(JsonObject jsonObject_nctype) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<NCTypeModel>> call = null;

            call = RetrofitClient.getInstance().getMyApi().getNCType(jsonObject_nctype);
            call.enqueue(new Callback<List<NCTypeModel>>() {
                @Override
                public void onResponse(Call<List<NCTypeModel>> call, Response<List<NCTypeModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<NCTypeModel> result = response.body();
                        try {
                            resultOutput.onListResponce_NCTypeList(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<NCTypeModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }

    public void getOwnerList(JsonObject jsonObject_owner) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<OwnerModel>> call = null;

            call = RetrofitClient.getInstance().getMyApi().getOwnerList(jsonObject_owner);
            call.enqueue(new Callback<List<OwnerModel>>() {
                @Override
                public void onResponse(Call<List<OwnerModel>> call, Response<List<OwnerModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<OwnerModel> result = response.body();
                        try {
                            resultOutput.onListResponce_OwnerList(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<OwnerModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }*/


   /* public void getContractPlantList(JsonObject jsonObject_crop) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<ContractPlantModel>> call = null;

            call = RetrofitClient.getInstance().getMyApi().getGetContractPlantList(jsonObject_crop);
            call.enqueue(new Callback<List<ContractPlantModel>>() {
                @Override
                public void onResponse(Call<List<ContractPlantModel>> call, Response<List<ContractPlantModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        List<ContractPlantModel> result = response.body();
                        try {
                            resultOutput.onListResponce_ContactPlant(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ContractPlantModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }*/
}
