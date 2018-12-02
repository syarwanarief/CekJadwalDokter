package mobile.project.cekjadwaldokter.DaftarDokterSpesialis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mobile.project.cekjadwaldokter.MenuUtama.Drawer;
import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.adapterRecyclerView.RecyclerAdapter;
import mobile.project.cekjadwaldokter.paket.firebase.FirebaseModul;

public class ListDokterSpesialis extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    List<FirebaseModul> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dokter_spesialis);
        ActionBar actionBar = getSupportActionBar();
        Bundle extras = getIntent().getExtras();
        String bundle = extras.getString("key");
        TextView textView = (TextView) findViewById(R.id.vtext);
        textView.setText(extras.getString("key1"));

        //display data here
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.idSpesialis);

        if (bundle.equals("dadi")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis A Dadi");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModul model = new FirebaseModul();
                        list = new ArrayList<FirebaseModul>();
                        FirebaseModul value = dataSnapshot1.getValue(FirebaseModul.class);
                        String vSpesialis = value.getSpesialis();
                        String gambar = value.getImage();
                        model.setSpesialis(vSpesialis);
                        model.setImage(gambar);
                        list.add(model);
                        RecyclerAdapter adapter = new RecyclerAdapter(list,ListDokterSpesialis.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListDokterSpesialis.this,1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else if (bundle.equals("imanuel")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Imanuel");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    FirebaseModul model = new FirebaseModul();
                    list = new ArrayList<FirebaseModul>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModul value = dataSnapshot1.getValue(FirebaseModul.class);
                        String vSpesialis = value.getSpesialis();
                        String gambar = value.getImage();
                        model.setSpesialis(vSpesialis);
                        model.setImage(gambar);
                        list.add(model);

                        RecyclerAdapter adapter = new RecyclerAdapter(list,ListDokterSpesialis.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListDokterSpesialis.this,1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else if (bundle.equals("moeloek")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Abdul Moeloek");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    FirebaseModul model = new FirebaseModul();
                    list = new ArrayList<FirebaseModul>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModul value = dataSnapshot1.getValue(FirebaseModul.class);
                        String vSpesialis = value.getSpesialis();
                        String gambar = value.getImage();
                        model.setSpesialis(vSpesialis);
                        model.setImage(gambar);
                        list.add(model);

                        RecyclerAdapter adapter = new RecyclerAdapter(list,ListDokterSpesialis.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListDokterSpesialis.this,1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else if (bundle.equals("advent")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Advent");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    FirebaseModul model = new FirebaseModul();
                    list = new ArrayList<FirebaseModul>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModul value = dataSnapshot1.getValue(FirebaseModul.class);
                        String vSpesialis = value.getSpesialis();
                        String gambar = value.getImage();
                        model.setSpesialis(vSpesialis);
                        model.setImage(gambar);
                        list.add(model);

                        RecyclerAdapter adapter = new RecyclerAdapter(list,ListDokterSpesialis.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListDokterSpesialis.this,1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else if (bundle.equals("dkt")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis DKT");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    FirebaseModul model = new FirebaseModul();
                    list = new ArrayList<FirebaseModul>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModul value = dataSnapshot1.getValue(FirebaseModul.class);
                        String vSpesialis = value.getSpesialis();
                        String gambar = value.getImage();
                        model.setSpesialis(vSpesialis);
                        model.setImage(gambar);
                        list.add(model);

                        RecyclerAdapter adapter = new RecyclerAdapter(list,ListDokterSpesialis.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListDokterSpesialis.this,1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
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
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.beranda:
                        Intent intent = new Intent(ListDokterSpesialis.this, Drawer.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.bantuan:
                        Toast.makeText(getApplicationContext(),"Bantuan telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.tentang:
                        Toast.makeText(getApplicationContext(),"Bantuan telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
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
}
