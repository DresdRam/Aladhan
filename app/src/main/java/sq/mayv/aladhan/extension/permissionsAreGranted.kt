package sq.mayv.aladhan.extension

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.permissionsAreGranted(permissions: List<String>): Boolean {
    var allIsGranted = true

    for (permission in permissions) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            allIsGranted = false
            break
        }
    }

    return allIsGranted
}