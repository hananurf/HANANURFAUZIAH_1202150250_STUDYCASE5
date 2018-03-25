package com.example.hananurfauziah.hananurfauziah_1202150250_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private TextView warna;
    int color;
    AlertDialog.Builder alert;
    SharedPreferences.Editor shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setContentView(R.layout.activity_settings);
        setTitle("Settings"); // untuk set title menjadi Settings

        alert = new AlertDialog.Builder(this); // untuk membuat alert dialog baru
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0); // untuk menginisialisasi shared preference
        shared = sharedP.edit();
        color = sharedP.getInt("Colourground", R.color.white);
        warna = findViewById(R.id.shape); // agar bisa mengakses textview pada layout
        warna.setText(getShapeColor(color)); // untuk meng set shape color
    }

    @Override
    public void onBackPressed() { // method ketika tombol back di tekan
        Intent intent = new Intent(Settings.this, MainActivity.class); // untuk pindah halaman ke LIST TO DO
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // method ketika pilihan pada menu dipilih
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed(); // untuk menjalankan method onbackpressed
        }
        return true;
    }

    public String getShapeColor(int i) { // untuk mendapatkan string warna yang akan digunakan
        if (i == R.color.green) {
            return "Green";
        } else if (i == R.color.red) {
            return "Red";
        } else if (i == R.color.blue) {
            return "Blue";
        } else {
            return "Default";
        }
    }

    public int getColorid(int i) { // untuk mendapatkan id dari warna yang akan digunakan
        if (i == R.color.red) {
            return R.id.red;
        } else if (i == R.color.green) {
            return R.id.green;
        } else if (i == R.color.blue) {
            return R.id.blue;
        } else {
            return R.id.white;
        }
    }

    public void pilih(View view) {
        alert.setTitle("Shape Color"); // untuk set title dari alert dialog
        view = getLayoutInflater().inflate(R.layout.color, null);
        alert.setView(view); // untuk menampilkan view yang telah dibuat
        final RadioGroup radG = view.findViewById(R.id.radio_color); // untuk mengakses radio group
        radG.check(getColorid(color));

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { // ketika menekan OK pada alert dialog
                int a = radG.getCheckedRadioButtonId(); // untuk mendapatkan id radio button yang di pilih
                switch (a) {
                    case R.id.red:
                        color = R.color.red;
                        break;
                    case R.id.green:
                        color = R.color.green;
                        break;
                    case R.id.blue:
                        color = R.color.blue;
                        break;
                    case R.id.white:
                        color = R.color.white;
                        break;
                }

                warna.setText(getShapeColor(color)); // untuk set shape color menjadi color yang dipilih
                shared.putInt("Colourground", color); // untuk menaruh shared preference
                shared.commit();
            }
        });

        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { // ketika menekan CANCEL pada alert dialog
                dialogInterface.dismiss();
            }
        });
        alert.create().show(); // untuk membuat dan menampilkan alert dialog
    }
}
