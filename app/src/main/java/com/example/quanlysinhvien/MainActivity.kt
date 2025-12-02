// MainActivity.kt (Cập nhật)
package com.example.quanlysinhvien

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        // 1. Click item: Mở DetailActivity
        val onItemClick: (Student) -> Unit = { student ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_STUDENT", student)
            }
            startActivity(intent)
        }

        // 2. Không cần Delete ở đây, logic xóa nằm trong DetailActivity
        adapter = StudentAdapter(
            StudentManager.students,
            onItemClick = onItemClick,
            onDeleteClick = { /* Không làm gì hoặc xóa khỏi adapter nếu cần */ }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        // Cập nhật lại danh sách khi quay về từ Add/Detail Activity
        adapter.notifyDataSetChanged()
    }

    // 3. Option Menu: Thêm nút "Thêm Sinh Viên"
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                startActivity(Intent(this, AddStudentActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}