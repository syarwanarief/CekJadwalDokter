package mobile.project.cekjadwaldokter.Akun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.MenuTambah.MenuTambahSpesialis;
import mobile.project.cekjadwaldokter.MenuUtama.Home;
import mobile.project.cekjadwaldokter.MenuUtama.Musik;
import mobile.project.cekjadwaldokter.R;

public class InfoAkun extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;
    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_akun);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        TextView nama = (TextView)findViewById(R.id.displayNama);
        TextView email = (TextView)findViewById(R.id.Displayemail);
        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.idAkun);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            nama.setText(user.getDisplayName());
            email.setText(user.getEmail());
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
                        Intent intent = new Intent(InfoAkun.this, Home.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.akun:
                        Intent intent10 = new Intent(InfoAkun.this, InfoAkun.class);
                        startActivity(intent10);
                        finish();
                        return true;
                    case R.id.tambahAdvent:
                        Intent intent5 = new Intent(InfoAkun.this, MenuTambahSpesialis.class);
                        intent5.putExtra("keyTambah", "Tambah Advent");
                        startActivity(intent5);
                        return true;
                    case R.id.tambahAbdulMoeloek:
                        Intent intent6 = new Intent(InfoAkun.this, MenuTambahSpesialis.class);
                        intent6.putExtra("keyTambah", "Tambah Moeloek");
                        startActivity(intent6);
                        return true;
                    case R.id.tambahBumiWaras:
                        Intent intent7 = new Intent(InfoAkun.this, MenuTambahSpesialis.class);
                        intent7.putExtra("keyTambah", "Tambah Bumi Waras");
                        startActivity(intent7);
                        return true;
                    case R.id.tambahDKT:
                        Intent intent8 = new Intent(InfoAkun.this, MenuTambahSpesialis.class);
                        intent8.putExtra("keyTambah", "Tambah DKT");
                        startActivity(intent8);
                        return true;
                    case R.id.tambahImanuel:
                        Intent intent9 = new Intent(InfoAkun.this, MenuTambahSpesialis.class);
                        intent9.putExtra("keyTambah", "Tambah Imanuel");
                        startActivity(intent9);
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(InfoAkun.this, Musik.class);
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(InfoAkun.this, Bantuan.class);
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(InfoAkun.this, Tentang.class);
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

    public void changePass(View view) {
        Intent intent = (new Intent(InfoAkun.this,UbahPassword.class));
        startActivity(intent);
    }

    public void logOut(View view) {
        Intent intent4 = new Intent(InfoAkun.this, LoginActivity.class);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(Emaill);
        editor.remove(Pass);
        editor.commit(); // commit changes

        startActivity(intent4);
        finish();
    }
}
