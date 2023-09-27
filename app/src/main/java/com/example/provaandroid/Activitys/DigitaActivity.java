package com.example.provaandroid.Activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;

import com.example.provaandroid.Repository.DatabaseContract;
import com.example.provaandroid.Repository.DatabaseHelper;
import com.example.provaandroid.Item;
import com.example.provaandroid.R;

public class DigitaActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText editTextTexto;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digita);

        radioGroup = findViewById(R.id.radioGroup);
        editTextTexto = findViewById(R.id.editTextTexto);

        Button btnInsere = findViewById(R.id.btnInsere);
        Button btnCancela = findViewById(R.id.btnCancela);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        btnInsere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editTextTexto.getText().toString().trim();
                if (texto.isEmpty()) {
                    Toast.makeText(DigitaActivity.this, "Digite um texto !!", Toast.LENGTH_SHORT).show();
                }
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(DigitaActivity.this, "Obrigatório escolher uma cor!!", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadio = findViewById(selectedId);
                    String selectedOption = selectedRadio.getText().toString();

                    String itemId = UUID.randomUUID().toString();

                    Item item = new Item(itemId, texto, selectedOption);
                    if (insertItem(item)) {
                        Toast.makeText(DigitaActivity.this, "Item inserido no banco de dados", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DigitaActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DigitaActivity.this, "Erro ao inserir o item", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DigitaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean insertItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.COLUMN_TEXT, item.getText());
        values.put(DatabaseContract.ItemEntry.COLUMN_OPTION, item.getOption());

        long newRowId = database.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);

        return newRowId != -1;
    }
}
