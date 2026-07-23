package ucne.edu.gregory_rodriguez_ap2_p2.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ucne.edu.gregory_rodriguez_ap2_p2.presentation.detail.GastoFormScreen
import ucne.edu.gregory_rodriguez_ap2_p2.presentation.list.GastoListScreen

@Composable
fun AppNavDisplay(
    backStack: NavBackStack<NavKey>,
    innerPadding: PaddingValues
) {
    NavDisplay(
        backStack = backStack,
        modifier = Modifier.padding(innerPadding),
        entryProvider = entryProvider {
            entry<Screen.GastoList> {
                GastoListScreen(
                    onGastoClick = { gastoId ->
                        backStack.add(Screen.GastoForm(gastoId))
                    },
                    onCreateGasto = {
                        backStack.add(Screen.GastoForm(0))
                    }
                )
            }
            entry<Screen.GastoForm> { key ->
                GastoFormScreen(
                    gastoId = key.id,
                    onNavigateBack = {
                        if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    }
                )
            }
        }
    )
}