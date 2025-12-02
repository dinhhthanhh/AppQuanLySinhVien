package com.example.quanlysinhvien
import java.io.Serializable

data class Student(
    val id: String,          // MSSV
    var name: String,        // Họ tên
    var phoneNumber: String = "", // 2. Thêm SĐT, gán giá trị mặc định
    var address: String = ""      // 3. Thêm Địa chỉ, gán giá trị mặc định
) : Serializable // 4. Kế thừa Serializable