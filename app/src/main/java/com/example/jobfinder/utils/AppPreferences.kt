package com.example.jobfinder.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class AppPreferences @Inject constructor(var context: Context) {
//    private var preferences: SharedPreferences = context.getSharedPreferences(
//        com.example.subham.pocketbus.constants.Preference.PREFERENCE_NAME, Context
//            .MODE_PRIVATE
//    )
//    var userCredentials: LoginResponseEntity?
//        get() {
//            val data =
//                preferences.getString(com.example.subham.pocketbus.constants.Preference.PREF_USER_CREDENTIALS, null)
//            return if (data == null) null else Gson().fromJson(data, LoginResponseEntity::class.java)
//        }
//        set(credentials) {
//            preferences.edit().putString(
//                com.example.subham.pocketbus.constants.Preference.PREF_USER_CREDENTIALS,
//                Gson().toJson(credentials)
//            ).apply()
//        }
}