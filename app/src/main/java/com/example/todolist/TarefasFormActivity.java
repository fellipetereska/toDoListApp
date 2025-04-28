package com.example.todolist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class TarefasFormActivity extends AppCompatActivity {

    private EditText editTextNome, editTextDescricao;
    private TarefasDao tarefasDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tarefa);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> {
            finish();
        });


        editTextNome = findViewById(R.id.editTextNome);
        editTextDescricao = findViewById(R.id.editTextDescricao);

        Button btnSalvar = findViewById(R.id.btnSalvar);

        TarefasDataBase db = Room.databaseBuilder(getApplicationContext(),
                       TarefasDataBase.class, "tarefas-database")
                .allowMainThreadQueries()
                .build();

        tarefasDao = db.tarefasDao();

        btnSalvar.setOnClickListener(v -> salvarTarefa());
    }

    private void salvarTarefa() {
        String nome = editTextNome.getText().toString();
        String descricao = editTextDescricao.getText().toString();

        if (!nome.isEmpty() && !descricao.isEmpty()) {
            try {
                Tarefas tarefa = new Tarefas(nome, descricao);
                tarefasDao.insert(tarefa);

                Toast.makeText(this, "Tarefa cadastrado!", Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Erro ao cadastrar tarefa.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
        }
    }
}
