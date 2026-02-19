package com.example.viikko6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.viikko6.data.local.AppDatabase
import com.example.viikko6.data.repository.TaskRepository
import com.example.viikko6.navigation.ROUTE_CALENDAR
import com.example.viikko6.navigation.ROUTE_HOME
import com.example.viikko6.view.CalendarScreen
import com.example.viikko6.view.HomeScreen
import com.example.viikko6.viewmodel.TaskViewModel
import com.example.viikko6.view.theme.Viikko6Theme
import com.example.viikko6.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = TaskRepository(database.taskDao())
        val factory = TaskViewModelFactory(repository)

        setContent {
            Viikko6Theme {
                val navController = rememberNavController()
                val taskViewModel: TaskViewModel = viewModel(factory = factory)

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