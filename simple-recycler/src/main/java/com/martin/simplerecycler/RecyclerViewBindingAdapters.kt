package com.martin.simplerecycler

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@Suppress("UNCHECKED_CAST")
@BindingAdapter(
    value = ["app:items", "app:itemCount"],
    requireAll = false
)
internal fun <T> RecyclerView.setRecyclerItems(
    items: List<T>?,
    itemsCount: Int? = null
) {
    items?.takeIf {
        it.isNotEmpty()
    }?.let {
        (adapter as? BaseAdapter<T, *>)
            ?.apply {
                setItems(
                    if (itemsCount != null && itemsCount != 0) {
                        items.take(itemsCount)
                    } else items
                )
            }
    }
}