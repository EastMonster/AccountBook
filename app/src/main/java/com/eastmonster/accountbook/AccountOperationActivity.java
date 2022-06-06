package com.eastmonster.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class AccountOperationActivity extends AppCompatActivity {

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
        EditText remark = findViewById(R.id.remarkEditText);

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        mAccountViewModel = AccountFragment.getmAccountViewModel(); // !!!

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
            if (mEditAmountView.getText().toString().equals("") ||
            mEditAmountView.getText().toString().equals("."))
                Toast.makeText(this, "请输入一个有效的数字", Toast.LENGTH_SHORT).show();
            else
                mAccountViewModel.insert(new Account(typePos, Math.max(0.01,
                        Double.parseDouble(mEditAmountView.getText().toString())),
                        date.getTime(), remark.getText().toString()));
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0); // 完成后收回软键盘
            finish();
        });

        deButton.setOnClickListener(view -> {
            mAccountViewModel.delete(typeInfo);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            finish();
        });

        if (typeInfo.getTime() == 0L) {
            spinner.setSelection(1);
        } else { // 修改行为
            spinner.setSelection(typeInfo.getType());
            labelText.setText(R.string.modify_account);
            mEditAmountView.setText(String.format("%.2f", typeInfo.getAmount()));
            remark.setText(typeInfo.getRemark());
            button.setOnClickListener((view) -> {
                mAccountViewModel.update(new Account(typeInfo.getId(), typePos, Math.max(0.01,
                        Double.parseDouble(mEditAmountView.getText().toString())),
                        typeInfo.getTime(), remark.getText().toString()));
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                finish();
            });
            deButton.setVisibility(View.VISIBLE);
        }

        mEditAmountView.requestFocus(); // 自动获得焦点

        imm.showSoftInput(mEditAmountView, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}