package com.acharyaprashantassignment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acharyaprashantassignment.features.presentation.HomeScreenContainer
import com.acharyaprashantassignment.features.presentation.HomeScreenViewModel

@Composable
fun NavigationManager(
    launchScreen: String = Routes.HOME_SCREEN,
    navHostController: NavHostController = rememberNavController()
){


    val viewModel = hiltViewModel() as HomeScreenViewModel
    NavHost(navController = navHostController, startDestination = launchScreen) {

        composable(route = Routes.HOME_SCREEN) {
            HomeScreenContainer(viewModel)
        }



    }


}