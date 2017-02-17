package xyz.ainunsalisutami.cirebontravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class KulinerKategori extends AppCompatActivity {
    @OnClick(R.id.kulineroleh) void submitKulinerOleh() {

        startActivity(new Intent(getApplicationContext(), KulinerOlehActivity.class));

    }
    @OnClick(R.id.kulinermkn) void submitKulinerMkn() {

        startActivity(new Intent(getApplicationContext(), KulinerMakananActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner_kategori);

        ButterKnife.bind(this);
    }
}
