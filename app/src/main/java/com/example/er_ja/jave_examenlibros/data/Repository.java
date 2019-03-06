package com.example.er_ja.jave_examenlibros.data;

import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {
    //Libros
    LiveData<List<Libro>> getLibros();
    void insertLibro(Libro libro);
//    void updateLibro(Libro libro);
    void deleteLibro(Libro libro);
    Libro getLibro(String titulo);
    String getTituloLibro(Long id);
}
