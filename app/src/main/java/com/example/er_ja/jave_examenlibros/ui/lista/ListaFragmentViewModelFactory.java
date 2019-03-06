package com.example.er_ja.jave_examenlibros.ui.lista;

import com.example.er_ja.jave_examenlibros.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListaFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public ListaFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListaFragmentViewModel(repository);
    }
}
