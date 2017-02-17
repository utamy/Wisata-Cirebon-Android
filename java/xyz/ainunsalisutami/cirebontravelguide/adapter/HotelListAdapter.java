package xyz.ainunsalisutami.cirebontravelguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import xyz.ainunsalisutami.cirebontravelguide.R;
import xyz.ainunsalisutami.cirebontravelguide.model.Hotel;

/**
 * Created by Ainun on 10/05/2016.
 */
public class HotelListAdapter extends BaseAdapter {
    private ArrayList<Hotel> listData;
    private LayoutInflater layoutInflater;

    public HotelListAdapter(Context context, ArrayList<Hotel> listData) {
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
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
            holder.kategoriView = (TextView) convertView.findViewById(R.id.deskripsi);
            holder.alamatView = (TextView) convertView.findViewById(R.id.alamat);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Hotel newsItem = listData.get(position);
        holder.namaView.setText(newsItem.getNama());
        holder.kategoriView.setText(newsItem.getJenis_hotel());
        holder.alamatView.setText(newsItem.getAlamat());

        if (holder.imageView != null) {
            new ImageDownloaderTask(holder.imageView).execute(newsItem.getUrl());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView namaView;
        ImageView imageView;
        TextView kategoriView;
        TextView alamatView;
    }
}