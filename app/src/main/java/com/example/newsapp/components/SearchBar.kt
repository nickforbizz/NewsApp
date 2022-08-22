package com.example.newsapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.network.NewsManager

@Composable
fun SearchBar(q: MutableState<String>, newsManager: NewsManager){
    val localFocusManager = LocalFocusManager.current
    Card(backgroundColor = colorResource(id = R.color.purple_500), elevation = 6.dp, shape = RoundedCornerShape(4.dp),
    modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        TextField(value = q.value, 
            onValueChange = {
                q.value = it
            }, 
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Search", color = White) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "", tint = White)
            },
            trailingIcon = {
                if (q.value != ""){
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "")
                    }
                }
            },
            textStyle = TextStyle(color = White, fontSize = 18.sp),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (q.value != ""){
                        newsManager.getSearchedArticles(q.value)
                    }
                    localFocusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(textColor = White)
        )
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    SearchBar(q = mutableStateOf(""), NewsManager())
}
