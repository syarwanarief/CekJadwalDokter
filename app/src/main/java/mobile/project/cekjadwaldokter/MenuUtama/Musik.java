package mobile.project.cekjadwaldokter.MenuUtama;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mobile.project.cekjadwaldokter.R;

public class Musik extends AppCompatActivity {
    //Mendefinisikan variabel
    MediaPlayer do1,re,mi,fa,so,la,si,doo;
    EditText noteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musik);
        do1=MediaPlayer.create(Musik.this,R.raw.do1);
        re=MediaPlayer.create(Musik.this,R.raw.re);
        mi=MediaPlayer.create(Musik.this,R.raw.mi);
        fa=MediaPlayer.create(Musik.this,R.raw.fa);
        so=MediaPlayer.create(Musik.this,R.raw.so);
        la=MediaPlayer.create(Musik.this,R.raw.laa);
        si=MediaPlayer.create(Musik.this,R.raw.si);
        doo=MediaPlayer.create(Musik.this,R.raw.doo);
        noteText = (EditText) findViewById(R.id.noteText);


    }

    public void playDo(View view) {
        do1.start();
        noteText.setText(noteText.getText()+"Do ");
    }
    public void playRe(View view) {
        re.start();
        noteText.setText(noteText.getText()+"Re ");
    }

    public void playMi(View view) {
        mi.start();
        noteText.setText(noteText.getText()+"Mi ");
    }

    public void playFa(View view) {
        fa.start();
        noteText.setText(noteText.getText()+"Fa ");
    }

    public void playSo(View view) {
        so.start();
        noteText.setText(noteText.getText()+"So ");
    }

    public void playLa(View view) {
        la.start();
        noteText.setText(noteText.getText()+"La ");
    }

    public void playSi(View view) {
        si.start();
        noteText.setText(noteText.getText()+"Si ");
    }

    public void playDoo(View view) {
        doo.start();
        noteText.setText(noteText.getText()+"Do' ");
    }
}
