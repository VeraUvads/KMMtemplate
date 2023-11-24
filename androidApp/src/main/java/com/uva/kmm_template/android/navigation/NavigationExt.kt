package com.uva.kmm_template.android.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlin.properties.ReadOnlyProperty

fun NavGraphBuilder.composable(
    destinationRule: DestinationRule,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = destinationRule.computeRoute(),
        arguments = destinationRule.requireArguments,
        content = content,
    )
}

fun NavController.navigateTo(
    destinationRule: DestinationRule,
) {
    navigate(destinationRule.computeRouteWithArgs())
}

fun <T> SavedStateHandle.arg(name: String? = null): ReadOnlyProperty<Any, T> {
    return ReadOnlyProperty { _, property ->
        requireNotNull(get(name ?: property.name)) {
            "Please check if name of variable is correct"
        }
    }
}

fun <T> SavedStateHandle.nullableArg(name: String? = null): ReadOnlyProperty<Any, T?> {
    return ReadOnlyProperty { _, property -> get(name ?: property.name) }
}
