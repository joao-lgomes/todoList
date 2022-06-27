package com.example.composeTodoList.model

data class Todo(
    var id: Long? = 0,
    val text: String,
    val isUrgent: Boolean,
    var isDone: Boolean
) {}