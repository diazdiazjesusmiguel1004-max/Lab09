package com.example.lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab09.model.PostModel
import com.example.lab09.network.PostApiService
import com.example.lab09.ui.theme.Lab09Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab09Theme {
                ProgPrincipal9()
            }
        }
    }
}

@Composable
fun ProgPrincipal9() {
    val urlBase = "https://jsonplaceholder.typicode.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val servicio = retrofit.create(PostApiService::class.java)
    val navController = rememberNavController()

    Scaffold(
        topBar = { BarraSuperior() },
        bottomBar = { BarraInferior(navController) },
        content = { paddingValues ->
            Contenido(paddingValues, navController, servicio)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "JSONPlaceHolder Access",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun BarraInferior(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.LightGray
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = navController.currentDestination?.route == "inicio",
            onClick = { navController.navigate("inicio") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Favorite, contentDescription = "Posts") },
            label = { Text("Posts") },
            selected = navController.currentDestination?.route == "posts",
            onClick = { navController.navigate("posts") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Productos") },
            label = { Text("Productos") },
            selected = navController.currentDestination?.route == "products",
            onClick = { navController.navigate("products") }
        )
    }
}

@Composable
fun Contenido(
    pv: PaddingValues,
    navController: NavHostController,
    servicio: PostApiService
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(pv)
    ) {
        NavHost(
            navController = navController,
            startDestination = "posts"
        ) {
            composable("inicio") { ScreenInicio() }
            composable("posts") { ScreenPosts(navController, servicio) }
            composable("postsVer/{id}", arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                ScreenPost(navController, servicio, id)
            }
            composable("products") { ProductsListScreen(navController) }
            composable("productDetail/{id}", arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                ProductDetailScreen(navController, id)
            }
        }
    }
}

@Composable
fun ScreenInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido a Lab09",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "App que consume JSONPlaceholder API",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Usa la barra inferior para navegar a Posts",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgPrincipal9() {
    Lab09Theme {
        ProgPrincipal9()
    }
}