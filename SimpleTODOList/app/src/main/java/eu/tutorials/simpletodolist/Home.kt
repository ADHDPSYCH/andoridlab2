package eu.tutorials.simpletodolist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ItemClass(val title: String, val task: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(modifier: Modifier = Modifier) {
    var isOpen by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var task by remember { mutableStateOf("") }
    val items = remember { mutableStateListOf<ItemClass>() }

    if (isOpen) {
        AlertDialog(onDismissRequest = { isOpen = false }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp)),

            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                OutlinedTextField(
                    value = task,
                    onValueChange = { task = it },
                    label = { Text("Task") }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        if (title.isNotEmpty() && task.isNotEmpty()){
                        items.add(ItemClass(title, task))
                        isOpen = false
                        title = ""
                        task = ""
                        }
                    }) {
                        Text("Add")
                    }
                    Button(onClick = { isOpen = false }) {
                        Text("Cancel")
                    }
                }
            }
        }
    }

    Column {
        Row(
            modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Your TODO List",
                fontSize = 30.sp
            )
            IconButton(onClick = { isOpen = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add the task"
                )
            }
        }

        LazyColumn(content = {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = item.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = item.task,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
        })
    }
}