package com.example.nytimes.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nytimes.data.RepositoriesViewModel

/**
 * Encompasses the entire home screen. Has a top bar with the name of the app.
 * User can type in a name of a github organization in the
 * text field and click the "Get top repositories" button to get the top three repositories
 * for that organization by star count, displayed in [RepositoryList].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(modifier: Modifier, viewModel: RepositoriesViewModel = viewModel()) {
    var orgName by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Github Repository Search") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White
            )
        )
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 76.dp), // 56.dp is height of top bar, added 20.dp to give space
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = modifier.width(240.dp)) {
                TextField(
                    value = orgName,
                    onValueChange = { orgName = it },
                    label = { Text("Organization name:") },
                    modifier = modifier
                        .fillMaxWidth()
                        .semantics { contentDescription = "user_input" }, // for UI testing

                    )
            }
            Button(
                modifier = modifier
                    .width(240.dp)
                    .padding(10.dp),
                onClick = {
                    viewModel.getRepositories(orgName)
                }) {
                Text(text = "Get top repositories")
            }
            RepositoryList(modifier, repositories = viewModel.repositories)
        }
        it // don't need to use scaffold padding values for anything
    }
}

