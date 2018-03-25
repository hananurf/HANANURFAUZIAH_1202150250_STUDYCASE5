package com.example.hananurfauziah.hananurfauziah_1202150250_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {

    private EditText ToDo, Description, Priority;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        setTitle("ADD TO DO"); // set title menjadi ADD TO DO

        ToDo = (EditText) findViewById(R.id.todo);
        Description = (EditText) findViewById(R.id.desc);
        Priority = (EditText) findViewById(R.id.priority);
        db = new Database(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddToDo.this, MainActivity.class); // untuk pindah halaman ke MainActivity
        startActivity(intent);
        this.finish();
    }

    public void addtodo(View view) {
        if (db.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))) { // kondisi apabila data todoname, deskripsi dan prioritas di isi
            Toast.makeText(this, "To Do List Ditambahkan !", Toast.LENGTH_SHORT).show(); // pesan ketika data berhasil ditambahkan di list
            startActivity(new Intent(AddToDo.this, MainActivity.class)); // untuk pindah halaman ke MainActivity
            this.finish();
        } else {
            Toast.makeText(this, "List tidak boleh kosong", Toast.LENGTH_SHORT).show(); // pesan ketika tidak ada data yang ditambahkan
            ToDo.setText(null); // meng set edit text menjadi kosong
            Description.setText(null);
            Priority.setText(null);
        }
    }
}