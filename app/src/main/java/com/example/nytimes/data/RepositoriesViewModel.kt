package com.example.nytimes.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nytimes.model.Repository
import kotlinx.coroutines.launch

class RepositoriesViewModel : ViewModel() {
    private val api = GithubApiService().api
    var repositories: List<Repository> by mutableStateOf(listOf())

    fun getRepositories() {
        viewModelScope.launch {
//            try {
//                val repoList = api.getRepositoriesFromOrgName("slackhq")
//                repositories = repoList
//            } catch (e: Exception) {
//                Log.d("error is", e.message.toString())
//            }
            val repoList = GithubRepository.getRepositories("slackhq")
            repositories = repoList
        }
    }
}