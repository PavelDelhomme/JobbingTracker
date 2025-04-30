package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.ui.appels.AddOrEditAppelScreen
import com.delhomme.jobbingtrack.ui.appels.AppelDetailScreen
import com.delhomme.jobbingtrack.ui.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.candidatures.AddOrEditCandidatureScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidatureDetailScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesTabScreen
import com.delhomme.jobbingtrack.ui.contacts.AddOrEditContactScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactDetailScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.entreprises.AddOrEditEntrepriseScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntrepriseDetailScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntretienDetailScreen
import com.delhomme.jobbingtrack.ui.entretiens.AddOrEditEntretienScreen
import com.delhomme.jobbingtrack.ui.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.login.LoginScreen
import com.delhomme.jobbingtrack.ui.main.CalendarScreen
import com.delhomme.jobbingtrack.ui.main.MainScreen
import com.delhomme.jobbingtrack.ui.relances.AddOrEditRelanceScreen
import com.delhomme.jobbingtrack.ui.relances.RelanceDetailScreen
import com.delhomme.jobbingtrack.ui.relances.RelancesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.MAIN) {
            MainScreen(navController)
        }
    }
}
