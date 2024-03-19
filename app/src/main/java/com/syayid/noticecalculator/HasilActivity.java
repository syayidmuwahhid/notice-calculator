package com.syayid.noticecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HasilActivity extends AppCompatActivity {


    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);


        Notice notice = new Notice();

        container = findViewById(R.id.container2);

        Intent intent = getIntent();
        ArrayList<String> notices = intent.getStringArrayListExtra("notices");
        ArrayList<String> jmlNotices = intent.getStringArrayListExtra("jmlNotices");
        String lokasi = intent.getStringExtra("lokasi");

//        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        DecimalFormat rupiahFormat = new DecimalFormat("#,###,###");

        int totalSeluruh = 0;
        int jmlSeluruh = 0;

        for (int i=0; i<notices.size(); i++){

            LinearLayout layout = new LinearLayout(this);
            TextView text = new TextView(this);
            TextView jml = new TextView(this);
            TextView hargaL = new TextView(this);
            TextView total = new TextView(this);

            layout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams layoutParams01 = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams01.weight = 0.05f;
            layoutParams01.setMargins(10,10,10,10);

            LinearLayout.LayoutParams layoutParams02 = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams02.weight = 0.2f;
            layoutParams02.setMargins(10,10,10,10);

            LinearLayout.LayoutParams layoutParams03 = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams03.weight = 0.3f;
            layoutParams03.setMargins(10,10,10,10);

            text.setLayoutParams(layoutParams02);
            hargaL.setLayoutParams(layoutParams02);
            hargaL.setGravity(Gravity.END);
            jml.setLayoutParams(layoutParams01);
            jml.setGravity(Gravity.CENTER);
            total.setLayoutParams(layoutParams02);
            total.setGravity(Gravity.END);

            int harga = notice.getTurun(notices.get(i));
            int totalHarga = harga * Integer.valueOf(jmlNotices.get(i));
            totalSeluruh += totalHarga;
            jmlSeluruh += Integer.valueOf(jmlNotices.get(i));

            text.setText(notices.get(i));
            jml.setText(jmlNotices.get(i));
            hargaL.setText("Rp " + String.valueOf(rupiahFormat.format(harga)));
            total.setText("Rp " + String.valueOf(rupiahFormat.format(totalHarga)));


            container.addView(layout);
            layout.addView(text);
            layout.addView(jml);
            layout.addView(hargaL);
            layout.addView(total);

        }

        TampilTotal(totalSeluruh, notice.getBiayaProses(lokasi), jmlSeluruh);

    }

    void TampilTotal(int total, int biayaProses, int jml){
        LinearLayout layout = new LinearLayout(this);
        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout layout3 = new LinearLayout(this);
        TextView totalSeluruh = new TextView(this);
        TextView totalSeluruhTitle = new TextView(this);
        TextView biaya = new TextView(this);
        TextView biayaTitle = new TextView(this);
        TextView totalNoticeTitle = new TextView(this);
        TextView totalNotice = new TextView(this);

        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams layoutParams08 = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams08.weight = 0.7f;
        layoutParams08.setMargins(10,10,10,10);

        LinearLayout.LayoutParams layoutParams02 = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams02.weight = 0.3f;
        layoutParams02.setMargins(10,10,10,10);

        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

        totalSeluruhTitle.setLayoutParams(layoutParams08);
        totalSeluruhTitle.setGravity(Gravity.END);
        totalSeluruhTitle.setTypeface(boldTypeface);
        totalSeluruh.setLayoutParams(layoutParams02);
        totalSeluruh.setGravity(Gravity.END);
        totalSeluruh.setTypeface(boldTypeface);
        biayaTitle.setLayoutParams(layoutParams08);
        biayaTitle.setGravity(Gravity.END);
        biayaTitle.setTypeface(boldTypeface);
        biaya.setLayoutParams(layoutParams02);
        biaya.setGravity(Gravity.END);
        biaya.setTypeface(boldTypeface);
        totalNoticeTitle.setLayoutParams(layoutParams08);
        totalNoticeTitle.setGravity(Gravity.END);
        totalNoticeTitle.setTypeface(boldTypeface);
        totalNotice.setLayoutParams(layoutParams02);
        totalNotice.setGravity(Gravity.END);
        totalNotice.setTypeface(boldTypeface);


        DecimalFormat rupiahFormat = new DecimalFormat("#,###,###");

        totalSeluruhTitle.setText("Total = ");
        totalSeluruh.setText("Rp " + String.valueOf(rupiahFormat.format(total)));
        biayaTitle.setText("Biaya Proses (Rp " + rupiahFormat.format(biayaProses) + " x " + jml + ") = ");
        biaya.setText("Rp " + String.valueOf(rupiahFormat.format(biayaProses * jml)));
        totalNoticeTitle.setText("Total Seluruh = ");
        totalNotice.setText("Rp " + String.valueOf(rupiahFormat.format((biayaProses * jml) + total)));

        container.addView(layout);
        layout.addView(totalSeluruhTitle);
        layout.addView(totalSeluruh);
        container.addView(layout2);
        layout2.addView(biayaTitle);
        layout2.addView(biaya);
        container.addView(layout3);
        layout3.addView(totalNoticeTitle);
        layout3.addView(totalNotice);
    }


}