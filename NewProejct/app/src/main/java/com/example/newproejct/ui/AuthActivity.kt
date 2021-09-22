package com.example.newproejct.ui

import android.content.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.newproejct.R
import com.example.newproejct.databinding.ActivityAuthBinding
import com.example.newproejct.snackbar
import com.example.newproejct.viewmodel.PhoneAuthViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class AuthActivity : BaseActivity<ActivityAuthBinding>(R.layout.activity_auth){

    private lateinit var auth: FirebaseAuth
    private var storedVerificationId = ""
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    private val viewModel: PhoneAuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        initViewModelCallback()
        binding.vm = viewModel
    }

     fun initViewModelCallback() {
        Log.d("jyl","dkdkdkdk")
        with(viewModel) {
            requestPhoneAuth.observe(this@AuthActivity, Observer {
                if (it) {
                    startPhoneNumberVerification("+1 "+viewModel.etPhoneNum.value.toString().substring(0))
                }else{
                    snackbar("전화번호를 입력해주세요")
                }
            })
            requestResendPhoneAuth.observe(this@AuthActivity, Observer {
                if (it) {
                    resendVerificationCode(
                        "+1 "+ viewModel.etPhoneNum.value.toString().substring(0)
                    , resendToken
                    )
                }
            })

            authComplete.observe(this@AuthActivity, Observer {
                val phoneCredential =
                    PhoneAuthProvider.getCredential(
                        storedVerificationId,
                        viewModel.etAuthNum.value!!
                    )

                verifyPhoneNumberWithCode(phoneCredential)
            })
        }
    }

    private val callback by lazy {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("jyl", "onVerificationCompleted")
                snackbar("인증코드가 전송되었습니다. 90초 이내에 입력해주세요")
//                UserInfo.get = credential.smsCode.toString()
                binding.phoneAuthEtAuthNum.setText(credential.smsCode.toString())
                binding.phoneAuthEtAuthNum.isEnabled = false

                Handler(Looper.getMainLooper()).postDelayed({
                    verifyPhoneNumberWithCode(credential)
                }, 3000)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w("jyl", "onVerificationFailted",e)
                if (e is FirebaseAuthInvalidCredentialsException) {

                }else if (e is FirebaseTooManyRequestsException) {

                }
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                Log.d("jyl", "onCodeSent")
                val id = p0
                val token = p1
                val code = "112233"
                verifyPhoneNumberWithCode(PhoneAuthProvider.getCredential(id, code))


            }

        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(90L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callback)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
        binding.phoneAuthBtn.run {
            text = "재전송"
            setTextColor(
                ContextCompat.getColor(
                    this@AuthActivity, R.color.browser_actions_bg_grey
                )
            )
        }
    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ){
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(90L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callback)

        if (token != null) {
            optionsBuilder.setForceResendingToken(token)
        }

        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    private fun verifyPhoneNumberWithCode(phoneAuthCredential: PhoneAuthCredential) {
       val tel = binding.phoneNumberEt.text.toString()
        val authNum = phoneAuthCredential.smsCode.toString()

        if (tel.isNullOrBlank() && authNum.isNotBlank()) {
            snackbar("인증 성공")
            startActivity(Intent(this, MainActivity::class.java))
        }

        Firebase.auth.signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener(this@AuthActivity){task ->
                if (task.isSuccessful) {
                    snackbar("인증 성공")
                    binding.phoneAuthEtAuthNum.isEnabled = true
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    snackbar("인증 실패")
                }
            }
    }

}