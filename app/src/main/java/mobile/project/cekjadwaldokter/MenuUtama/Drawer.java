package mobile.project.cekjadwaldokter.MenuUtama;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import mobile.project.cekjadwaldokter.DaftarDokterSpesialis.List_spesialis_Advent;
import mobile.project.cekjadwaldokter.R;

public class Drawer extends AppCompatActivity {

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
        int images[] = {R.drawable.indonesia_sehat, R.drawable.dbd, R.drawable.germas};

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
                        finish();
                        return true;
                    case R.id.bantuan:
                        Toast.makeText(getApplicationContext(), "Bantuan telah dipilih", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.tentang:
                        Toast.makeText(getApplicationContext(), "Tentang telah dipilih", Toast.LENGTH_SHORT).show();
                        return true;
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

    public void BackPress(){
        if (DoubleBackToExit){
            super.onBackPressed();
            return;
        }
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Keluar")
                .setMessage("Yakin Ingin Keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();
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

    public void KlikDadi(View view) {
        Intent intent = new Intent(Drawer.this, List_spesialis_Advent.class);
        Bundle b = new Bundle();
        String string = "dadi";
        b.putString("key", string);
        String string1 = "Spesialis RS. A. Dadi";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void KlikImanuel(View view) {
        Intent intent = new Intent(Drawer.this, List_spesialis_Advent.class);
        Bundle b = new Bundle();
        String string = "imanuel";
        b.putString("key", string);
        String string1 = "Spesialis RS. Imanuel";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void KlikMoeloek(View view) {
        Intent intent = new Intent(Drawer.this, List_spesialis_Advent.class);
        Bundle b = new Bundle();
        String string = "moeloek";
        b.putString("key", string);
        String string1 = "Spesialis RS. Abdul Moeloek";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void KlikDKT(View view) {
        Intent intent = new Intent(Drawer.this, List_spesialis_Advent.class);
        Bundle b = new Bundle();
        String string = "dkt";
        b.putString("key", string);
        String string1 = "Spesialis RS. DKT";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void advent(View view) {
        Intent intent = new Intent(Drawer.this, List_spesialis_Advent.class);
        Bundle b = new Bundle();
        String string = "advent";
        b.putString("key", string);
        String string1 = "Spesialis RS. Advent";
        b.putString("key1", string1);
        intent.putExtras(b);
        startActivity(intent);

    }
}
