package com.example.todolist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.model.todo

class todoAdapter(
    private var todos: ArrayList<todo>
): RecyclerView.Adapter<todoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutRes = R.layout.todo
        val itemView = layoutInflater.inflate(layoutRes, parent, false)
        return todoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        holder.bind(this.todos[position])
    }

    override fun getItemCount(): Int {
        return this.todos.size
    }


}