package mahyco.iqc.nxg.view.observationlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.iqc.nxg.R;
import mahyco.iqc.nxg.adapter.ActionAdapter;
import mahyco.iqc.nxg.adapter.ProcessInspectionAdapter;
import mahyco.iqc.nxg.util.Preferences;

public class ObservationList extends AppCompatActivity implements ObservationListAPIListener {
    ObservationListAPI observationListAPI;
    RecyclerView rc_observationlist;
    LinearLayoutManager mManager;
    Context context;
    ProcessInspectionAdapter actionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("My Observations");
        context = ObservationList.this;
        rc_observationlist = findViewById(R.id.rc_observationlist);
        mManager = new LinearLayoutManager(context);
        rc_observationlist.setLayoutManager(mManager);
        observationListAPI = new ObservationListAPI(context, this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID).toString().trim());
        jsonObject.addProperty("FilterOption", "UserCode");

        observationListAPI.getAllObservation(jsonObject);
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List result) {
        if (result != null) {
            actionAdapter = new ProcessInspectionAdapter((ArrayList) result, context, this);
            rc_observationlist.setAdapter(actionAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // app icon in action bar clicked; goto parent activity.
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}