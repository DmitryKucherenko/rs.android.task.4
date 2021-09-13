package com.fatalzero.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.fatalzero.R

private const val SORT_BY_KEY = "sort_by"
private const val SWITCH_KEY = "switch"
private const val ROOM = "ROOM"
private const val CURSOR = "switch"
private const val BRAND_SORT = "brand"


class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    var sharedPreferences: SharedPreferences? = null
    var listPreference: ListPreference? = null
    var switchPreference: SwitchPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        listPreference = findPreference<ListPreference>(SORT_BY_KEY)
        switchPreference = findPreference<SwitchPreference>(SWITCH_KEY)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
        listPreference?.summary =
            getString(R.string.sort_by_word) + " " + sharedPreferences?.getString(SORT_BY_KEY, "")
        switchPreference?.summary =
            if (sharedPreferences?.getBoolean(SWITCH_KEY, true) == true) ROOM else CURSOR

    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            SORT_BY_KEY -> listPreference?.summary =
                SORT_BY_KEY + sharedPreferences?.getString(SORT_BY_KEY, BRAND_SORT)
            SWITCH_KEY -> switchPreference?.summary =
                if (sharedPreferences?.getBoolean(SWITCH_KEY, true) == true) ROOM else CURSOR

        }
    }

}