package mahyco.iqc.nxg;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.iqc.nxg.adapter.ActionAdapter;
import mahyco.iqc.nxg.adapter.IQCPlantAdapter;
import mahyco.iqc.nxg.model.ActionModel;
import mahyco.iqc.nxg.util.Preferences;
import mahyco.iqc.nxg.view.correctionlist.CorrectionList;
import mahyco.iqc.nxg.view.createcorrection.CreateCorrection;
import mahyco.iqc.nxg.view.investigation.CreateInvestigation;
import mahyco.iqc.nxg.view.login.Login;
import mahyco.iqc.nxg.view.observationlist.ObservationList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,MainActivityListListener, IQCPlantAdapter.EventListener {
    RelativeLayout rl_fragment_container;
    MainActivityAPI mainActivityAPI;
    Context context;
    RecyclerView rc_pendingaction;
    LinearLayoutManager mManager;
    IQCPlantAdapter actionAdapter;
    EditText editText_search;
    int type=1;
    TextView txtlbl;
    RadioGroup radioGroup;
    ImageButton btn_createInvastigation;
    RecyclerView rc_viewiqcplant;
    JsonObject jsonObject;
    String plantid,roleid;
    Button button_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("IQC");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        context=MainActivity.this;
        mainActivityAPI=new MainActivityAPI(context,this);
        init();
    }

    public void init() {
        rc_viewiqcplant=findViewById(R.id.rc_viewiqcplant);
        mManager = new LinearLayoutManager(context);
        rc_viewiqcplant.setLayoutManager(mManager);
        txtlbl=findViewById(R.id.txt_lbl);
        button_list=findViewById(R.id.button);
        if(Preferences.get(context,Preferences.USER_NAME)!=null)
        txtlbl.setText(Html.fromHtml("<b>Welcome - </b>"+Preferences.get(context,Preferences.USER_NAME)));
        plantid=Preferences.get(context,Preferences.IQCPlantId);

        jsonObject=new JsonObject();
       jsonObject.addProperty("filterValue",plantid);
    //    jsonObject.addProperty("filterValue",1);
        jsonObject.addProperty("FilterOption","IQCPlantId");
        mainActivityAPI.getIqcPlant(jsonObject);
        roleid=Preferences.get(context,Preferences.ROLE_ID);
      //  roleid="3";
        if(roleid.equals("2"))
        {
            button_list.setText("See Correction");
        }
        else if(roleid.equals("3"))
        {
            button_list.setText("My Observation");
        }

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
    public void onListResponce(List lst_actionModels) {
        //Toast.makeText(context, ""+result.size(), Toast.LENGTH_SHORT).show();
        actionAdapter = new IQCPlantAdapter((ArrayList) lst_actionModels, context,this);
        rc_viewiqcplant.setAdapter(actionAdapter);

    }

    @Override
    public void onListResponce(ActionModel result) {


    }

    @Override
    public void onDataReset(String result) {
        try{
            JSONArray jsonArray=new JSONArray(result.trim());
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            Toast.makeText(context, ""+jsonObject.getString("OpMessage"), Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onPlantSelected(String plantcode) {
        Preferences.save(context,Preferences.SELECTEIQCPLANT,plantcode);

      //  roleid=Preferences.get(context,Preferences.ROLE_ID);
        if(roleid.equals("2")) // End User Role
        {
            Intent intent=new Intent(context, CreateCorrection.class);
            intent.putExtra("type","New");
            intent.putExtra("InspectionId","0");
            startActivity(intent);
        }
        else if(roleid.equals("3")) // IQC Manager Role
        {
            Intent intent=new Intent(context,CreateInvestigation.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(context, "Sorry, Only IQC Manager and End User can proceed !", Toast.LENGTH_SHORT).show();
        }
    }
    public void  showlist(View v)
    {


        if(roleid.equals("2"))
        {
            Intent intent=new Intent(context, CorrectionList.class);
            startActivity(intent);
        }
        else if(roleid.equals("3"))
        {
            Intent intent=new Intent(context, ObservationList.class);
            startActivity(intent);
        }

    }
}