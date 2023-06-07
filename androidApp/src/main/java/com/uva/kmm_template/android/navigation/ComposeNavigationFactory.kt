package com.uva.kmm_template.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Idea from https://habr.com/ru/company/moex/blog/586192/
 */
interface ComposeNavigationFactory {

    fun create(builder: NavGraphBuilder, controller: NavHostController)
}

interface TopLevelDeclaration : ComposeNavigationFactory
