package com.example.dstest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ImageItem[] data;
    private SizeController sizeController;

    public ImageAdapter(Context context, int layoutResourceId, ImageItem[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

        sizeController = SizeController.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            ImageView image = holder.image = (ImageView) row.findViewById(R.id.image);
            ViewGroup.LayoutParams lp = image.getLayoutParams();
            lp.width = sizeController.size;
            lp.height = sizeController.size;
            image.setLayoutParams(lp);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = data[position];
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());
        return row;
    }

    public ImageItem getItemAtPosition (int position) {
        return data[position];
    }



    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}

