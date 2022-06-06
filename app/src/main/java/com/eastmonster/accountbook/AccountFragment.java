package com.eastmonster.accountbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eastmonster.accountbook.databinding.FragmentAccountBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    public static final Account DEFAULT_ACCOUNT = new Account(0, 0, 0, "");

    private static AccountViewModel mAccountViewModel;

    public static AccountViewModel getmAccountViewModel() {
        return mAccountViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        FloatingActionButton fabAdd = binding.fabAddAccount;

        final AccountListAdapter adapter = new AccountListAdapter(new AccountListAdapter.AccountDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAccountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        mAccountViewModel.getAllAccounts().observe(getViewLifecycleOwner(), accounts -> {
            adapter.submitList(accounts);
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountOperationActivity.class).putExtra("account", DEFAULT_ACCOUNT));
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}