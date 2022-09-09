package mahyco.iqc.nxg.view.actionlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahyco.iqc.nxg.R;
import mahyco.iqc.nxg.adapter.ActionAdapter;
import mahyco.iqc.nxg.model.ActionModel;
import mahyco.iqc.nxg.util.Preferences;
import mahyco.iqc.nxg.util.SqlightDatabase;

public class PendingActionList extends AppCompatActivity implements ActionAdapter.EventListener  {
    Context context;

    List<ActionModel> lst_actionModels;
    SqlightDatabase sqlightDatabase;
    RecyclerView rc_pendingaction;
    LinearLayoutManager mManager;
    ActionAdapter actionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_action_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context= PendingActionList.this;

        rc_pendingaction=findViewById(R.id.rc_pendingaction);
        mManager = new LinearLayoutManager(context);
        rc_pendingaction.setLayoutManager(mManager);
        int pendingfor=Integer.parseInt(Preferences.get(context,Preferences.PENDINGFOR_LOCALLIST));


    }
    public void laodLocalAction(List<ActionModel> lst_actionModels)
    {
        try{

            Toast.makeText(context, ""+lst_actionModels.size(), Toast.LENGTH_SHORT).show();
            actionAdapter = new ActionAdapter((ArrayList) lst_actionModels, context,this);
            rc_pendingaction.setAdapter(actionAdapter);

        }catch (Exception e)
        {

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





    @Override
    public void onResetData(String usercode) {

    }
}