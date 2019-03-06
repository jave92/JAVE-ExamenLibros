package com.example.er_ja.jave_examenlibros.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libro")
public class Libro {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "titulo")
    private String titulo;
    @ColumnInfo(name = "autor")
    private String autor;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "portada")
    private String portada;
    @ColumnInfo(name = "sinopsis")
    private String sinopsis;

    public Libro(String titulo, String autor, String fecha, String portada, String sinopsis) {
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.portada = portada;
        this.sinopsis = sinopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
