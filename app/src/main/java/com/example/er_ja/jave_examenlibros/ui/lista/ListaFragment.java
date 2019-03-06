package com.example.er_ja.jave_examenlibros.ui.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.er_ja.jave_examenlibros.R;
import com.example.er_ja.jave_examenlibros.data.RepositoryImpl;
import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;
import com.example.er_ja.jave_examenlibros.databinding.ListaFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ListaFragment extends Fragment {
    ListaFragmentViewModel vm;
    ListaFragmentBinding b;

    private ListaFragmentAdapter listAdapter;

    public ListaFragment() {
    }

    public static ListaFragment newInstance(){
        return new ListaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = ListaFragmentBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(ListaFragment.this, new ListaFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(ListaFragmentViewModel.class);
        setupViews();
        observeLista();
    }

    private void observeLista() {
        vm.getLibros().observe(this, lista -> {
            listAdapter.submitList(lista);
        });
    }

    private void setupViews() {
        listAdapter = new ListaFragmentAdapter();
        b.lstLista.setHasFixedSize(true);
        b.lstLista.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.lstLista_columns)));
        b.lstLista.setItemAnimator(new DefaultItemAnimator());
        b.lstLista.setAdapter(listAdapter);
        //TODO fab nuevo libro
//        b.listaFab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_empresasFragment_to_empresaFragment));
        b.listaFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.insert(new Libro("Libro1","Ken Follet","2015","portada","Esta sinopsis"));
            }
        });

        //TODO snackbar de confirmacion
        // Se crea el helper.
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    // Cuando se detecta un gesto swipe to dismiss.
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        vm.delete(listAdapter.getItem(viewHolder.getAdapterPosition()));
                    }
                });
        // Se enlaza con el RecyclerView.
        itemTouchHelper.attachToRecyclerView(b.lstLista);
    }
}
