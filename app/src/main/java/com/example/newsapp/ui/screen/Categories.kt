package com.example.newsapp.ui.screen

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.BottomMenuScreen
import com.example.newsapp.R
import com.example.newsapp.models.MockData
import com.example.newsapp.models.MockData.getTimeAgo
import com.example.newsapp.models.TopNewsArticle
import com.example.newsapp.models.getAllArticlesCategory
import com.example.newsapp.network.NewsManager
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Categories(onFetchCategory: (String) -> Unit = {}, newsManager: NewsManager){
    val tabItems = getAllArticlesCategory()

    Column{
        LazyRow{
            items(tabItems.size){
                val category = tabItems[it]
                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }

        ArticleContent(articles = newsManager.getArticleByCategory.value.articles ?: listOf())
    }
}



@Composable
fun CategoryTab(
    category: String,
    isSelected: Boolean = false,
    onFetchCategory: (String) -> Unit
){
    val background = if (isSelected) colorResource(id = R.color.purple_500) else colorResource(id = R.color.gray)
    Surface(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 16.dp)
            .clickable { onFetchCategory(category) },
        shape = MaterialTheme.shapes.large,
        color = background
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body2,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(start = 16.dp, end=16.dp, top=20.dp, bottom = 20.dp)
        )
    }
}



@Composable
fun ArticleContent(articles: List<TopNewsArticle>, modifier: Modifier=Modifier){
    LazyColumn {
        items(articles){
            article -> 
            Card(modifier.padding(8.dp), elevation = 5.dp) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                    CoilImage(
                        imageModel = article.urlToImage,
                        placeHolder = painterResource(id = R.drawable.breaking_news),
                        modifier = Modifier.size(100.dp),
                        error = painterResource(id = R.drawable.breaking_news),
                    )
                    
                    Column(modifier.padding(8.dp)) {
                        Text(text = article.title ?: "Not Available",
                            fontWeight = FontWeight.Bold,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        
                        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = article.author ?: "No Author")
                            Text(text = MockData.stringToDate(article.publishedAt ?: "").getTimeAgo())
                        }
                    }
                }
            }
        }
    }

}


@Preview
@Composable
fun ArticleContentPreview(){
    ArticleContent(articles = listOf(TopNewsArticle(
        author = "James Bond",
        title = "'You are not alone': EU Parliament delegation tells Taiwan on first official visit - Reuters",
        description =
        "The European Parliament's first official delegation to Taiwan said on Thursday the diplomatically isolated " +
                "island is not alone and called for bolder actions to strengthen EU-Taiwan ties as Taipei faces rising pressure from Beijing.",
        publishedAt = "2021-11-04T03:37:00Z"
    )), modifier = Modifier)
}