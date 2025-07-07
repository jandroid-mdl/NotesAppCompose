package com.example.notesappcompose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesappcompose.model.Note

@Composable
fun NoteItem(
    note: Note,
    onDelete: (Note) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${note.id}. ${note.title}: ${note.content}",
            modifier = Modifier.weight(1f)
        )
        Button(onClick = { onDelete(note) }) {
            Text("Eliminar")
        }
    }
}
