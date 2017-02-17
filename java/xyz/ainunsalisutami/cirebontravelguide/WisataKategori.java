package xyz.ainunsalisutami.cirebontravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WisataKategori extends AppCompatActivity {
    @OnClick(R.id.wisataalam) void submitWisataAlam() {

        startActivity(new Intent(getApplicationContext(), WisataAlamActivity.class));

    }
    @OnClick(R.id.wisatasejarah) void submitWisataSejarah() {

        startActivity(new Intent(getApplicationContext(), WisataSejarahActivity.class));

    }
    @OnClick(R.id.wisatareligi) void submitWisataReligi() {

        startActivity(new Intent(getApplicationContext(), WisataReligiActivity.class));

    }
    @OnClick(R.id.senidankerajinan) void submitSeniKerajinan() {

        startActivity(new Intent(getApplicationContext(), WisataSeniKerajinanActivity.class));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_kategori);

        ButterKnife.bind(this);
    }
}
