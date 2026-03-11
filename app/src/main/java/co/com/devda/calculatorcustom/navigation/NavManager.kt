package co.com.devda.calculatorcustom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.com.devda.calculatorcustom.view.AddPriceView
import co.com.devda.calculatorcustom.view.CalculatorView
import co.com.devda.calculatorcustom.view.EditPriceView
import co.com.devda.calculatorcustom.view.HomeView
import co.com.devda.calculatorcustom.viewmodel.PriceViewModel

@Composable
fun NavManager(viewModel: PriceViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "calculator") {
        composable("home") {
            HomeView(navController, viewModel)
        }
        composable("add") {
            AddPriceView(navController, viewModel)
        }
        composable("calculator") {
            CalculatorView(navController, viewModel)
        }
        composable("edit/{id}/{price}/{description}", arguments = listOf(
            navArgument("id") { type = NavType.LongType },
            navArgument("price") { type = NavType.LongType },
            navArgument("description") { type = NavType.StringType }
        )) {
            EditPriceView(
                navController,
                viewModel,
                it.arguments!!.getLong("id"),
                it.arguments!!.getLong("price"),
                it.arguments!!.getString("description")
            )
        }
    }
}