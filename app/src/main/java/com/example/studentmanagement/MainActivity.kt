package com.example.studentmanagement

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tenMonEditText: EditText
    private lateinit var soTinChiEditText: EditText
    private lateinit var namHocEditText: EditText
    private lateinit var giangVienEditText: EditText
    private lateinit var diemEditText: EditText
    private lateinit var themMonButton: Button
    private lateinit var tinhDiemTBButton: Button
    private lateinit var ketQuaTextView: TextView

    // Danh sách các môn học
    private val danhSachMonHoc = mutableListOf<MonHoc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        tenMonEditText = findViewById(R.id.tenMonEditText)
        soTinChiEditText = findViewById(R.id.soTinChiEditText)
        namHocEditText = findViewById(R.id.namHocEditText)
        giangVienEditText = findViewById(R.id.giangVienEditText)
        diemEditText = findViewById(R.id.diemEditText)
        themMonButton = findViewById(R.id.themMonButton)
        tinhDiemTBButton = findViewById(R.id.tinhDiemTBButton)
        ketQuaTextView = findViewById(R.id.ketQuaTextView)

        // Thêm môn học vào danh sách
        themMonButton.setOnClickListener {
            themMonHoc()
        }

        // Tính điểm trung bình
        tinhDiemTBButton.setOnClickListener {
            tinhDiemTrungBinh()
        }
    }

    private fun themMonHoc() {
        val tenMon = tenMonEditText.text.toString()
        val soTinChi = soTinChiEditText.text.toString().toIntOrNull()
        val namHoc = namHocEditText.text.toString()
        val giangVien = giangVienEditText.text.toString()
        val diem = diemEditText.text.toString().toFloatOrNull()

        // Kiểm tra các thông tin đầu vào
        if (tenMon.isEmpty() || soTinChi == null || diem == null) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin hợp lệ", Toast.LENGTH_SHORT).show()
            return
        }

        // Tạo đối tượng MonHoc và thêm vào danh sách
        val monHoc = MonHoc(tenMon, soTinChi, namHoc, giangVien, diem)
        danhSachMonHoc.add(monHoc)

        // Thông báo thêm thành công và xóa các trường nhập
        Toast.makeText(this, "Đã thêm môn học: $tenMon", Toast.LENGTH_SHORT).show()
        tenMonEditText.text.clear()
        soTinChiEditText.text.clear()
        namHocEditText.text.clear()
        giangVienEditText.text.clear()
        diemEditText.text.clear()
    }

    private fun tinhDiemTrungBinh() {
        if (danhSachMonHoc.isEmpty()) {
            Toast.makeText(this, "Chưa có môn học nào được nhập", Toast.LENGTH_SHORT).show()
            return
        }

        var tongDiem = 0.0f
        var tongTinChi = 0

        for (monHoc in danhSachMonHoc) {
            tongDiem += monHoc.diem * monHoc.soTinChi
            tongTinChi += monHoc.soTinChi
        }

        val diemTrungBinh = if (tongTinChi > 0) tongDiem / tongTinChi else 0.0f
        val xepLoai = when {
            diemTrungBinh >= 9.0 -> "Giỏi"
            diemTrungBinh >= 7.0 -> "Khá"
            diemTrungBinh >= 5.0 -> "Trung bình"
            else -> "Yếu"
        }

        // Hiển thị kết quả
        ketQuaTextView.text = "Điểm trung bình: $diemTrungBinh\nXếp loại: $xepLoai"
    }
}

//package com.example.studentmanagement
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.studentmanagement.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnNhapDiem.setOnClickListener {
//            startActivity(Intent(this, NhapDiemActivity::class.java))
//        }
//
//        binding.btnTinhDiemTrungBinh.setOnClickListener {
//            startActivity(Intent(this, TinhDiemTrungBinhActivity::class.java))
//        }
//    }
//}