package com.presldn.empowerment.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.presldn.empowerment.R


class AboutFragment : PreferenceFragmentCompat() {


    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        when(preference?.title) {
            getText(R.string.github) -> {
                val url = getString(R.string.github_repo_link)
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }

        }

        return super.onPreferenceTreeClick(preference)

    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_about, rootKey)
    }
}