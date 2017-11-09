package viooktarian.kuis_matematika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText angka1, angka2, operator, inputJawaban;
    TextView jawabanBenar, jwbSalah, hasilJawab;
    Button cekJawaban;
    int value1, value2, value3, jawabBenar, jawabSalah;
    String getJawab, getOpr;

    SharedPref classShardPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classShardPref = new SharedPref (MainActivity.this.getApplicationContext());

        angka1 = (EditText) findViewById(R.id.angka1);
        angka2 = (EditText) findViewById(R.id.angka2);
        operator = (EditText) findViewById(R.id.operator);
        inputJawaban = (EditText) findViewById(R.id.inputJawaban);
        jawabanBenar = (TextView) findViewById(R.id.jawabanBenar);
        jwbSalah = (TextView) findViewById(R.id.jwbSalah);
        hasilJawab = (TextView) findViewById(R.id.hasilJawaban);
        cekJawaban = (Button) findViewById(R.id.cekJawaban);

        this.generateSoal(findViewById(R.id.buatSoal));

        //GetFromPreferences
        jawabBenar = classShardPref.getNilaiBenar();
        jawabSalah = classShardPref.getNilaiSalah();
        jawabanBenar.setText(String.valueOf(classShardPref.getNilaiBenar()) + " Jawaban Benar");
        jwbSalah.setText(String.valueOf(classShardPref.getNilaiSalah()) + " Jawaban Salah");
    }

    public void generateSoal(View view) {
        cekJawaban.setEnabled(true);
        inputJawaban.setText("");
        value1 = (int)(Math.random()*100 + 1);
        value2 = (int)(Math.random()*100 + 1);
        value3 = (int)(Math.random()*4 +1);
        if(value3==1){
            operator.setText(String.valueOf("+"));
        }
        else if(value3==2){
            operator.setText(String.valueOf("-"));
        }
        else if(value3==3){
            operator.setText(String.valueOf("x"));
        }
        else if(value3==4){
            operator.setText(String.valueOf("/"));
        }

        angka1.setText(String.valueOf(value1));
        angka2.setText(String.valueOf(value2));
    }

    public void cekJawaban(View view) {
        cekJawaban.setEnabled(false);
        getOpr = operator.getText().toString();
        int hasil = 0;
        if(getOpr.equals("+")){
            hasil = value1 + value2;
        }
        else if(getOpr.equals("-")){
            hasil = value1 - value2;
        }
        else if(getOpr.equals("x")){
            hasil = value1 * value2;
        }
        else if(getOpr.equals("/")){
            hasil = value1 / value2;
        }
        getJawab = inputJawaban.getText().toString();
        if (getJawab.equals(String.valueOf(hasil))) {

            jawabBenar = jawabBenar + 1;
            jawabanBenar.setText(String.valueOf(jawabBenar) + " Jawaban Benar");
            hasilJawab.setText("Jawaban: "+String.valueOf(hasil)+"\nHoree, jawaban kamu benar");

            classShardPref.setNilaiBenar(jawabBenar);
        } else {
            jawabSalah = jawabSalah + 1;

            jwbSalah.setText(String.valueOf(jawabSalah) + " Jawaban Salah");
            hasilJawab.setText("Jawaban: "+String.valueOf(hasil)+"\nYaahh, kok salah! coba lagi");

            classShardPref.setNilaiSalah(jawabSalah);

        }
    }
}
