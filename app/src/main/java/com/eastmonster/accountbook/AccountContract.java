package com.eastmonster.accountbook;

import static android.app.Activity.*;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AccountContract extends ActivityResultContract<String, Account> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, String input) {
        return new Intent(context, NewAccountActivity.class);
    }

    @Override
    public Account parseResult(int resultCode, @Nullable Intent intent) {
        if (resultCode == RESULT_OK) {
            return (Account) intent.getSerializableExtra(NewAccountActivity.EXTRA_REPLY);
        }
        return null;
    }
}
