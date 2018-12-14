package mobile.project.cekjadwaldokter.Akun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.MenuLokasi.LokasiActivity;
import mobile.project.cekjadwaldokter.MenuUtama.Home;
import mobile.project.cekjadwaldokter.MenuUtama.Musik;
import mobile.project.cekjadwaldokter.R;

public class UbahPassword extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;
    DatabaseReference mdatabase;
    String emaill, oldpass, newpass;

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.idAkun);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
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
                        Intent intent = new Intent(UbahPassword.this, Home.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.akun:
                        Intent intent10 = new Intent(UbahPassword.this, InfoAkun.class);
                        finish();
                        startActivity(intent10);
                        return true;
                    case R.id.maps:
                        Intent intent11 = new Intent(UbahPassword.this, LokasiActivity.class);
                        finish();
                        startActivity(intent11);
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(UbahPassword.this, Musik.class);
                        finish();
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(UbahPassword.this, Bantuan.class);
                        finish();
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(UbahPassword.this, Tentang.class);
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

        Button simpan = (Button) findViewById(R.id.btnUbahSandi);
        final TextView email = (TextView) findViewById(R.id.ubahEmail);
        final TextView oldPass = (TextView) findViewById(R.id.sandiLama);
        final TextView newPass = (TextView) findViewById(R.id.sandiBaru);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        email.setText(sharedpreferences.getString(Emaill, ""));
        email.setEnabled(false);
        //final String emaill = user.getEmail();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myEmail = sharedpreferences.getString(Emaill, "");
                oldpass = oldPass.getText().toString().trim();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                AuthCredential credential = EmailAuthProvider.getCredential(myEmail, oldpass);
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Error! Silahkan Login Kembali", Toast.LENGTH_SHORT).show();
                    Intent intent = (new Intent(UbahPassword.this, LoginActivity.class));
                    startActivity(intent);
                    finish();
                } else {
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                newpass = newPass.getText().toString().trim();
                                user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        final String UserId = user.getUid();
                                        if (task.isSuccessful() && oldPass.getText() != null) {
                                            // save to firebase
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(UserId)
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                        @Override
                                                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                            Map<String, Object> postValues = new HashMap<String, Object>();
                                                                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                                                postValues.put(snapshot.getKey(), snapshot.getValue());
                                                                                            }
                                                                                            postValues.put("password", newpass);
                                                                                            FirebaseDatabase.getInstance().getReference()
                                                                                                    .child("Users").child(UserId).updateChildren(postValues);
                                                                                        }

                                                                                        @Override
                                                                                        public void onCancelled(DatabaseError databaseError) {
                                                                                        }
                                                                                    }
                                                    );
                                            // sukses
                                            Toast.makeText(getApplicationContext(), "Kata Sandi Baru Disimpan ", Toast.LENGTH_SHORT).show();
                                            Intent intent = (new Intent(UbahPassword.this, LoginActivity.class));
                                            startActivity(intent);
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.remove(Emaill);
                                            editor.remove(Pass);
                                            editor.commit();
                                            finish();
                                        } else if (!task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                            oldPass.setFocusable(true);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Kata Sandi Salah ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });//update pass
                            } else {
                                Toast.makeText(getApplicationContext(), "Gagal Auth! \n Silahkan Login Kembali", Toast.LENGTH_SHORT).show();
                                Intent intent = (new Intent(UbahPassword.this, LoginActivity.class));
                                startActivity(intent);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.remove(Emaill);
                                editor.remove(Pass);
                                editor.commit();
                            }
                        }
                    });//credential
                }
            }
        });//onclick button
    }
}
