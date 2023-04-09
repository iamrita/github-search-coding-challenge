package com.example.nytimes

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nytimes.components.HomeView
import com.example.nytimes.ui.theme.MyApplicationTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Basic Instrumented test, which will execute on an Android device.
 * Simply tests that the home page loads with the user input
 * and "get top repositories" button.
 */
@RunWith(AndroidJUnit4::class)
class GithubUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_user_entered_organization_returns_results() {
        // Start the app
        lateinit var textFieldContentDescription: String
        composeTestRule.setContent {
            textFieldContentDescription = "user_input"
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
               HomeView(Modifier)
            }
        }
        composeTestRule.onNodeWithContentDescription(textFieldContentDescription).assertIsDisplayed()
        composeTestRule.onNodeWithText("Get top repositories").assertIsDisplayed()
    }
}