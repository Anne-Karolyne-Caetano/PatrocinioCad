package com.example.cadastropatrocinadores

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.material3.TextFieldDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(onVoltar: () -> Unit = {}, onVerCadastrados: () -> Unit = {}) {
    var nomePatrocinador by remember { mutableStateOf("") }
    var cnpjPatrocinador by remember { mutableStateOf("") }
    var contatoPatrocinador by remember { mutableStateOf("") }
    var valorInvestido by remember { mutableStateOf("") }
    var competicao by remember { mutableStateOf("") }


    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val scope = rememberCoroutineScope()

    var salvando by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Patrocíonio",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF710F)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            OutlinedTextField(
                value = nomePatrocinador,
                onValueChange = { nomePatrocinador = it },
                label = { Text("Nome do patrocinador") },
                modifier = Modifier.fillMaxWidth(),

                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFFFF710F),
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color(0xFFFF710F),
                    cursorColor = Color(0xFFFF710F),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedContainerColor = Color(0xFFBDBDBD),
                    unfocusedContainerColor = Color(0xFFBDBDBD)
                )


            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = cnpjPatrocinador,
                onValueChange = { cnpjPatrocinador = it },
                label = { Text("CNPJ do patrocinador") },
                modifier = Modifier.fillMaxWidth(),

                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFFFF710F),
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color(0xFFFF710F),
                    cursorColor = Color(0xFFFF710F),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedContainerColor = Color(0xFFBDBDBD),
                    unfocusedContainerColor = Color(0xFFBDBDBD)
                )

            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = competicao,
                onValueChange = { competicao = it },
                label = { Text("Competição patrocinada") },
                modifier = Modifier.fillMaxWidth(),

                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFFFF710F),
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color(0xFFFF710F),
                    cursorColor = Color(0xFFFF710F),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedContainerColor = Color(0xFFBDBDBD),
                    unfocusedContainerColor = Color(0xFFBDBDBD)
                )

            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = contatoPatrocinador,
                    onValueChange = { contatoPatrocinador = it },
                    label = { Text("Contato") },
                    modifier = Modifier.weight(2f),

                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFFFF710F),
                        unfocusedIndicatorColor = Color.Gray,
                        focusedLabelColor = Color(0xFFFF710F),
                        cursorColor = Color(0xFFFF710F),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray,
                        focusedContainerColor = Color(0xFFBDBDBD),
                        unfocusedContainerColor = Color(0xFFBDBDBD)
                    )
                )

                OutlinedTextField(
                    value = valorInvestido,
                    onValueChange = { valorInvestido = it },
                    label = { Text("Valor") },
                    modifier = Modifier.weight(1f),

                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFFFF710F),
                        unfocusedIndicatorColor = Color.Gray,
                        focusedLabelColor = Color(0xFFFF710F),
                        cursorColor = Color(0xFFFF710F),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray,
                        focusedContainerColor = Color(0xFFBDBDBD),
                        unfocusedContainerColor = Color(0xFFBDBDBD)
                    )
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (nomePatrocinador.isNotBlank() && cnpjPatrocinador.isNotBlank()) {
                        scope.launch {
                            salvando = true

                            db.patrocinadorDao().inserir(
                                Patrocinador(
                                    nome = nomePatrocinador,
                                    cnpj = cnpjPatrocinador,
                                    contato = contatoPatrocinador,
                                    valor = valorInvestido,
                                    competicao = competicao
                                )
                            )

                            // Limpa os campos
                            nomePatrocinador = ""
                            cnpjPatrocinador = ""
                            contatoPatrocinador = ""
                            valorInvestido = ""
                            competicao = ""

                            salvando = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF710F),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                if (salvando) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 3.dp,
                        modifier = Modifier.size(26.dp)
                    )
                } else {
                    Text("Cadastrar", fontSize = 18.sp)
                }
            }

            Button(
                onClick = {
                    scope.launch {
                        if (!salvando) {
                            onVerCadastrados()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Ver Cadastrados", fontSize = 18.sp)
            }
        }
    }
}
