package com.uva.kmm_template.android.detail

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.uva.kmm_template.android.navigation.ComposeNavigationFactory
import com.uva.kmm_template.android.navigation.DestinationRule
import com.uva.kmm_template.android.navigation.composable

object DetailDestination : DestinationRule() {

    override val screenName: String = "detail"
    override val requireArguments: List<NamedNavArgument> = listOf(
        navArgument("detailedItem") {
            type = NavType.StringType
        }
    )

    fun with(detailedItem: String): DestinationRule {
        return super.with(detailedItem)
    }
}

class DetailNavFactory : ComposeNavigationFactory {

    override fun create(builder: NavGraphBuilder, controller: NavHostController) {
        builder.composable(DetailDestination) {
            DetailScreen()
        }
    }
}
