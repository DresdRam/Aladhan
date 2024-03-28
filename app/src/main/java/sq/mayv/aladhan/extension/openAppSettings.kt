package sq.mayv.aladhan.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult

fun Activity.openAppSettings(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    )
    launcher.launch(intent)
}