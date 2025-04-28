package com.example.todolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tarefas {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private String descricao;

    public Tarefas(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
}
