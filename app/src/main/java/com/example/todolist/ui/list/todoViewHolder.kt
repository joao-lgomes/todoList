package com.example.todolist.ui.list

import android.view.View
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.model.todo

class todoViewHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView) {
    private val frameUrgent: FrameLayout = itemView.findViewById(R.id.frameUrgent)
    private val txtText: TextView = itemView.findViewById(R.id.txtText)
    private val checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone)

    private lateinit var currentTodo: todo

    fun bind(chatMessage: todo) {
        this.currentTodo = chatMessage
        if(currentTodo.isUrgent)
            this.frameUrgent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.red))
        else
            this.frameUrgent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green))
        this.txtText.text = currentTodo.text;
        this.checkBoxDone.isChecked = currentTodo.isDone
    }

}