package com.example.er_ja.jave_examenlibros.data.local;

import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LibroDao {
    @Insert
    void insert(Libro libro);
//    @Update
//    void update(Libro libro);
    @Delete
    void delete(Libro libro);
    @Query("SELECT * FROM libro ORDER BY titulo")
    LiveData<List<Libro>> getLibros();
    @Query("SELECT * FROM libro WHERE titulo=:titulo")
    Libro getLibro(String titulo);
    @Query("SELECT titulo FROM libro WHERE id=:id")
    String getTituloLibro(Long id);
}
