import louisfilms.mmi.fr.api.DetailMovie
import louisfilms.mmi.fr.api.DetailSerie
import louisfilms.mmi.fr.api.MovieResponse
import louisfilms.mmi.fr.api.SerieResponse
import louisfilms.mmi.fr.api.ActorDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): MovieResponse

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String): SerieResponse

    @GET("movie/{id}?append_to_response=credits")
    suspend fun detailmovies(@Path("id") id : String, @Query("api_key") api_key: String): DetailMovie

    @GET("tv/{id}?append_to_response=credits")
    suspend fun detailseries(@Path("id") id : String, @Query("api_key") api_key: String): DetailSerie

    @GET("person/{id}")
    suspend fun actorDetails(@Path("id") id: String, @Query("api_key") api_key: String): ActorDetails
}