package mahyco.mipl.nxg;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.util.SqlightDatabase;
import mahyco.mipl.nxg.view.downloadcategories.DownloadCategoryActivity;
import mahyco.mipl.nxg.view.growerregistration.NewGrowerRegistration;
import mahyco.mipl.nxg.view.login.Login;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, MainActivityListListener {
    RelativeLayout rl_fragment_container;
    MainActivityAPI mainActivityAPI;
    Context context;
    RecyclerView rc_pendingaction;
    LinearLayoutManager mManager;
    EditText editText_search;
    int type = 1;
    TextView txtlbl;
    RadioGroup radioGroup;
    ImageButton btn_createInvastigation;
    RecyclerView rc_viewiqcplant;
    JsonObject jsonObject;
    String plantid, roleid;

    CardView card_downlaodMaster, card_upload_data_layout, card_grower, card_organizer;
    JsonObject jsonObject_Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("MIPL");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        context = MainActivity.this;
        mainActivityAPI = new MainActivityAPI(context, this);
        init();
    }

    public void init() {
        rc_viewiqcplant = findViewById(R.id.rc_viewiqcplant);
        mManager = new LinearLayoutManager(context);
        rc_viewiqcplant.setLayoutManager(mManager);
        txtlbl = findViewById(R.id.txt_lbl);

        if (Preferences.get(context, Preferences.USER_NAME) != null)
            txtlbl.setText(Html.fromHtml("<b>Welcome - </b> <b style='color:#fc5a03;'>" + Preferences.get(context, Preferences.USER_NAME) + "</b>"));
        card_downlaodMaster = (CardView) findViewById(R.id.downloadmaster);

        card_upload_data_layout = (CardView) findViewById(R.id.upload_data_layout);

        card_grower = (CardView) findViewById(R.id.new_grower_registration_layout);
        card_organizer = (CardView) findViewById(R.id.new_organizer_registration_layout);


        card_downlaodMaster.setOnClickListener(this);
        card_upload_data_layout.setOnClickListener(this);
        card_grower.setOnClickListener(this);
        card_organizer.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_app:
                Toast.makeText(this, "We are working on it.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Preferences.save(MainActivity.this, Preferences.USER_ID, "");
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.nav_myprofile:

                Toast.makeText(this, "We are working on it.", Toast.LENGTH_SHORT).show();

                break;


        }


        return false;
    }

    private boolean isWriteExtStorageAllow() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

            //   requestStoragePermission();
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
            //  Toast.makeText(this, "TRue", Toast.LENGTH_SHORT).show();
            // requestStoragePermission();
        }


        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (isWriteExtStorageAllow()) {

        } else {
            requestStoragePermission();
        }
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List<CategoryModel> lst) {
        Toast.makeText(context, "" + lst.size(), Toast.LENGTH_SHORT).show();
        try {
            SqlightDatabase database = new SqlightDatabase(context);
            database.trucateTable("tbl_categorymaster");
            for (CategoryModel categoryModel : lst)
                database.addCategory(categoryModel);

        } catch (Exception e) {

        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.downloadmaster: {
                // downloadMasterData();
                Intent intent = new Intent(context, DownloadCategoryActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.upload_data_layout:
                downloadLocationData();
                break;

            case R.id.new_grower_registration_layout: {
                if (!checkCategoryDataDownloaded()) {
                    Toast.makeText(context, "Please download category master data in download master data first", Toast.LENGTH_SHORT).show();
                } else if (!checkLocationMasterDataDownloaded()) {
                    Toast.makeText(context, "Please download location master data in download master data first", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, NewGrowerRegistration.class);
                    intent.putExtra("title", "Grower");
                    startActivity(intent);
                }
                break;
            }
            case R.id.new_organizer_registration_layout:
                if (!checkCategoryDataDownloaded()) {
                    Toast.makeText(context, "Please download category master data in download master data first", Toast.LENGTH_SHORT).show();
                } else if (!checkLocationMasterDataDownloaded()) {
                    Toast.makeText(context, "Please download location master data in download master data first", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, NewGrowerRegistration.class);
                    intent.putExtra("title", "Organizer");
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean checkLocationMasterDataDownloaded() {
        return !Preferences.get(context, Preferences.COUNTRY_MASTER_ID).equalsIgnoreCase("");
    }

    private boolean checkCategoryDataDownloaded() {
        return !Preferences.get(context, Preferences.STORED_CATEGORY_SIZE).equalsIgnoreCase("");
    }

    private void downloadLocationData() {
        try {
            jsonObject_Category = new JsonObject();
            jsonObject_Category.addProperty("filterValue", "1");
            jsonObject_Category.addProperty("FilterOption", "CategoryId");
            mainActivityAPI.getCategory(jsonObject_Category);


        } catch (Exception e) {

        }
    }

    private void downloadMasterData() {
        try {
            jsonObject_Category = new JsonObject();
            jsonObject_Category.addProperty("filterValue", "Malawi");
            jsonObject_Category.addProperty("FilterOption", "GetCountry");
            mainActivityAPI.getCategory(jsonObject_Category);


        } catch (Exception e) {

        }
    }

}