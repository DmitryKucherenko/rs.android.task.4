package com.fatalzero.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.fatalzero.R



class SettingsFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }


}