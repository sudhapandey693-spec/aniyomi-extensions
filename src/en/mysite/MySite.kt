package eu.kanade.tachiyomi.animeextension.en.mysite

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.Animesource
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

    override fun animeDetailsRequest(anime: Animesource) = GET(baseUrl)
    override fun episodeListRequest(anime: Animesource) = GET(baseUrl)
    override fun videoListRequest(episode: SEpisode) = GET(baseUrl)

    override fun animeDetailsParse(response: Response) = throw Exception("Demo – no details")
    override fun episodeListParse(response: Response) = listOf(
        SEpisode.create().apply {
            name = "Sample Episode"
            episode_number = 1f
            setUrlWithoutDomain("/ep1")
        }
    )
    override fun videoListParse(response: Response) = listOf(
        SVideo("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "720p", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
    )
    override fun searchAnimeRequest(page: Int, query: String, filters: AnimeFilterList) = GET(baseUrl)
    override fun searchAnimeParse(response: Response) = throw Exception("Demo – no search")
    override fun latestUpdatesRequest(page: Int) = GET(baseUrl)
    override fun latestUpdatesParse(response: Response) = throw Exception("Demo – no latest")
}
