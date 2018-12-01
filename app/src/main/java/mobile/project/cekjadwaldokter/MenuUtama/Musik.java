package mobile.project.cekjadwaldokter.MenuUtama;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mobile.project.cekjadwaldokter.R;

public class Musik extends AppCompatActivity {
    //Mendefinisikan variabel
    SoundPool do1, re, mi, fa, so, la, si, doo;

    int doId, miId, reId, faId, soId, laId, siId, dooId;

    //EditText noteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musik);

        do1 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        re = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mi = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        fa = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        so = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        la = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        si = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        doo = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        doId = do1.load(this, R.raw.do1, 1);
        reId = re.load(this, R.raw.re, 1);
        miId = mi.load(this, R.raw.mi, 1);
        faId = fa.load(this, R.raw.fa, 1);
        soId = so.load(this, R.raw.so, 1);
        laId = la.load(this, R.raw.laa, 1);
        siId = si.load(this, R.raw.si, 1);

    }

    public void playDo(View view) {
        do1.play(doId, 1, 1, 1, 0, 1);
        //noteText.setText(noteText.getText()+"Do ");
    }
    public void playRe(View view) {
        re.play(doId, 1, 1, 1, 0, 1);
        //noteText.setText(noteText.getText()+"Re ");
    }

    public void playMi(View view) {
        mi.play(doId, 1, 1, 1, 0, 1);
        //noteText.setText(noteText.getText()+"Mi ");
    }

    public void playFa(View view) {
        fa.play(doId, 1, 1, 1, 0, 1);
        //noteText.setText(noteText.getText()+"Fa ");
    }

    public void playSo(View view) {
        so.play(doId, 1, 1, 1, 0, 1);
        //noteText.setText(noteText.getText()+"So ");
    }

    public void playLa(View view) {
        la.play(doId, 1, 1, 1, 0, 1);
        //noteText.setText(noteText.getText()+"La ");
    }

    public void playSi(View view) {
        si.play(doId, 1, 1, 1, 0, 1);
        //noteText.setText(noteText.getText()+"Si ");
    }

}
