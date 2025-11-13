package com.example.cadastropatrocinadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*   // ✅ Import necessário para remember e mutableStateOf
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color(0xFFCA5A0C).toArgb()

        setContent {
            var telaAtual by remember { mutableStateOf("cadastro") }

            when (telaAtual) {
                "cadastro" -> TelaCadastro(
                    onVerCadastrados = { telaAtual = "lista" } // 👉 vai para a lista
                )
                "lista" -> TelaListaPatrocinadores(
                    onVoltar = { telaAtual = "cadastro" } // 👉 volta para o cadastro
                )
            }
        }
    }
}
