package com.fatalzero.ui

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.fatalzero.R



class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
     var  sharedPreferences:SharedPreferences? = null
     var listPreference: ListPreference?=null
     var switchPreference: SwitchPreference?=null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)
        listPreference=findPreference<ListPreference>("sort_by")
        switchPreference=findPreference<SwitchPreference>("switch")
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
        listPreference?.summary="sort by "+sharedPreferences?.getString("sort_by", "")
        switchPreference?.summary = if(sharedPreferences?.getBoolean("switch",true) == true)"Room" else "Cursor"

    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key) {
            "sort_by"->  listPreference?.summary="sort by "+sharedPreferences?.getString("sort_by", "brand")
            "switch"->   switchPreference?.summary = if(sharedPreferences?.getBoolean("switch",true) == true)"Room" else "Cursor"
        }
    }

}