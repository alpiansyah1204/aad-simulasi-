package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import com.dicoding.courseschedule.util.NightMode
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        findPreference<ListPreference>(getString(R.string.pref_key_dark))?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                val themeSelected = when ((newValue as String).toUpperCase(Locale.ROOT)) {
                    NightMode.ON.name -> NightMode.ON
                    NightMode.OFF.name -> NightMode.OFF
                    else -> NightMode.AUTO
                }
                updateTheme(themeSelected.value)

                true
            }

        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val prefNotification = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        prefNotification?.setOnPreferenceChangeListener { preference, newValue ->

            val dailyReminder = DailyReminder()
            when (newValue as Boolean) {
                true -> {
                    context?.let { it -> dailyReminder.setDailyReminder(it) }
                }
                false -> {
                    context?.let { it -> dailyReminder.cancelAlarm(it) }
                }
            }

            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}