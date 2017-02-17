package xyz.ainunsalisutami.cirebontravelguide;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Locale;

import butterknife.OnClick;
import xyz.ainunsalisutami.cirebontravelguide.model.Hotel;

public class DetailHotelActivity extends AppCompatActivity {
    TextView nama;
    TextView alamat;
    TextView fasilitas;
    TextView no_tlp;
    String nama_wisata;
    String alamat_wisata;
    String id;
    String latitude;
    String longitude;
    ImageView hotelImage;
    //ImageView gambar;
    //double latitude;
    //double longitude;
    //private Button b;
    /*@OnClick(R.id.btnMapHotel) void submitMapHotel() {

        startActivity(new Intent(getApplicationContext(), MapHotel.class));

    }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        nama = (TextView)findViewById(R.id.nama);
        alamat = (TextView)findViewById(R.id.alamat);
        no_tlp = (TextView)findViewById(R.id.notlp);
        fasilitas = (TextView)findViewById(R.id.deskripsi);
        //gambar = (ImageView)findViewById(R.id.gambar);

        //jenis_hotel = (TextView)findViewById(R.id.jenis_kuliner);
        Intent i = getIntent();
        nama.setText(i.getStringExtra("nama"));
        fasilitas.setText(i.getStringExtra("fasilitas"));
        alamat.setText(i.getStringExtra("alamat"));
        no_tlp.setText(i.getStringExtra("no_tlp"));
        latitude = i.getStringExtra("lat");
        longitude = i.getStringExtra("lng");
        hotelImage = (ImageView) findViewById(R.id.imageHotel);
        Glide.with(this)
                .load(i.getStringExtra("url"))
                .into(hotelImage);


        Log.e("cobba",i.getStringExtra("url"));

        //gambar.setImageResource("gambar");

        Button peta  = (Button)findViewById(R.id.btnMapHotel);
        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailHotelActivity.this, MapHotel.class);
                intent.putExtra("nama", nama.getText().toString());
                intent.putExtra("alamat", alamat.getText().toString());
                intent.putExtra("lat", latitude);
                intent.putExtra("lng", longitude);
                startActivity(intent);

            }
        });


    }


}
