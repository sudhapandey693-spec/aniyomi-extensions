package eu.kanade.tachiyomi.animeextension.en.mysite

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.SAnime
import eu.kanade.tachiyomi.animesource.model.SEpisode
import eu.kanade.tachiyomi.animesource.model.SVideo
import eu.kanade.tachiyomi.animesource.online.AnimeHttpSource
import okhttp3.Request
import okhttp3.Response

class MySite : AnimeHttpSource() {
    override val name = "My Site"
    override val baseUrl = "https://example.com"
    override val lang = "en"
    override val supportsLatest = false

    // --- mandatory overrides ---
    override fun animeDetailsRequest(anime: SAnime) = GET(baseUrl)
    override fun episodeListRequest(anime: SAnime)   = GET(baseUrl)
    override fun videoListRequest(episode: SEpisode) = GET(baseUrl)

    override fun animeDetailsParse(response: Response): SAnime =
        SAnime.create().apply { title = "Demo Anime" }

    override fun episodeListParse(response: Response): List<SEpisode> =
        listOf(SEpisode.create().apply {
            name = "Episode 1"
            episode_number = 1f
            setUrlWithoutDomain("/ep1")
        })

    override fun videoListParse(response: Response): List<SVideo> =
        listOf(SVideo("https://example.com/stream.mp4", "720p", "https://example.com/stream.mp4"))

    // --- unused but required ---
    override fun searchAnimeRequest(page: Int, query: String, filters: AnimeFilterList) = GET(baseUrl)
    override fun searchAnimeParse(response: Response) = throw Exception("Search not implemented")
    override fun latestUpdatesRequest(page: Int) = GET(baseUrl)
    override fun latestUpdatesParse(response: Response) = throw Exception("Latest not implemented")
}
