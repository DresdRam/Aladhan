package sq.mayv.aladhan.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import sq.mayv.aladhan.model.Coordinates
import javax.inject.Inject

class LocationService @Inject constructor(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient

) : ILocationService {

    override fun getLastKnownLocation(): Flow<Coordinates?> = callbackFlow {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            trySend(null)
            return@callbackFlow
        }

        locationClient.lastLocation
            .addOnSuccessListener { location ->
            val latitude = location.latitude
            val longitude = location.longitude

            trySend(
                Coordinates(
                    latitude = latitude,
                    longitude = longitude
                )
            )
        }

        awaitClose{
            channel.close()
        }
    }
}