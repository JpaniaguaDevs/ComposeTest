package com.nsel.testcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.nsel.testcompose.presentation.view.login.MainActivity
import org.junit.Rule
import org.junit.Test

class LoginToHomeJourneyTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun completeUserJourney_loginSuccessToHomeScreen() {
        composeTestRule.onNodeWithTag("username_input")
            .performTextInput("Juan")

        composeTestRule.onNodeWithTag("password_input")
            .performTextInput("123456")

        composeTestRule.onNodeWithTag("login_button")
            .performClick()

        // 4. Esperar a que la corrutina termine y navegue
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Últimos Clientes")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        // 5. Asertar que estamos en Home
        composeTestRule.onNodeWithText("Últimos Clientes")
            .assertIsDisplayed()
    }
}