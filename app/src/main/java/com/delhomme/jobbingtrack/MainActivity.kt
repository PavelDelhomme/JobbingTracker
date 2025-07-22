package com.delhomme.jobbingtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.rememberNavController
import com.delhomme.jobbingtrack.navigation.NavGraph
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
class MainActivity : AppCompatActivity() {
    @Inject lateinit var tokenManager: TokenManager


    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 100
    }


    private val notificationPermissionLauncher by lazy {
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                // Tu peux logguer, afficher un toast ou une boîte de dialogue
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Demande de permission Android 13+
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }*/

        checkNotificationPermission()

        val accessToken = tokenManager.getAccessToken()
        val userId = tokenManager.getUserId()

        setContent {
            val navController = rememberNavController()

            val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
                "ViewModelStoreOwner is not available"
            }

            Surface(color = MaterialTheme.colorScheme.background) {
                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides viewModelStoreOwner
                ) {
                    NavGraph(navController = navController, isLoggedIn = accessToken != null, userId = userId)
                }
            }
        }
    }


    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée, vous pouvez envoyer des notifications
            } else {
                // Permission refusée, vous pouvez informer l'utilisateur
                Toast.makeText(this, "Les notifications sont désactivées", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
