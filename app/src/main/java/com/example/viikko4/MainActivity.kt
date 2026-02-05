package com.example.viikko4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.viikko4.navigation.ROUTE_CALENDAR
import com.example.viikko4.navigation.ROUTE_HOME
import com.example.viikko4.view.CalendarScreen
import com.example.viikko4.view.HomeScreen
import com.example.viikko4.viewmodel.TaskViewModel
import com.example.viikko4.view.theme.Viikko4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko4Theme {

                val navController = rememberNavController()
                val taskViewModel: TaskViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = ROUTE_HOME
                ) {
                    composable(ROUTE_HOME) {
                        HomeScreen(
                            viewModel = taskViewModel,
                            onOpenCalendar = {
                                navController.navigate(ROUTE_CALENDAR)
                            }
                        )
                    }
                    composable(ROUTE_CALENDAR) {
                        CalendarScreen(
                            viewModel = taskViewModel,
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
