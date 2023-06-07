package com.uva.kmm_template.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uva.kmm_template.android.home.HomeScreen

class HomeNavFactory : ComposeNavigationFactory {

    override fun create(builder: NavGraphBuilder, controller: NavHostController) {
        builder.composable("home") {
            HomeScreen()
        }
    }
}
