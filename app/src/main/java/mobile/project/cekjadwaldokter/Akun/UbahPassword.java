package mobile.project.cekjadwaldokter.Akun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import mobile.project.cekjadwaldokter.R;

public class UbahPassword extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;
    DatabaseReference mdatabase;
    String emaill, oldpass, newpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

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
