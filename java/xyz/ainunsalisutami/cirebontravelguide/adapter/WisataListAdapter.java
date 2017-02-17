package xyz.ainunsalisutami.cirebontravelguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.ainunsalisutami.cirebontravelguide.R;
import xyz.ainunsalisutami.cirebontravelguide.model.Wisata;

import java.util.ArrayList;

public class WisataListAdapter extends BaseAdapter {
    private ArrayList<Wisata> listData;
    private LayoutInflater layoutInflater;

    public WisataListAdapter(Context context, ArrayList<Wisata> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.namaView = (TextView) convertView.findViewById(R.id.nama);
            holder.alamatView = (TextView) convertView.findViewById(R.id.alamat);
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
            holder.jenisView = (TextView) convertView.findViewById(R.id.deskripsi);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Wisata newsItem = listData.get(position);
        holder.namaView.setText(newsItem.getNama());
        holder.alamatView.setText(newsItem.getAlamat());
        holder.jenisView.setText(newsItem.getJenis_wisata());

        if (holder.imageView != null) {
            new ImageDownloaderTask(holder.imageView).execute(newsItem.getUrl());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView namaView;
        TextView alamatView;
        ImageView imageView;
        TextView jenisView;
    }
}