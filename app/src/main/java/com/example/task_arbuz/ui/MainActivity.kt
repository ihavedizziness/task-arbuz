package com.example.task_arbuz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.task_arbuz.ui.component.main.ShopApp
import com.example.task_arbuz.ui.theme.TaskarbuzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskarbuzTheme {
                ShopApp()
            }
        }
    }
}
