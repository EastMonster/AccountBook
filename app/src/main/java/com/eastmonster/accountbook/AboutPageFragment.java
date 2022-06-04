package com.eastmonster.accountbook;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class AboutPageFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.about_preferences, rootKey);
        MediaPlayer mMediaPlayer = MediaPlayer.create(getContext(), R.raw.mus_ohyes);
        Preference version = findPreference("version");
        version.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                mMediaPlayer.start();
                Toast.makeText(getActivity(), "Ready to see Mettaton EX?", Toast.LENGTH_SHORT).show(); // unavailable
                return true;
            }
        });
    }
}