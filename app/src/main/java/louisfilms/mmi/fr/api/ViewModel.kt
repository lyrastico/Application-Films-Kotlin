import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import louisfilms.mmi.fr.api.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrendingMoviesViewModel : ViewModel() {


    val trendingMovies =  MutableStateFlow<List<Movie>>(listOf())

    val BASE_URL = "https://api.themoviedb.org/3/"

    var api : Api

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
                trendingMovies.value = api.lastmovies("432dbeb947fe645d6b008204074295c6").results
        }
    }
}

