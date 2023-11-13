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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import louisfilms.mmi.fr.ui.theme.FilmsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            FilmsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "destination1") {
                        composable("destination1") { Screen1(windowSizeClass){
                            navController.navigate("destination2")
                            currentScreen = "destination2"
                            }
                        }
                        composable("destination2") { Screen2{
                            navController.navigate("destination3")
                            currentScreen = "destination3"
                            }
                        }
                        composable("destination3") { Screen3{
                            navController.navigate("destination1")
                            currentScreen = "destination1"
                            }
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
fun Screen3(onClick:()-> Unit) {
    UniversalButton(onClick, "Screen1")
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

@Composable
fun BottomNavigation(){
    BottomNavigationItem(
        selected = currentScreen == "destination1",
        onClick = {
            navController.navigate("destination1")
            currentScreen = "destination1"
        },
        icon = {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        },
        label = {
            Text("Home")
        }
    )
    BottomNavigationItem(
        selected = currentScreen == "destination2",
        onClick = {
            navController.navigate("destination2")
            currentScreen = "destination2"
        },
        icon = {
            Icon(Icons.Filled.Send, contentDescription = "Screen 2")
        },
        label = {
            Text("Screen 2")
        }
    )
    BottomNavigationItem(
        selected = currentScreen == "destination3",
        onClick = {
            navController.navigate("destination3")
            currentScreen = "destination3"
        },
        icon = {
            Icon(Icons.Filled.Person, contentDescription = "Screen 3")
        },
        label = {
            Text("Screen 3")
        }
    )
}