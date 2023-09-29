package com.john.themoviedb.details.view.viewholders

import com.john.themoviedb.databinding.TitleListContentBinding
import com.john.themoviedb.details.model.Category


class TitleViewHolder(private val binding: TitleListContentBinding) : BaseViewHolder<Category>(binding.root) {
    override fun bind(item: Category) {
        binding.model = item
    }
}