package zawwin.naung.eventcalendar.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import zawwin.naung.eventcalendar.domain.core.MyState

interface AccountService {

    suspend fun signInWithGoogle(idToken: String)
}