// NhapDiemActivity.kt
package com.example.studentmanagement

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.databinding.ActivityNhapDiemBinding

class NhapDiemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNhapDiemBinding
    private val danhSachMonHoc = mutableListOf<MonHoc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNhapDiemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLuuMonHoc.setOnClickListener {
            val tenMon = binding.edtTenMon.text.toString()
            val soTinChi = binding.edtSoTinChi.text.toString().toIntOrNull() ?: 0
            val namHoc = binding.edtNamHoc.text.toString()
            val giangVien = binding.edtGiangVien.text.toString()
            val diem = binding.edtDiem.text.toString().toFloatOrNull() ?: 0.0f

            if (tenMon.isNotEmpty() && soTinChi > 0 && diem >= 0) {
                val monHoc = MonHoc(tenMon, soTinChi, namHoc, giangVien, diem)
                danhSachMonHoc.add(monHoc)
                Toast.makeText(this, "Lưu môn học thành công", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        binding.edtTenMon.text.clear()
        binding.edtSoTinChi.text.clear()
        binding.edtNamHoc.text.clear()
        binding.edtGiangVien.text.clear()
        binding.edtDiem.text.clear()
    }
}
