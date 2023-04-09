package com.example.nytimes.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.nytimes.model.Repository
import kotlinx.coroutines.launch

/**
 * RepositoriesViewModel is a view model that represents the result of the data fetching. It
 * is used by the UI in [MainActivity] to display the results of the API call.
 */
class RepositoriesViewModel: ViewModel() {
    var repositories: List<Repository> by mutableStateOf(listOf())
    fun getRepositories(orgName: String) {
        viewModelScope.launch {
            try {
                val repoList = GithubRepository().getRepositories(orgName)
                repositories = repoList
            } catch (e: Exception) {
                Log.d("error is", e.message.toString())
            }

        }
    }
}