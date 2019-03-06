package com.example.er_ja.jave_examenlibros.ui.agregar;

import com.example.er_ja.jave_examenlibros.data.Repository;
import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;

import androidx.lifecycle.ViewModel;

public class AgregarFragmentViewModel extends ViewModel {
    private final Repository repository;

    public AgregarFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void insert(Libro libro){
        repository.insertLibro(libro);
    }

}
