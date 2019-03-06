package com.example.er_ja.jave_examenlibros.ui.lista;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.er_ja.jave_examenlibros.R;
import com.example.er_ja.jave_examenlibros.RecyclerViewOnItemClickListener;
import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ListaFragmentAdapter extends ListAdapter<Libro, ListaFragmentAdapter.ViewHolder> {

    private final RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public ListaFragmentAdapter(RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        super(new DiffUtil.ItemCallback<Libro>() {
            @Override
            public boolean areItemsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) {
                return TextUtils.equals(oldItem.getTitulo(), newItem.getTitulo()) &&
                        TextUtils.equals(oldItem.getAutor(), newItem.getAutor()) &&
                        TextUtils.equals(oldItem.getFecha(), newItem.getFecha()) &&
                        TextUtils.equals(oldItem.getPortada(), newItem.getPortada()) &&
                        TextUtils.equals(oldItem.getSinopsis(), newItem.getSinopsis());
            }
        });
        this.recyclerViewOnItemClickListener=recyclerViewOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item, parent, false),recyclerViewOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    protected Libro getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblTitulo, lblAutor, lblFecha;

        public ViewHolder(@NonNull View itemView, RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
            super(itemView);
            lblTitulo = ViewCompat.requireViewById(itemView, R.id.lista_item_titulo);
            lblAutor = ViewCompat.requireViewById(itemView, R.id.lista_item_autor);
            lblFecha = ViewCompat.requireViewById(itemView, R.id.lista_item_fecha);
            itemView.setOnClickListener(v -> recyclerViewOnItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition()));        }

        void bind(Libro libro){
            lblTitulo.setText(libro.getTitulo());
            lblAutor.setText(libro.getAutor());
            lblFecha.setText(libro.getFecha());
        }

    }
}
