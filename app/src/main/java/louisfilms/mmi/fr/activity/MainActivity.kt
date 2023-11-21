package louisfilms.mmi.fr.activity

import Trending
import TrendingMoviesViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import louisfilms.mmi.fr.ui.theme.FilmsTheme
import louisfilms.mmi.fr.ui.theme.profile.Profil

sealed class Destination(val destination: String, val label: String, val icon: ImageVector) {
    object Profil : Destination("profil", "Mon Profil", Icons.Filled.Person)
    object Edition : Destination("edition", "Edition du profil", Icons.Filled.Edit)
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : TrendingMoviesViewModel by viewModels()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val destinations = listOf(Destination.Profil, Destination.Edition)
            FilmsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                        if (currentDestination?.hierarchy?.any { it.route == Destination.Profil.destination } == false) {
                            BottomNavigation {
                                destinations.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = { Icon(screen.icon, contentDescription = null) },
                                        label = { Text(screen.label) },
                                        selected = currentDestination.hierarchy.any { it.route == screen.destination },
                                        onClick = { navController.navigate(screen.destination) }
                                    )
                                }
                            }
                        }
                    }) { innerPadding ->
                        NavHost(navController, startDestination = Destination.Profil.destination,
                            Modifier.padding(innerPadding)) {
                            composable(Destination.Profil.destination) { Profil(windowSizeClass){navController.navigate(
                                Destination.Edition.destination)} }
                            composable(Destination.Edition.destination) { Trending(viewModel){navController.navigate("profil")} }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun UniversalButton(onClick:()-> Unit, txt:String) {
    if (txt == "Suivant") {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(16.dp) // Ajoute un espacement sous le bouton
                .fillMaxWidth(), // Le bouton occupe toute la largeur
        ) {
            Text(txt)
        }
    }else {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .offset(x = 16.dp, y = 16.dp) // Ajoute un espacement en haut à gauche
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(48.dp), // Taille de l'icône
            )
        }
    }
}

