package mahyco.iqc.nxg.view.actionlist;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonObject;

public class PendingActionListAPI {


        Context context;
        String result = "";
        ProgressDialog progressDialog;
        PendingActionListListener resultOutput;

        public PendingActionListAPI(Context context, PendingActionListListener resultOutput) {
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

}
