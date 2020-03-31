package com.john.themoviedb.common

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding


open abstract class SingleTypeDataBoundAdapter<T : ViewDataBinding>(@LayoutRes layoutId: Int) :
    BaseDataBoundAdapter<T>() {
    private val mLayoutId: Int = layoutId

    override fun getItemLayoutId(layoutId: Int): Int {
        return mLayoutId
    }
}