package com.example.nytimes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import com.example.nytimes.data.GithubApi
import com.example.nytimes.data.GithubRepository
import com.example.nytimes.data.RepositoriesViewModel
import com.example.nytimes.model.Repository
import com.example.nytimes.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<RepositoriesViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserInput(Modifier, mainViewModel)

                }
            }
        }
    }
}

@Composable
fun RepositoryList(repositories: List<Repository>) {
    val context = LocalContext.current
    LazyColumn {
        items(repositories) { repo ->
            Text(text = repo.fullName,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.url))
                context.startActivity(intent)
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UserInput(modifier: Modifier, viewModel: RepositoriesViewModel) {
    var name by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = {
            // Dismiss the keyboard when the user clicks outside of the TextField
            keyboardController?.hide()
            // maybe remove ripple effect?
        })
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = modifier.width(240.dp)) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Enter your desired username:") },
                    modifier = modifier.fillMaxWidth()
                )
            }
            Button(onClick = { viewModel.getRepositories(name) }) {
                Text(text = "Go")
            }
            RepositoryList(repositories = viewModel.repositories)
        }
    }

    // add a button which sends the name to call api on click callback
    // add error checking for the name

    Log.d("name is", name)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}