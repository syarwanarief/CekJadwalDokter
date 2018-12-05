package mobile.project.cekjadwaldokter.SplashScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import mobile.project.cekjadwaldokter.Akun.LoginActivity;
import mobile.project.cekjadwaldokter.MenuUtama.Drawer;
import mobile.project.cekjadwaldokter.R;

public class WelcomeActivity extends Activity {

    public static int SPLASH_TIME_OUT = 2300;
    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Emaill) && sharedpreferences.contains(Pass)) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent homeIntent = new Intent(WelcomeActivity.this, Drawer.class);
                    startActivity(homeIntent);
                    finish();
                }
            },SPLASH_TIME_OUT);

        }else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent homeIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }
    }
}
