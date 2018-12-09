package mobile.project.cekjadwaldokter.MenuTambah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobile.project.cekjadwaldokter.R;

public class TambahSpesialis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_spesialis);

        final Button button = (Button) findViewById(R.id.btnTambah);
        final TextView nama = (TextView) findViewById(R.id.tambahNama);
        final TextView hari = (TextView) findViewById(R.id.tambahHari);
        final TextView jam = (TextView) findViewById(R.id.tambahWaktu);
        final TextView poli = (TextView) findViewById(R.id.tambahPoli);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bundlee
                Bundle extras = getIntent().getExtras();
                String key = extras.getString("key1");

                if (key.equals("Saraf Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Syaraf Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Radiologi Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Radiologi Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Bedah Tulang Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Tulang Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Kulit Dan Kelamin Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Kulit Dan Kelamin Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Bedah Saluran Kemih Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Saluran Kemih Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Gigi Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Gigi Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Bedah Umum Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Bedah Umum Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Penyakit Dalam Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Penyakit Dalam Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Mata Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Mata Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("THT Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis THT Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Anak Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Anak Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else if (key.equals("Kandungan Dan Kebidanan Advent")){
                    String vnama = nama.getText().toString();
                    String vhari = hari.getText().toString();
                    String vjam = jam.getText().toString();
                    String vpoli = poli.getText().toString();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("List Dokter Spesialis Kandungan Dan Kebidanan Advent").push();

                    DatabaseReference keyNama = mRef.child("NamaDokter");
                    DatabaseReference keyHari = mRef.child("Hari");
                    DatabaseReference keyJam = mRef.child("Jam");
                    DatabaseReference keyPoli = mRef.child("Poli");

                    keyNama.setValue(vnama);
                    keyHari.setValue(vhari);
                    keyJam.setValue(vjam);
                    keyPoli.setValue(vpoli);

                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    hari.setText("");
                    jam.setText("");
                    poli.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "Kesalahan Terjadi, Silahkan Tunggu Hingga Update Berikutnya ", Toast.LENGTH_SHORT).show();
                    button.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
