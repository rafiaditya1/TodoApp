package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel
    private lateinit var btnDelete : Button
    private lateinit var edtTitle: TextInputEditText
    private lateinit var edtDescription: TextInputEditText
    private lateinit var edtDueDate : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action

        edtTitle = findViewById(R.id.detail_ed_title)
        edtDescription = findViewById(R.id.detail_ed_description)
        edtDueDate = findViewById(R.id.detail_ed_due_date)
        btnDelete = findViewById(R.id.btn_delete_task)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, viewModelFactory) [DetailTaskViewModel::class.java]
        detailTaskViewModel.setTaskId(intent.getIntExtra(TASK_ID, 0))
        detailTaskViewModel.task.observe(this) {
            if (it != null) {
                edtTitle.setText(it.title)
                edtDescription.setText(it.description)
                edtDueDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
            }
        }
        btnDelete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            onBackPressed()
        }
    }
}