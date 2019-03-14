package com.jeeraphan.manow.data.entity.response

class FeedResponse {
    var status: String? = null
    val totalResults: Int? = null
    var articles: MutableList<Article>? = null

    class Article {
        val source: Source? = null
        var author: String? = null
        var title: String? = null
        var description: String? = null
        var url: String? = null
        var urlToImage: String? = null
        var publishedAt: String? = null

        class Source {
            var id: String? = null
            var name: String? = null
        }
    }
}