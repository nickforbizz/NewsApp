package com.example.newsapp.ui.screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.models.MockData
import com.example.newsapp.models.MockData.getTimeAgo


import com.example.newsapp.models.NewsData
import com.example.newsapp.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage
import com.example.newsapp.R


@Composable
fun TopNews(navController: NavController, articles: List<TopNewsArticle>){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)

        LazyColumn{
            items(articles.size){
                index -> TopNewsItem(
                    articles[index], onNewsClick = {
                    navController.navigate("DetailScreen/${index}"
                    )
            })
            }
        }
    }
}



@Composable
fun TopNewsItem(article: TopNewsArticle, onNewsClick: ()->Unit ={}){

    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }){

        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news),
        )

        Column(modifier = Modifier
            .wrapContentHeight()
            //.background(Brush.verticalGradient(0F to Color.Transparent, 4F to Color.Gray, 0.4F to Color.Gray))
            .padding(top = 15.dp, start = 15.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = MockData.stringToDate(article.publishedAt!!).getTimeAgo() , color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = article.title!!, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview(){
    TopNewsItem(
        TopNewsArticle(
        author = "Not available",
        title = "'You are not alone': EU Parliament delegation tells Taiwan on first official visit - Reuters",
        description =
        "The European Parliament's first official delegation to Taiwan said on Thursday the diplomatically isolated " +
                "island is not alone and called for bolder actions to strengthen EU-Taiwan ties as Taipei faces rising pressure from Beijing.",
        publishedAt = "2021-11-04T03:37:00Z"
    ))
}