package hu.ait.roomgradesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.roomgradesdemo.data.AppDatabase
import hu.ait.roomgradesdemo.data.Grade
import hu.ait.roomgradesdemo.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            thread {
                val newGrade = Grade(null,
                    binding.etStudentId.text.toString(),
                    binding.etGrade.text.toString()
                )
                AppDatabase.getInstance(this).gradeDao().insertGrades(newGrade)
            }
        }

        binding.btnSearch.setOnClickListener {
            thread {
                val grades = AppDatabase.getInstance(this).gradeDao().getAllGrades()

                runOnUiThread {
                    binding.tvResult.text = ""
                    grades.forEach {
                        binding.tvResult.append("${it.studentId} ${it.grade} ")
                    }
                }
            }
        }

    }
}