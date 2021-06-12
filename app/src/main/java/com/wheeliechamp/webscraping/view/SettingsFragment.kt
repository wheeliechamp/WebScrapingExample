package com.wheeliechamp.webscraping.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.wheeliechamp.webscraping.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}