package louisfilms.mmi.fr.ui.theme.Films

import GeneralViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.Text
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import louisfilms.mmi.fr.activity.UniversalButton

@Composable
fun DetailActor(viewModel: GeneralViewModel, actorId: String, onClick: () -> Unit) {
    viewModel.getActorDetails(actorId)
    val actorDetails by viewModel.actorDetails.collectAsStateWithLifecycle()

    actorDetails?.let { actor ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${actor.profile_path}",
                contentDescription = "Image of ${actor.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Nom: ${actor.name}", modifier = Modifier.padding(4.dp))
            Text(text = "Popularité: ${actor.popularity}", modifier = Modifier.padding(4.dp))

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Films connus pour:", modifier = Modifier.padding(4.dp))
            actor.known_for.forEach { movie ->
                Text(text = movie.title, modifier = Modifier.padding(4.dp))
            }
        }
    }
    UniversalButton(onClick, "Retour")
}
