package com.example.nytimes.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.nytimes.model.Repository
import kotlinx.coroutines.launch

// should i take in the repo here, or use static
class RepositoriesViewModel : ViewModel() {
    var repositories: List<Repository> by mutableStateOf(listOf())
    fun getRepositories(orgName: String) {
        viewModelScope.launch {
            try {
                val repoList = GithubRepository.getRepositories(orgName)
                repositories = repoList
            } catch (e: Exception) {
                Log.d("error is", e.message.toString())
            }

        }
    }
}