package com.eastmonster.accountbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceFragmentCompat;

import androidx.preference.Preference;

import com.eastmonster.accountbook.database.AccountRepository;


public class SettingFragment extends PreferenceFragmentCompat {

    AccountRepository ar;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference cleanAll = findPreference("clean_all");

        cleanAll.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                showDialog();
                return true;
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("删除所有账目");
        alertDialog.setMessage("本操作无法逆转，是否继续？");

        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ar = new AccountRepository(getActivity().getApplication());
                ar.nukeTable();
                dialogInterface.dismiss();
            }
        });

        alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }
}