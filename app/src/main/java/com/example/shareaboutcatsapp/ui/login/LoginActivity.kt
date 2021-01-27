package com.example.shareaboutcatsapp.ui.login

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.local.share_preferences.AppPreferences
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_details_categories.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    private val RC_SIGN_IN = 1
    private lateinit var auth: FirebaseAuth
    private var callBackManager: CallbackManager? = null
    private lateinit var appPreferences: AppPreferences

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }

    override fun doViewCreated() {
        auth = Firebase.auth
        callBackManager = CallbackManager.Factory.create()
        appPreferences = AppPreferences(this@LoginActivity)

        initListener()
    }

    private fun checkWifi(): Boolean? {
        return appPreferences.getConnect()
    }

    private fun initListener() {
        linearLoginWithFacebook.setOnClickListener(this)
        linearLoginWithGoogle.setOnClickListener(this)
    }

    private fun loginWithFacebook() {
        LoginManager.getInstance()
            .logInWithReadPermissions(this@LoginActivity, listOf("public_profile", "email"))

        LoginManager.getInstance()
            .registerCallback(callBackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    handleFacebookAccessToken(result.accessToken)
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException?) {
                }

            })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userName = user?.displayName ?: ""
                    val email = user?.email ?: ""
                    val phoneNumber = user?.phoneNumber ?: ""
                    val photoUrl = user?.photoUrl ?: ""

                    appPreferences.setLoginUserName(userName)
                    appPreferences.setLoginEmail(email)
                    appPreferences.setLoginPhoneNumber(phoneNumber)
                    appPreferences.setLoginAvatar(photoUrl.toString())

                    switchMainScreen()

                } else {
                    Log.w("Login with Facebook", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun switchMainScreen() {
        showLoading()
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loginWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestScopes(Scope(Scopes.PLUS_LOGIN))
            .build()

        val googleSignClient = GoogleSignIn.getClient(this@LoginActivity, gso)
        val signInIntent = googleSignClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val email = user?.email ?: ""
                    val userName = user?.displayName ?: ""
                    val photoUrl = user?.photoUrl ?: ""
                    val phoneNumber = user?.phoneNumber ?: ""

                    appPreferences.setLoginEmail(email)
                    appPreferences.setLoginUserName(userName)
                    appPreferences.setLoginAvatar(photoUrl.toString())
                    appPreferences.setLoginPhoneNumber(phoneNumber)


                    switchMainScreen()
                } else {
                    Log.d(
                        getString(R.string.login_with_google),
                        getString(R.string.error_google),
                        task.exception
                    )
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.linearLoginWithFacebook -> {
                if (checkWifi() == true) {
                    loginWithFacebook()
                    hideLoading()
                } else {
                    Toasty.error(this, "Turn on wifi to Log in", Toasty.LENGTH_SHORT).show()
                }

            }
            R.id.linearLoginWithGoogle -> {
                if (checkWifi() == true) {
                    loginWithGoogle()
                    hideLoading()
                } else {
                    Toasty.error(this, "Turn on wifi to Log in", Toasty.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("Login Google", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("Login Google", "Google sign in failed", e)
            }
        }

        callBackManager?.onActivityResult(requestCode, resultCode, data)
    }
}