package one.xord.android

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs
import one.xord.android.statics.*

/**
 * Created by sami on 11/17/18.
 */

class DApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize the Prefs class
        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
        getPrefs()
    }

    private fun getPrefs() {
        IS_SIGNED_UP = Prefs.getBoolean(IS_SIGNED_UP_KEY, false)
        IS_VERIFIED = Prefs.getBoolean(IS_VERIFIED_KEY, false)
        NIC_NUMBER = Prefs.getString(NIC_NUMBER_KEY, "4220100000000")
        NAME = Prefs.getString(NAME_KEY, "Salman Khan")
        FATHER_NAME = Prefs.getString(FATHER_NAME_KEY, "Zafar Khan")
        GENDER = Prefs.getString(GENDER_KEY, "Male")
        COUNTRY = Prefs.getString(COUNTRY_KEY, "Pakistan")
        DATE_OF_BIRTH = Prefs.getString(DATE_OF_BIRTH_KEY, "24.11.1996")
        DATE_OF_ISSUE = Prefs.getString(DATE_OF_ISSUE_KEY, "24.11.2015")
        DATE_OF_EXPIRY = Prefs.getString(DATE_OF_EXPIRY_KEY, "24.11.2025")
        PHONE_NUMBER = Prefs.getString(PHONE_NUMBER_KEY, "")
    }
}