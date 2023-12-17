package louisfilms.mmi.fr.api

data class MovieResponse(
    val results: List<Movie> = listOf()
)

data class Movie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = ""
)
data class SerieResponse(
    val results: List<Serie> = listOf()
)

data class Serie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = ""
)

data class DetailSerieResponse(
    val results: List<DetailSerie> = listOf()
)

data class DetailSerie(
    val adult: Boolean,
    val backdrop_path: String,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val seasons: List<Season>,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Double,
    val vote_count: Int,
    val credits: Credits
)

data class DetailMovieResponse(
    val results: List<DetailMovie> = listOf()
)

data class DetailMovie(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val credits: Credits,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class CreatedBy(
    val id: Int,
    val credit_id: String,
    val name: String,
    val gender: Int,
    val profile_path: String?
)

data class LastEpisodeToAir(
    val id: Int,
    val name: String,
    val overview: String,
    val vote_average: Double,
    val vote_count: Int,
    val air_date: String,
    val episode_number: Int,
    val episode_type: String,
    val production_code: String,
    val runtime: Int,
    val season_number: Int,
    val show_id: Int,
    val still_path: String?
)

data class Network(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int,
    val vote_average: Double
)

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Cast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val character: String,
    val credit_id: String,
    val order: Int
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)

data class ActorDetails(
    val adult: Boolean = false,
    val id: String = "",
    val name: String = "",
    val original_name: String = "",
    val media_type: String = "",
    val popularity: Double = 0.1,
    val gender: Int = 0,
    val known_for_department: String = "",
    val profile_path: String = "",
    val known_for: List<Movie> = listOf()
)