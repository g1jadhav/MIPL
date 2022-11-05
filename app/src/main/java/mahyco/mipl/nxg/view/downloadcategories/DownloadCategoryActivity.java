package mahyco.mipl.nxg.view.downloadcategories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.model.CropModel;
import mahyco.mipl.nxg.model.CropTypeModel;
import mahyco.mipl.nxg.model.DownloadGrowerModel;
import mahyco.mipl.nxg.model.ProductCodeModel;
import mahyco.mipl.nxg.model.ProductionClusterModel;
import mahyco.mipl.nxg.model.SeasonModel;
import mahyco.mipl.nxg.model.SeedBatchNoModel;
import mahyco.mipl.nxg.model.SeedReceiptModel;
import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.util.SqlightDatabase;

public class DownloadCategoryActivity extends BaseActivity implements View.OnClickListener, DownloadCategoryListListener {

    private Context mContext;
    private CardView mCategoryMaster;
    private CardView mLocationMaster;
    private CardView mSeasonMaster;
    private CardView mGrowerMaster;
    private CardView mCropMaster;
    private CardView mProdClusterMaster;
    private CardView mProdCodeMaster;
    private CardView mSeedBatchMaster;
    private CardView mCropTypeMaster;
    private CardView mParentSeedReceiptMaster;

    private JsonObject mJsonObjectCategory;

    private DownloadCategoryApi mDownloadCategoryApi;

    private List<CategoryModel> mCategoryMasterList;
    private List<CategoryChildModel> mLocationMasterList;
    private List<SeasonModel> mSeasonMasterList;
    private List<DownloadGrowerModel> mGrowerMasterList;
    private List<CropModel> mCropMasterList;
    private List<ProductionClusterModel> mProductionClusterList;
    private List<ProductCodeModel> mProductCodeList;
    private List<SeedBatchNoModel> mSeedBatchNoList;
    private List<CropTypeModel> mCropTypeList;
    private List<SeedReceiptModel> mParentSeedReceiptList;

    private String mDatabaseName = "";
    final String LOCATION_MASTER_DATABASE = "LocationMaster";
    final String CATEGORY_MASTER_DATABASE = "CategoryMaster";
    final String SEASON_MASTER_DATABASE = "SeasonMaster";
    final String GROWER_MASTER_DATABASE = "GrowerMaster";
    final String CROP_MASTER_DATABASE = "CropMaster";
    final String CLUSTER_MASTER_DATABASE = "ClusterMaster";
    final String PROD_CODE_MASTER_DATABASE = "ProductCodeMaster";
    final String SEED_BATCH_NO_MASTER_DATABASE = "SeedBatchNoMaster";
    final String CROP_TYPE_MASTER_DATABASE = "CropTypeMaster";
    final String PARENT_SEED_RECEIPT_MASTER_DATABASE = "ParentSeedReceiptMaster";


    @Override
    protected int getLayout() {
        return R.layout.activity_download_categories;
    }

