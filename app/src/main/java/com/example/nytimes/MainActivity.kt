package com.example.nytimes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.nytimes.data.RepositoriesViewModel
import com.example.nytimes.model.Repository
import com.example.nytimes.ui.theme.MyApplicationTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nytimes.ui.theme.Purple40
import androidx.compose.material.*
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  HomeView(modifier = Modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(modifier: Modifier, viewModel: RepositoriesViewModel = viewModel()) {
    var orgName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp),
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
                    .semantics { contentDescription = "user_input" },

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
        RepositoryList(repositories = viewModel.repositories)
    }

}

@Composable
fun RepositoryList(repositories: List<Repository>) {
    val context = LocalContext.current
    var index = 1
    LazyColumn {
        items(repositories) { repo ->
            Text(
                text = "${index}. ${repo.fullName}",
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        openCustomTab(context, Uri.parse(repo.url))
                    },
                fontWeight = FontWeight.Bold
            )
            index++
        }
    }
}

private fun openCustomTab(context: Context, url: Uri) {
    val packageName = "com.android.chrome"
    val activity = (context as? Activity)
    val builder = CustomTabsIntent.Builder()

    // setting show title
    // to true to display the title for
    // our chrome tabs.
    builder.setShowTitle(true)

    // enabling instant
    // app to open if it is available.
    builder.setInstantAppsEnabled(true)

    // setting tool bar color for our custom chrome tabs.
    builder.setToolbarColor(ContextCompat.getColor(context, R.color.purple_40)) // change color

    // on below line we are creating a
    // variable to build our builder.
    val customBuilder = builder.build()

    if (packageName != null) {
        customBuilder.intent.setPackage(packageName)
        customBuilder.launchUrl(context, url)
    } else {
        // if chrome is not installed in user device
        // start activity normally
        val intent = Intent(Intent.ACTION_VIEW, url)
        activity?.startActivity(intent)
    }
}