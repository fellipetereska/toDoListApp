package com.example.todolist;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tarefas.class}, version = 1)
public abstract class TarefasDataBase extends RoomDatabase {
    private static TarefasDataBase instance;

    public abstract TarefasDao tarefasDao();

    public static synchronized TarefasDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TarefasDataBase.class,
                            "tarefas-database"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
