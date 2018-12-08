package mobile.project.cekjadwaldokter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahSpesialis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_spesialis);

        Button button = (Button) findViewById(R.id.btnTambah);
        final TextView nama = (TextView) findViewById(R.id.tambahNama);
        final TextView hari = (TextView) findViewById(R.id.tambahHari);
        final TextView jam = (TextView) findViewById(R.id.tambahWaktu);
        final TextView poli = (TextView) findViewById(R.id.tambahPoli);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }
}
