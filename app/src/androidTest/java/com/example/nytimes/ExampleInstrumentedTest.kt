package com.example.nytimes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nytimes.ui.theme.MyApplicationTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_user_entered_organization_returns_results() {
        // Start the app
        lateinit var textFieldContentDescription: String
        composeTestRule.setContent {
            textFieldContentDescription = "textfield"
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
               HomeView(Modifier)
            }
        }
        composeTestRule.onNodeWithContentDescription(textFieldContentDescription).performTextInput("slackhq")
        composeTestRule.onNodeWithText("Go").performClick()
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("slackhq/circuit").fetchSemanticsNodes().size == 1
        }
    }
}