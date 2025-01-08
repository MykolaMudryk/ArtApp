package com.example.artapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.artapp.ui.navigation.AppNavGraph
import com.example.artapp.ui.theme.ArtAppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtAppTheme {
                AppNavGraph()
            }
        }
    }
}
