package com.example.composeTodoList.data

import androidx.compose.runtime.mutableStateListOf
import com.example.composeTodoList.model.Todo

object todoSingleton {
    private val to_dos = mutableStateListOf<Todo>()
    fun updateTodos(todos: ArrayList<Todo>) {
        this.to_dos.clear()
        this.to_dos.addAll(todos)
    }
    fun getTodos(): List<Todo> {
        return this.to_dos
    }
}