package mobile.project.cekjadwaldokter.InfoDokterSpesialis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import mobile.project.cekjadwaldokter.R;

public class NamaDokter extends AppCompatActivity {

    //database variable
    private DatabaseReference mDatabase;

    //list layout dokter
    private Button btnAdd;
    private EditText etDokter;
    private ListView listView;

    //array dokter
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_dokter);

        //isi database
        mDatabase = FirebaseDatabase.getInstance().getReference();




        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView = (ListView) findViewById(R.id.dokter_listview);

        listView.setAdapter(adapter);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        etDokter = (EditText) findViewById(R.id.etDokter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.push().setValue(etDokter.getText().toString());
                etDokter.setText("");

            }
        });

        mDatabase.addChildEventListener(new ChildEventListener() {
             @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String string = dataSnapshot.getValue(String.class);
                arrayList.add(string);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String string = dataSnapshot.getValue(String.class);
                arrayList.add(string);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
    }
}
