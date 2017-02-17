package xyz.ainunsalisutami.cirebontravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @OnClick(R.id.buttonWisata) void submitWisata() {

        startActivity(new Intent(getApplicationContext(), WisataKategori.class));

    }
    @OnClick(R.id.buttonGallery) void submitGallery() {

        startActivity(new Intent(getApplicationContext(), GalleryActivity.class));

    }
    @OnClick(R.id.buttonKuliner) void submitKuliner() {

        startActivity(new Intent(getApplicationContext(), KulinerKategori.class));

    }
    @OnClick(R.id.buttonHotel) void submitHotel() {

        startActivity(new Intent(getApplicationContext(), HotelKategori.class));

    }
    @OnClick(R.id.buttonAngkot) void submitAngkot() {

        startActivity(new Intent(getApplicationContext(), AngkotActivity.class));

    }
    @OnClick(R.id.buttonMaps) void submitMaps() {

        startActivity(new Intent(getApplicationContext(), MapsActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
}
