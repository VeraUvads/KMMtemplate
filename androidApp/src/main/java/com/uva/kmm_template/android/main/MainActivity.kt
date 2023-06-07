package com.uva.kmm_template.android.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.uva.kmm_template.android.navigation.ComposeNavigationFactory
import com.uva.kmm_template.android.navigation.TopLevelGraph
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.inject

// import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val factories = getKoin().getAll<ComposeNavigationFactory>() // with qualifier
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopLevelGraph(
                        factories,
                        "home"
                    )
                }
            }
        }
    }
}
