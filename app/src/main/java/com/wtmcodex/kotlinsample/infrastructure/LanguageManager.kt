package com.wtmcodex.kotlinsample.infrastructure

import android.content.Context
import android.preference.PreferenceManager

import java.util.Locale

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageManager @Inject
constructor(internal var mContext: Context) {

    companion object {
        // fields
        private val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    }

    var mCurrentLanguage: String? = ""
        internal set


    init {
        initialize(mContext)
        setCurrentLanguage()
    }

    fun initialize(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(lang)
    }


    /**
     * This override would not toggle the given language , rather would blindly wrap our application around it.
     *
     * @param language
     * @return
     */

    fun setLocale(language: String?): Context {
        persist(mContext, language)

        // update current language
        reportLanguageChange(language)

        // update device language
        return updateResourcesLegacy(mContext, language)
    }

    internal fun reportLanguageChange(language: String?) {

        if (mCurrentLanguage == null) {
            mCurrentLanguage = language
        } else {
            if (mCurrentLanguage != language) {
                mCurrentLanguage = language // store the language change in memory . Dont worry the change has also being persisted in the shared prefs.
            }
        }

    }

    // this method is used to update deice configuration
    private fun updateResourcesLegacy(context: Context, language: String?): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }

    // set current language
    private fun setCurrentLanguage(){

        if (mCurrentLanguage == null) {
            val lang = getPersistedData(mContext, Locale.getDefault().language)  //will return the cellphone default if there is no language saved in prefs
            mCurrentLanguage = lang
        }

    }

    // persist data in shared preferences
    private fun persist(context: Context, language: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()

        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    fun getPersistedData(context: Context, defaultLanguage: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
    }

}
