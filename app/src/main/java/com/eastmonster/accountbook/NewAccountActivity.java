package com.eastmonster.accountbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class NewAccountActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.myapplication2.accountlistsql.REPLY";

    private AccountViewModel mAccountViewModel;

    private EditText mEditAmountView;

    private int typePos = 0;
    private final Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        Intent intent = getIntent();
        Account typeInfo = (Account) intent.getSerializableExtra("account");

        mEditAmountView = findViewById(R.id.amountNumberDecimal);
        final Button button = findViewById(R.id.okButton);
        final Button deButton = findViewById(R.id.deleteButton);
        Spinner spinner = findViewById(R.id.spinner);
        TextView labelText = findViewById(R.id.labelText);

        //final AccountListAdapter adapter = new AccountListAdapter(new AccountListAdapter.AccountDiff());
        mAccountViewModel = MainActivity.getmAccountViewModel(); // !!
        //mAccountViewModel.getAllAccounts().observe(this, adapter::submitList);

        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_dropdown_item); // 初始化微调框
        spinner.setAdapter(sAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                typePos = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        button.setOnClickListener((view) -> {
            mAccountViewModel.insert(new Account(typePos, Math.max(0.01, Double.parseDouble(mEditAmountView.getText().toString())), date.getTime()));
            finish();
        });

        deButton.setOnClickListener(view -> {
            mAccountViewModel.delete(typeInfo);
            finish();
        });

        if (typeInfo.getTime() == 0L) {
            spinner.setSelection(1);
        } else { // 修改行为
            spinner.setSelection(typeInfo.getType());
            labelText.setText(R.string.modify_account);
            mEditAmountView.setText(String.format("%.2f", typeInfo.getAmount()));
            button.setOnClickListener((view) -> {
                mAccountViewModel.update(new Account(typeInfo.getId(), typePos, Math.max(0.01, Double.parseDouble(mEditAmountView.getText().toString())), typeInfo.getTime()));
                finish();
            });
            deButton.setVisibility(View.VISIBLE);
        }
//            Intent replyIntent = new Intent();

//            if (TextUtils.isEmpty(mEditWordView.getText())) {
//                setResult(RESULT_CANCELED, replyIntent);
//            } else {
//                String amount = mEditWordView.getText().toString();
//                try {
//                    replyIntent.putExtra(EXTRA_REPLY, new Account(typePos, Double.parseDouble(amount), date.getTime()));
//                } catch (NumberFormatException nfe) {
//                    setResult(RESULT_CANCELED, replyIntent);
//                    finish();
//                }
//                setResult(RESULT_OK, replyIntent);
//            }
    }
}