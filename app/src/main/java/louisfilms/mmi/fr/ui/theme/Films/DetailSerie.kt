import android.util.Log
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
import louisfilms.mmi.fr.api.Cast
import louisfilms.mmi.fr.api.DetailSerie


@Composable
fun DetailSeries(viewModel: GeneralViewModel, id: String) {
    DetailSeriesScreen(viewModel, id)
}

@Composable
fun DetailSeriesScreen(viewModel: GeneralViewModel, id: String) {
    viewModel.getDetailSeries(id)
    val detail by viewModel.DetailSeries.collectAsStateWithLifecycle()
    Log.i("detail", detail.toString())
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        item { detail?.let { DetailSeriesCard(detail = it) } }
    }
}

@Composable
fun DetailSeriesCard(detail : DetailSerie) {
    val posterMovie = "https://image.tmdb.org/t/p/w780/${detail.poster_path}"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = posterMovie,
            contentDescription = detail.original_name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Title: ${detail.original_name}",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Release Date: ${detail.last_air_date}",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Rating: ${detail.vote_average} (${detail.vote_count} votes)",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Overview: ${detail.overview}",
            modifier = Modifier.padding(4.dp)
        )

        // Appel de DetailMovieCast pour afficher le casting
        detail.credits?.let { credits ->
            DetailSerieCast(cast = credits.cast)
        }
    }
}

@Composable
fun ActorSerieCard(actor: Cast) {
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
            ) {
                Text(text = actor.name, modifier = Modifier.padding(4.dp))
                Text(text = "as ${actor.character}", modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun DetailSerieCast(cast: List<Cast>) {
    Column(modifier = Modifier.padding(4.dp)) {
        Text(text = "Cast:", modifier = Modifier.padding(4.dp))
        cast.take(10).forEach { actor ->
            ActorSerieCard(actor = actor)
        }
    }
}