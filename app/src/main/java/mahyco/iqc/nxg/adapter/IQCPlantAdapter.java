package mahyco.iqc.nxg.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mahyco.iqc.nxg.R;
import mahyco.iqc.nxg.model.ActionModel;
import mahyco.iqc.nxg.model.IQCPlantModel;


public class IQCPlantAdapter extends RecyclerView.Adapter<IQCPlantAdapter.DataObjectHolder> {

    Context context;

    private static final int UNSELECTED = -1;
EventListener eventListener;
    ArrayList<IQCPlantModel> actionModelArrayList = null;

    public interface EventListener {
        void onPlantSelected(String plantid);
    }

    public IQCPlantAdapter(ArrayList<IQCPlantModel> actionModels, Context context, EventListener eventListener) {

        this.actionModelArrayList = actionModels;
        Log.i("Action Count:", ">>" + actionModels.size());
        this.context = context;

        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pending_local_actions, parent, false);

        return new DataObjectHolder(view);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        //  if (mSellerProductlist.size() > 0) {
        return actionModelArrayList.size();
        //} else {
        //  return 0;
        // }
    }


    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        try {
            IQCPlantModel actionModel = actionModelArrayList.get(position);

            holder.btnGo.setText(actionModel.getPlantTitle());

            holder.btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListener.onPlantSelected(""+actionModel.getIQCPlantId());
                }
            });

        } catch (Exception e) {
            Log.i("Error ", e.getMessage());
        }
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder {


        LinearLayout ll;

        Button btnGo;

        public DataObjectHolder(View itemView) {
            super(itemView);


            btnGo = (Button) itemView.findViewById(R.id.btngo);
        }
    }


}
