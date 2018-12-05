package mobile.project.cekjadwaldokter.DaftarDokterSpesialis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import mobile.project.cekjadwaldokter.Akun.LoginActivity;
import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.MenuUtama.Musik;
import mobile.project.cekjadwaldokter.ViewDokterSpesialis.ListInfoDokterSpesialis;
import mobile.project.cekjadwaldokter.MenuUtama.Drawer;
import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.adapterRecyclerView.RecyclerAdapter;
import mobile.project.cekjadwaldokter.paket.firebase.FirebaseModelListSpesialis;

public class ListDokterSpesialis extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    List<FirebaseModelListSpesialis> list;

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

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

        if (bundle.equals("bumiwaras")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bumi Waras");
            reference.keepSynced(true);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list = new ArrayList<FirebaseModelListSpesialis>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModelListSpesialis model = new FirebaseModelListSpesialis();
                        FirebaseModelListSpesialis value = dataSnapshot1.getValue(FirebaseModelListSpesialis.class);
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

                        findViewById(R.id.loading).setVisibility(View.GONE);
                        adapter.setOnItemClick(new RecyclerAdapter.OnItemClick() {
                            @Override
                            public void getPosition(int pos) {
                                if (pos == 0){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Data Dokter Spesialis";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);

                                }else{
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Data Dokter Spesialis";
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


        } else if (bundle.equals("imanuel")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Imanuel");
            reference.keepSynced(true);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list = new ArrayList<FirebaseModelListSpesialis>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModelListSpesialis model = new FirebaseModelListSpesialis();
                        FirebaseModelListSpesialis value = dataSnapshot1.getValue(FirebaseModelListSpesialis.class);
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

                        findViewById(R.id.loading).setVisibility(View.GONE);
                        adapter.setOnItemClick(new RecyclerAdapter.OnItemClick() {
                            @Override
                            public void getPosition(int pos) {
                                if (pos == 0){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Data Dokter Spesialis";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);

                                }else{
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Data Dokter Spesialis";
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

        } else if (bundle.equals("moeloek")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Abdul Moeloek");
            reference.keepSynced(true);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list = new ArrayList<FirebaseModelListSpesialis>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModelListSpesialis model = new FirebaseModelListSpesialis();
                        FirebaseModelListSpesialis value = dataSnapshot1.getValue(FirebaseModelListSpesialis.class);
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

                        findViewById(R.id.loading).setVisibility(View.GONE);
                        adapter.setOnItemClick(new RecyclerAdapter.OnItemClick() {
                            @Override
                            public void getPosition(int pos) {
                                if (pos == 0){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Syaraf Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);

                                }else if (pos == 1){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Bedah Tulang Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 2){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Saluran Kemih Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 3){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Rehabilitasi Medik Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 4){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Paru Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 5){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Gigi Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 6){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Kebidanan Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 7){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Anak Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 8){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Penyakit Dalam Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 9){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Jantung Dan Pembuluh Darah Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 10){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "THT Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 11){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Mata Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 12){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Bedah Umum Abdul Moeloek";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Data Dokter Spesialis";
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

        } else if (bundle.equals("advent")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Advent");
            reference.keepSynced(true);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list = new ArrayList<FirebaseModelListSpesialis>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModelListSpesialis model = new FirebaseModelListSpesialis();
                        FirebaseModelListSpesialis value = dataSnapshot1.getValue(FirebaseModelListSpesialis.class);
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

                        findViewById(R.id.loading).setVisibility(View.GONE);
                        adapter.setOnItemClick(new RecyclerAdapter.OnItemClick() {
                            @Override
                            public void getPosition(int pos) {
                                if (pos == 0){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Saraf Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);

                                }else if (pos == 1){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Radiologi Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 2){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Bedah Tulang Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 3){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Kulit Dan Kelamin Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 4){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Bedah Saluran Kemih Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 5){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Gigi Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 6){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Bedah Umum Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 7){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Penyakit Dalam Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 8){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Mata Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 9){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "THT Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 10){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Anak Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 11){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Kandungan Dan Kebidanan Advent";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Data Dokter Spesialis";
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

        } else if (bundle.equals("dkt")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis DKT");
            reference.keepSynced(true);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list = new ArrayList<FirebaseModelListSpesialis>();

                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        FirebaseModelListSpesialis model = new FirebaseModelListSpesialis();
                        FirebaseModelListSpesialis value = dataSnapshot1.getValue(FirebaseModelListSpesialis.class);
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

                        findViewById(R.id.loading).setVisibility(View.GONE);
                        adapter.setOnItemClick(new RecyclerAdapter.OnItemClick() {
                            @Override
                            public void getPosition(int pos) {
                                if (pos == 0){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Syaraf DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);

                                }else if (pos == 1){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Kulit Dan Kelamin DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 2){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Bedah Umum DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 3){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Bedah Tulang DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 4){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Gigi DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 5){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Rehabilitasi Medik DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 6){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Saluran Kemih DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 7){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Anak DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 8){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Penyakit Dalam DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 9){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Paru DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 10){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Mata DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else if (pos == 11){
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "THT DKT";
                                    b.putString("key1", string1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }else{
                                    Intent intent = new Intent(ListDokterSpesialis.this, ListInfoDokterSpesialis.class);
                                    Bundle b = new Bundle();
                                    String string1 = "Data Dokter Spesialis";
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
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(ListDokterSpesialis.this, Musik.class);
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(ListDokterSpesialis.this, Bantuan.class);
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(ListDokterSpesialis.this, Tentang.class);
                        startActivity(intent3);
                        return true;
                    case R.id.logOut:{
                        Intent intent4 = new Intent(ListDokterSpesialis.this, LoginActivity.class);
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
