import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import louisfilms.mmi.fr.api.DetailMovie


@Composable
fun DetailFilms(viewModel: GeneralViewModel, id: String) {
    DetailFilmsScreen(viewModel, id)
}

@Composable
fun DetailFilmsScreen(viewModel: GeneralViewModel, id: String) {
    viewModel.getDetailMovies(id)
    val detail by viewModel.DetailMovies.collectAsStateWithLifecycle()
    Log.i("detail", detail.toString())
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        item {DetailFilmsCard(detail = detail)}
    }
}

@Composable
fun DetailFilmsCard(detail: DetailMovie) {
    val posterMovie = "https://image.tmdb.org/t/p/w780/" + detail.poster_path
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = posterMovie,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
