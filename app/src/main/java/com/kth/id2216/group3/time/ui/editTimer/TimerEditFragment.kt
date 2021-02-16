package com.kth.id2216.group3.time.ui.editTimer

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.kth.id2216.group3.time.R

class TimerEditFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}