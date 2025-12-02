package com.recurt.encyclopedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.recurt.core.common.theme.EncyclopediaTheme
import com.recurt.encyclopedia.navigation.EncyclopediaNavigation
import com.recurt.feature.creature.domain.config.CreatureConfig
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val creatureConfig: CreatureConfig by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EncyclopediaTheme(config = creatureConfig) {
                EncyclopediaNavigation()
            }
        }
    }
}
