package mobile.project.cekjadwaldokter.MenuLokasi;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
import mobile.project.cekjadwaldokter.Layanan.Bantuan;
import mobile.project.cekjadwaldokter.Layanan.Tentang;
import mobile.project.cekjadwaldokter.MenuUtama.Home;
import mobile.project.cekjadwaldokter.MenuUtama.Musik;
import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.adapterRecyclerView.RecyclerAdapterLokasi;
import mobile.project.cekjadwaldokter.adapterRecyclerView.RecyclerAdapterRS;
import mobile.project.cekjadwaldokter.paket.firebase.ModelRS;

public class LokasiActivity extends AppCompatActivity {

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
    private AdView mAdView, adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);

        dialog = new ProgressDialog(this);

        MobileAds.initialize(this, "ca-app-pub-3797575949971621~7107973295");

        mAdView = findViewById(R.id.adView);
        adView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        adView.loadAd(adRequest);

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
                        Intent intent = new Intent(LokasiActivity.this, Home.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.akun:
                        Intent intent10 = new Intent(LokasiActivity.this, InfoAkun.class);
                        startActivity(intent10);
                        return true;
                    case R.id.maps:
                        Intent intent11 = new Intent(LokasiActivity.this, LokasiActivity.class);
                        startActivity(intent11);
                        finish();
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(LokasiActivity.this, Musik.class);
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(LokasiActivity.this, Bantuan.class);
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(LokasiActivity.this, Tentang.class);
                        startActivity(intent3);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

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
                    String gambarRS = value.getImage();
                    model.setNama(vNamaRS);
                    model.setImage(gambarRS);
                    list.add(model);

                    RecyclerAdapterLokasi adapter = new RecyclerAdapterLokasi(list, LokasiActivity.this) {
                    };
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(LokasiActivity.this, 2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();

                    adapter.setOnItemClick(new RecyclerAdapterRS.OnItemClick() {
                        @Override
                        public void getPosition(int pos) {
                            switch (pos){
                                case 0:{
                                    //code navigation
                                    /*Uri uri = Uri.parse("google.navigation:q=" + 12f + "," + 2f + " (" + "RSUD Dr.H Abdul Moeloek" + ")");
                                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                                    mapIntent.setPackage("com.google.android.apps.maps");*/
                                    //startActivity(mapIntent);
                                    String uri = "http://maps.google.com/maps?daddr=" + 12f + "," + 2f + "RSUD Dr.H Abdul Moelok";
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    intent.setPackage("com.google.android.apps.maps");

                                    try
                                    {
                                        startActivity(intent);
                                    }
                                    catch(ActivityNotFoundException ex)
                                    {
                                        try
                                        {
                                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                            startActivity(unrestrictedIntent);
                                        }
                                        catch(ActivityNotFoundException innerEx)
                                        {
                                            Toast.makeText(LokasiActivity.this, "Silahkan Install Aplikasi Maps Terlebih Dahulu", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    break;

                                }case 1:{

                                    String uri = "http://maps.google.com/maps?daddr=" + 12f + "," + 2f + "Rumah Sakit Advent Bandar Lampung";
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    intent.setPackage("com.google.android.apps.maps");

                                    try
                                    {
                                        startActivity(intent);
                                    }
                                    catch(ActivityNotFoundException ex)
                                    {
                                        try
                                        {
                                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                            startActivity(unrestrictedIntent);
                                        }
                                        catch(ActivityNotFoundException innerEx)
                                        {
                                            Toast.makeText(LokasiActivity.this, "Silahkan Install Aplikasi Maps Terlebih Dahulu", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    break;

                                }case 2:{

                                    String uri = "http://maps.google.com/maps?daddr=" + "Rumah Sakit Bumi Waras";
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    intent.setPackage("com.google.android.apps.maps");

                                    try
                                    {
                                        startActivity(intent);
                                    }
                                    catch(ActivityNotFoundException ex)
                                    {
                                        try
                                        {
                                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                            startActivity(unrestrictedIntent);
                                        }
                                        catch(ActivityNotFoundException innerEx)
                                        {
                                            Toast.makeText(LokasiActivity.this, "Silahkan Install Aplikasi Maps Terlebih Dahulu", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    break;

                                }case 3:{

                                    String uri = "http://maps.google.com/maps?daddr=" + "Rumah Sakit DKT";
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    intent.setPackage("com.google.android.apps.maps");

                                    try
                                    {
                                        startActivity(intent);
                                    }
                                    catch(ActivityNotFoundException ex)
                                    {
                                        try
                                        {
                                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                            startActivity(unrestrictedIntent);
                                        }
                                        catch(ActivityNotFoundException innerEx)
                                        {
                                            Toast.makeText(LokasiActivity.this, "Silahkan Install Aplikasi Maps Terlebih Dahulu", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    break;

                                }case 4:{

                                    String uri = "http://maps.google.com/maps?daddr=" + "Rumah Sakit Imanuel Way Halim";
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    intent.setPackage("com.google.android.apps.maps");

                                    try
                                    {
                                        startActivity(intent);
                                    }
                                    catch(ActivityNotFoundException ex)
                                    {
                                        try
                                        {
                                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                            startActivity(unrestrictedIntent);
                                        }
                                        catch(ActivityNotFoundException innerEx)
                                        {
                                            Toast.makeText(LokasiActivity.this, "Silahkan Install Aplikasi Maps Terlebih Dahulu", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    break;

                                }default:
                                    return;
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
}
