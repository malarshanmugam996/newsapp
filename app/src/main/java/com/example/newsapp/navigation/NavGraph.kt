package com.example.newsapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.api.SpaceNewsApi
import com.example.newsapp.data.api.SpaceNewsApiProvider
import com.example.newsapp.data.datastore.PreferenceDataStore
import com.example.newsapp.data.local.AppDatabase
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.ui.detail.NewsDetailScreen
import com.example.newsapp.ui.detail.NewsDetailViewModel
import com.example.newsapp.ui.detail.NewsDetailViewModelFactory
import com.example.newsapp.ui.list.NewsListScreen
import com.example.newsapp.ui.list.NewsListViewModel
import com.example.newsapp.ui.list.NewsListViewModelFactory
import com.example.newsapp.ui.preference.PreferenceScreen
import com.example.newsapp.ui.preference.PreferenceViewModel
import com.example.newsapp.ui.preference.PreferenceViewModelFactory


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = remember { AppDatabase.getInstance(context) }
    val preferenceDataStore = remember {
        PreferenceDataStore(context)
    }

    val repository = remember {
        NewsRepository(
            api = SpaceNewsApiProvider.api,
            dao = database.articleDao(),
            preferenceDataStore = preferenceDataStore
        )
    }






    // ✅ ViewModel
    val newsListViewModel: NewsListViewModel = viewModel(
        factory = NewsListViewModelFactory(repository)
    )

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NewsListScreen(
                viewModel = newsListViewModel,
                onArticleClick = { id -> navController.navigate("detail/$id") },
                onPreferenceClick = { navController.navigate("prefs") }
            )
        }

        composable("detail/{id}") { backStackEntry ->

            val articleId = backStackEntry.arguments
                ?.getString("id")
                ?.toIntOrNull()
                ?: return@composable

            val detailViewModel: NewsDetailViewModel = viewModel(
                key = "detail_$articleId", // 🔥 VERY IMPORTANT
                factory = NewsDetailViewModelFactory(repository, articleId)
            )

            NewsDetailScreen(
                viewModel = detailViewModel,
                onBack = { navController.popBackStack() },
                onNext = {
                    navController.navigate("detail/${articleId + 1}") {
                        popUpTo("detail/$articleId") { inclusive = true }
                    }
                },
                onPrevious = {
                    if (articleId > 1) {
                        navController.navigate("detail/${articleId - 1}") {
                            popUpTo("detail/$articleId") { inclusive = true }
                        }
                    }
                }
            )
        }



        composable("prefs") {

            val preferenceViewModel: PreferenceViewModel = viewModel(
                factory = PreferenceViewModelFactory(repository)
            )

            PreferenceScreen(
                viewModel = preferenceViewModel,
                onSave = { navController.popBackStack() }
            )
        }

    }
}


