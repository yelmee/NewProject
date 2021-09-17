package com.example.newproejct

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential

interface AuthViewInteractor: ViewInteractor {
    fun showSnackBarMessage(message: String)

    fun goToGoalActivity()

    fun startSMSListner()

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential)

    fun getActivity(): Activity
}