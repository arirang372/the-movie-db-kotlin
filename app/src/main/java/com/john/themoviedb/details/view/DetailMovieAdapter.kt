package com.john.themoviedb.details.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.themoviedb.databinding.ReviewListContentBinding
import com.john.themoviedb.databinding.TitleListContentBinding
import com.john.themoviedb.databinding.TrailerListContentBinding
import com.john.themoviedb.details.callbacks.MovieReviewItemListener
import com.john.themoviedb.details.callbacks.MovieTrailerItemListener
import com.john.themoviedb.details.model.Category
import com.john.themoviedb.details.model.Review
import com.john.themoviedb.details.model.Trailer
import com.john.themoviedb.details.view.viewholders.BaseViewHolder
import com.john.themoviedb.details.view.viewholders.ReviewViewHolder
import com.john.themoviedb.details.view.viewholders.TitleViewHolder
import com.john.themoviedb.details.view.viewholders.TrailerViewHolder
import java.util.*


class DetailMovieAdapter(
    trailerCallback: MovieTrailerItemListener,
    reviewCallback: MovieReviewItemListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var trailersReviews: MutableList<Comparable<*>> = Collections.emptyList()
    private var mTrailerCallback: MovieTrailerItemListener = trailerCallback
    private var mReviewCallback: MovieReviewItemListener = reviewCallback

    companion object {
        private const val TYPE_TITLE = 0
        private const val TYPE_TRAILER = 1
        private const val TYPE_REVIEW = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_TITLE -> {
                val binding = TitleListContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TitleViewHolder(
                    binding
                )
            }
            TYPE_TRAILER -> {
                val binding = TrailerListContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TrailerViewHolder(
                    binding,
                    trailersReviews,
                    mTrailerCallback
                )
            }
            TYPE_REVIEW -> {
                val binding = ReviewListContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReviewViewHolder(
                    binding,
                    trailersReviews,
                    mReviewCallback
                )
            }
            else -> throw java.lang.IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemId(position: Int): Long {
        return RecyclerView.NO_ID
    }

    override fun getItemCount(): Int {
        return trailersReviews.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = trailersReviews[position]
        when (holder) {
            is TitleViewHolder -> holder.bind(element as Category)
            is TrailerViewHolder -> holder.bind(element as Trailer)
            is ReviewViewHolder -> holder.bind(element as Review)
            else -> throw java.lang.IllegalArgumentException("Invalid view type")
        }
    }

    fun setMovieTrailersAndReviews(trailersAndReviews: MutableList<Comparable<*>>?) {
        trailersAndReviews?.let {
            this.trailersReviews = it
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (trailersReviews[position]) {
            is Category -> TYPE_TITLE
            is Trailer -> TYPE_TRAILER
            is Review -> TYPE_REVIEW
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }
}