package one.xord.android.utils

import android.content.Context

class PreferenceHelper (context : Context) {



    val PREFERENCE_NAME = "SharedPreference"
    val PREFERENCE_IS_NIC_VERIFIED="LoginCount"


    val preference = context.getSharedPreferences(PREFERENCE_NAME ,Context.MODE_PRIVATE);

    fun getVerificationStatus() : Boolean{
        return preference.getBoolean(PREFERENCE_IS_NIC_VERIFIED ,false)
    }

    fun setVerificationStatus (count : Boolean)  {

        val editor = preference.edit()
        editor.putBoolean(PREFERENCE_IS_NIC_VERIFIED,true)
        editor.apply()

    }
}