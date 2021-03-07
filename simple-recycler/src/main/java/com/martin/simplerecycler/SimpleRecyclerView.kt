package com.martin.simplerecycler

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class SimpleRecyclerView<T, V : ViewDataBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs), BaseAdapter.BaseItemsListener<T> {

    private var itemsListener: BaseAdapter.BaseItemsListener<T> = this
    private var onItemClicked: ((item: T) -> Unit)? = null

    @Suppress("unused", "UNCHECKED_CAST")
    fun setItemsListener(itemsListener: BaseAdapter.BaseItemsListener<T>) {
        this.itemsListener = itemsListener
        (adapter as BaseAdapter<T, *>).itemsListener = itemsListener
    }

    fun setItems(items: List<T>) {
        setRecyclerItems(items)
    }

    @Suppress("unused")
    fun setOnItemClicked(onItemClicked: (item: T) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    private var layoutId: Int? = null

    init {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.SimpleRecyclerView)
        layoutId = typedArray.getResourceId(R.styleable.SimpleRecyclerView_itemView, 0)
        adapter = BaseAdapter<T, V>(
            layoutId = if (layoutId != null && layoutId != 0) layoutId!! else R.layout.base_item,
            items = listOf(),
            itemsListener = this
        )
        typedArray.recycle()
    }

    override fun onItemClicked(item: T) {
        onItemClicked?.invoke(item)
    }

    override fun onDetachedFromWindow() {
        onItemClicked = null
        super.onDetachedFromWindow()
    }
}