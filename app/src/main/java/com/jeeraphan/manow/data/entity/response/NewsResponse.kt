package com.jeeraphan.manow.data.entity.response

class NewsResponse {
    var status: String? = null
    val totalResults: Int? = null
    var articles: List<Article>? = null

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