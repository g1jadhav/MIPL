package mahyco.iqc.nxg.adapter;

import android.content.Context;
import android.content.Intent;
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
import mahyco.iqc.nxg.model.CorrectionModel;
import mahyco.iqc.nxg.model.ProcessInspectionModel;
import mahyco.iqc.nxg.util.Preferences;
import mahyco.iqc.nxg.view.correctionlist.CorrectionListAPIListener;
import mahyco.iqc.nxg.view.createcorrection.CreateCorrection;
import mahyco.iqc.nxg.view.observationlist.ObservationListAPIListener;
import mahyco.iqc.nxg.view.rca_capa.AddCapaActivity;


public class CorrectionListAdapter extends RecyclerView.Adapter<CorrectionListAdapter.DataObjectHolder> {

    Context context;
    private static final int UNSELECTED = -1;
    CorrectionListAPIListener eventListener;
    ArrayList<CorrectionModel> actionModelArrayList = null;

    public CorrectionListAdapter(ArrayList<CorrectionModel> actionModels, Context context, CorrectionListAPIListener eventListener) {

        this.actionModelArrayList = actionModels;
        Log.i("Action Count:", ">>" + actionModels.size());
        this.context = context;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_correction_model, parent, false);

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
            CorrectionModel actionModel = actionModelArrayList.get(position);

            holder.txt_planttitle.setText(actionModel.getPlantTitle());
            holder.txt_processtitle.setText(actionModel.getProcessTitle());
            holder.txt_remark.setText(actionModel.getObservation_Correction());
            holder.txt_hybridtitle.setText(actionModel.getHybridTitle());
            holder.txt_croptitle.setText(actionModel.getCropTitle());
            holder.btn_addcapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Preferences.save(context,Preferences.PROCESSINSPECTIONID,""+actionModel.getPlantInspectionId());
                    Preferences.save(context,Preferences.CORRECTIONTEXT,""+actionModel.getObservation_Correction());
                    Intent intent=new Intent(context, CreateCorrection.class);

                    intent.putExtra("type","Edit");
                    intent.putExtra("InspectionId",""+actionModel.getPlantInspectionId());

                    context.startActivity(intent);
                }
            });


        } catch (Exception e) {
            Log.i("Error ", e.getMessage());
        }
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder {


        LinearLayout ll;


        TextView txt_planttitle,txt_processtitle,txt_remark,txt_hybridtitle,txt_croptitle;
         Button btn_addcapa;
        public DataObjectHolder(View itemView) {
            super(itemView);
            txt_planttitle=(TextView) itemView.findViewById(R.id.txt_planttitle);
                    txt_processtitle=(TextView) itemView.findViewById(R.id.txt_processtitle);
            txt_remark=(TextView) itemView.findViewById(R.id.txt_remark);
                    txt_hybridtitle=(TextView) itemView.findViewById(R.id.txt_hybridtitle);
            txt_croptitle=(TextView) itemView.findViewById(R.id.txt_croptitle);
            btn_addcapa=(Button) itemView.findViewById(R.id.btn_addcapa);

        }
    }


}
