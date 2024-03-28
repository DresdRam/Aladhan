package sq.mayv.aladhan.ui.screens.load_prayers.viewstate

sealed interface PermissionViewState {
    data object CheckingPermissions : PermissionViewState
    data object GrantedPermissions : PermissionViewState
    data object RevokedPermissions : PermissionViewState
}