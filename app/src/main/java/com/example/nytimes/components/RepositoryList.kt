package com.example.nytimes.components

import android.content.Context
import android.net.Uri
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
                            openCustomTab(context, Uri.parse(repo.url))
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
}

/**
 * Opens Custom Chrome tab if user clicks on one of hte listed repositories.
 */
private fun openCustomTab(context: Context, url: Uri) {
    val packageName = "com.android.chrome"
    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)

    // enabling Instant Apps to open if available
    builder.setInstantAppsEnabled(true)

    builder.setToolbarColor(ContextCompat.getColor(context, R.color.purple_40)) // change color

    val customBuilder = builder.build()

    // if chrome is installed, launch custom tab
    customBuilder.intent.setPackage(packageName)
    customBuilder.launchUrl(context, url)
}