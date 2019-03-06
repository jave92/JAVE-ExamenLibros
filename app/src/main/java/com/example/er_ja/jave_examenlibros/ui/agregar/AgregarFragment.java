package com.example.er_ja.jave_examenlibros.ui.agregar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.er_ja.jave_examenlibros.R;
import com.example.er_ja.jave_examenlibros.base.YesNoDialogFragment;
import com.example.er_ja.jave_examenlibros.data.RepositoryImpl;
import com.example.er_ja.jave_examenlibros.data.local.entity.Libro;
import com.example.er_ja.jave_examenlibros.databinding.AgregarFragmentBinding;
import com.example.er_ja.jave_examenlibros.utils.KeyboardUtils;
import com.example.er_ja.jave_examenlibros.utils.TextViewUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

public class AgregarFragment extends Fragment implements YesNoDialogFragment.Listener {
    private AgregarFragmentBinding b;
    private AgregarFragmentViewModel vm;
    private static final String TAG_DIALOG_FRAGMENT = "TAG_DIALOG_FRAGMENT";
    private static final int RC_DIALOG_FRAGMENT = 1;

    public AgregarFragment() {
    }

    public static AgregarFragment newInstance() {
        AgregarFragment agregarFragment = new AgregarFragment();
        return agregarFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = AgregarFragmentBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(AgregarFragment.this, new AgregarFragmentViewModelFactory(new RepositoryImpl(getActivity().getApplication()))).get(AgregarFragmentViewModel.class);
        setupViews();
    }

    private void setupViews() {
        setHasOptionsMenu(false);
        TextViewUtils.addAfterTextChangedListener(b.txtTitulo, s -> checkIsValidTitulo());
        TextViewUtils.addAfterTextChangedListener(b.txtAutor, s -> checkIsValidAutor());
        TextViewUtils.addAfterTextChangedListener(b.txtFecha, s -> checkIsValidFecha());
        b.agregarFab.setOnClickListener(v -> {
            checkIsValidForm();
            if (isValidForm()) {
                showConfirmationDialog();
            }
        });
    }

    private void checkIsValidForm() {
        checkIsValidTitulo();
        checkIsValidAutor();
        checkIsValidFecha();
    }

    private boolean isValidForm() {
        return isValidTitulo() && isValidAutor() && isValidFecha();
    }

    private boolean isValidTitulo() {
        return b.txtTitulo.getText() != null && !TextUtils.isEmpty(b.txtTitulo.getText().toString());
    }

    private void checkIsValidTitulo() {
        b.tilTitulo.setError(!isValidTitulo() ? getString(R.string.main_required_field) : null);
    }
    private boolean isValidAutor() {
        return b.txtAutor.getText() != null && !TextUtils.isEmpty(b.txtAutor.getText().toString());
    }

    private void checkIsValidAutor() {
        b.tilAutor.setError(!isValidAutor() ? getString(R.string.main_required_field) : null);
    }
    private boolean isValidFecha() {
        return b.txtFecha.getText() != null && !TextUtils.isEmpty(b.txtFecha.getText().toString());
    }

    private void checkIsValidFecha() {
        b.tilFecha.setError(!isValidFecha() ? getString(R.string.main_required_field) : null);
    }


    private void showConfirmationDialog() {
        YesNoDialogFragment dialogFragment = YesNoDialogFragment.newInstance(
                getString(R.string.main_fragment_confirm_deletion),
                getString(R.string.main_fragment_sure),
                getString(R.string.main_fragment_yes),
                getString(R.string.main_fragment_no));
        dialogFragment.setTargetFragment(this, RC_DIALOG_FRAGMENT);
        //noinspection ConstantConditions
        dialogFragment.show(getFragmentManager(),
                TAG_DIALOG_FRAGMENT);
    }


    @Override
    public void onPositiveButtonClick(DialogInterface dialog) {
        guardarLibro();
    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialog) {

    }

    private void guardarLibro() {
        KeyboardUtils.hideSoftKeyboard(getActivity());
        vm.insert(new Libro(
                b.txtTitulo.getText().toString(),
                b.txtAutor.getText().toString(),
                b.txtFecha.getText().toString(),
                b.txtPortada.getText().toString(),
                b.txtSinopsis.getText().toString()));
        Toast.makeText(getContext(), "Libro guardado", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(getView()).navigate(R.id.listaFragment);
    }
}
