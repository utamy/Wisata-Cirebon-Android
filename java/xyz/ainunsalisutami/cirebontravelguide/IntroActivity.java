package xyz.ainunsalisutami.cirebontravelguide;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import xyz.ainunsalisutami.cirebontravelguide.intro.SampleSlide;
import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro {

    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));
        addSlide(SampleSlide.newInstance(R.layout.intro5));
        addSlide(SampleSlide.newInstance(R.layout.intro6));
    }

    private void loadMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(), "You Skipped intro application", Toast.LENGTH_LONG).show();
    }
    public void onSlideChanged() {
    }
    public void onNextPressed(){
    }
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }
}