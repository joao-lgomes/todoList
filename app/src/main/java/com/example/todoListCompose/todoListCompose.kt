package br.pro.viniciusfm.funnymoviesapp

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.pro.viniciusfm.funnymoviesapp.ui.components.frames.MainScreen
import com.example.composeTodoList.data.todoRequest
import com.example.todoListCompose.ui.theme.TodoListTheme

class TodoListCompose : ComponentActivity() {
    private lateinit var todoRequest: todoRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.todoRequest = todoRequest(this)
        this.todoRequest.startTodoRequest()
        setContent {
            TodoListTheme {
                this.SetupUIConfigs()
                MainScreen(this.todoRequest)
            }
        }
    }
    @Composable
    private fun SetupUIConfigs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (MaterialTheme.colors.isLight) {
                window.decorView
                    .windowInsetsController?.setSystemBarsAppearance(
                        APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS
                    )
            } else {
                window.decorView
                    .windowInsetsController?.setSystemBarsAppearance(
                        0, APPEARANCE_LIGHT_STATUS_BARS
                    )
            }
        }
        window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
    }
}