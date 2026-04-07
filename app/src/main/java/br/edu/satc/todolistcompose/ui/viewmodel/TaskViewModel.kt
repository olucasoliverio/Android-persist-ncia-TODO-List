package br.edu.satc.todolistcompose.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.data.TaskData
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = AppDatabase.getDatabase(application).taskDao()
    val tasks = mutableStateListOf<TaskData>()

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            tasks.clear()
            tasks.addAll(taskDao.getAllTasks())
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            val newTask = TaskData(title = title, description = description, complete = false)
            taskDao.insert(newTask)
            loadTasks()
        }
    }

    fun toggleTaskComplete(task: TaskData, isComplete: Boolean) {
        viewModelScope.launch {
            val updatedTask = task.copy(complete = isComplete)
            taskDao.update(updatedTask)
            loadTasks()
        }
    }
}