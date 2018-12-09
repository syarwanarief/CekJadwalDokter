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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        emaill = email.getText().toString().trim();
        oldpass = oldPass.getText().toString().trim();
        newpass = newPass.getText().toString().trim();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        email.setText(sharedpreferences.getString(Emaill, ""));
        email.setEnabled(false);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users").push();
        //final String emaill = user.getEmail();
        final AuthCredential credential = EmailAuthProvider
                .getCredential(emaill, oldpass);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                mdatabase.child("password").setValue(newPass);
                                                Toast.makeText(getApplicationContext(), "Kata Sandi Baru Disimpan ", Toast.LENGTH_SHORT).show();
                                                Intent intent = (new Intent(UbahPassword.this,LoginActivity.class));
                                                startActivity(intent);
                                                finish();
                                            }else if (!task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(), "Kata Sandi Salah ", Toast.LENGTH_SHORT).show();
                                                oldPass.setFocusable(true);
                                            }else {
                                                Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed Auth ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public String getPassword(){

        return newpass;
    }
}
