package louisfilms.mmi.fr.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import louisfilms.mmi.fr.R
import louisfilms.mmi.fr.activity.UniversalButton


@Composable
fun Profil(windowClass: WindowSizeClass, onClick:()-> Unit) {
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
        UniversalButton(onClick, "Suivant")
    }
}

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