package com.example.nytimes.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.nytimes.R
import com.example.nytimes.model.Repository

/**
 * Displays the names and star count of the top three repositories for a given
 * organization. User can click on the name of the github repository to go to its
 * url in a custom chrome tab.
 */
@Composable
fun RepositoryList(modifier: Modifier, repositories: List<Repository>) {
    val context = LocalContext.current
    var url by remember { mutableStateOf("") }
    var index = 1
    LazyColumn {
        items(repositories) { repo ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${index}. ${repo.fullName}",
                    modifier = modifier
                        .clickable {
                            url = repo.url
                            // openCustomTab(context, Uri.parse(repo.url))
                        },
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${repo.stars} \u2B50",
                )
            }
            index++
        }
    }
    AndroidView(modifier = modifier.padding(top = 20.dp), factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    },
        update = { view -> view.loadUrl(url) })
}


/**
 * Opens Custom Chrome tab if user clicks on one of the listed repositories.
 */
private fun openCustomTab(context: Context, url: Uri) {
    val packageName = "com.android.chrome"
    val activity = (context as? Activity)
    val customBuilder = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setInstantAppsEnabled(true) // enabling Instant Apps to open if available
        .setToolbarColor(ContextCompat.getColor(context, R.color.purple_40))
        .build()

    // customBuilder.intent.setPackage(packageName)
    // customBuilder.launchUrl(context, url)

    val i = Intent(Intent.ACTION_VIEW, url)
    activity?.startActivity(i)
}