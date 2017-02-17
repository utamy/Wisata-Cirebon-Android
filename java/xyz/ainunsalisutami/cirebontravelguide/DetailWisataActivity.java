package xyz.ainunsalisutami.cirebontravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import xyz.ainunsalisutami.cirebontravelguide.model.Wisata;

public class DetailWisataActivity extends AppCompatActivity {
//public static String KEY_ITEM = "item";
TextView nama;
    TextView alamat_wisata;
    TextView deskripsi;
    TextView jenis_wisata;
    ImageView gambar_wisata;
    String latitude;
    String longitude;
    ImageView wisataImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        nama = (TextView)findViewById(R.id.nama_wisata);
        alamat_wisata = (TextView)findViewById(R.id.alamat_wisata);
        deskripsi = (TextView)findViewById(R.id.deskripsi);
        //jenis_wisata = (TextView)findViewById(R.id.jenis_wisata);
        //gambar_wisata = (ImageView)findViewById(R.id.gambar);
        Intent i = getIntent();
        //gambar_wisata.setText(i.getStringExtra("nama"));
        nama.setText(i.getStringExtra("nama"));
        alamat_wisata.setText(i.getStringExtra("alamat_wisata"));
        deskripsi.setText(i.getStringExtra("deskripsi"));
        //jenis_wisata.setText(i.getStringExtra("jenis_wisata"));
        latitude = i.getStringExtra("lat");
        longitude = i.getStringExtra("lng");
        wisataImage = (ImageView) findViewById(R.id.imageWisata);
        Glide.with(this)
                .load(i.getStringExtra("gambar_wisata"))
                .into(wisataImage);


        Log.e("cobba",i.getStringExtra("gambar_wisata"));

        Button peta  = (Button)findViewById(R.id.btnMapWisata);
        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailWisataActivity.this, MapWisata.class);
                intent.putExtra("nama", nama.getText().toString());
                intent.putExtra("alamat", alamat_wisata.getText().toString());
                intent.putExtra("lat", latitude);
                intent.putExtra("lng", longitude);
                startActivity(intent);

            }
        });
    }

}
