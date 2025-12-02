package com.recurt.encyclopedia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.recurt.feature.creature.presentation.detail.CreatureDetailScreen
import com.recurt.feature.creature.presentation.list.CreatureListScreen
import kotlinx.serialization.Serializable

@Serializable
object CreatureListRoute

@Serializable
data class CreatureDetailRoute(val id: String)

@Composable
fun EncyclopediaNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CreatureListRoute
    ) {
        composable<CreatureListRoute> {
            CreatureListScreen(
                onCreatureClick = { id ->
                    navController.navigate(CreatureDetailRoute(id))
                }
            )
        }

        composable<CreatureDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CreatureDetailRoute>()
            CreatureDetailScreen(
                creatureId = route.id,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}