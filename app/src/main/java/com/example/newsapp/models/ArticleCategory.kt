package com.example.newsapp.models

import com.example.newsapp.models.ArticleCategory.*

enum class ArticleCategory(val categoryName: String) {
    BUSINESS("BUSINESS"),
    ENTERTAINMENT("ENTERTAINMENT"),
    HEALTH("HEALTH"),
    GENERAL("GENERAL"),
    SCIENCE("SCIENCE"),
    SPORTS("SPORTS"),
    TECHNOLOGY("TECHNOLOGY")
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