    @Override
    protected void init() {

        setTitle("Download");

        mContext = this;

        mCategoryMaster = findViewById(R.id.download_category_master_layout);
        mLocationMaster = findViewById(R.id.download_location_master_layout);
        mSeasonMaster = findViewById(R.id.download_season_master_layout);
        mGrowerMaster = findViewById(R.id.download_grower_master_layout);
        mCropMaster = findViewById(R.id.download_crop_master_layout);
        mProdClusterMaster = findViewById(R.id.download_prod_cluster_master_layout);
        mProdCodeMaster = findViewById(R.id.download_prod_code_master_layout);
        mSeedBatchMaster = findViewById(R.id.download_seed_batch_master_layout);
        mCropTypeMaster = findViewById(R.id.download_crop_type_master_layout);
        mParentSeedReceiptMaster = findViewById(R.id.download_parent_seed_receipt_master_layout);

        mCategoryMaster.setOnClickListener(this);
        mLocationMaster.setOnClickListener(this);
        mSeasonMaster.setOnClickListener(this);
        mGrowerMaster.setOnClickListener(this);
        mCropMaster.setOnClickListener(this);
        mProdClusterMaster.setOnClickListener(this);
        mProdCodeMaster.setOnClickListener(this);
        mSeedBatchMaster.setOnClickListener(this);
        mCropTypeMaster.setOnClickListener(this);
        mParentSeedReceiptMaster.setOnClickListener(this);

        mDownloadCategoryApi = new DownloadCategoryApi(mContext, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download_category_master_layout:
                downloadMasterData();
                break;
            case R.id.download_location_master_layout:
                downloadLocationData();
                break;
            case R.id.download_season_master_layout:
                downloadSeasonMasterData();
                break;
            case R.id.download_grower_master_layout:
                downloadGrowerMasterData();
                break;
            case R.id.download_crop_master_layout:
                downloadCropMasterData();
                break;
            case R.id.download_prod_cluster_master_layout:
                downloadProductionClusterMasterData();
                break;
            case R.id.download_prod_code_master_layout:
                downloadProductCodeMasterData();
                break;
            case R.id.download_seed_batch_master_layout:
                downloadSeedBatchNoMasterData();
                break;
            case R.id.download_crop_type_master_layout:
                downloadCropTypeMasterData();
                break;
            case R.id.download_parent_seed_receipt_master_layout:
                downloadParentSeedReceiptMasterData();
                break;
        }
    }


    @Override
    public void onResult(String result) {
    }

