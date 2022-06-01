package com.example.composeTodoList.model

data class Todo(
    val text: String,
    val isUrgent: Boolean,
    val isDone: Boolean
) {}