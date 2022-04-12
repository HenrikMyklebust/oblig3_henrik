package com.example.oblig3_henrik.ui.main.toolbar

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.oblig3_henrik.R.xml.root_preferences

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(root_preferences, rootKey)

    }
}