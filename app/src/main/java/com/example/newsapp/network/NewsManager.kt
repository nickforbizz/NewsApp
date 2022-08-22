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

    val srcName = mutableStateOf("abc-news")
    val q = mutableStateOf("")

    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember{
            _newsResponse
        }

    private val _searchedNewsResponse = mutableStateOf(TopNewsResponse())
    val searchedNewsResponse: State<TopNewsResponse>
        @Composable get() = remember{
            _searchedNewsResponse
        }

    private val _getArticleByCategory = mutableStateOf(TopNewsResponse())
    val getArticleByCategory: MutableState<TopNewsResponse>
        @Composable get() = remember{
            _getArticleByCategory
        }

    private val _getArticleBySource = mutableStateOf(TopNewsResponse())
    val getArticleBySource: MutableState<TopNewsResponse>
        @Composable get() = remember{
            _getArticleBySource
        }

    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)
    init {
        getArticles()
    }

    private fun getArticles(){
        val service = Api.retrofitService.getTopArticles(Api.COUNTRY)
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


    fun getArticlesByCategory(category: String){
        val service = Api.retrofitService.getArticlesByCategory(category)
        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _getArticleByCategory.value = response.body()!!
                    Log.d("news By Category", "${_getArticleByCategory.value}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error34", " ${t}")
            }

        })
    }

    fun getArticlesBySource(){
        val service = Api.retrofitService.getArticlesBySource(srcName.value)
        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _getArticleBySource.value = response.body()!!
                    Log.d("news By Src", "${_getArticleBySource.value}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error34", " ${t}")
            }

        })
    }

    fun getSearchedArticles(q:String){
        val service = Api.retrofitService.getArticles(q)
        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _searchedNewsResponse.value = response.body()!!
                    Log.d("SearchQ", "searched by name: ${_searchedNewsResponse.value}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("SearchError", " ${t}")
            }

        })
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getArticleCategory(category)
        selectedCategory.value = newCategory
    }

}