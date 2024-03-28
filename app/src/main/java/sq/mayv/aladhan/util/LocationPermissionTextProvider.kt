package sq.mayv.aladhan.util

import sq.mayv.aladhan.data.IPermissionTextProvider


class LocationPermissionTextProvider : IPermissionTextProvider {
    override fun getDescription(): String {
        return "Aladhan needs access to your location in order to load the monthly prayer times according to your city."
    }
}