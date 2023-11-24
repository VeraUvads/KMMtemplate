package com.uva.kmm_template.android.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.uva.kmm_template.android.home.HomeDestination
import com.uva.kmm_template.android.navigation.ComposeNavigationFactory
import com.uva.kmm_template.android.navigation.TopLevelGraph
import com.uva.kmm_template.android.utils.injectAll
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {
    private val factories: List<ComposeNavigationFactory> by injectAll(named("topLevel"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            factories.forEach { Logger.e(it.toString()) }
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    TopLevelGraph(
                        factories,
                        HomeDestination.computeRoute(),
                    )
                }
            }
        }
    }
}
