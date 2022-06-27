package com.example.todolist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoListCompose.R
import com.example.todolist.model.todo

class todoAdapter(
    private var todos: ArrayList<todo>
): RecyclerView.Adapter<todoViewHolder>() {

    fun interface onCheckedChangeListenner {
        fun onCheckedChange(todo: todo, bool: Boolean)
    }

    private var listenner: onCheckedChangeListenner? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutRes = R.layout.todo
        val itemView = layoutInflater.inflate(layoutRes, parent, false)
        return todoViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        holder.bind(this.todos[position])
    }

    override fun getItemCount(): Int {
        return this.todos.size
    }

    fun setOnCheckedChangeListenner(listenner: onCheckedChangeListenner?){
        this.listenner = listenner
    }

    fun getOnCheckedChangeListenner(): onCheckedChangeListenner?{
        return this.listenner
    }

}