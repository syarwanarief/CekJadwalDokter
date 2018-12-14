package mobile.project.cekjadwaldokter.MenuTambah;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobile.project.cekjadwaldokter.Akun.InfoAkun;
import mobile.project.cekjadwaldokter.EditDeleteActivity;
import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.MenuLokasi.LokasiActivity;
import mobile.project.cekjadwaldokter.MenuUtama.Home;
import mobile.project.cekjadwaldokter.MenuUtama.Musik;
import mobile.project.cekjadwaldokter.R;

public class TambahSpesialisAdvent extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_spesialis_advent);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.idAkun);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            textView.setText(user.getEmail());
        }

        //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //textView.setText(sharedpreferences.getString(Emaill, ""));

        //hide menu tambah
        Menu nav_Menu = navigationView.getMenu();

        nav_Menu.findItem(R.id.tambahAdvent).setVisible(false);
        nav_Menu.findItem(R.id.tambahAbdulMoeloek).setVisible(false);
        nav_Menu.findItem(R.id.tambahBumiWaras).setVisible(false);
        nav_Menu.findItem(R.id.tambahDKT).setVisible(false);
        nav_Menu.findItem(R.id.tambahImanuel).setVisible(false);

        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Menginisiasi  NavigationView
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()) {
                    case R.id.beranda:
                        Intent intent = new Intent(TambahSpesialisAdvent.this, Home.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.akun:
                        Intent intent10 = new Intent(TambahSpesialisAdvent.this, InfoAkun.class);
                        finish();
                        startActivity(intent10);
                        return true;
                    case R.id.maps:
                        Intent intent11 = new Intent(TambahSpesialisAdvent.this, LokasiActivity.class);
                        finish();
                        startActivity(intent11);
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(TambahSpesialisAdvent.this, Musik.class);
                        finish();
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(TambahSpesialisAdvent.this, Bantuan.class);
                        finish();
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(TambahSpesialisAdvent.this, Tentang.class);
                        finish();
                        startActivity(intent3);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Home Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();

        final Button button = (Button) findViewById(R.id.btnTambah);
        final Button cek = (Button) findViewById(R.id.btnCek);
        final TextView nama = (TextView) findViewById(R.id.tambahNama);
        final TextView hari = (TextView) findViewById(R.id.tambahHari);
        final TextView jam = (TextView) findViewById(R.id.tambahWaktu);
        final TextView poli = (TextView) findViewById(R.id.tambahPoli);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //display
        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spesialis = spinner.getSelectedItem().toString();
                if (spesialis.equals("Spesialis Syaraf")){
                    Intent intent = (new Intent(TambahSpesialisAdvent.this,EditDeleteActivity.class));
                    intent.putExtra("key1","Saraf Advent");
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Pilih Spesialis Terlebih Dahulu\nPada Kolom Diatas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //save
        button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                //bundlee
                Bundle extras = getIntent().getExtras();
                String key = extras.getString("keyTambah");

                if (key.equals("Tambah Advent")) {
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();
                    String vspesialis = spinner.getSelectedItem().toString();

                    if (vspesialis.equals("Spesialis Syaraf")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Syaraf Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");

                    } else if (vspesialis.equals("Spesialis Radiologi")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Radiologi Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Bedah Tulang")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Tulang Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Kulit Dan Kelamin")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Kulit Dan Kelamin Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Bedah Saluran Kemih")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Saluran Kemih Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Gigi")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Gigi Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Bedah Umum")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Umum Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Penyakit Dalam")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Penyakit Dalam Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Mata")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Mata Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis THT")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis THT Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Anak")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Anak Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else if (vspesialis.equals("Spesialis Kandungan Dan Kebidanan")) {

                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Kandungan Dan Kebidanan Advent").push();

                        DatabaseReference keyNama = mRef.child("NamaDokter");
                        DatabaseReference keyHari = mRef.child("Hari");
                        DatabaseReference keyJam = mRef.child("Jam");
                        DatabaseReference keyPoli = mRef.child("Poli");

                        keyNama.setValue(vnama);
                        keyHari.setValue(vhari);
                        keyJam.setValue(vjam);
                        keyPoli.setValue(vpoli);

                        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                        nama.setText("");
                        hari.setText("");
                        jam.setText("");
                        poli.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi, Silahkan Tunggu Hingga Update Berikutnya ", Toast.LENGTH_SHORT).show();
                        button.setVisibility(View.INVISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Kesalahan Terjadi, Silahkan Tunggu Hingga Update Berikutnya ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
