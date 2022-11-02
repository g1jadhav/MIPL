package mahyco.mipl.nxg.util;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.model.SeasonModel;
import mahyco.mipl.nxg.model.SuccessModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<List<CategoryModel>> getGrower(@Body JsonObject jsonObject);

    @POST(Constants.GET_SEASON)
    Call<List<SeasonModel>> getSeason(@Body JsonObject jsonObject);

    @Multipart
    @POST("processInspection/uploadFile?FileType=Image")
    Call<String> uploadProductQualityImage(@Part MultipartBody.Part file, @Part("files") RequestBody items);

    @POST(Constants.GETCATEGORY_BY_PARENT)
    Call<List<CategoryChildModel>> getCategoryByParent(@Body JsonObject jsonObject);

    @POST(Constants.SUBMIT_GROWER)
    Call<SuccessModel> submitGrowerDetails(@Body JsonObject jsonObject);
}
