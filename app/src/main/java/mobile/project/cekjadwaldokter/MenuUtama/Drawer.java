package mobile.project.cekjadwaldokter.MenuUtama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import mobile.project.cekjadwaldokter.Akun.LoginActivity;
import mobile.project.cekjadwaldokter.DaftarDokterSpesialis.ListDokterSpesialis;
import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.R;

public class Drawer extends AppCompatActivity {

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    boolean DoubleBackToExit = false;

    //flipper
    ViewFlipper v_flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        //flipper
        int images[] = {R.drawable.ic_beranda, R.drawable.ic_beranda, R.drawable.ic_about};
        v_flipper = findViewById(R.id.v_flipper);
        for (int image : images) {
            flipperImage(image);
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
                        Intent intent = new Intent(Drawer.this, Drawer.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(Drawer.this, Musik.class);
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(Drawer.this, Bantuan.class);
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(Drawer.this, Tentang.class);
                        startActivity(intent3);
                        return true;
                    case R.id.logOut:{
                        Intent intent4 = new Intent(Drawer.this, LoginActivity.class);
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.remove(Emaill);
                        editor.remove(Pass);
                        editor.commit(); // commit changes

                        startActivity(intent4);
                        finish();
                        return true;
                    }
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
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
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Kembali Untuk Keluar Aplikasi", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void flipperImage (int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(5000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
    }

    public void musik(View view) {
        Intent intent = new Intent(Drawer.this, Musik.class);
        startActivity(intent);
    }

    public void KlikBumiWaras(View view) {
        Intent intent = new Intent(Drawer.this, ListDokterSpesialis.class);
        Bundle b = new Bundle();
        String string = "bumiwaras";
        b.putString("key", string);
        String string1 = "Dokter Spesialis RS. Bumi Waras";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void KlikImanuel(View view) {
        Intent intent = new Intent(Drawer.this, ListDokterSpesialis.class);
        Bundle b = new Bundle();
        String string = "imanuel";
        b.putString("key", string);
        String string1 = "Dokter Spesialis RS. Imanuel";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void KlikMoeloek(View view) {
        Intent intent = new Intent(Drawer.this, ListDokterSpesialis.class);
        Bundle b = new Bundle();
        String string = "moeloek";
        b.putString("key", string);
        String string1 = "Dokter Spesialis RS. Abdul Moeloek";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void KlikDKT(View view) {
        Intent intent = new Intent(Drawer.this, ListDokterSpesialis.class);
        Bundle b = new Bundle();
        String string = "dkt";
        b.putString("key", string);
        String string1 = "Dokter Spesialis RS. DKT";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void advent(View view) {
        Intent intent = new Intent(Drawer.this, ListDokterSpesialis.class);
        Bundle b = new Bundle();
        String string = "advent";
        b.putString("key", string);
        String string1 = "Dokter Spesialis RS. Advent";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);

    }
}
