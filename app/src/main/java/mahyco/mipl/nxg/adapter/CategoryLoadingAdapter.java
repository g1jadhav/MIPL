package mahyco.mipl.nxg.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.view.growerregistration.Listener;

public class CategoryLoadingAdapter extends RecyclerView.Adapter<CategoryLoadingAdapter.DataObjectHolder> {
    Context context;

    private static final int UNSELECTED = -1;
    Listener eventListener;
    ArrayList<CategoryModel> actionModelArrayList = null;
    static int cnt=0;


    public CategoryLoadingAdapter(ArrayList<CategoryModel> actionModels, Context context, Listener eventListener) {

        this.actionModelArrayList = actionModels;
        Log.i("Action Count:", ">>" + actionModels.size());
        this.context = context;

        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_model, parent, false);

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
            CategoryModel actionModel = actionModelArrayList.get(position);
            holder.txt_title.setText(actionModel.getDisplayTitle());
            holder.sp_list.setId(actionModel.getPosition());


            holder.sp_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(context, ""+holder.sp_list.getId(), Toast.LENGTH_SHORT).show();

                     if(cnt==0) {
                         cnt=1;
                         eventListener.onSpinnerClick(holder.sp_list.getId(), "" + actionModel.getCategoryId());
                     }
                         else
                         eventListener.onSpinnerClick(actionModel.getPosition()+1,""+actionModel.getCategoryId());



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (Exception e) {
            Log.i("Error ", e.getMessage());
        }
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView txt_title;
        SearchableSpinner sp_list;


        LinearLayout ll;

        Button btnDownloadPA;

        public DataObjectHolder(View itemView) {
            super(itemView);


            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            sp_list = (SearchableSpinner) itemView.findViewById(R.id.sp_list);

        }
    }

}
