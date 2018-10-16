package mobile.project.cekjadwaldokter.InfoDokter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mobile.project.cekjadwaldokter.R;

public class HariPrakter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hari_prakter);
    }

    public void senin(View view) {
        Intent intent = new Intent(HariPrakter.this, DokterGigi.class);
        startActivity(intent);
    }
}
