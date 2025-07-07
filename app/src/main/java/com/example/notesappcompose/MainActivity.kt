package com.example.notesappcompose.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import com.example.notesappcompose.presentation.components.NoteItem
import com.example.notesappcompose.model.Note
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.notesappcompose.ui.theme.NotesAppComposeTheme
import com.example.notesappcompose.presentation.components.NotesAppScaffold



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            NotesAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFEFEFEF)
                ) {
                NotesAppScaffold()
                }
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    NotesScreen()
//                }
            }
        }
    }
}

@Composable
fun NotesScreen() {
    var notes by remember { mutableStateOf(listOf<Note>()) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var noteId by remember { mutableIntStateOf(1) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                if (title.isNotBlank() && content.isNotBlank()) {
                    notes = notes + Note(noteId++, title, content)
                    title = ""
                    content = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Note")
        }
        if (notes.isEmpty()) {
            Text(
                text = "No hay notas todavía. Agrega una para comenzar.",
                modifier = Modifier.padding(top = 16.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(notes) { note ->
                    Row() {
                        NoteItem(
                            note = note,
                            onDelete = {
                                notes = notes.filter { it.id != note.id }
                            }
                        )
                    }
                }
            }
        }
    }
}