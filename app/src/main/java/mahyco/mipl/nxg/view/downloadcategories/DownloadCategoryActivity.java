package mahyco.mipl.nxg.view.downloadcategories;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.mipl.nxg.model.SeasonModel;
import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.util.SqlightDatabase;

public class DownloadCategoryActivity extends BaseActivity implements View.OnClickListener, DownloadCategoryListListener {

    private Context mContext;
    private CardView mCategoryMaster;
    private CardView mLocationMaster;
    private CardView mSeasonMaster;
    private CardView mGrowerMaster;

    private JsonObject mJsonObjectCategory;

    private DownloadCategoryApi mDownloadCategoryApi;

    private List<CategoryModel> mCategoryMasterList;
    private List<CategoryChildModel> mLocationMasterList;
    private List<SeasonModel> mSeasonMasterList;
    private List<CategoryModel> mGrowerMasterList;

    private String mDatabaseName = "";
    final String LOCATION_MASTER_DATABASE = "LocationMaster";
    final String CATEGORY_MASTER_DATABASE = "CategoryMaster";
    final String SEASON_MASTER_DATABASE = "SeasonMaster";
    final String GROWER_MASTER_DATABASE = "GrowerMaster";


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

        mCategoryMaster.setOnClickListener(this);
        mLocationMaster.setOnClickListener(this);
        mSeasonMaster.setOnClickListener(this);
        mGrowerMaster.setOnClickListener(this);

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
        }
    }

    @Override
    public void onResult(String result) {
    }

    @Override
    public void onListCategoryMasterResponse(List<CategoryModel> lst) {
        Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mCategoryMasterList != null) {
            mCategoryMasterList.clear();
        }
        mCategoryMasterList = lst;
        mDatabaseName = "CategoryMaster";
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListLocationResponse(List<CategoryChildModel> lst) {
        Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mLocationMasterList != null) {
            mLocationMasterList.clear();
        }
        mLocationMasterList = lst;
        mDatabaseName = "LocationMaster";
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListSeasonMasterResponse(List<SeasonModel> lst) {
        Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mSeasonMasterList != null) {
            mSeasonMasterList.clear();
        }
        mSeasonMasterList = lst;
        mDatabaseName = "SeasonMaster";
        new MasterAsyncTask().execute();
    }

    @Override
    public void onListGrowerResponse(List<CategoryModel> lst) {
        Toast.makeText(mContext, "" + lst.size(), Toast.LENGTH_SHORT).show();
        if (mGrowerMasterList != null) {
            mGrowerMasterList.clear();
        }
        mGrowerMasterList = lst;
        mDatabaseName = "GrowerMaster";
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
                mJsonObjectCategory.addProperty("filterValue", "");
                mJsonObjectCategory.addProperty("FilterOption", "GetCountry");
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

    private class MasterAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected final Void doInBackground(Void... voids) {
            SqlightDatabase database = null;
            try {
                database = new SqlightDatabase(mContext);
                switch (mDatabaseName) {
                    case LOCATION_MASTER_DATABASE:
                        database.trucateTable("tbl_SeasonMaster");
                        for (SeasonModel param : mSeasonMasterList) {
                          /*  if (param.getParentId() == 0) {
                                Preferences.save(mContext, Preferences.COUNTRY_MASTER_ID, "" + param.getCountryMasterId());
                            }*/
                            database.addSeason(param);
                        }
                        break;
                    case CATEGORY_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_categorymaster");
                        for (CategoryModel param : mCategoryMasterList) {
                            Preferences.save(mContext, Preferences.STORED_CATEGORY_SIZE, "" + mCategoryMasterList.size());
                            database.addCategory(param);
                        }
                        break;
                    case GROWER_MASTER_DATABASE:
                        database = new SqlightDatabase(mContext);
                        database.trucateTable("tbl_growermastermaster");
                        for (CategoryModel param : mCategoryMasterList) {
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
                }
                if (database != null) {
                    database.close();
                }
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        hideKeyboard(mContext);
        dismissNoInternetDialog();
        super.onDestroy();
    }
}
