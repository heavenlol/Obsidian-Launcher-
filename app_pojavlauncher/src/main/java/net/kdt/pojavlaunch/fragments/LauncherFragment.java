package net.kdt.pojavlaunch.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import git.artdeell.mojo.R;

public class LauncherFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launcher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean isCustomEnabled = PreferenceManager.getDefaultSharedPreferences(requireContext())
                .getBoolean("ui_customization_enabled", false);

        if (isCustomEnabled) {
            String bgHex = PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .getString("ui_color_background", "#000000");
            String btnHex = PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .getString("ui_color_button", "#D32F2F");

            try {
                int bgColor = Color.parseColor(bgHex);
                View mainBg = view.findViewById(R.id.fragment_menu_main);
                if (mainBg != null) mainBg.setBackgroundColor(bgColor);

                View bottomBarBg = view.findViewById(R.id._background_display_view);
                if (bottomBarBg != null) bottomBarBg.setBackgroundColor(bgColor);
            } catch (Exception ignored) {}

            try {
                int buttonColor = Color.parseColor(btnHex);
                
                int[] buttonIds = {
                    R.id.news_button,
                    R.id.social_media_button,
                    R.id.custom_control_button,
                    R.id.install_jar_button,
                    R.id.share_logs_button,
                    R.id.open_files_button,
                    R.id.play_button
                };

                for (int id : buttonIds) {
                    View btn = view.findViewById(id);
                    if (btn != null) {
                        if (btn.getBackground() != null) {
                            btn.getBackground().setColorFilter(buttonColor, PorterDuff.Mode.SRC_ATOP);
                        } else {
                            btn.setBackgroundColor(buttonColor);
                        }
                    }
                }
            } catch (Exception ignored) {}
        }
    }
}
