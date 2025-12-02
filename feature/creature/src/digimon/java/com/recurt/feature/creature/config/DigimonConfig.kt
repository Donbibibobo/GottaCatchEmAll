package com.recurt.feature.creature.config

import androidx.compose.ui.graphics.Color
import com.recurt.feature.creature.domain.config.CreatureConfig

class DigimonConfig : CreatureConfig {
    override val appName: String = "Digimon Encyclopedia"
    override val idPadLength: Int = 3
    override val primaryColor: Color = Color(0xFFF5C3B8)
    override val secondaryColor: Color = Color(0xFFE8A898)
    override val primaryDarkColor: Color = Color(0xFFD18B78)
    override val secondaryDarkColor: Color = Color(0xFFAD6655)
}