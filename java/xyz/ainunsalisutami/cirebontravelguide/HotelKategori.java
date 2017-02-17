package xyz.ainunsalisutami.cirebontravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HotelKategori extends AppCompatActivity {
    @OnClick(R.id.bintang1) void submitBintang1() {

        startActivity(new Intent(getApplicationContext(), HotelBintang1.class));

    }
    @OnClick(R.id.bintang2) void submitBintang2() {

        startActivity(new Intent(getApplicationContext(), HotelBintang2.class));

    }
    @OnClick(R.id.bintang3) void submitBintang3() {

        startActivity(new Intent(getApplicationContext(), HotelBintang3.class));

    }
    @OnClick(R.id.bintang4) void submitBintang4() {

        startActivity(new Intent(getApplicationContext(), HotelBintang4.class));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_kategori);

        ButterKnife.bind(this);
    }
}
