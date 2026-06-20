package net.kdt.pojavlaunch.prefs.screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import git.artdeell.mojo.R;

public class LauncherPreferenceUIFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_ui, rootKey);

        setupColorPreference("ui_color_background", "#000000");
        setupColorPreference("ui_color_button", "#D32F2F");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.BLACK);
    }

    private void setupColorPreference(final String key, final String defaultHex) {
        Preference preference = findPreference(key);
        if (preference != null) {
            String currentVal = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString(key, defaultHex);
            preference.setSummary(currentVal);

            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference pref) {
                    showColorInputDialog(key, pref, defaultHex);
                    return true;
                }
            });
        }
    }

    private void showColorInputDialog(final String key, final Preference pref, String defaultHex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(pref.getTitle());

        final EditText input = new EditText(requireContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        
        String currentVal = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString(key, defaultHex);
        input.setText(currentVal);
        builder.setView(input);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hex = input.getText().toString().trim();
                try {
                    Color.parseColor(hex);
                    PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString(key, hex).apply();
                    pref.setSummary(hex);
                } catch (Exception e) {
                    input.setError("Invalid Hex Format (e.g. #000000)");
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
