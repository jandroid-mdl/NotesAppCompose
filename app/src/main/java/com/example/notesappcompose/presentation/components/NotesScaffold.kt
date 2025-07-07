package com.example.notesappcompose.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text


import com.example.notesappcompose.ui.NotesScreen





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppScaffold() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Notas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Botón presionado")
                }
            }) {
                Text("+")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            NotesScreen()

        }
    }

}
