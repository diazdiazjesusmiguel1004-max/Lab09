package com.example.lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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

    // 👇 CREAR INSTANCIA DEL SERVICIO (Paso 5.2.1)
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

@Composable
fun BarraSuperior() {
    Text(text = "Lab09 - Posts")
}

@Composable
fun BarraInferior(navController: Any) {
    Text(text = "Navegación")
}

@Composable
fun Contenido(paddingValues: androidx.compose.foundation.layout.PaddingValues, navController: Any, servicio: PostApiService) {
    Text(text = "Contenido principal - Servicio listo")
}

@Preview(showBackground = true)
@Composable
fun PreviewProgPrincipal9() {
    Lab09Theme {
        ProgPrincipal9()
    }
}