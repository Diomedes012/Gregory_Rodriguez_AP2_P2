package ucne.edu.gregory_rodriguez_ap2_p2.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ucne.edu.gregory_rodriguez_ap2_p2.presentation.detail.BorrameDetailScreen
import ucne.edu.gregory_rodriguez_ap2_p2.presentation.list.BorrameListScreen

@Composable
fun AppNavDisplay(
    backStack: NavBackStack<NavKey>,
    innerPadding: PaddingValues
){
    NavDisplay(
        backStack = backStack,
        modifier = Modifier.padding(innerPadding),
        entryProvider = entryProvider {
            entry<Screen.List>{
                BorrameListScreen(
                    onClick = {id ->
                        backStack.add(Screen.Detail(id))
                    }
                )
            }

            entry<Screen.Detail>{key ->
                BorrameDetailScreen(
                    onBack = {
                        if(backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    }
                )
            }
        }
    )
}