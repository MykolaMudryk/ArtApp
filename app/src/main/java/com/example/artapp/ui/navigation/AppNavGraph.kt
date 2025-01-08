// com/example/artapp/ui/navigation/AppNavGraph.kt

package com.example.artapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artapp.ui.screens.currentScreen.ArtDetailsScreen
import com.example.artapp.ui.screens.artsByCategoryScreen.ArtsByCategoryScreen
import com.example.artapp.ui.screens.categoriesScreen.CategoriesScreen
import com.example.artapp.ui.screens.mainScreen.MainScreen
import com.example.artapp.ui.screens.searchScreen.SearchScreen

object ScreenRoutes {
    const val MAIN_SCREEN = "main_screen"
    const val CATEGORIES_SCREEN = "categories_screen"
    const val ARTS_BY_CATEGORY_SCREEN = "artsByCategory_screen/{categoryName}"
    const val ART_DETAILS_SCREEN = "artDetails_screen/{objectId}"
    const val SEARCH_SCREEN = "search_screen"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.MAIN_SCREEN
    ) {
        // Головний екран
        composable(ScreenRoutes.MAIN_SCREEN) {
            MainScreen(
                onArtObjectClick = { artObject ->
                    navController.navigate(
                        ScreenRoutes.ART_DETAILS_SCREEN.replace(
                            "{objectId}", artObject.objectID.toString()
                        )
                    )
                }
            )
        }

        // Екран категорій
        composable(ScreenRoutes.CATEGORIES_SCREEN) {
            CategoriesScreen(
                onCategoryClick = { categoryName ->
                    navController.navigate(
                        ScreenRoutes.ARTS_BY_CATEGORY_SCREEN.replace(
                            "{categoryName}", categoryName
                        )
                    )
                }
            )
        }

        // Екран мистецьких об'єктів за категорією
        composable(
            route = ScreenRoutes.ARTS_BY_CATEGORY_SCREEN,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "painting"
            ArtsByCategoryScreen(
                navController = navController,
                categoryName = categoryName
            )
        }

        // Екран деталей мистецького об'єкта
        composable(
            route = ScreenRoutes.ART_DETAILS_SCREEN,
            arguments = listOf(navArgument("objectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val objectId = backStackEntry.arguments?.getInt("objectId") ?: 0
            ArtDetailsScreen(
                navController = navController,
                objectId = objectId
            )
        }

        // Екран пошуку
        composable(ScreenRoutes.SEARCH_SCREEN) {
            SearchScreen(
                onArtObjectClick = { artObject ->
                    navController.navigate(
                        ScreenRoutes.ART_DETAILS_SCREEN.replace(
                            "{objectId}", artObject.objectID.toString()
                        )
                    )
                }
            )
        }
    }
}
