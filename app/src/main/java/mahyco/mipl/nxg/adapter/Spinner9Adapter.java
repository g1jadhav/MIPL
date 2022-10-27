package mahyco.mipl.nxg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mahyco.mipl.nxg.R;
import mahyco.mipl.nxg.model.CategoryChildModel;

public class Spinner9Adapter extends ArrayAdapter<CategoryChildModel> {

    LayoutInflater flater;
    List<CategoryChildModel> list;

    public Spinner9Adapter(Context context, int resouceId, List<CategoryChildModel> list) {
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

        CategoryChildModel rowItem = getItem(position);

        Spinner9Adapter.viewHolder holder;
        View rowViews = convertView;
        if (rowViews == null) {

            holder = new Spinner9Adapter.viewHolder();
            rowViews = flater.inflate(R.layout.spinner_rows, null, false);

            holder.txtTitle = rowViews.findViewById(R.id.sub);
            rowViews.setTag(holder);
        } else {
            holder = (Spinner9Adapter.viewHolder) rowViews.getTag();
        }
        holder.txtTitle.setText(rowItem.getKeyValue());

        return rowViews;
    }

    private class viewHolder {
        TextView txtTitle;
    }
}
