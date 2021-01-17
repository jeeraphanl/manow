package com.jeeraphan.manow.presentation

import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.jeeraphan.manow.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authButton.setOnClickListener {
            //authenticationBiometric()
            encryptedSharePref()
            //encryptedFile()
        }
    }

    private fun getMasterKeyAlias(): MasterKey {
        // this is equivalent to using deprecated MasterKeys.AES256_GCM_SPEC
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                setUnlockedDeviceRequired(true)
                setIsStrongBoxBacked(true)
            }
        }.build()

        return MasterKey.Builder(this)
            .setKeyGenParameterSpec(keyGenParameterSpec)
            .build()
    }

    private fun encryptedSharePref() {
        val encryptedSharedPreferences = EncryptedSharedPreferences.create(
            this,
            "share_pref_app_name",
            getMasterKeyAlias(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        // write
        encryptedSharedPreferences.edit().putString("sharePref_key", "value").apply()

        // read
        val result = encryptedSharedPreferences.getString("sharePref_key", null)
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    private fun encryptedFile() {

        val encryptedFile = EncryptedFile.Builder(
            this,
            File(filesDir, "file_name"),
            getMasterKeyAlias(),
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        encryptedFile.openFileOutput().use { output ->
            output.write("message_body".toByteArray())
        }

        encryptedFile.openFileInput().use { input ->
            val messageBody = String(input.readBytes(), Charsets.UTF_8)
            Toast.makeText(this, messageBody, Toast.LENGTH_SHORT).show()
        }
    }

    private fun authenticationBiometric() {
        val authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                // handle success case
            }

            override fun onAuthenticationFailed() {
                // handle error case
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                // handle error case
            }
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric")
            .setDescription("Scan your fingerprint")
            .setDeviceCredentialAllowed(true)
            .build()

        val biometricPrompt = BiometricPrompt(
            this,
            ContextCompat.getMainExecutor(this),
            authenticationCallback
        )

        when (BiometricManager.from(this).canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                biometricPrompt.authenticate(promptInfo)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE,
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE,
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // handle biometric error
            }
        }
    }
}
