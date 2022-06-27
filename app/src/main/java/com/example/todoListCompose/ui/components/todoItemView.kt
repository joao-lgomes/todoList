package com.example.composeTodoList.ui.components

import android.graphics.Paint
import android.graphics.fonts.FontStyle
import androidx.appcompat.widget.ActivityChooserView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeTodoList.model.Todo
import com.example.todoListCompose.ui.theme.white

@Composable
fun todoItemView(todo: Todo){

    var maxlines = remember { mutableStateOf(1) };

    var todoIsDone by remember {
        mutableStateOf(todo.isDone)
    }

    Card(modifier =
    Modifier
        .padding(horizontal = 5.dp, vertical = 2.5.dp)
        .fillMaxWidth() ,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colors.primaryVariant).height(intrinsicSize = IntrinsicSize.Min).clickable {
                if(maxlines.value == 1) maxlines.value = 10000 else maxlines.value = 1;
            },
            horizontalArrangement = Arrangement.SpaceBetween){
           frameUrgent(todo, Modifier.padding(end = 10.dp))
           taskText(todo, maxlines,
               Modifier
                   .fillMaxWidth(0.6f)
                   .weight(1f))


            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End, modifier = Modifier.padding(end = 5.dp)){
                Checkbox(checked = todoIsDone, onCheckedChange = {
                    todoIsDone = !todoIsDone;
                    todo.isDone = todoIsDone;
                }, modifier = Modifier.align(Alignment.CenterVertically))
                Text(text = "Done",color = MaterialTheme.colors.primary, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.CenterVertically) )
            }
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
fun taskText(todo: Todo, maxlines: MutableState<Int>, modifier: Modifier = Modifier){
    Text(
        text = todo.text,
        fontSize = 15.sp,
        maxLines = maxlines.value,
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxWidth(0.55f),
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center)
}


@Preview
@Composable
fun previewTodoItemView(){
    val todo = Todo(1,"aAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", true, false);
    todoItemView(todo);
}