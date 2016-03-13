package procalc.procalc;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by section11 on 13/03/16.
 */
public class GridAdapter  extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Uri> data = new ArrayList<Uri>();

    public GridAdapter(Context context, int layoutResourceId, ArrayList<Uri> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = new ArrayList<Uri>(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.gridViewImage);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.image.setImageURI(data.get(position));

        //Picasso.with(context).load(item.getUrl()).into(holder.image);

        return row;
    }

    static class ViewHolder {
        ImageView image;
    }
}