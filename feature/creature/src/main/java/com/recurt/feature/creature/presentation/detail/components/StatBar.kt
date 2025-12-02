package com.recurt.feature.creature.presentation.detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.recurt.feature.creature.domain.model.Stat

@Composable
fun StatBar(
    stat: Stat,
    modifier: Modifier = Modifier
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) stat.value.toFloat() / stat.maxValue else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "stat_animation"
    )

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stat.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(60.dp)
        )

        Text(
            text = "${stat.value}/${stat.maxValue}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(80.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress)
                    .clip(RoundedCornerShape(10.dp))
                    .background(getStatColor(stat.value, stat.maxValue))
            )
        }
    }
}

private fun getStatColor(value: Int, maxValue: Int): Color {
    val percentage = value.toFloat() / maxValue
    return when {
        percentage >= 0.75f -> Color(0xFF4CAF50)
        percentage >= 0.5f -> Color(0xFFFFC107)
        percentage >= 0.25f -> Color(0xFFFF9800)
        else -> Color(0xFFF44336)
    }
}