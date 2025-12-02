package com.example.quanlysinhvien

object StudentManager {
    val students = mutableListOf<Student>()

    init {
        // Thêm dữ liệu mẫu vào đây (thay thế hàm addSampleData() trong MainActivity)
        students.addAll(listOf(
            Student("20200001", "Nguyễn Văn A", "0901234567", "Hà Nội"),
            Student("20200002", "Trần Thị B", "0902345678", "TP. HCM"),
            Student("20200003", "Lê Văn C", "0903456789", "Đà Nẵng"),
            Student("20200004", "Phạm Thị D", "0904567890", "Cần Thơ")
        ))
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(updatedStudent: Student) {
        // Tìm và cập nhật theo ID (MSSV)
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            students[index].name = updatedStudent.name
            students[index].phoneNumber = updatedStudent.phoneNumber
            students[index].address = updatedStudent.address
        }
    }

    fun deleteStudent(studentId: String) {
        students.removeAll { it.id == studentId }
    }
}