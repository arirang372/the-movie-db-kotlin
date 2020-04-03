package com.john.themoviedb.details.view.viewholders

import com.john.themoviedb.databinding.TitleListContentBinding
import com.john.themoviedb.details.model.Category


class TitleViewHolder(binding: TitleListContentBinding) : BaseViewHolder<Category>(binding.root) {
    private val mBinding = binding
    override fun bind(item: Category) {
        mBinding.model = item
    }
}