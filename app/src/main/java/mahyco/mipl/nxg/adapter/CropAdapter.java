package mahyco.mipl.nxg.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CropModel;

public class CropAdapter extends ArrayAdapter<CropModel> {

    LayoutInflater flater;
    List<CropModel> list;

    public CropAdapter(Context context, int resouceId, List<CropModel> list) {
        super(context, resouceId, list);
        flater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowView(convertView, position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowView(convertView, position);
    }

    private View rowView(View convertView, int position) {

        CropModel rowItem = getItem(position);

        viewHolder holder;
        View rowViews = convertView;
        if (rowViews == null) {

            holder = new viewHolder();
            rowViews = flater.inflate(R.layout.spinner_rows, null, false);

            holder.txtTitle = rowViews.findViewById(R.id.sub);
            rowViews.setTag(holder);
        } else {
            holder = (viewHolder) rowViews.getTag();
        }
        holder.txtTitle.setText(rowItem.getCropName());

        return rowViews;
    }

    private class viewHolder {
        TextView txtTitle;
    }
}
