package mahyco.mipl.nxg.util;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.model.CropModel;
import mahyco.mipl.nxg.model.CropTypeModel;
import mahyco.mipl.nxg.model.DownloadGrowerModel;
import mahyco.mipl.nxg.model.GetAllSeedDistributionModel;
import mahyco.mipl.nxg.model.OldGrowerSeedDistributionModel;
import mahyco.mipl.nxg.model.ProductCodeModel;
import mahyco.mipl.nxg.model.ProductionClusterModel;
import mahyco.mipl.nxg.model.SeasonModel;
import mahyco.mipl.nxg.model.SeedBatchNoModel;
import mahyco.mipl.nxg.model.SeedReceiptModel;
import mahyco.mipl.nxg.model.SuccessModel;
import mahyco.mipl.nxg.view.seeddistribution.ParentSeedDistributionParameter;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface Api {

    @POST(Constants.CHECK_LOGIN)
    Call<String> checkLogin(@Body JsonObject jsonObject);

    @POST(Constants.CREATEUSER)
    Call<String> createUser(@Body JsonObject jsonObject);


    @POST(Constants.GETCATEGORY)
    Call<List<CategoryModel>> getCategory(@Body JsonObject jsonObject);

    @POST(Constants.GET_LOCATION)
    Call<List<CategoryChildModel>> getLocation(@Body JsonObject jsonObject);

    @POST(Constants.GET_GROWER)
    Call<List<DownloadGrowerModel>> getGrower(@Body JsonObject jsonObject);

    @POST(Constants.GET_SEASON)
    Call<List<SeasonModel>> getSeason(@Body JsonObject jsonObject);

    @POST(Constants.GET_CROP)
    Call<List<CropModel>> getCrop(@Body JsonObject jsonObject);

    @POST(Constants.PRODUCTION_CLUSTER)
    Call<List<ProductionClusterModel>> getProductionCluster(@Body JsonObject jsonObject);

    @POST(Constants.PRODUCTION_CODE)
    Call<List<ProductCodeModel>> getProductCode(@Body JsonObject jsonObject);

    @POST(Constants.SEED_RECEIPT_BATCH)
    Call<List<SeedReceiptModel>> getSeedReceiptNo(@Body JsonObject jsonObject);

    @POST(Constants.MALE_FEMALE_BATCH)
    Call<List<SeedBatchNoModel>> getSeedBatchNo(@Body JsonObject jsonObject);

    @POST(Constants.CROP_TYPE)
    Call<List<CropTypeModel>> getCropType(@Body JsonObject jsonObject);

    @Multipart
    @POST("processInspection/uploadFile?FileType=Image")
    Call<String> uploadProductQualityImage(@Part MultipartBody.Part file, @Part("files") RequestBody items);

    @POST(Constants.GETCATEGORY_BY_PARENT)
    Call<List<CategoryChildModel>> getCategoryByParent(@Body JsonObject jsonObject);

    @POST(Constants.SUBMIT_GROWER)
    Call<SuccessModel> submitGrowerDetails(@Body JsonObject jsonObject);

    //@Body list: List<TourEventParamItem>
    @POST(Constants.CREATE_DISTRIBUTION)
    Call<SuccessModel> seedDistribution(@Body /*ArrayList<OldGrowerSeedDistributionModel>*/ParentSeedDistributionParameter jsonObject);

    @POST(Constants.PARENT_SEED_DISTRIBUTION_GET_ALL)
    Call<List<GetAllSeedDistributionModel>> getSeedDistributionList(@Body JsonObject jsonObject);
}
