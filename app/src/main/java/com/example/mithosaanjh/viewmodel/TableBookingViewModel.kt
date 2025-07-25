package com.example.mithosaanjh.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mithosaanjh.model.TableBookingModel
import com.example.mithosaanjh.repository.TableBookingRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TableBookingViewModel (val repository: TableBookingRepository) {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    val bookingStatus = MutableLiveData<String>()
    val bookingsList = MutableLiveData<List<TableBookingModel>>()

    fun createBooking(booking: TableBookingModel) {
        repository.createBooking(booking, database) { success, error ->
            if (success) {
                bookingStatus.postValue("Booking confirmed!")
            } else {
                bookingStatus.postValue("Error: $error")
            }
        }
    }

    fun getBookings() {
        repository.getBookings(database) { bookings, error ->
            if (bookings != null) {
                bookingsList.postValue(bookings)
            } else {
                bookingStatus.postValue(error ?: "Error fetching bookings")
            }
        }
    }

    fun updateBooking(booking: TableBookingModel) {
        repository.updateBooking(booking, database) { success, error ->
            if (success) {
                bookingStatus.postValue("Booking updated successfully!")
            } else {
                bookingStatus.postValue("Error: $error")
            }
        }
    }

    fun deleteBooking(bookingId: String) {
        repository.deleteBooking(bookingId, database) { success, error ->
            if (success) {
                // Remove the deleted booking from the current bookings list
                val currentList = bookingsList.value?.toMutableList() ?: mutableListOf()
                val bookingToRemove = currentList.find { it.bookingId == bookingId }
                if (bookingToRemove != null) {
                    currentList.remove(bookingToRemove)  // Remove the booking from the list
                }

                // Update the LiveData with the modified list
                bookingsList.postValue(currentList)

                // Notify that the booking was deleted
                bookingStatus.postValue("Booking deleted successfully!")
            } else {
                bookingStatus.postValue("Error: $error")
            }
        }
    }
}