package zawwin.naung.eventcalendar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import zawwin.naung.eventcalendar.presentation.home.HomeScreen
import zawwin.naung.eventcalendar.presentation.sign_in.SignInScreen

@Composable
fun MyNavigationHost() {
    val navController = rememberNavController()

//    if (Firebase.auth.currentUser.)

    NavHost(navController = navController, startDestination = SignIn) {
        composable<SignIn> {
            SignInScreen(navController)
        }
        composable<Home> {
            HomeScreen()
        }
    }

}