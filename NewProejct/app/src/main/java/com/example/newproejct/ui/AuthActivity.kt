package com.example.newproejct.ui

import android.content.*
import android.os.Bundle
import android.widget.Toast
import com.example.newproejct.AuthViewInteractor
import com.example.newproejct.R
import com.example.newproejct.viewmodel.AuthViewModel
import com.example.newproejct.databinding.ActivityAuthBinding
import com.example.newproejct.snackbar
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : BaseActivity<ActivityAuthBinding>(R.layout.activity_auth),
AuthViewInteractor{

    companion object{
        private const val CREDENTIAL_PICKER_REQUEST = 1
        private const val  SMS_CONSENT_REQUEST = 2

        fun getIntent(context: Context): Intent{
            return Intent(context, MainActivity::class.java)
        }
    }

    lateinit var firebaseAuth : FirebaseAuth
    private val viewModel: AuthViewModel by viewModel()

    private val smsVertificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                val extras = intent.extras
                val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

                when (smsRetrieverStatus.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        val consentIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                        try {
                            startActivityForResult(consentIntent, SMS_CONSENT_REQUEST)
                        } catch (e: ActivityNotFoundException) {
                            snackbar(e.message?: "something went wrong")
                        }
                    }

                    CommonStatusCodes.TIMEOUT -> {

                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewInteractor = this

        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsVertificationReceiver, intentFilter)

        binding.phoneAuthBtn.setOnClickListener {
            if(viewModel.checkIfPhoneIsValid(binding.phoneNumberEt.text.toString())){
                viewModel.sendOptToPhone(binding.phoneNumberEt.text.toString())
            }else{
                binding.phoneNumberEt.error = "Invalid Phone: Please enter phone number with country code"
            }
        }

    }

    override fun showSnackBarMessage(message: String) {

    }

    override fun goToGoalActivity() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }

    override fun startSMSListner() {
        val smsRetrieverClient = SmsRetriever.getClient(this)
        val task = smsRetrieverClient.startSmsUserConsent(null)
        task.addOnSuccessListener {
            Toast.makeText(this, "SMS retriever starts", Toast.LENGTH_LONG).show()
        }
        task.addOnFailureListener {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

        }
    }

    override fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ){
                if (it.exception is FirebaseAuthInvalidCredentialsException) {
                    snackbar(it.exception?.message?: "Verification failed")
                }else{
                    snackbar("Verification Failed")
                }
            }
    }
}