package com.example.hananurfauziah.hananurfauziah_1202150250_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Database dataBase;
    private RecyclerView recView;
    private Adapter adapter;
    private ArrayList<AddData> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TO DO LIST"); // set title menjadi TO DO LIST

        recView = findViewById(R.id.recycler_view); // untuk bisa mengakses recyclerview
        list_data = new ArrayList<>(); // membuat arraylist yang baru
        dataBase = new Database(this); // membuat database yang baru
        dataBase.readdata(list_data); // untuk memanggil method readdata

        SharedPreferences shared = this.getApplicationContext().getSharedPreferences("Preferences", 0); // untuk menginisialisasi shared preference
        int color = shared.getInt("ColorGround", R.color.white);

        adapter = new Adapter(this, list_data, color);  // untuk membuat adapter baru
        recView.setHasFixedSize(true); // untuk mengatur perubahan ukuran
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter); // sebagai inisiasi adapter untuk recycler view

        hapusgeser(); // untuk menjalankan method hapus data pada TO DO LIST
    }

    public void hapusgeser() { // method untuk menghapus item pada TO DO LIST
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) { // untuk membuat touch helper baru recyclerview

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                if (direction == ItemTouchHelper.LEFT) { // kondisi apabila item di swipe ke kiri
                    if (dataBase.removedata(current.getTodo())) { // kondisi untuk remove item yang dipilih dengan mengenali TO DO sebagai primary key
                        adapter.deleteData(position); // untuk menghapus data
                        Snackbar.make(findViewById(R.id.coordinator), "List Telah Terhapus", 3000).show(); // membuat snack bar
                    }
                }
            }
        };

        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall); //untuk menentukan itemtouchhelper recyclerview
        touchhelp.attachToRecyclerView(recView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // membuat menu didalam activity
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // merupakan method yang dijalankan ketika item dipilih
        int id = item.getItemId(); // untuk mendapatkan id dari item
        if (id == R.id.action_settings) { // ketika item yang dipilih adalah setting
            Intent intent = new Intent(MainActivity.this, Settings.class); // untuk pindah halaman ke Settings
            startActivity(intent);
            finish();
        }
        return true;
    }

    public void add (View view) { // merupakan method yang akan dijalankan ketika tombol diklik
        Intent intent = new Intent(MainActivity.this, AddToDo.class); // untuk pindah halaman ke ADD TO DO
        startActivity(intent);
    }
}