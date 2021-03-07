package com.martin.simplerecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T, B : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int,
    private var items: List<T>,
    var itemsListener: BaseItemsListener<T>? = null
) : RecyclerView.Adapter<BaseAdapter.ItemViewHolder<B>>() {

    private var layoutInflater: LayoutInflater? = null

    private fun getLayoutInflater(view: View): LayoutInflater {
        return layoutInflater ?: LayoutInflater.from(view.context).also {
            layoutInflater = it
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<B> {
        return ItemViewHolder(createViewDataBinding(getLayoutInflater(parent), parent))
    }

    private fun createViewDataBinding(inflater: LayoutInflater, parent: ViewGroup): B {
        return DataBindingUtil.inflate(inflater, layoutId, parent, false)
    }

    override fun getItemCount(): Int = items.size

    @Suppress("USELESS_IS_CHECK")
    override fun onBindViewHolder(holder: ItemViewHolder<B>, position: Int) {
        if (holder is ItemViewHolder<*>) {
            holder.viewBinding.setVariable(BR.item, items[position])
            holder.viewBinding.setVariable(BR.listener, itemsListener)
        }
    }

    open fun setItems(newItems: List<T>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                if (oldItemPosition < 0 || newItemPosition < 0) {
                    return oldItemPosition == newItemPosition
                }
                return areItemsTheSame(items[oldItemPosition], newItems[newItemPosition])
            }

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                if (oldItemPosition < 0 || newItemPosition < 0) {
                    return oldItemPosition == newItemPosition
                }
                return areContentsTheSame(items[oldItemPosition], newItems[newItemPosition])
            }
        }).let {
            items = newItems
            it.dispatchUpdatesTo(this)
        }
    }

    open fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) ?: false
    }

    open fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) ?: false
    }

    class ItemViewHolder<B : ViewDataBinding>(val viewBinding: B) : BaseViewHolder(viewBinding.root)

    open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface BaseItemsListener<T> {
        fun onItemClicked(item: T) {}
    }
}

internal class Item