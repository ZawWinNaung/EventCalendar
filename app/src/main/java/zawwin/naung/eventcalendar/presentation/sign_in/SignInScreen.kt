package zawwin.naung.eventcalendar.presentation.sign_in

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import zawwin.naung.eventcalendar.domain.core.MyState
import zawwin.naung.eventcalendar.navigation.Home

@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {

    val state = viewModel.signInState.collectAsState()
    var showButton by remember { mutableStateOf(false) }

    val openAndPopUp = {
        showButton = false
        navController.navigate(Home)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("#auth", Firebase.auth.currentUser?.isAnonymous.toString())
        when (state.value) {
            MyState.Initial -> {
                showButton = true
            }

            is MyState.Loading -> {
                showButton = false
                CircularProgressIndicator()
            }

            is MyState.Error -> {
                showButton = true
            }

            MyState.Success -> {
                showButton = false
            }
        }

        if (showButton) {
            AuthenticationButton(
                buttonText = "Sign In with Google",
                onGetCredentialResponse = { credential ->
                    viewModel.onSignInWithGoogle(credential, openAndPopUp)
                })
        }
    }
}