package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoListCompose.R
import com.example.todolist.model.todo
import com.example.todolist.ui.list.todoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var eTxtInput: EditText
    private lateinit var todos: ArrayList<todo>
    private lateinit var rvTodoList: RecyclerView
    private lateinit var todoAdapter: todoAdapter
    private lateinit var switch_urgent: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.eTxtInput = findViewById(R.id.eTxtInput)
        this.rvTodoList = findViewById(R.id.rvTodoList)
        this.switch_urgent = findViewById(R.id.switch_urgent)

        if(savedInstanceState == null)
            this.todos = ArrayList()
        else if(savedInstanceState.containsKey("RECOVER_MESSAGES")) {
            this.todos =
                savedInstanceState.getParcelableArrayList<todo>("RECOVER_MESSAGES")
                        as ArrayList<todo>
        }

        this.todoAdapter = todoAdapter(this.todos)
        this.todoAdapter.setOnCheckedChangeListenner{ todo, bool ->
            todo.isDone = bool
        }

        this.rvTodoList.layoutManager = LinearLayoutManager(this)
        this.rvTodoList.adapter = this.todoAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("RECOVER_MESSAGES", this.todos)
    }

    fun onClickSend(v: View) {
        val messageText = this.eTxtInput.text.toString()
        if(messageText.isNotBlank()) {
            val todo = todo(messageText,
                this.switch_urgent.isChecked,
                false)
            this.todos.add(todo)
            this.todoAdapter.notifyItemInserted(this.todos.size-1)
            this.rvTodoList.scrollToPosition(this.todos.size-1)
            this.eTxtInput.text.clear()
        }
    }
}