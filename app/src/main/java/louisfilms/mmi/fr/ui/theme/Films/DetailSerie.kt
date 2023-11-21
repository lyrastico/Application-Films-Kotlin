import android.telecom.Call.Details
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
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
        item {DetailSeriesCard(detail = detail)}
    }
}

@Composable
fun DetailSeriesCard(detail : DetailSerie) {
    val posterMovie = "https://image.tmdb.org/t/p/w780/" + detail.poster_path
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
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
