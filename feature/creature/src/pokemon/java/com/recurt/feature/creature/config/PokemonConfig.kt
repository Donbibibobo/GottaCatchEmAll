package com.recurt.feature.creature.config
import androidx.compose.ui.graphics.Color
import com.recurt.feature.creature.domain.config.CreatureConfig

class PokemonConfig : CreatureConfig {
    override val appName: String = "Pokédex"
    override val idPadLength: Int = 4
    override val primaryColor: Color = Color(0xFF91E8F3)
    override val secondaryColor: Color = Color(0xFF6DAEB7)
    override val primaryDarkColor: Color = Color(0xFF578C92)
    override val secondaryDarkColor: Color = Color(0xFF3D6367)
}