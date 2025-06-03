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
import com.delhomme.jobbingtrack.data.networks.TokenManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val token = TokenManager.getTokens(this);
        val userId = TokenManager.getUserId(this);
        setContent {
            val navController = rememberNavController()

            val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
                "ViewModelStoreOwner is not available"
            }

            Surface(color = MaterialTheme.colorScheme.background) {
                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides viewModelStoreOwner
                ) {
                    NavGraph(navController = navController, isLoggedIn = token != null, userId = userId)
                }
            }
        }
    }
}
