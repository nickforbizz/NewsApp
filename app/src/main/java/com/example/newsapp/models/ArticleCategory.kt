package com.example.newsapp.models

import com.example.newsapp.models.ArticleCategory.*

enum class ArticleCategory(val categoryName: String) {
    BUSINESS("BUSINESS"),
    ENTERTAINMENT("entertainment"),
    HEALTH("health"),
    GENERAL("general"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")
}


fun getAllArticlesCategory(): List<ArticleCategory>{
    return listOf(
        ArticleCategory.BUSINESS,
        ArticleCategory.ENTERTAINMENT,
        ArticleCategory.GENERAL,
        ArticleCategory.HEALTH,
        ArticleCategory.SCIENCE,
        ArticleCategory.SPORTS,
        ArticleCategory.TECHNOLOGY
    )
}




fun getArticleCategory(category: String): ArticleCategory? {
    val map = values().associateBy(ArticleCategory::categoryName)
    return  map[category]
}