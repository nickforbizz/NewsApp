package com.example.newsapp.network

import android.util.Log
import androidx.compose.runtime.*
import com.example.newsapp.models.ArticleCategory
import com.example.newsapp.models.TopNewsResponse
import com.example.newsapp.models.getAllArticlesCategory
import com.example.newsapp.models.getArticleCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {

    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember{
            _newsResponse
        }

    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)
    init {
        getArticles()
    }

    private fun getArticles(){
        val service = Api.retrofitService.getTopArticles("us", "50cd46070c1e4fe8be9609293a0728ec")
        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _newsResponse.value = response.body()!!
                    Log.d("news56", "${_newsResponse.value}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error34", " ${t}")
            }

        })
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getArticleCategory(category)
        selectedCategory.value = newCategory
    }

}