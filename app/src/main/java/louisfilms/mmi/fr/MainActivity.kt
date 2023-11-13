package louisfilms.mmi.fr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import louisfilms.mmi.fr.ui.theme.FilmsTheme

sealed class Destination(val destination: String, val label: String, val icon: ImageVector) {
    object Profil : Destination("profil", "Mon Profil", Icons.Filled.Person)
    object Edition : Destination("edition", "Edition du profil", Icons.Filled.Edit)
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        bottomBar = { BottomNavigation {
                            destinations.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, contentDescription = null) },
                                    label = { Text(screen.label) },
                                    selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                                    onClick = { navController.navigate(screen.destination) })
                            }}
                        }) { innerPadding ->
                        NavHost(navController, startDestination = Destination.Profil.destination,
                            Modifier.padding(innerPadding)) {
                            composable(Destination.Profil.destination) { Screen1(windowSizeClass){navController.navigate("destination2")} }
                            composable(Destination.Edition.destination) { Screen2{navController.navigate("destination3")} }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun Screen1(windowClass: WindowSizeClass, onClick:()-> Unit) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            PortraitContent(onClick)
        }
        else -> {
            LandscapeContent()
        }
    }
}

@Composable
fun Screen2(onClick:()-> Unit) {
    UniversalButton(onClick, "Screen3")
}

@Composable
fun PortraitContent(onClick:()-> Unit) {
    MaBox(onClick)
}

@Composable
fun LandscapeContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonImage()
        Text(text = "Mode portrait")
    }
}

@Composable
fun MaBox(onClick:()-> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        MonImage()
        Text()
        Description()
        Reseaux()
        UniversalButton(onClick, "Screen2")
    }
}

// ... Vos autres fonctions composables restent inchangées


@Composable
fun MonImage(){
    Image(
        painter = painterResource(R.drawable.photo_de_profile),
        contentDescription = "photo de profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Text(){
    Text(
        text = "Louis Barthes",
        fontSize = 24.sp
    )
}
@Composable
fun Description(){
    Text(
        text = "Etudiant en MMI (Metiers du Multimédia et de l'Internet)",
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(10.dp)
    )
}


@Composable
fun Reseaux(){
    Row {
        LogoInsta()
        Insta()
    }
    Row {
       LogoLinkedin()
       Linkedin()
   }

}

@Composable
fun Insta(){
    Text(
        text = "lyra_stico",
        fontSize = 18.sp
    )
}
@Composable
fun Linkedin(){
    Text(
        text = "Louis Barthes",
        fontSize = 18.sp
    )
}

@Composable
fun LogoInsta(){
    Image(
        painter = painterResource(R.drawable.instagram),
        contentDescription = "logo Instagram",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(20.dp)
    )
}

@Composable
fun LogoLinkedin(){
    Image(
        painter = painterResource(R.drawable.logo_linkedin),
        contentDescription = "logo linkedin",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(20.dp)
    )
}

@Composable
fun UniversalButton(onClick:()-> Unit, txt:String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp) // Ajoute un espacement sous le bouton
            .fillMaxWidth(), // Le bouton occupe toute la largeur
    ) {
        Text(txt)
    }
}

