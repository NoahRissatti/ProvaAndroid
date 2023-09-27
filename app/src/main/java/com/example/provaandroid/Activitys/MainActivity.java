package com.example.provaandroid.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.provaandroid.Repository.DatabaseHelper;
import com.example.provaandroid.Item;
import com.example.provaandroid.ItemAdapter;
import com.example.provaandroid.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Button btnIrParaNovaActivity = findViewById(R.id.btnDigita);

        btnIrParaNovaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DigitaActivity.class);
                startActivity(intent);
            }
        });

        List<Item> itemList = databaseHelper.getAllItems();

        ItemAdapter adapter = new ItemAdapter(this, itemList, databaseHelper);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);
                if (item != null) {
                    String texto = item.getText();
                    String cor = item.getOption();
                    String mensagem = "Texto: " + texto + ", Cor: " + cor;
                    Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.removeItem(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}