    @Override
    public void onListCategoryMasterResponse(List<CategoryModel> lst) {
        // Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mCategoryMasterList != null) {
            mCategoryMasterList.clear();
        }
        mCategoryMasterList = lst;
        mDatabaseName = "CategoryMaster";
        showNoInternetDialog(mContext, "Category Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListLocationResponse(List<CategoryChildModel> lst) {
        // Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mLocationMasterList != null) {
            mLocationMasterList.clear();
        }
        mLocationMasterList = lst;
        mDatabaseName = "LocationMaster";
        showNoInternetDialog(mContext, "Location Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override

    public void onListSeasonMasterResponse(List<SeasonModel> lst) {
        //  Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mSeasonMasterList != null) {
            mSeasonMasterList.clear();
        }
        mSeasonMasterList = lst;
        mDatabaseName = "SeasonMaster";
        showNoInternetDialog(mContext, "Season Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListGrowerResponse(List<DownloadGrowerModel> lst) {
        // Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mGrowerMasterList != null) {
            mGrowerMasterList.clear();
        }
        mGrowerMasterList = lst;
        mDatabaseName = "GrowerMaster";
        showNoInternetDialog(mContext, "Grower Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListCropResponse(List<CropModel> lst) {
        if (mCropMasterList != null) {
            mCropMasterList.clear();
        }
        mCropMasterList = lst;
        mDatabaseName = "CropMaster";
        showNoInternetDialog(mContext, "Crop Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListProductionClusterResponse(List<ProductionClusterModel> lst) {
        if (mProductionClusterList != null) {
            mProductionClusterList.clear();
        }
        mProductionClusterList = lst;
        mDatabaseName = "ClusterMaster";
        showNoInternetDialog(mContext, "Production Cluster Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListProductCodeResponse(List<ProductCodeModel> lst) {
        if (mProductCodeList != null) {
            mProductCodeList.clear();
        }
        mProductCodeList = lst;
        mDatabaseName = "ProductCodeMaster";
        showNoInternetDialog(mContext, "Product Code Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListSeedReceiptNoResponse(List<SeedReceiptModel> lst) {
        if (mParentSeedReceiptList != null) {
            mParentSeedReceiptList.clear();
        }
        mParentSeedReceiptList = lst;
        mDatabaseName = "ParentSeedReceiptMaster";
        showNoInternetDialog(mContext, "Parent Seed Receipt Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListSeedBatchNoResponse(List<SeedBatchNoModel> lst) {
        if (mSeedBatchNoList != null) {
            mSeedBatchNoList.clear();
        }
        mSeedBatchNoList = lst;
        mDatabaseName = "SeedBatchNoMaster";
        showNoInternetDialog(mContext, "Seed Batch No. Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListCropTypeResponse(List<CropTypeModel> lst) {
        if (mCropTypeList != null) {
            mCropTypeList.clear();
        }
        mCropTypeList = lst;
        mDatabaseName = "CropTypeMaster";
        showNoInternetDialog(mContext, "Crop Type Master Downloaded Successfully");
        new MasterAsyncTask().execute();
    }

    private void downloadLocationData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", Preferences.get(mContext, Preferences.COUNTRYNAME));
                mJsonObjectCategory.addProperty("FilterOption", "GetByCountryName");
                mDownloadCategoryApi.getLocation(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", Preferences.get(mContext, Preferences.COUNTRYNAME));
                mJsonObjectCategory.addProperty("FilterOption", "GetCountry");
                mDownloadCategoryApi.getCategory(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadGrowerMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", Preferences.get(mContext, Preferences.COUNTRYCODE));
                mJsonObjectCategory.addProperty("FilterOption", "CountryId");
                mDownloadCategoryApi.getGrower(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadSeasonMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", "1");
                mJsonObjectCategory.addProperty("FilterOption", "CountryId");
                mDownloadCategoryApi.getSeason(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadCropMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", "");
                mJsonObjectCategory.addProperty("FilterOption", "");
                mDownloadCategoryApi.getCrop(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadProductionClusterMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", "");
                mJsonObjectCategory.addProperty("FilterOption", "");
                mDownloadCategoryApi.getProductionCluster(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadProductCodeMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", "");
                mJsonObjectCategory.addProperty("FilterOption", "");
                mDownloadCategoryApi.getProductCode(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadSeedBatchNoMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", "");
                mJsonObjectCategory.addProperty("FilterOption", "");
                mDownloadCategoryApi.getSeedBatchNo(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadCropTypeMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", "");
                mJsonObjectCategory.addProperty("FilterOption", "");
                mDownloadCategoryApi.getCropType(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }

    private void downloadParentSeedReceiptMasterData() {
        if (checkInternetConnection(mContext)) {
            try {
                mJsonObjectCategory = null;
                mJsonObjectCategory = new JsonObject();
                mJsonObjectCategory.addProperty("filterValue", "");
                mJsonObjectCategory.addProperty("FilterOption", "");
                mDownloadCategoryApi.getSeedReceiptNo(mJsonObjectCategory);
            } catch (Exception e) {
            }
        } else {
            showNoInternetDialog(mContext, "Please check your internet connection");
        }
    }


    private class MasterAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                switch (mDatabaseName) {
                    case LOCATION_MASTER_DATABASE:
                        database.trucateTable("tbl_locationmaster");
                        Preferences.save(mContext, Preferences.COUNTRY_MASTER_ID,"");
                        for (CategoryChildModel param : mLocationMasterList) {
                            if (param.getParentId() == 0) {
                                Preferences.save(mContext, Preferences.COUNTRY_MASTER_ID, "" + param.getCountryMasterId());
                            }
                            database.addLocation(param);
                        }
                        break;
                    case CATEGORY_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_categorymaster");
                        Preferences.save(mContext, Preferences.STORED_CATEGORY_SIZE, "");
                        for (CategoryModel param : mCategoryMasterList) {
                            Preferences.save(mContext, Preferences.STORED_CATEGORY_SIZE, "" + mCategoryMasterList.size());
                            database.addCategory(param);
                        }
                        break;
                    case GROWER_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_growermaster");

                        DownloadGrowerModel downloadGrowerModel = new DownloadGrowerModel();
                        downloadGrowerModel.setFullName("Search by Name/Id");
                        downloadGrowerModel.setUniqueCode("");

                        mGrowerMasterList.add(0, downloadGrowerModel);

                        for (DownloadGrowerModel param : mGrowerMasterList) {
                            database.addGrower(param);
                        }
                        break;
                    case SEASON_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_seasonmaster");
                        for (SeasonModel param : mSeasonMasterList) {
                            database.addSeason(param);
                        }
                        break;
                    case CROP_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_cropmaster");
                        for (CropModel param : mCropMasterList) {
                            database.addCrop(param);
                        }
                        break;
                    case CLUSTER_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_clustermaster");
                        for (ProductionClusterModel param : mProductionClusterList) {
                            database.addProdCluster(param);
                        }
                        break;
                    case PROD_CODE_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_productcodemaster");
                        for (ProductCodeModel param : mProductCodeList) {
                            database.addProdCode(param);
                        }
                        break;
                    case SEED_BATCH_NO_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_seedbatchnomaster");
                        for (SeedBatchNoModel param : mSeedBatchNoList) {
                            database.addSeedBatchNo(param);
                        }
                        break;
                    case CROP_TYPE_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_croptypemaster");
                        for (CropTypeModel param : mCropTypeList) {
                            database.addCropType(param);
                        }
                        break;
                    case PARENT_SEED_RECEIPT_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_seedreciptmaster");
                        for (SeedReceiptModel param : mParentSeedReceiptList) {
                            database.addSeedReceipt(param);
                        }
                        break;
                }
            } finally {
                switch (mDatabaseName) {
                    case LOCATION_MASTER_DATABASE:
                        mLocationMasterList.clear();
                        break;
                    case CATEGORY_MASTER_DATABASE:
                        mCategoryMasterList.clear();
                        break;
                    case GROWER_MASTER_DATABASE:
                        mGrowerMasterList.clear();
                        break;
                    case SEASON_MASTER_DATABASE:
                        mSeasonMasterList.clear();
                        break;
                    case CROP_MASTER_DATABASE:
                        mCropMasterList.clear();
                        break;
                    case CLUSTER_MASTER_DATABASE:
                        mProductionClusterList.clear();
                        break;
                    case PROD_CODE_MASTER_DATABASE:
                        mProductCodeList.clear();
                        break;
                    case SEED_BATCH_NO_MASTER_DATABASE:
                        mSeedBatchNoList.clear();
                        break;
                    case CROP_TYPE_MASTER_DATABASE:
                        mCropTypeList.clear();
                        break;
                    case PARENT_SEED_RECEIPT_MASTER_DATABASE:
                        mParentSeedReceiptList.clear();
                        break;
                }
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }

        /*@Override
        protected void onPostExecute(Void unused) {
            new GetCategoriesAsyncTask().execute();
            super.onPostExecute(unused);
        }*/
    }

    /*private class GetCategoriesAsyncTask extends AsyncTask<Void, Void, ArrayList<SeedReceiptModel>> {
        @Override
        protected final ArrayList<SeedReceiptModel> doInBackground(Void... voids) {
            SqlightDatabase database = null;
            ArrayList<SeedReceiptModel> actionModels;
            try {
                database = new SqlightDatabase(mContext);
                actionModels = database.getSeedReceiptMaster();
            } finally {
                if (database != null) {
                    database.close();
                }
            }
            return actionModels;
        }

        @Override
        protected void onPostExecute(ArrayList<SeedReceiptModel> result) {
            Log.e("temporary"," result "+result);
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    Log.e("temporary"," getCropType "+result.get(i).getProductionCode()+
                            " getCropTypeId "+result.get(i).getCropName());
                }
            }
            super.onPostExecute(result);
        }
    }*/

    @Override
    protected void onDestroy() {
        hideKeyboard(mContext);
        dismissNoInternetDialog();
        super.onDestroy();
    }
}
