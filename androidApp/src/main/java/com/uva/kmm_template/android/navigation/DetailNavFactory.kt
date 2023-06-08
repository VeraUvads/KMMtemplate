package com.uva.kmm_template.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uva.kmm_template.android.detail.DetailScreen

class DetailNavFactory : ComposeNavigationFactory {

    override fun create(builder: NavGraphBuilder, controller: NavHostController) {
        builder.composable("detail") {
            DetailScreen()
        }
    }
}
