package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var edtTitle: TextInputEditText
    private lateinit var edtDescription: TextInputEditText
    private lateinit var edtDueDate: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val factory = ViewModelFactory.getInstance(this)
        val detailTaskViewModel =
            ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)
        val extra = intent.extras
        if (extra != null) {
            val taskId = extra.getInt(TASK_ID)
            detailTaskViewModel.setTaskId(taskId)
            detailTaskViewModel.task.observe(this) { task ->
                if (task != null) {
                    populateTasks(task)
                }
                findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
                    detailTaskViewModel.deleteTask()
                    finish()
                }
            }
        }
    }

    private fun populateTasks(task: Task) {
        edtTitle = findViewById(R.id.detail_ed_title)
        edtDescription = findViewById(R.id.detail_ed_description)
        edtDueDate = findViewById(R.id.detail_ed_due_date)
        with(task) {
            edtTitle.setText(title)
            edtDescription.setText(description)
            edtDueDate.setText(DateConverter.convertMillisToString(dueDateMillis))
        }
    }
}