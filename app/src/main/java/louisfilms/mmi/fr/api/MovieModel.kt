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
