import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import louisfilms.mmi.fr.api.ActorDetails
import louisfilms.mmi.fr.api.DetailMovie
import louisfilms.mmi.fr.api.DetailSerie
import louisfilms.mmi.fr.api.Movie
import louisfilms.mmi.fr.api.Serie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeneralViewModel : ViewModel() {


    val trendingMovies = MutableStateFlow<List<Movie>>(listOf())
    val trendingSeries = MutableStateFlow<List<Serie>>(listOf())
    val DetailSeries = MutableStateFlow<DetailSerie?>(null)
    val DetailMovies = MutableStateFlow<DetailMovie?>(null)
    val actorDetails = MutableStateFlow<ActorDetails?>(null)


    val api_key = "432dbeb947fe645d6b008204074295c6"

    val BASE_URL = "https://api.themoviedb.org/3/"

    var api: Api

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(Api::class.java)

    }

    fun getMovies() {
        viewModelScope.launch {
            trendingMovies.value = api.lastmovies(api_key = api_key).results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            trendingSeries.value = api.lastseries(api_key = api_key).results
        }
    }

    fun getDetailSeries(id : String) {
        viewModelScope.launch {
            DetailSeries.value = api.detailseries(api_key = api_key, id = id)
        }
    }

    fun getDetailMovies(id : String) {
        viewModelScope.launch {
            DetailMovies.value = api.detailmovies(api_key = api_key, id = id)
        }
    }


    fun getActorDetails(actorId: String) {
        viewModelScope.launch {
            actorDetails.value = api.actorDetails(api_key = api_key, id = actorId)
        }
    }
}

