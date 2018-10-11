package mobile.project.cekjadwaldokter.DaftarDokterSpesialis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import mobile.project.cekjadwaldokter.InfoDokter.DokterGigi;
import mobile.project.cekjadwaldokter.R;

public class advent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advent);
    }

    public void gigi(View view) {
        Intent intent = new Intent(advent.this, DokterGigi.class);
        startActivity(intent);
    }
}
