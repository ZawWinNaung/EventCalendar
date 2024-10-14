package zawwin.naung.eventcalendar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zawwin.naung.eventcalendar.presentation.home.HomeScreen

@Composable
fun MyNavigationHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =  Home){
        composable<Home> {
            HomeScreen()
        }
    }

}