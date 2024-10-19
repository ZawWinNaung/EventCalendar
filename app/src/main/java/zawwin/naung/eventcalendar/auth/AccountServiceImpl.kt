package zawwin.naung.eventcalendar.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import zawwin.naung.eventcalendar.domain.core.MyState
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {

    override suspend fun signInWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.signInWithCredential(firebaseCredential).await()
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

}