package com.example.newsapp.ui.screen

import android.graphics.drawable.Icon
import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.models.MockData
import com.example.newsapp.models.MockData.getTimeAgo
import com.example.newsapp.models.NewsData
import com.example.newsapp.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun DetailScreen(navController: NavController, article: TopNewsArticle, scrollState: ScrollState){
    
    
    Scaffold(topBar = {
        DetailTopAppBar(onBackPressed = {navController.popBackStack()})
    }) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(text = "Details Screen", fontWeight = FontWeight.SemiBold)

            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.breaking_news),
                placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news),
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                infoWithIcon(Icons.Default.Edit, article.author?:"N/A")
                infoWithIcon(Icons.Default.DateRange, info =  MockData.stringToDate(article.publishedAt!!).getTimeAgo())

            }

            Text(text = article.title?:"N/A", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
            Text(text = article.description!!, modifier = Modifier.padding(top = 15.dp, bottom = 5.dp))

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                thickness = 1.dp
            )

            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()){
                Button(onClick = {
                    //navController.navigate("TopNews")
                    navController.popBackStack()
                }) {

                    Text(text = "Go Back")
                }
            }

        }
    }

}



@Composable
fun DetailTopAppBar(onBackPressed: ()->Unit = {}){
    TopAppBar(title = {
        Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        })
}

@Composable
fun infoWithIcon(icon: ImageVector, info: String){
    Row {
        Icon(icon, contentDescription = info, modifier = Modifier.padding(end=5.dp), colorResource(
            id = R.color.purple_500
        ))
        Text(text = info)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    DetailScreen(rememberNavController(),
        TopNewsArticle(
            author = "Not available",
            title = "'You are not alone': EU Parliament delegation tells Taiwan on first official visit - Reuters",
            description =
            "The European Parliament's first official delegation to Taiwan said on Thursday the diplomatically isolated island is not alone and called for bolder actions to strengthen EU-Taiwan ties as Taipei faces rising pressure from Beijing.",
            publishedAt = "2021-11-04T03:37:00Z"
        ),
        rememberScrollState()
    )
}