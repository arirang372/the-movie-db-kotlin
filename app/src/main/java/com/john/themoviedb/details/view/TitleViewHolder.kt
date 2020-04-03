package com.john.themoviedb.details.view

import com.john.themoviedb.common.BaseViewHolder
import com.john.themoviedb.databinding.TitleListContentBinding
import com.john.themoviedb.details.model.Category


class TitleViewHolder(binding: TitleListContentBinding) : BaseViewHolder<Category>(binding.root) {
    private val mBinding = binding
    override fun bind(item: Category) {
        mBinding.model = item
    }
}