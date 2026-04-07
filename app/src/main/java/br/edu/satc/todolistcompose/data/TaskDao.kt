package br.edu.satc.todolistcompose.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: TaskData)

    @Update
    suspend fun update(task: TaskData)

    @Delete
    suspend fun delete(task: TaskData)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskData>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskData?
}