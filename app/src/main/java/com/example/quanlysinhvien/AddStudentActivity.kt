package com.example.quanlysinhvien

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quanlysinhvien.StudentManager
import com.example.quanlysinhvien.Student

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Cấu hình thanh tiêu đề
        supportActionBar?.title = "Thêm Sinh Viên Mới"

        // Khởi tạo Views
        val etId = findViewById<EditText>(R.id.etStudentId)
        val etName = findViewById<EditText>(R.id.etFullName)
        val etPhone = findViewById<EditText>(R.id.etPhoneNumber)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        // Xử lý sự kiện nhấn nút Add
        btnAdd.setOnClickListener {
            val mssv = etId.text.toString().trim()
            val hoTen = etName.text.toString().trim()
            val sdt = etPhone.text.toString().trim()
            val diaChi = etAddress.text.toString().trim()

            // 1. Kiểm tra trường bắt buộc
            if (mssv.isEmpty() || hoTen.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập MSSV và Họ tên", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Kiểm tra trùng MSSV
            if (StudentManager.students.any { it.id == mssv }) {
                Toast.makeText(this, "MSSV đã tồn tại!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Tạo đối tượng Student mới
            val newStudent = Student(mssv, hoTen, sdt, diaChi)

            // 4. Thêm vào danh sách toàn cục
            StudentManager.addStudent(newStudent)

            Toast.makeText(this, "Đã thêm sinh viên: $hoTen", Toast.LENGTH_SHORT).show()

            // 5. Đóng Activity và quay về MainActivity (nơi sẽ tự động cập nhật danh sách)
            finish()
        }
    }
}