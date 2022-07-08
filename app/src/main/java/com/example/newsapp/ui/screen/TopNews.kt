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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.models.MockData
import com.example.newsapp.models.MockData.getTimeAgo


import com.example.newsapp.models.NewsData

@Composable
fun TopNews(navController: NavController){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)

        LazyColumn{
            items(MockData.topNewsList){
                item -> TopNewsItem(newsdata = item, onNewsClick = {
                    navController.navigate("DetailScreen/${item.id}")
            })
            }
        }
    }
}



@Composable
fun TopNewsItem(newsdata: NewsData, onNewsClick: ()->Unit ={}){

    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }){
        Image(painter = painterResource(id = newsdata.image),
            contentDescription = "",
            contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier
            .wrapContentHeight()
            //.background(Brush.verticalGradient(0F to Color.Transparent, 4F to Color.Gray, 0.4F to Color.Gray))
            .padding(top = 15.dp, start = 15.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = MockData.stringToDate(newsdata.publishedAt).getTimeAgo() , color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = newsdata.title, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview(){
    TopNews(rememberNavController())
}