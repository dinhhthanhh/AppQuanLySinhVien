// DetailActivity.kt (Tạo file mới)
package com.example.quanlysinhvien

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity// Nhớ import StudentManager

class DetailActivity : AppCompatActivity() {

    private lateinit var currentStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. Nhận dữ liệu Student
        currentStudent = intent.getSerializableExtra("EXTRA_STUDENT") as? Student ?: run {
            Toast.makeText(this, "Không tìm thấy thông tin sinh viên.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        supportActionBar?.title = "Chi tiết: ${currentStudent.name}"

        val etId = findViewById<EditText>(R.id.etStudentId)
        val etName = findViewById<EditText>(R.id.etFullName)
        val etPhone = findViewById<EditText>(R.id.etPhoneNumber)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        // 2. Hiển thị thông tin
        etId.setText(currentStudent.id)
        etName.setText(currentStudent.name)
        etPhone.setText(currentStudent.phoneNumber)
        etAddress.setText(currentStudent.address)

        // 3. Xử lý CẬP NHẬT
        btnUpdate.setOnClickListener {
            val hoTenMoi = etName.text.toString().trim()
            val sdtMoi = etPhone.text.toString().trim()
            val diaChiMoi = etAddress.text.toString().trim()

            if (hoTenMoi.isEmpty()) {
                Toast.makeText(this, "Họ tên không được để trống", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedStudent = Student(currentStudent.id, hoTenMoi, sdtMoi, diaChiMoi)
            StudentManager.updateStudent(updatedStudent)
            Toast.makeText(this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 4. Xử lý XÓA
        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Xác nhận Xóa")
                .setMessage("Bạn có chắc chắn muốn xóa sinh viên ${currentStudent.name}?")
                .setPositiveButton("Xóa") { _, _ ->
                    StudentManager.deleteStudent(currentStudent.id)
                    Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }
}