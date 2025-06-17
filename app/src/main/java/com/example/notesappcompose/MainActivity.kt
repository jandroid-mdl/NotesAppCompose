package com.example.notesappcompose

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
import com.example.notesappcompose.model.Note
import com.example.notesappcompose.ui.theme.NotesAppComposeTheme











class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NotesScreen()
                }
            }
        }
    }
}

@Composable
fun NotesScreen() {
    var notes by remember { mutableStateOf(listOf<Note>()) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var noteId by remember { mutableStateOf(1) }

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
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
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

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(notes.size) { index ->
                val note = notes[index]
                Text("${note.id}. ${note.title}: ${note.content}")
            }
        }
    }
}