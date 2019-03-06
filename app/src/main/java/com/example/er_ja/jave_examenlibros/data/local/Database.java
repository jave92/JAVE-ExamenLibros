package com.example.er_ja.jave_examenlibros.data.local;

import android.content.Context;

import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Libro.class}, version = 2)
public abstract class Database extends RoomDatabase {

    private static final String DATABASE_NAME = "libros.db";
    private static volatile Database instance;
    public abstract LibroDao libroDao();

    public static Database getInstance(Context context){
        if(instance==null){
            synchronized (Database.class){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),Database.class,DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
