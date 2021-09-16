package com.example.newproejct.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.newproejct.AuthViewInteractor
import com.example.newproejct.utils.FireBaseAuthProvider
import com.example.newproejct.utils.PhoneCallBacksListener
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    application: Application
) :
    BaseViewModel<AuthViewInteractor>(application), PhoneCallBacksListener {

    var selectedPhoneNumber = MutableLiveData<String>()
    var phone: String = ""
    private val context = getApplication<Application>().applicationContext
    private val firebaseAuthProvider = FireBaseAuthProvider(context)

    init {
        firebaseAuthProvider.setPhoneCallbacksListener(this)
        if (firebaseAuthProvider.isUserVerified()) {
            viewInteractor?.goToGoalActivity()
        }
    }

    fun checkIfPhoneIsValid(phone: String): Boolean {
        return phone.let {
            !it.isBlank() && (it.length > 10)
        }
    }

    fun sendOptToPhone(phone: String) {
        this.phone = phone
        viewInteractor?.startSMSListner()
        firebaseAuthProvider.sendVerificationCode(phone)
    }

    override fun onVerificationCompleted() {
        TODO("Not yet implemented")
    }

    override fun onVerificationCodeDetected(code: String) {
        TODO("Not yet implemented")
    }

    override fun onVerificationFailed(message: String) {
        TODO("Not yet implemented")
    }
}