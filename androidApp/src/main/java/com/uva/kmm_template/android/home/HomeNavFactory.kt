package com.uva.kmm_template.android.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uva.kmm_template.android.navigation.ComposeNavigationFactory
import com.uva.kmm_template.android.navigation.DestinationRule
import com.uva.kmm_template.android.navigation.composable
import com.uva.kmm_template.android.navigation.navigateTo

object HomeDestination : DestinationRule() {

    override val screenName: String = "home"
}

class HomeNavFactory : ComposeNavigationFactory {

    override fun create(builder: NavGraphBuilder, controller: NavHostController) {
        builder.composable(HomeDestination) {
            HomeScreen() {
                controller.navigateTo(it)
            }
        }
    }
}
