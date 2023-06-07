package com.uva.kmm_template.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun TopLevelGraph(
    factories: List<ComposeNavigationFactory> ,
    startDestination: String
) {
    val topLevelNavController = rememberNavController()
    NavHost(
        navController = topLevelNavController,
        startDestination = startDestination,
    ) {
        factories.forEach { it.create(this, topLevelNavController) }
    }
}