package com.example.quanlysinhvien

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var edtMSSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    private val studentList = mutableListOf<Student>()
    private var selectedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo views
        edtMSSV = findViewById(R.id.edtMSSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)

        // Setup RecyclerView
        adapter = StudentAdapter(
            studentList,
            onItemClick = { student ->
                // Khi click vào item, hiển thị thông tin lên EditText
                val position = studentList.indexOf(student)
                selectedPosition = position
                edtMSSV.setText(student.id)
                edtHoTen.setText(student.name)
            },
            onDeleteClick = { position ->
                // Xóa sinh viên
                adapter.deleteStudent(position)
                clearFields()
                Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Xử lý nút Add
        btnAdd.setOnClickListener {
            addStudent()
        }

        // Xử lý nút Update
        btnUpdate.setOnClickListener {
            updateStudent()
        }

        // Thêm dữ liệu mẫu
        addSampleData()
    }

    private fun addStudent() {
        val mssv = edtMSSV.text.toString().trim()
        val hoTen = edtHoTen.text.toString().trim()

        if (mssv.isEmpty() || hoTen.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        // Kiểm tra MSSV đã tồn tại chưa
        if (studentList.any { it.id == mssv }) {
            Toast.makeText(this, "MSSV đã tồn tại", Toast.LENGTH_SHORT).show()
            return
        }

        val student = Student(mssv, hoTen)
        adapter.addStudent(student)
        clearFields()
        Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun updateStudent() {
        if (selectedPosition == -1) {
            Toast.makeText(this, "Vui lòng chọn sinh viên cần cập nhật", Toast.LENGTH_SHORT).show()
            return
        }

        val mssv = edtMSSV.text.toString().trim()
        val hoTen = edtHoTen.text.toString().trim()

        if (hoTen.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedStudent = Student(mssv, hoTen)
        adapter.updateStudent(selectedPosition, updatedStudent)
        clearFields()
        Toast.makeText(this, "Đã cập nhật sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun clearFields() {
        edtMSSV.text.clear()
        edtHoTen.text.clear()
        selectedPosition = -1
    }

    private fun addSampleData() {
        val sampleStudents = listOf(
            Student("20200001", "Nguyễn Văn A"),
            Student("20200002", "Trần Thị B"),
            Student("20200003", "Lê Văn C"),
            Student("20200004", "Phạm Thị D"),
            Student("20200005", "Hoàng Văn E"),
            Student("20200006", "Vũ Thị F"),
            Student("20200007", "Đặng Văn G"),
            Student("20200008", "Bùi Thị H")
        )

        studentList.addAll(sampleStudents)
        adapter.notifyDataSetChanged()
    }
}