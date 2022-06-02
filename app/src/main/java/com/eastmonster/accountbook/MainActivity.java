package com.eastmonster.accountbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final Account DEFAULT_ACCOUNT = new Account(0, 0, 0, "");
    private static AccountViewModel mAccountViewModel;

    public static AccountViewModel getmAccountViewModel() {
        return mAccountViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fabAdd = findViewById(R.id.fabAddAccount);
        FloatingActionButton fabStat = findViewById(R.id.fabStatButton);

        final AccountListAdapter adapter = new AccountListAdapter(new AccountListAdapter.AccountDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAccountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        mAccountViewModel.getAllAccounts().observe(this, accounts -> {
            adapter.submitList(accounts);
        });

        fabAdd.setOnClickListener(view -> {
            startActivity(new Intent(this, NewAccountActivity.class).putExtra("account", DEFAULT_ACCOUNT));
        });

        fabStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StatActivity.class));
            }
        });
    }
//    原来使用的返回数据方法
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