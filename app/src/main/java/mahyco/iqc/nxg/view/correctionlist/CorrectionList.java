package mahyco.iqc.nxg.view.correctionlist;

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
import mahyco.iqc.nxg.adapter.CorrectionListAdapter;
import mahyco.iqc.nxg.adapter.ProcessInspectionAdapter;
import mahyco.iqc.nxg.util.Preferences;
import mahyco.iqc.nxg.view.observationlist.ObservationList;
import mahyco.iqc.nxg.view.observationlist.ObservationListAPI;

public class CorrectionList extends AppCompatActivity implements CorrectionListAPIListener {
    CorrectionListAPI correctionListAPI;
    RecyclerView rc_list;
    LinearLayoutManager mManager;
    Context context;
    CorrectionListAdapter actionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Correction List");
        context = CorrectionList.this;
        rc_list = findViewById(R.id.rc_observationlist);
        mManager = new LinearLayoutManager(context);
        rc_list.setLayoutManager(mManager);
        correctionListAPI = new CorrectionListAPI(context, this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID).toString().trim());
        jsonObject.addProperty("FilterOption", "UserCode");

        correctionListAPI.getAllObservation(jsonObject);
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List result) {
        if (result != null) {
            actionAdapter = new CorrectionListAdapter((ArrayList) result, context, this);
            rc_list.setAdapter(actionAdapter);
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