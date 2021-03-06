package br.pro.viniciusfm.funnymoviesapp.ui.components.frames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeTodoList.data.todoRequest
import com.example.composeTodoList.data.todoSingleton
import com.example.composeTodoList.model.Todo
import com.example.composeTodoList.ui.components.todoItemView
import kotlinx.coroutines.launch

@Composable
fun MainScreen(todoRequest: todoRequest) {
    var switchIsUrgentValue = remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    var textFieldText = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        modifier =
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),

    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                LazyColumn (modifier = Modifier.fillMaxHeight(0.6f),state = listState) {
                    var getTodos = todoSingleton.getTodos();
                    items(getTodos) { todo ->
                        todoItemView(todo = todo, todoRequest)
                    }
                }


                Column(modifier = Modifier.padding(
                    start = 15.dp, end = 15.dp, top = 10.dp, bottom = 3.dp), verticalArrangement = Arrangement.Bottom
                ) {

                    // SWITCH ROW
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            "Urgente",
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                        )
                        Switch(
                            checked = switchIsUrgentValue.value,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colors.primary,
                                uncheckedThumbColor = MaterialTheme.colors.primary,
                                checkedTrackColor = Color.Red,
                                uncheckedTrackColor = Color.Green,
                            ),
                            onCheckedChange = {
                                switchIsUrgentValue.value = !switchIsUrgentValue.value;
                            },
                        )
                    }


                    //TEXT FIELD ROW
                    Row(Modifier.fillMaxWidth().padding(top = 10.dp).weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        TextField(
                            value = textFieldText.value,
                            onValueChange = { newValue -> textFieldText.value = newValue },
                            Modifier.fillMaxWidth().fillMaxHeight(),
                            textStyle = TextStyle(color = MaterialTheme.colors.primary),
                            placeholder = { Text("Descreva a Tarefa...") },
                        )
                    }



                    //BUTTON ROW
                    Row(Modifier.fillMaxWidth().height(50.dp), verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f),
                            onClick = {
                                todoRequest.addNewTodo(Todo(text = textFieldText.value.text, isUrgent = switchIsUrgentValue.value, isDone = false),);
                                textFieldText.value = TextFieldValue("")
                            },
                        ) {
                            Text("Salvar Tarefa")
                        }
                    }


                }



                //columnAddNewTodo(switchIsUrgentValue, textFieldText, todoRequest)
                //switchRow(switchIsUrgentValue)
                //TextFieldRow(textFieldText)
                //buttonRow(switchIsUrgentValue, textFieldText, todoRequest)
            }
        }
    }
}

/*
@Preview
@Composable
fun previewMainScreenView(){
    MainScreen()
}*/
