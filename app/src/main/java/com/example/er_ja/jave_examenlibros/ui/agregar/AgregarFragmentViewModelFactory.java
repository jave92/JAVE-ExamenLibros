package com.example.er_ja.jave_examenlibros.ui.agregar;

import com.example.er_ja.jave_examenlibros.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AgregarFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public AgregarFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AgregarFragmentViewModel(repository);
    }
}
