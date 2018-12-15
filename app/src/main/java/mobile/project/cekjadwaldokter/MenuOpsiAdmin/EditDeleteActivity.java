package mobile.project.cekjadwaldokter.MenuOpsiAdmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class EditDeleteActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
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

        if (Temp.equals("Saraf Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Syaraf Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        model.setKey(dataSnapshot1.getKey());
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                        adapter.setWarna("advent");
                        adapter.setSpesialis("List Dokter Spesialis Syaraf Advent");
                        dialog.dismiss();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Data Tidak Ditemukan ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

        } else if (Temp.equals("Radiologi Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Radiologi Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Bedah Tulang Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Tulang Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Kulit Dan Kelamin Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Kulit Dan Kelamin Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Bedah Saluran Kemih Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Saluran Kemih Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Gigi Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Gigi Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Bedah Umum Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Umum Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Penyakit Dalam Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Penyakit Dalam Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Mata Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Mata Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("THT Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis THT Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Anak Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Anak Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Kandungan Dan Kebidanan Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Kandungan Dan Kebidanan Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else if (Temp.equals("Radiologi Advent")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Radiologi Advent");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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

        } else if (Temp.equals("Rehabilitasi Bumi Waras")) {
            dialog.setMessage("Memuat Data...");
            dialog.setIndeterminate(true);
            dialog.show();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Rehabilitasi Bumi Waras");
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
                        model.setNamaDokter(vNamaDokter);
                        model.setPoli(Poli);
                        model.setHari(hari);
                        model.setJam(jam);
                        list.add(model);
                        RecyclerAdapterDetailDokter adapter = new RecyclerAdapterDetailDokter(list, EditDeleteActivity.this);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditDeleteActivity.this, 1);
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
        } else {
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
                        Intent intent = new Intent(EditDeleteActivity.this, Home.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.akun:
                        Intent intent10 = new Intent(EditDeleteActivity.this, InfoAkun.class);
                        startActivity(intent10);
                        return true;
                    case R.id.maps:
                        Intent intent11 = new Intent(EditDeleteActivity.this, LokasiActivity.class);
                        startActivity(intent11);
                        return true;
                    case R.id.musik:
                        Intent intent1 = new Intent(EditDeleteActivity.this, Musik.class);
                        startActivity(intent1);
                        return true;
                    case R.id.bantuan:
                        Intent intent2 = new Intent(EditDeleteActivity.this, Bantuan.class);
                        startActivity(intent2);
                        return true;
                    case R.id.tentang:
                        Intent intent3 = new Intent(EditDeleteActivity.this, Tentang.class);
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
