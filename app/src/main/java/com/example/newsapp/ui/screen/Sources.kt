package com.example.newsapp.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.models.TopNewsArticle
import com.example.newsapp.network.NewsManager

@Composable
fun Sources(newsManager: NewsManager){

    val items = listOf(
       "TechCrunch" to "techcrunch",
       "TalkSport" to "talksport",
       "Business Insider" to "business-insider",
       "Reuters" to "reuters",
       "TheVerge" to "the-verge",
    )
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "${newsManager.srcName.value.uppercase()} Source") },
                actions = {

                    var menuExpanded by remember { mutableStateOf(false)}
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }

                    MaterialTheme(shapes = MaterialTheme.shapes.copy(
                        medium = RoundedCornerShape(16.dp)
                    )){
                        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false}) {
                            items.forEach{
                                DropdownMenuItem(onClick = {
                                    newsManager.srcName.value = it.second
                                    menuExpanded = false
                                }) {
                                    Text(text = it.first)
                                }
                            }

                        }
                    }
                }
            )
        }
            ){
        newsManager.getArticlesBySource()
        val articles = newsManager.getArticleBySource.value
        SourceContent(articles = articles.articles ?: listOf())

    }
}


@Composable
fun SourceContent(articles:List<TopNewsArticle>){

    val urlHandler = LocalUriHandler.current
    LazyColumn{
        items(articles){
            article ->
            val annotatedString  = buildAnnotatedString {
                pushStringAnnotation(
                    tag = "URL",
                    annotation = article.url ?: "newsapi.org"
                )
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.purple_500),
                        textDecoration = TextDecoration.Underline
                    )
                ){
                    append("Read More")
                }
            }
            
            Card(backgroundColor = colorResource(id = R.color.purple_500), elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier
                    .height(200.dp)
                    .padding(end = 6.dp, start = 6.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = article.title ?: "Not Available",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = article.description ?: "Not Available",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    Card(backgroundColor = Color.White, elevation = 6.dp) {
                       ClickableText(text = annotatedString, modifier = Modifier.padding(8.dp), onClick = {
                           annotatedString.getStringAnnotations(it, it).firstOrNull()?.let{
                               res ->
                               if (res.tag === "URL"){
                                   urlHandler.openUri(res.item)
                               }
                           }
                       })
                    }
                }
            }
        }
    }
}