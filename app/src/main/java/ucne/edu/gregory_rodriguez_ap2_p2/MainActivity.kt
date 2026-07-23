package ucne.edu.gregory_rodriguez_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberNavBackStack
import dagger.hilt.android.AndroidEntryPoint
import ucne.edu.gregory_rodriguez_ap2_p2.presentation.navigation.AppNavDisplay
import ucne.edu.gregory_rodriguez_ap2_p2.presentation.navigation.Screen
import ucne.edu.gregory_rodriguez_ap2_p2.ui.theme.Gregory_Rodriguez_AP2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gregory_Rodriguez_AP2_P2Theme {

                val backStack = rememberNavBackStack(Screen.GastoList)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavDisplay(
                        backStack = backStack,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}