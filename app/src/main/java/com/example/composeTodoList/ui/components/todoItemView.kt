package com.example.composeTodoList.ui.components

import androidx.appcompat.widget.ActivityChooserView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeTodoList.model.Todo

@Composable
fun todoItemView(todo: Todo){
    Card(modifier =
    Modifier
        .padding(horizontal = 5.dp, vertical = 2.5.dp)
        .fillMaxWidth()
        .height(IntrinsicSize.Min),
        backgroundColor = Color.White
    ) {
        Row{
           frameUrgent(todo, Modifier.padding(end = 10.dp))
           taskText(todo,
               Modifier
                   .fillMaxWidth()
                   .weight(1f))
           checkBoxDone(todo)
        }
    }
}

@Composable
fun frameUrgent(todo: Todo, modifier: Modifier = Modifier){
    if(todo.isUrgent)    Box(modifier = modifier
        .width(30.dp)
        .fillMaxHeight()
        .background(Color.Red))
    else                 Box(modifier = modifier
        .width(30.dp)
        .fillMaxHeight()
        .background(Color.Green))
}

@Composable
fun taskText(todo: Todo, modifier: Modifier = Modifier){
    Text(
        text = todo.text,
        maxLines = 1,
        style = MaterialTheme.typography.h3,
        fontSize = 42.sp,
        overflow = TextOverflow.Ellipsis)
}

@Composable
fun checkBoxDone(todo: Todo, modifier: Modifier = Modifier){
    Row{
        Checkbox(checked = todo.isDone, onCheckedChange = {
            todo.isDone = it;
        }, modifier = Modifier.align(Alignment.CenterVertically))
        Text(text = "Done", textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.CenterVertically) )
    }
    
}


@Preview
@Composable
fun previewTodoItemView(){
    val todo = Todo("Fazer o TFC2", true, false);
    todoItemView(todo);
}