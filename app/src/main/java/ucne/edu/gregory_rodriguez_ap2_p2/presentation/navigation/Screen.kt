package ucne.edu.gregory_rodriguez_ap2_p2.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen: NavKey {
    @Serializable
    data object GastoList : Screen()

    @Serializable
    data class GastoForm(val id: Int = 0) : Screen()
}