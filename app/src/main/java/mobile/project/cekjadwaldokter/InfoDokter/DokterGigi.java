package mobile.project.cekjadwaldokter.InfoDokter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import mobile.project.cekjadwaldokter.R;

public class DokterGigi extends AppCompatActivity {

    protected ListView lv;
    protected ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;
    String[] Pil;
    String[] Ltn;
    String[] Gbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_gigi);

        lv = (ListView) findViewById(R.id.lv);

        Pil = new String[] {"Dr. A", "Dr. B", "Dr. C", "Dr. D", "Dr. E"};
        Ltn = new String[] {"Pukul : 15.00-17.00", "Pukul : 15.00-17.00", "Pukul : 15.00-17.00", "Pukul : 15.00-17.00", "Pukul : 15.00-17.00"};
        Gbr = new String[] {Integer.toString(R.drawable.ic_profil),
                Integer.toString(R.drawable.ic_profil),
                Integer.toString(R.drawable.ic_profil),
                Integer.toString(R.drawable.ic_profil),
                Integer.toString(R.drawable.ic_profil) };

        mylist = new ArrayList<HashMap<String,String>>();

        for (int i = 0; i < Pil.length; i++){
            map = new HashMap<String, String>();
            map.put("list", Pil[i]);
            map.put("latin", Ltn[i]);
            map.put("gbr", Gbr[i]);
            mylist.add(map);
        }

        Adapter = new SimpleAdapter(this, mylist, R.layout.list_spesialis,
                new String[] {"list", "latin", "gbr"}, new int[] {R.id.tv_nama, R.id.tv_ltn, R.id.imV});
        lv.setAdapter(Adapter);
    }
}
