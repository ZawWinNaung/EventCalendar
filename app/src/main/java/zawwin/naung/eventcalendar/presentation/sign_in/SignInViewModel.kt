package zawwin.naung.eventcalendar.presentation.sign_in

import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import zawwin.naung.eventcalendar.auth.AccountService
import zawwin.naung.eventcalendar.domain.core.MyState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    private val _signInState: MutableStateFlow<MyState> = MutableStateFlow(MyState.Initial)
    val signInState: StateFlow<MyState>
        get() = _signInState

    fun onSignInWithGoogle(credential: Credential, callOnSuccess : () -> Unit) {
        viewModelScope.launch {
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                _signInState.value = MyState.Loading
                accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                callOnSuccess()
            }
        }
    }
}