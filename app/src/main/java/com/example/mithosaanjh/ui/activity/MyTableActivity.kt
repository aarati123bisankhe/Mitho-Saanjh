package com.example.mithosaanjh.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mithosaanjh.databinding.ActivityMyTableBinding
import com.example.mithosaanjh.R
import com.example.mithosaanjh.adapter.TableBookingAdapter
import com.example.mithosaanjh.model.TableBookingModel
import com.example.mithosaanjh.repository.TableBookingRepositoryImpl
import com.example.mithosaanjh.utils.LoadingUtils
import com.example.mithosaanjh.viewmodel.TableBookingViewModel

class MyTableActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyTableBinding
    lateinit var  tableBookingViewModel: TableBookingViewModel
    lateinit var adapter: TableBookingAdapter
    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils = LoadingUtils(this)

        val repo = TableBookingRepositoryImpl()
        tableBookingViewModel = TableBookingViewModel(repo)

        setupRecyclerView()
        observeViewModel()

        tableBookingViewModel.getBookings()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        adapter = TableBookingAdapter(
            bookingList = emptyList(),
            onUpdateClick = { booking -> openUpdateDialog(booking) },
            onDeleteClick = { bookingId -> onDeleteBooking(bookingId) }
        )

        binding.recyclerViewTableBooking.apply {
            layoutManager = LinearLayoutManager(this@MyTableActivity)
            adapter = this@MyTableActivity.adapter
        }
    }

    private fun observeViewModel() {
        tableBookingViewModel.bookingsList.observe(this) { bookings ->
            adapter.updateList(bookings)
        }

        tableBookingViewModel.bookingStatus.observe(this) { status ->
            loadingUtils.dismiss()
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUpdateDialog(booking: TableBookingModel) {
        val intent = Intent(this, UpdateBookingActivity::class.java)
        intent.putExtra("booking", booking)
        startActivity(intent)
    }

    private fun onDeleteBooking(bookingId: String) {
        loadingUtils.show()
        tableBookingViewModel.deleteBooking(bookingId)
    }

}












