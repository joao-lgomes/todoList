package com.example.composeTodoList.data

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.composeTodoList.model.Todo
import org.json.JSONArray
import org.json.JSONObject

class todoRequest(context: Context) {
    private val queue = Volley.newRequestQueue(context)
    companion object {
        private val URL = "http://10.0.2.2:8000"
        private val GET_TODOS = "/tasks"
        private val POST_TODO = "/tasks/new"
    }
    fun startTodoRequest() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                todosRequest()
                handler.postDelayed(this, 5000)
            }
        })
    }
    private fun todosRequest() {
        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            URL + GET_TODOS,
            null,
            { response ->
                val movieList = JSONArrayToTodoList(response)
                todoSingleton.updateTodos(movieList)
            },
            { volleyError ->
                Log.e("TodosRequestError", "Connection error. ${volleyError.toString()}")
            }
        )
        this.queue.add(jsonRequest)
    }
    fun JSONArrayToTodoList(jsonArray: JSONArray): ArrayList<Todo> {
        val todoList = ArrayList<Todo>()
        for(i in 0..(jsonArray.length() - 1)) {
            val jsonObject = jsonArray.getJSONObject(i)
            val todo = Todo(
                id = jsonObject.getLong("id"),
                jsonObject.getString("description"),
                jsonObject.getBoolean("is_urgent"),
                jsonObject.getBoolean("is_done"),
            )
            todoList.add(todo)
        }
        return todoList
    }

    fun addNewTodo(todo: Todo) {

        val jsonObject = JSONObject()
        jsonObject.put("description", todo.text)
        jsonObject.put("is_urgent", todo.isUrgent)
        jsonObject.put("is_done", todo.isDone)


        val jsonArrayRequest = JsonObjectRequest(
            Request.Method.POST,
            URL + POST_TODO,
            jsonObject,
            { response ->
                todosRequest()
            },
            { volleyError ->
                Log.e("RequestPOSTTaskError", "Connection error. ${volleyError.toString()}")
            }
        )

        this.queue.add(jsonArrayRequest);
    }
}
