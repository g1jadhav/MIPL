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


public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.DataObjectHolder> {

    Context context;

    private static final int UNSELECTED = -1;
EventListener eventListener;
    ArrayList<ActionModel> actionModelArrayList = null;

    public interface EventListener {
        void onResetData(String usercode);
    }

    public ActionAdapter(ArrayList<ActionModel> actionModels, Context context, EventListener eventListener) {

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
            ActionModel actionModel = actionModelArrayList.get(position);

            holder.tvusername.setText(actionModel.getName());
            holder.tvusermobile.setText(actionModel.getMobile());
                    holder.tvusercode.setText(actionModel.getUser_Code());



            holder.btnDownloadPA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListener.onResetData(actionModel.getUser_Code());
                }
            });

        } catch (Exception e) {
            Log.i("Error ", e.getMessage());
        }
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvusername,
                tvusermobile,
                tvusercode
              ;


        LinearLayout ll;

        Button btnDownloadPA;

        public DataObjectHolder(View itemView) {
            super(itemView);

         /*   tvusercode = (TextView) itemView.findViewById(R.id.tv_usercode);
            tvusername = (TextView) itemView.findViewById(R.id.tv_username);
            tvusermobile = (TextView) itemView.findViewById(R.id.tv_mobile);


            btnDownloadPA = (Button) itemView.findViewById(R.id.btnDownloadPA);*/
        }
    }


}
