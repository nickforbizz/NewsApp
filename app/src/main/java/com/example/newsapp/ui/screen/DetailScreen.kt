package com.example.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.models.NewsData

@Composable
fun DetailScreen(navController: NavController, newsData: NewsData){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Details Screen", fontWeight = FontWeight.SemiBold)
        Button(onClick = {
            //navController.navigate("TopNews")
            navController.popBackStack()
        }) {
            Text(text = "Go To Top News + ${newsData.author}")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    DetailScreen(rememberNavController(),
        NewsData(
            3,
            R.drawable.reuters,
            author = "Not available",
            title = "'You are not alone': EU Parliament delegation tells Taiwan on first official visit - Reuters",
            description =
            "The European Parliament's first official delegation to Taiwan said on Thursday the diplomatically isolated island is not alone and called for bolder actions to strengthen EU-Taiwan ties as Taipei faces rising pressure from Beijing.",
            publishedAt = "2021-11-04T03:37:00Z"
        ),)
}