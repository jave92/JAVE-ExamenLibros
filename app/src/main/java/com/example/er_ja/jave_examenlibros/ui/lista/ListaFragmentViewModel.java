package com.example.er_ja.jave_examenlibros.ui.lista;

import com.example.er_ja.jave_examenlibros.data.Repository;
import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ListaFragmentViewModel extends ViewModel {
    private final Repository repository;
    LiveData<List<Libro>> libros;

    public ListaFragmentViewModel(Repository repository) {
        this.repository = repository;
        libros = repository.getLibros();
    }
    //TODO INSERT EN EL VIEWMODEL DEL AGREGARFRAGMENT
    public void insert(Libro libro){
        repository.insertLibro(libro);
    }
    public void delete(Libro libro){
        repository.deleteLibro(libro);
    }

    public LiveData<List<Libro>> getLibros(){
        return libros;
    }
}
