import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mithosaanjh.databinding.ItemReviewBinding
import com.example.mithosaanjh.model.ReviewModel

class ReviewAdapter(
    var reviews: List<ReviewModel>,
    val onDeleteClick: (String) -> Unit,
    val onEditClick: (ReviewModel) -> Unit // Added onEditClick function
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviews[position]
        with(holder.binding) {
            emailTextView.text = review.email
            ratingBar.rating = review.rating.toFloat()
            messageTextView.text = review.message

            btnDeleteReview.setOnClickListener { onDeleteClick(review.reviewId) }

            btnEditReview.setOnClickListener {
                onEditClick(review)
            }
        }
    }

    override fun getItemCount(): Int = reviews.size

    fun updateData(newReviews: List<ReviewModel>) {
        reviews = newReviews
        notifyDataSetChanged()
    }
}
