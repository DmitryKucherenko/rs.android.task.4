package com.fatalzero.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.fatalzero.R

class SettingsFragment : PreferenceFragmentCompat() {

    interface CallBack{
         fun openListFragment()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)
    }
}