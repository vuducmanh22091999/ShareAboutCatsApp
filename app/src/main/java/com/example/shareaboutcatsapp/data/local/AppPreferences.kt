package com.example.shareaboutcatsapp.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class AppPreferences(context: Context) {
    companion object {
        const val LOGIN_SAVE_EMAIL = "login_save_email"
        const val LOGIN_SAVE_USER_NAME = "login_save_user_name"
        const val LOGIN_SAVE_PHOTO_URL = "login_save_photo_url"
        const val LOGIN_SAVE_PHONE_NUMBER = "login_save_phone_number"
    }

    private val sharedPreferences =
        context.getSharedPreferences("db_share_about_cats_app", MODE_PRIVATE)

    fun setLoginEmail(email: String) {
        sharedPreferences.edit().putString(LOGIN_SAVE_EMAIL, email).apply()
    }

    fun getLoginEmail(): String? {
        return sharedPreferences.getString(LOGIN_SAVE_EMAIL, null)
    }

    fun setLoginUserName(userName: String) {
        sharedPreferences.edit().putString(LOGIN_SAVE_USER_NAME, userName).apply()
    }

    fun getLoginUserName(): String? {
        return sharedPreferences.getString(LOGIN_SAVE_USER_NAME, null)
    }

    fun setLoginAvatar(photoUrl: String) {
        sharedPreferences.edit().putString(LOGIN_SAVE_PHOTO_URL, photoUrl).apply()
    }

    fun getLoginAvatar(): String? {
        return sharedPreferences.getString(LOGIN_SAVE_PHOTO_URL, null)
    }

    fun setLoginPhoneNumber(phoneNumber: String) {
        sharedPreferences.edit().putString(LOGIN_SAVE_PHONE_NUMBER, phoneNumber).apply()
    }

    fun getLoginPhoneNumber(): String? {
        return sharedPreferences.getString(LOGIN_SAVE_PHONE_NUMBER, null)
    }

}