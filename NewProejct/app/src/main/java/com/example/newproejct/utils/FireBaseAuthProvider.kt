package com.example.newproejct.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class FireBaseAuthProvider (private val context: Context) {
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var verificationId: String

    private lateinit var phoneCallBacksListener: PhoneCallBacksListener
    val firebaseAuth = FirebaseAuth.getInstance()

    fun setPhoneCallbacksListener(listener: PhoneCallBacksListener){
        this.phoneCallBacksListener = listener
    }

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                    phoneCallBacksListener.onVerificationCodeDetected(code)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                when(e){
                    is FirebaseAuthInvalidCredentialsException -> phoneCallBacksListener.onVerificationFailed(e.message?:" ")
                    is FirebaseTooManyRequestsException -> phoneCallBacksListener.onVerificationFailed(e.message?:" ")
                    else -> phoneCallBacksListener.onVerificationFailed(e.message?:" ")
                }
            }
        }
    fun sendVerificationCode(phone: String) {
        Log.d("jyl", "context: ${(context)}")
        Log.d("jyl", "getActivity: ${getActivity(context)}")
        val options = PhoneAuthOptions.newBuilder()
            .setPhoneNumber(phone.trim())
            .setTimeout(30, TimeUnit.SECONDS)
            .setActivity(getActivity(context)!!)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun getActivity(context: Context?): Activity? {
        if (context == null) {
            return null
        } else if (context is ContextWrapper) {
            return if (context is Activity) {
                context
            } else {
                getActivity(context.baseContext)
            }
        }
        return null
    }

    fun verifyVerificationCode(code: String): PhoneAuthCredential {
        return PhoneAuthProvider.getCredential(verificationId, code)
    }

    fun isUserVerified(): Boolean {
        return firebaseAuth.currentUser != null
    }

}

interface PhoneCallBacksListener{
    fun onVerificationCompleted()
    fun onVerificationCodeDetected(code: String)
    fun onVerificationFailed(message: String)
}