package sq.mayv.aladhan.util

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREFERENCE_NAME = "AEGYPTUS"

    private const val BASE_URL = "BASE_URL"
    private const val PRAYERS_DOWNLOAD_IS_NEEDED = "PRAYERS_DOWNLOAD_IS_NEEDED"

    fun getPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = edit()
        operation(edit)
        edit.apply()
    }

    var SharedPreferences.baseUrl
        get() = getString(BASE_URL, "http://api.aladhan.com/")!!
        set(value) {
            edit {
                it.putString(BASE_URL, value)
            }
        }

    var SharedPreferences.prayersDownloadIsNeeded
        get() = getBoolean(PRAYERS_DOWNLOAD_IS_NEEDED, true)!!
        set(value) {
            edit {
                it.putBoolean(PRAYERS_DOWNLOAD_IS_NEEDED, value)
            }
        }

}