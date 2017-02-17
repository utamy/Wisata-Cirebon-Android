package xyz.ainunsalisutami.cirebontravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailKulinerActivity extends AppCompatActivity {
    TextView nama;

    TextView deskripsi;

    ImageView kulinerImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kuliner);

        nama = (TextView)findViewById(R.id.nama);

        deskripsi = (TextView)findViewById(R.id.deskripsi);
        Intent i = getIntent();
        nama.setText(i.getStringExtra("nama"));
        deskripsi.setText(i.getStringExtra("deskripsi"));
        kulinerImage = (ImageView) findViewById(R.id.kulinerHotel);
        Glide.with(this)
                .load(i.getStringExtra("url"))
                .into(kulinerImage);


        Log.e("cobba",i.getStringExtra("url"));

    }

}
