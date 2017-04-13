package com.example.chemm.youtubeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DrawerAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> listResult;

    public DrawerAdapter(Context context, ArrayList<String> listResult) {
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listResult = listResult;
    }

    @Override
    public int getCount() {
        return listResult.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.drawer_item, parent, false);
            holder = new DrawerHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.drawer_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (DrawerHolder) convertView.getTag();
        }

        holder.textView1.setText(listResult.get(position));

        return convertView;
    }
}

class DrawerHolder {
    TextView textView1;
    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
}