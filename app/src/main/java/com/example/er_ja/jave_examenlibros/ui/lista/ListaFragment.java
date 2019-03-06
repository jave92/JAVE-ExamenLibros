package com.example.er_ja.jave_examenlibros.ui.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.er_ja.jave_examenlibros.R;
import com.example.er_ja.jave_examenlibros.RecyclerViewOnItemClickListener;
import com.example.er_ja.jave_examenlibros.data.RepositoryImpl;
import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;
import com.example.er_ja.jave_examenlibros.databinding.ListaFragmentBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
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
    private BottomSheetBehavior<NestedScrollView> bsb;
    private NestedScrollView panel;

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
        setHasOptionsMenu(true);
        vm.getLibros().observe(this, lista -> {
            listAdapter.submitList(lista);
            if (lista.isEmpty()) {
                b.lstLista.setVisibility(View.GONE);
                b.lblEmptyView.setVisibility(View.VISIBLE);
            }
            else {
                b.lstLista.setVisibility(View.VISIBLE);
                b.lblEmptyView.setVisibility(View.GONE);
            }
        });
    }

    private void setupViews() {
        panel = b.bsInclude.bottomSheet;
        bsb = BottomSheetBehavior.from(panel);
        listAdapter = new ListaFragmentAdapter(position -> setContent(listAdapter.getItem(position)));
        b.lstLista.setHasFixedSize(true);
        b.lstLista.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.lstLista_columns)));
        b.lstLista.setItemAnimator(new DefaultItemAnimator());
        b.lstLista.setAdapter(listAdapter);
        b.listaFab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listaFragment_to_agregarFragment));
        b.lblEmptyView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listaFragment_to_agregarFragment));

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
                        bsb.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        vm.delete(listAdapter.getItem(viewHolder.getAdapterPosition()));
                    }


                });
        // Se enlaza con el RecyclerView.
        itemTouchHelper.attachToRecyclerView(b.lstLista);
    }


    private void setContent(Libro libro) {
        bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
        b.bsInclude.bsTitulo.setText(libro.getTitulo());
        b.bsInclude.bsSinopsis.setText(libro.getSinopsis().equals("")?"<Sinopsis no disponible>":libro.getSinopsis());
    }
}
