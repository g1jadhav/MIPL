package mahyco.iqc.nxg.util;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.iqc.nxg.model.ActionModel;
import mahyco.iqc.nxg.model.CheckPoint;
import mahyco.iqc.nxg.model.ContractPlantModel;
import mahyco.iqc.nxg.model.CorrectionModel;
import mahyco.iqc.nxg.model.CropModel;
import mahyco.iqc.nxg.model.HybridModel;
import mahyco.iqc.nxg.model.IQCPlantModel;
import mahyco.iqc.nxg.model.InspectionModel;
import mahyco.iqc.nxg.model.NCTypeModel;
import mahyco.iqc.nxg.model.ObservationResponseModel;
import mahyco.iqc.nxg.model.OwnerModel;
import mahyco.iqc.nxg.model.ProcessInspectionModel;
import mahyco.iqc.nxg.model.ProcessModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface Api {

    @POST(Constants.CHECK_LOGIN)
    Call<String> checkLogin(@Body JsonObject jsonObject);

    @POST(Constants.GET_PENDINGACTION)
    Call<List<ActionModel>> getPendingActions (@Query("UserName") String username);

    @POST(Constants.GET_PENDINGACTION_USERCODE)
    Call<List<ActionModel>> getPendingActions_usercode (@Query("UserCode") String usercode);

    @POST(Constants.RESETDATA)
    Call<String> clearData(@Query("UserCode") String usercode);

    @POST(Constants.GETIQC_PLANT)
    Call<List<IQCPlantModel>> getIQCPlant(@Body JsonObject jsonObject);

    @POST(Constants.GETPROCESSLIST)
    Call<List<ProcessModel>> getProcessList(@Body JsonObject jsonObject_process);

    @POST(Constants.GETCROPLIST)
    Call<List<CropModel>> getCropList(@Body JsonObject jsonObject_crop);

    @POST(Constants.GETHYBRIDLIST)
    Call<List<HybridModel>> getHybridList(@Body JsonObject jsonObject_hybrid);

    @POST(Constants.GETCHECKPOINT)
    Call<List<CheckPoint>> getCheckPoint(@Body JsonObject jsonObject_checkPoint);

    @POST(Constants.GET_NC_TYPE)
    Call<List<NCTypeModel>> getNCType(@Body JsonObject jsonObject_nctype);

    @POST(Constants.GET_OWNER)
    Call<List<OwnerModel>> getOwnerList(@Body JsonObject jsonObject_owner);

    @POST(Constants.CREATE_OBSERVATION)
    Call<ObservationResponseModel> createObservation(@Body JsonObject jsonObject);

    @Multipart
    @POST("processInspection/uploadFile?FileType=Image")
    Call<String> uploadProductQualityImage(@Part MultipartBody.Part file, @Part("files") RequestBody items);

    @POST(Constants.GET_ALL_PROCESS_INSPECTION)
    Call<List<ProcessInspectionModel>> getUserObservationAll(@Body JsonObject jsonObject);

    @POST(Constants.GET_CONTRACTPLANT)
    Call<List<ContractPlantModel>> getGetContractPlantList(@Body JsonObject jsonObject);

    @POST(Constants.GET_ALL_USER_CORRECTIONS)
    Call<List<CorrectionModel>> getUserCorrectionAll(@Body JsonObject jsonObject);

    @POST(Constants.GET_INSPECTIONDETAILS)
    Call<InspectionModel> getInspectionDetails(@Body JsonObject jsonObject_correctionDetails);

    @POST(Constants.UPDATE_OBSERVATION)
    Call<ObservationResponseModel> updateObservation(@Body JsonObject jsonObject);
}
