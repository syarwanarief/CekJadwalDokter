package mobile.project.cekjadwaldokter.MenuUtama;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mobile.project.cekjadwaldokter.Akun.InfoAkun;
import mobile.project.cekjadwaldokter.Akun.LoginActivity;
import mobile.project.cekjadwaldokter.DaftarDokterSpesialis.ListDokterSpesialis;
import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.MenuLokasi.LokasiActivity;
import mobile.project.cekjadwaldokter.MenuOpsiAdmin.TambahSpesialisAdvent;
import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.adapterRecyclerView.RecyclerAdapterRS;
import mobile.project.cekjadwaldokter.paket.firebase.ModelRS;

public class Home extends AppCompatActivity {

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;
    List<ModelRS> list;
    ProgressDialog dialog;

    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    //flipper
    ViewFlipper v_flipper;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dialog = new ProgressDialog(this);

        MobileAds.initialize(this, "ca-app-pub-3797575949971621~7107973295");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }
        });

        //login akses tambah
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.idAkun);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        textView.setText(sharedpreferences.getString(Emaill, ""));
        Menu nav_Menu = navigationView.getMenu();

        nav_Menu.findItem(R.id.tambahAdvent).setVisible(false);
        nav_Menu.findItem(R.id.tambahAbdulMoeloek).setVisible(false);
        nav_Menu.findItem(R.id.tambahBumiWaras).setVisible(false);
        nav_Menu.findItem(R.id.tambahDKT).setVisible(false);
        nav_Menu.findItem(R.id.tambahImanuel).setVisible(false);
        nav_Menu.findItem(R.id.beranda).setVisible(false);

        if (textView.getText().toString().trim().equals("advent@gmail.com")) {
            nav_Menu.findItem(R.id.tambahAdvent).setVisible(true);

        } else if (textView.getText().toString().trim().equals("abdulmoeloek@gmail.com")) {
            nav_Menu.findItem(R.id.tambahAbdulMoeloek).setVisible(true);

        } else if (textView.getText().toString().trim().equals("bumiwaras@gmail.com")) {
            nav_Menu.findItem(R.id.tambahBumiWaras).setVisible(true);

        } else if (textView.getText().toString().trim().equals("dkt@gmail.com")) {
            nav_Menu.findItem(R.id.tambahDKT).setVisible(true);

        } else if (textView.getText().toString().trim().equals("imanuel@gmail.com")) {
            nav_Menu.findItem(R.id.tambahImanuel).setVisible(true);
        } else {
            nav_Menu.findItem(R.id.tambahAdvent).setVisible(false);
            nav_Menu.findItem(R.id.tambahAbdulMoeloek).setVisible(false);
            nav_Menu.findItem(R.id.tambahBumiWaras).setVisible(false);
            nav_Menu.findItem(R.id.tambahDKT).setVisible(false);
            nav_Menu.findItem(R.id.tambahImanuel).setVisible(false);
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
                    case R.id.beranda:
                        Intent intent = new Intent(Home.this, Home.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.akun:
                        Intent intent10 = new Intent(Home.this, InfoAkun.class);
                        startActivity(intent10);
                        return true;
                    case R.id.maps:
                        Intent intent11 = new Intent(Home.this, LokasiActivity.class);
                        startActivity(intent11);
                        return true;
                    case R.id.tambahAdvent:
                        Intent intent5 = new Intent(Home.this, TambahSpesialisAdvent.class);
                        intent5.putExtra("keyTambah", "Tambah Advent");
                        startActivity(intent5);
                        return true;
                    case R.id.tambahAbdulMoeloek:
                        Intent intent6 = new Intent(Home.this, TambahSpesialisAdvent.class);
                        intent6.putExtra("keyTambah", "Tambah Moeloek");
                        startActivity(intent6);
                        return true;
                    case R.id.tambahBumiWaras:
                        Intent intent7 = new Intent(Home.this, TambahSpesialisAdvent.class);
                        intent7.putExtra("keyTambah", "Tambah Bumi Waras");
                        startActivity(intent7);
                        return true;
                    case R.id.tambahDKT:
                        Intent intent8 = new Intent(Home.this, TambahSpesialisAdvent.class);
                        intent8.putExtra("keyTambah", "Tambah DKT");
                        startActivity(intent8);
                        return true;
                    case R.id.tambahImanuel:
                        Intent intent9 = new Intent(Home.this, TambahSpesialisAdvent.class);
                        intent9.putExtra("keyTambah", "Tambah Imanuel");
                        startActivity(intent9);
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(Home.this, Musik.class);
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(Home.this, Bantuan.class);
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(Home.this, Tentang.class);
                        startActivity(intent3);
                        return true;
                    case R.id.logOut:
                        Intent intent4 = new Intent(Home.this, LoginActivity.class);
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.remove(Emaill);
                        editor.remove(Pass);
                        editor.commit();
                        startActivity(intent4);
                        finish();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        //flipper
        int images[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
        v_flipper = findViewById(R.id.v_flipper);
        for (int image : images) {
            flipperImage(image);
        }

        dialog.setMessage("Mohon Tunggu...");
        dialog.setIndeterminate(true);
        dialog.show();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listRS);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Rumah Sakit");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<ModelRS>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelRS model = new ModelRS();
                    ModelRS value = dataSnapshot1.getValue(ModelRS.class);
                    String vNamaRS = value.getNama();
                    String vAlamatRS = value.getAlamat();
                    String gambarRS = value.getImage();
                    model.setNama(vNamaRS);
                    model.setAlamat(vAlamatRS);
                    model.setImage(gambarRS);
                    list.add(model);

                    RecyclerAdapterRS adapter = new RecyclerAdapterRS(list, Home.this);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Home.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();

                    adapter.setOnItemClick(new RecyclerAdapterRS.OnItemClick() {
                        @Override
                        public void getPosition(int pos) {
                            if (pos == 0) {
                                Intent intent = new Intent(Home.this, ListDokterSpesialis.class);
                                Bundle b = new Bundle();
                                String string = "moeloek";
                                b.putString("key", string);
                                String string1 = "Dokter Spesialis RS. Abdul Moeloek";
                                b.putString("key1", string1);
                                intent.putExtras(b);
                                startActivity(intent);

                            } else if (pos == 1) {
                                Intent intent = new Intent(Home.this, ListDokterSpesialis.class);
                                Bundle b = new Bundle();
                                String string = "advent";
                                b.putString("key", string);
                                String string1 = "Dokter Spesialis RS. Advent";
                                b.putString("key1", string1);
                                intent.putExtras(b);
                                startActivity(intent);
                            } else if (pos == 2) {
                                Intent intent = new Intent(Home.this, ListDokterSpesialis.class);
                                Bundle b = new Bundle();
                                String string = "bumiwaras";
                                b.putString("key", string);
                                String string1 = "Dokter Spesialis RS. Bumi Waras";
                                b.putString("key1", string1);
                                intent.putExtras(b);
                                startActivity(intent);
                            } else if (pos == 3) {
                                Intent intent = new Intent(Home.this, ListDokterSpesialis.class);
                                Bundle b = new Bundle();
                                String string = "dkt";
                                b.putString("key", string);
                                String string1 = "Dokter Spesialis RS. DKT";
                                b.putString("key1", string1);
                                intent.putExtras(b);
                                startActivity(intent);
                            } else if (pos == 4) {
                                Intent intent = new Intent(Home.this, ListDokterSpesialis.class);
                                Bundle b = new Bundle();
                                String string = "imanuel";
                                b.putString("key", string);
                                String string1 = "Dokter Spesialis RS. Imanuel";
                                b.putString("key1", string1);
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

    boolean doubleBackToExitPressedOnce = false;

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Kembali Untuk Keluar Aplikasi", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void flipperImage(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(5000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
