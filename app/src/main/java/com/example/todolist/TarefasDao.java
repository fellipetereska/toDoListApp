package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TarefasDao {
    @Insert
    void insert(Tarefas tarefas);

    @Query("SELECT * FROM Tarefas")
    List<Tarefas> getAllTarefas();
}
