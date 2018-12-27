package mobile.project.cekjadwaldokter.DaftarDokterSpesialis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mobile.project.cekjadwaldokter.Akun.InfoAkun;
import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.MenuLokasi.LokasiActivity;
import mobile.project.cekjadwaldokter.MenuUtama.Home;
import mobile.project.cekjadwaldokter.MenuUtama.Musik;
import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.adapterRecyclerView.RecyclerAdapterDetailDokter;
import mobile.project.cekjadwaldokter.paket.firebase.ModelInfoSpesialis;

public class ListInfoDokterSpesialis extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;


    List<ModelInfoSpesialis> list;
    ProgressDialog dialog;

    SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info_dokter_spesialis);

        //hide menu tambah
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu nav_Menu = navigationView.getMenu();

        nav_Menu.findItem(R.id.tambahAdvent).setVisible(false);
        nav_Menu.findItem(R.id.tambahAbdulMoeloek).setVisible(false);
        nav_Menu.findItem(R.id.tambahBumiWaras).setVisible(false);
        nav_Menu.findItem(R.id.tambahDKT).setVisible(false);
        nav_Menu.findItem(R.id.tambahImanuel).setVisible(false);
        TextView emailDrawer = (TextView) navigationView.getHeaderView(0).findViewById(R.id.idAkun);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            emailDrawer.setText(user.getEmail());
        }

        Bundle extras = getIntent().getExtras();
        String Temp = extras.getString("key1");
        dialog = new ProgressDialog(this);

        //display data here
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.infoSpesialis);
        String keyFirebase = extras.getString("keyFB","");

        if (Temp.equals("Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(keyFirebase);
            reference.keepSynced(true);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list = new ArrayList<ModelInfoSpesialis>();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ModelInfoSpesialis model = new ModelInfoSpesialis();
                        ModelInfoSpesialis value = dataSnapshot1.getValue(ModelInfoSpesialis.class);
                        String vNamaDokter = value.getNamaDokter();
                        String Poli = value.getPoli();
                        String hari = value.getHari();
                        String jam = value.getJam();
                        String gambar = value.getImage();
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        model.setImage(gambar);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, ListInfoDokterSpesialis.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListInfoDokterSpesialis.this, 1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                        adapter.setWarna("advent");
                        dialog.dismiss();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Data Tidak Ditemukan ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

        }else if (Temp.equals("bumiwaras")){
            Toast.makeText(getApplicationContext(), "Data Belum Tersedia Untuk RS Bumi Waras ", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }else if (Temp.equals("dkt")){
            Toast.makeText(getApplicationContext(), "Data Belum Tersedia Untuk RS DKT ", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }else if (Temp.equals("moeloek")){
            Toast.makeText(getApplicationContext(), "Data Belum Tersedia Untuk RS Abdul Moeloek ", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }else if (Temp.equals("imanuel")){
            Toast.makeText(getApplicationContext(), "Data Belum Tersedia Untuk RS Imanuel ", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else {
            Toast.makeText(getApplicationContext(), "Data Tidak Ditemukan ", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
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
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.beranda:
                        Intent intent = new Intent(ListInfoDokterSpesialis.this, Home.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.akun:
                        Intent intent10 = new Intent(ListInfoDokterSpesialis.this, InfoAkun.class);
                        startActivity(intent10);
                        return true;
                    case R.id.maps:
                        Intent intent11 = new Intent(ListInfoDokterSpesialis.this, LokasiActivity.class);
                        startActivity(intent11);
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(ListInfoDokterSpesialis.this, Musik.class);
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(ListInfoDokterSpesialis.this, Bantuan.class);
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(ListInfoDokterSpesialis.this, Tentang.class);
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

    }
}