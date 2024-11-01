// TinhDiemTrungBinhActivity.kt
package com.example.studentmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.databinding.ActivityTinhDiemTrungBinhBinding

class TinhDiemTrungBinhActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTinhDiemTrungBinhBinding
    private val danhSachMonHoc = mutableListOf<MonHoc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTinhDiemTrungBinhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTinhDiem.setOnClickListener {
            val (diemTrungBinh, xepLoai) = tinhDiemTrungBinh(danhSachMonHoc)
            binding.txtDiemTrungBinh.text = "Điểm trung bình: %.2f".format(diemTrungBinh)
            binding.txtXepLoai.text = "Xếp loại: $xepLoai"
        }
    }
    private fun tinhDiemTrungBinh(danhSachMonHoc: List<MonHoc>): Pair<Float, String> {
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
        return Pair(diemTrungBinh, xepLoai)
    }

//    private fun tinhDiemTrungBinh(danhSachMonHoc: List<MonHoc>): Pair<Float, String> {
//        val tongDiem = danhSachMonHoc.sumOf { it.diem * it.soTinChi }
//        val tongTinChi = danhSachMonHoc.sumOf { it.soTinChi }
//
//        val diemTrungBinh = if (tongTinChi > 0) tongDiem / tongTinChi else 0.0f
//        val xepLoai = when {
//            diemTrungBinh >= 9.0 -> "Giỏi"
//            diemTrungBinh >= 7.0 -> "Khá"
//            diemTrungBinh >= 5.0 -> "Trung bình"
//            else -> "Yếu"
//        }
//        return Pair(diemTrungBinh, xepLoai)
//    }
}
