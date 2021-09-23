package com.example.newproejct.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class PhoneAuthViewModel @Inject constructor(): BaseViewModel() {
    var phoneAuthNum: String = ""
    var isResendPhoneAuth: Boolean = false
    val etPhoneNum = MutableLiveData<String>()
    val etAuthNum = MutableLiveData<String>()

    private val _requestPhoneAuth = MutableLiveData<Boolean>()
    private val _requestResendPhoneAuth = MutableLiveData<Boolean>()
    private val _authComplete = SingleLiveEvent<Unit>()

    val requestPhoneAuth: LiveData<Boolean> get() = _requestPhoneAuth
    val requestResendPhoneAuth: LiveData<Boolean> get() = _requestResendPhoneAuth
    val authComplete: LiveData<Unit> get() = _authComplete

    fun requestPhoneAuth() {
        Log.d("jyl","requestphoneAuth")
        if (!isResendPhoneAuth) {
            etPhoneNum.value = "650-555-1234"
            _requestPhoneAuth.value = !etPhoneNum.value.isNullOrBlank()
        }else{
            _requestResendPhoneAuth.value = !etPhoneNum.value.isNullOrBlank()
        }
    }

    fun authComplete() {
        _authComplete.call()
    }
}