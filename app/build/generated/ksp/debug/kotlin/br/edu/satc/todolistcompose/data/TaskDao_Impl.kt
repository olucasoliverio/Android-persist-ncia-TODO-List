package br.edu.satc.todolistcompose.`data`

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class TaskDao_Impl(
  __db: RoomDatabase,
) : TaskDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfTaskData: EntityInsertAdapter<TaskData>

  private val __deleteAdapterOfTaskData: EntityDeleteOrUpdateAdapter<TaskData>

  private val __updateAdapterOfTaskData: EntityDeleteOrUpdateAdapter<TaskData>
  init {
    this.__db = __db
    this.__insertAdapterOfTaskData = object : EntityInsertAdapter<TaskData>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `tasks` (`id`,`title`,`description`,`complete`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: TaskData) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.description)
        val _tmp: Int = if (entity.complete) 1 else 0
        statement.bindLong(4, _tmp.toLong())
      }
    }
    this.__deleteAdapterOfTaskData = object : EntityDeleteOrUpdateAdapter<TaskData>() {
      protected override fun createQuery(): String = "DELETE FROM `tasks` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: TaskData) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfTaskData = object : EntityDeleteOrUpdateAdapter<TaskData>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `tasks` SET `id` = ?,`title` = ?,`description` = ?,`complete` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: TaskData) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.description)
        val _tmp: Int = if (entity.complete) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(task: TaskData): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTaskData.insert(_connection, task)
  }

  public override suspend fun delete(task: TaskData): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfTaskData.handle(_connection, task)
  }

  public override suspend fun update(task: TaskData): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfTaskData.handle(_connection, task)
  }

  public override suspend fun getAllTasks(): List<TaskData> {
    val _sql: String = "SELECT * FROM tasks"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfComplete: Int = getColumnIndexOrThrow(_stmt, "complete")
        val _result: MutableList<TaskData> = mutableListOf()
        while (_stmt.step()) {
          val _item: TaskData
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpComplete: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfComplete).toInt()
          _tmpComplete = _tmp != 0
          _item = TaskData(_tmpId,_tmpTitle,_tmpDescription,_tmpComplete)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTaskById(id: Int): TaskData? {
    val _sql: String = "SELECT * FROM tasks WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfComplete: Int = getColumnIndexOrThrow(_stmt, "complete")
        val _result: TaskData?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpComplete: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfComplete).toInt()
          _tmpComplete = _tmp != 0
          _result = TaskData(_tmpId,_tmpTitle,_tmpDescription,_tmpComplete)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
