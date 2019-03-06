package com.example.er_ja.jave_examenlibros.data;

import android.app.Application;
import android.os.AsyncTask;

import com.example.er_ja.jave_examenlibros.data.local.Database;
import com.example.er_ja.jave_examenlibros.data.local.LibroDao;
import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {

    private final LibroDao libroDao;

    public RepositoryImpl(Application application) {
        Database db = Database.getInstance(application);
        libroDao = db.libroDao();
    }

    @Override
    public LiveData<List<Libro>> getLibros() {
        return libroDao.getLibros();
    }

    @Override
    public void insertLibro(Libro libro) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->libroDao.insert(libro));
    }

//    @Override
//    public void updateLibro(Libro libro) {
//        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->libroDao.update(libro));
//    }

    @Override
    public void deleteLibro(Libro libro) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->libroDao.delete(libro));
    }

    @Override
    public Libro getLibro(String titulo) {
        return libroDao.getLibro(titulo);
    }

    @Override
    public String getTituloLibro(Long id) {
        return libroDao.getTituloLibro(id);
    }
}
