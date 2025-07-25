package com.example.mithosaanjh.repository

import com.example.mithosaanjh.model.ReviewModel
import com.google.firebase.database.DatabaseReference

interface ReviewRepository {
    fun createReview(review: ReviewModel, database: DatabaseReference, callback: (Boolean, String?) -> Unit)
    fun getReviews(database: DatabaseReference, callback: (List<ReviewModel>?, String?) -> Unit)
    fun updateReview(review: ReviewModel, database: DatabaseReference, callback: (Boolean, String?) -> Unit)
    fun deleteReview(reviewId: String, database: DatabaseReference, callback: (Boolean, String?) -> Unit)
}
