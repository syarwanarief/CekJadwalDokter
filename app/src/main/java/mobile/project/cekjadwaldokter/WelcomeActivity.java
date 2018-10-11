package mobile.project.cekjadwaldokter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import mobile.project.cekjadwaldokter.MenuUtama.Drawer;

public class WelcomeActivity extends Activity {

    public static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
        public void run() {
            Intent homeIntent = new Intent(WelcomeActivity.this, Drawer.class);
            startActivity(homeIntent);
            finish();
        }
    },SPLASH_TIME_OUT);
    }
}
