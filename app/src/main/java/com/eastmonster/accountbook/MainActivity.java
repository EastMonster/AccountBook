package com.eastmonster.accountbook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_ACCOUNT_ACTIVITY_REQUEST_CODE = 1;

    public static final Account DEFAULT_ACCOUNT = new Account(0,0,0);
    private static AccountViewModel mAccountViewModel;

    public static AccountViewModel getmAccountViewModel() {
        return mAccountViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fab = findViewById(R.id.fabAddAccount);

        final AccountListAdapter adapter = new AccountListAdapter(new AccountListAdapter.AccountDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAccountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        mAccountViewModel.getAllAccounts().observe(this, accounts -> {
            adapter.submitList(accounts);
        });

        fab.setOnClickListener(view -> {
            startActivity(new Intent(this, NewAccountActivity.class).putExtra("account", DEFAULT_ACCOUNT));
        });
    }
//
//    ActivityResultLauncher<String> launcher = registerForActivityResult(new AccountContract(),
//        new ActivityResultCallback<Account>() {
//        @Override
//        public void onActivityResult(Account result) {
//            if (result == null) {
//                Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_SHORT).show();
//            } else {
//                mAccountViewModel.insert(result);
//            }
//        }
//    });
}