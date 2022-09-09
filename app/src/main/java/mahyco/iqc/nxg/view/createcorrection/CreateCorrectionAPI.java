package mahyco.iqc.nxg.view.createcorrection;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.iqc.nxg.model.CheckPoint;
import mahyco.iqc.nxg.model.ContractPlantModel;
import mahyco.iqc.nxg.model.CropModel;
import mahyco.iqc.nxg.model.HybridModel;
import mahyco.iqc.nxg.model.InspectionModel;
import mahyco.iqc.nxg.model.NCTypeModel;
import mahyco.iqc.nxg.model.ObservationResponseModel;
import mahyco.iqc.nxg.model.OwnerModel;
import mahyco.iqc.nxg.model.ProcessModel;
import mahyco.iqc.nxg.util.RetrofitClient;
import mahyco.iqc.nxg.view.investigation.CreateInvestigationListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCorrectionAPI {


        Context context;
        String result = "";
        ProgressDialog progressDialog;
        CreateCorrectionListener resultOutput;

        public CreateCorrectionAPI(Context context, CreateCorrectionListener resultOutput) {
            this.context = context;
            this.resultOutput = resultOutput;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait..");
        }
        public void getPendingActions(JsonObject jsonObject)
        {
            /*try {

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

            }*/
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
    }

    public void createObservation(JsonObject jsonObject) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<ObservationResponseModel> call = null;

            call = RetrofitClient.getInstance().getMyApi().createObservation(jsonObject);
            call.enqueue(new Callback<ObservationResponseModel>() {
                @Override
                public void onResponse(Call<ObservationResponseModel> call, Response<ObservationResponseModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        ObservationResponseModel result = response.body();
                        try {
                            resultOutput.onObservationResponse(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ObservationResponseModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }

    public void getContractPlantList(JsonObject jsonObject_crop) {
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
    }

    public void getInspectionDetails(JsonObject jsonObject_correctionDetails) {
            try{
        if (!progressDialog.isShowing())
            progressDialog.show();

        Call<InspectionModel> call = null;

        call = RetrofitClient.getInstance().getMyApi().getInspectionDetails(jsonObject_correctionDetails);
        call.enqueue(new Callback<InspectionModel>() {
            @Override
            public void onResponse(Call<InspectionModel> call, Response<InspectionModel> response) {

                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                if (response.body() != null) {
                    InspectionModel result = response.body();
                    try {
                        resultOutput.onGetInspectionDetails(result);
                    } catch (NullPointerException e) {
                        Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<InspectionModel> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Log.e("Error is", t.getMessage());
            }
        });
    } catch (Exception e) {

    }
    }

    public void updateObservation(JsonObject jsonObject) {
        try {


            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<ObservationResponseModel> call = null;

            call = RetrofitClient.getInstance().getMyApi().updateObservation(jsonObject);
            call.enqueue(new Callback<ObservationResponseModel>() {
                @Override
                public void onResponse(Call<ObservationResponseModel> call, Response<ObservationResponseModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        ObservationResponseModel result = response.body();
                        try {
                            resultOutput.onObservationResponse(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ObservationResponseModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
}
