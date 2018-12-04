package mobile.project.cekjadwaldokter.Akun;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.project.cekjadwaldokter.MenuUtama.Drawer;
import mobile.project.cekjadwaldokter.R;

public class LoginActivity extends AppCompatActivity {

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    EditText Email, Password;
    TextView Register;
    Button LogInButton;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;
    String email,displayName, password;
    ProgressDialog dialog;
    public static final String userEmail="";

    public static final String TAG="LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogInButton = (Button) findViewById(R.id.idLogin);
        Register = (TextView) findViewById(R.id.buttonRegister);
        Email = (EditText) findViewById(R.id.idEmailMasuk);
        Password = (EditText) findViewById(R.id.id_PasswordMasuk);
        dialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mUser != null) {
                    Intent intent = new Intent(LoginActivity.this, Drawer.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Log.d(TAG,"AuthStateChanged:Keluar");
                }

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        //removeAuthSateListner is used  in onStart function just for checking purposes,it helps in logging you out.
        mAuth.removeAuthStateListener(mAuthListner);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListner != null) {
            mAuth.removeAuthStateListener(mAuthListner);
        }

    }

    private void userSign() {
        email = Email.getText().toString().trim();
        displayName = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(displayName)) {
            Toast.makeText(LoginActivity.this, "Email/Nama Pengguna Masih Kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Password Masih Kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.setMessage("Mohon Tunggu...");
        dialog.setIndeterminate(true);
        dialog.show();

        //check Veriff
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Email Belum Terdaftar", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else {
                    dialog.dismiss();
                    checkIfEmailVerified();

                }
            }
        });
    }

    //This function helps in verifying whether the email is verified or not.
    private void checkIfEmailVerified(){
        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        //boolean emailVerified=users.isEmailVerified();
        /*if(!emailVerified){
            Toast.makeText(this,"Verifikasi Email ID",Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            finish();
        }
        else {*/
            Intent intent = new Intent(LoginActivity.this, Drawer.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(userEmail,email);
            startActivity(intent);
            finish();
    }

    public void Masuk(View view) {
        userSign();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Emaill, email);
        editor.putString(Pass, password);
        editor.commit();
    }

    public void Daftar(View view) {
        Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
        startActivity(intent);
    }

    //close app
    boolean doubleBackToExitPressedOnce = false;
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Kembali Untuk Keluar Aplikasi", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
