package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {

    private ListView listViewTarefas;
    private Button buttonAddTarefa;
    private TarefasDao tarefasDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTarefas = findViewById(R.id.listTarefas);
        buttonAddTarefa = findViewById(R.id.btnAddTarefa);

        TarefasDataBase db = Room.databaseBuilder(getApplicationContext(),
                        TarefasDataBase.class, "tarefas-database")
                .allowMainThreadQueries()
                .build();

        tarefasDao = db.tarefasDao();

        buttonAddTarefa.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TarefasFormActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTarefas();
    }

    private void loadTarefas() {
        List<Tarefas> tarefas = tarefasDao.getAllTarefas();

        if (tarefas.isEmpty()) {
            String[] vazio = new String[]{"Nenhuma tarefa cadastrada."};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, vazio);
            listViewTarefas.setAdapter(adapter);
        } else {
            List<String> tarefasInfo = tarefas.stream()
                    .map(tarefa ->
                            "Nome: " + tarefa.getNome() +
                                    "\nDescrição: " + tarefa.getDescricao())
                    .collect(Collectors.toList());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, tarefasInfo);
            listViewTarefas.setAdapter(adapter);
        }
    }

}
