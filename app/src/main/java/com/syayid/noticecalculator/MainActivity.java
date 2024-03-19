package com.syayid.noticecalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    private Button addSpinnerButton;
    private Button submitButton;
    private Button resetButton;

    private List<Spinner> spinnerList;
    private List<EditText> numberInputList;

    private LinearLayout spinnerLayoutMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        spinnerLayoutMaster = findViewById(R.id.spinnerLayoutmaster);
        addSpinnerButton = findViewById(R.id.addSpinnerButton);
        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        spinnerList = new ArrayList<>();
        numberInputList = new ArrayList<>();

        addSpinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSpinner();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                // Membangun kotak dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Reset"); // Ganti dengan judul yang sesuai
                builder.setMessage("Yakin mereset semua data?"); // Ganti dengan pesan yang sesuai

                // Tombol Positif (jika diperlukan)
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Tindakan yang diambil saat tombol OK ditekan
//                        dialog.dismiss(); // Menutup dialog
                        spinnerList = new ArrayList<>();
                        numberInputList = new ArrayList<>();
                        spinnerLayoutMaster.removeAllViews();
                        addNewSpinner();
                    }
                });

                // Tombol Negatif (jika diperlukan)
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Tindakan yang diambil saat tombol Batal ditekan
                        dialog.dismiss(); // Menutup dialog
                    }
                });

                // Menampilkan kotak dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        addNewSpinner();
    }

    private void addNewSpinner() {
        LinearLayout spinnerLayout = new LinearLayout(this);
//        LinearLayout spinnerLayout = findViewById(R.id.spinnerLayout);
        spinnerLayout.setOrientation(LinearLayout.HORIZONTAL);

        Spinner newSpinner = new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newSpinner.setAdapter(adapter);

        // Tambahkan weight 70%
        LinearLayout.LayoutParams layoutParamsSpinner = new LinearLayout.LayoutParams(
                0, // Set layout_width menjadi 0dp
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParamsSpinner.weight = 0.7f; // Set layout_weight menjadi 0.7 (70%)
        newSpinner.setLayoutParams(layoutParamsSpinner);

        int spinnerId = View.generateViewId();
        newSpinner.setId(spinnerId);


        EditText numberInput = new EditText(this);
        // Tambahkan weight 70%
        LinearLayout.LayoutParams layoutParamsNum = new LinearLayout.LayoutParams(
                0, // Set layout_width menjadi 0dp
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParamsNum.weight = 0.2f; // Set layout_weight menjadi 0.7 (70%)
        numberInput.setLayoutParams(layoutParamsNum);
        numberInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        numberInput.setHint("0");
//        int numberInputId = View.generateViewId();
        numberInput.setId(spinnerId);
        numberInput.setGravity(Gravity.CENTER);

        Button removeBt = new Button(this);
        LinearLayout.LayoutParams layoutParamsBt = new LinearLayout.LayoutParams(
                0, // Set layout_width menjadi 0dp
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParamsBt.weight = 0.1f; // Set layout_weight menjadi 0.7 (70%)\
        removeBt.setLayoutParams(layoutParamsBt);
        removeBt.setText("🗑️");
        int redColor = Color.parseColor("#FF5733");
        ColorStateList redColorStateList = ColorStateList.valueOf(redColor);

        removeBt.setBackgroundTintList(redColorStateList);
        //        int BtId = View.generateViewId();
        removeBt.setId(spinnerId);
        removeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Menghapus spinnerLayout dari spinnerLayoutMaster
                spinnerLayoutMaster.removeView(spinnerLayout);

                // Menghapus elemen dari daftar spinnerList dan numberInputList
                spinnerList.remove(newSpinner);
                numberInputList.remove(numberInput);
            }
        });

        removeBt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "Hapus", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        spinnerLayout.addView(newSpinner);
        spinnerLayout.addView(numberInput);
        spinnerLayout.addView(removeBt);

        spinnerLayoutMaster.addView(spinnerLayout);
//        container.addView(spinnerLayout);
        spinnerList.add(newSpinner);
        numberInputList.add(numberInput);
    }

    private void handleSubmit() {
//        StringBuilder selectedItems = new StringBuilder("Item yang dipilih dan angka:\n");
        ArrayList<String> notices = new ArrayList<>();
        ArrayList<String> jmlNotices = new ArrayList<>();
        Spinner lokasi = findViewById(R.id.lokasi);


        for (int i = 0; i < spinnerList.size(); i++) {
            Spinner spinner = spinnerList.get(i);
            String selectedItem = spinner.getSelectedItem().toString();

            EditText numberInput = numberInputList.get(i);
            String number = numberInput.getText().toString();

            if (number.equalsIgnoreCase("")
                    || number.equalsIgnoreCase("0")
                    || selectedItem.equalsIgnoreCase("Pilih Type") ){
                continue;
            }

            notices.add(selectedItem);
            jmlNotices.add(number);
        }

        Intent intent = new Intent(this, HasilActivity.class);
        intent.putStringArrayListExtra("notices", notices);
        intent.putStringArrayListExtra("jmlNotices", jmlNotices);
        intent.putExtra("lokasi", lokasi.getSelectedItem().toString());
        startActivity(intent);

    }
}
