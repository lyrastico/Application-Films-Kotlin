import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import louisfilms.mmi.fr.activity.UniversalButton
import louisfilms.mmi.fr.api.Movie


@Composable
fun TrendingFilms(viewModel: GeneralViewModel, atClick: (id : String) -> Unit, onClick: () -> Unit) {
    TrendingMoviesScreen(viewModel, atClick)
    UniversalButton(onClick, "Retour")
}

@Composable
fun TrendingMoviesScreen(viewModel: GeneralViewModel, atClick: (id : String) -> Unit) {
    LaunchedEffect(key1 = 0) {
        viewModel.getMovies()
    }

    val movies by viewModel.trendingMovies.collectAsStateWithLifecycle()
    Log.i("movies", movies.size.toString())
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        for (movie in movies) {
            item {
                MovieCard(movie = movie, atClick)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie, atClick: (id : String) -> Unit) {
    val posterMovie = "https://image.tmdb.org/t/p/w780/" + movie.poster_path
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
            .clickable(onClick = {atClick(movie.id)})
    ) {
        AsyncImage(
            model = posterMovie,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
