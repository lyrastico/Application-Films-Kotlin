import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import louisfilms.mmi.fr.activity.UniversalButton
import louisfilms.mmi.fr.api.Cast
import louisfilms.mmi.fr.api.DetailMovie


@Composable
fun DetailFilms(viewModel: GeneralViewModel, id: String, onActorClick: (String) -> Unit, onClick: () -> Unit) {
    DetailFilmsScreen(viewModel, id, onActorClick)
    UniversalButton(onClick, "Retour")
}

@Composable
fun DetailFilmsScreen(viewModel: GeneralViewModel, id: String, onActorClick: (String) -> Unit) {
    viewModel.getDetailMovies(id)
    val detail by viewModel.DetailMovies.collectAsStateWithLifecycle()
    Log.i("detail", detail.toString())
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        item { detail?.let { DetailFilmsCard(detail = it, onActorClick = onActorClick) } }
    }
}

@Composable
fun DetailFilmsCard(detail: DetailMovie, onActorClick: (String) -> Unit) {
    val posterMovie = "https://image.tmdb.org/t/p/w780/${detail.poster_path}"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = posterMovie,
            contentDescription = detail.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Title: ${detail.title}", modifier = Modifier.padding(4.dp))
        Text(text = "Release Date: ${detail.release_date}", modifier = Modifier.padding(4.dp))
        Text(text = "Rating: ${detail.vote_average} (${detail.vote_count} votes)", modifier = Modifier.padding(4.dp))
        Text(text = "Overview: ${detail.overview}", modifier = Modifier.padding(4.dp))

        // Appel de DetailMovieCast pour afficher le casting
        detail.credits?.let { credits ->
            DetailMovieCast(cast = credits.cast, onActorClick)
        }
    }
}

@Composable
fun ActorCard(actor: Cast, onClick: (String) -> Unit) {
    val imageUrl = "https://image.tmdb.org/t/p/w500/${actor.profile_path}"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Image of ${actor.name}",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { onClick(actor.id.toString()) }
            ) {
                Text(text = actor.name, modifier = Modifier.padding(4.dp))
                Text(text = "as ${actor.character}", modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun DetailMovieCast(cast: List<Cast>, onActorClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(4.dp)) {
        Text(text = "Cast:", modifier = Modifier.padding(4.dp))
        cast.take(10).forEach { actor ->
            ActorCard(actor = actor, onClick = onActorClick)
        }
    }
}