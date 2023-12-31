package com.reisdeveloper.githubapiconsumer.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.reisdeveloper.githubapiconsumer.databinding.ItemUserBinding
import com.reisdeveloper.githubapiconsumer.uiModel.UserUiModel

class UserAdapter(
    private val listener: Listener
) : PagingDataAdapter<UserUiModel, UserViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
            holder.itemView.setOnClickListener {
                listener.onClickItem(tile)
            }
        }
    }

    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserUiModel>() {
            override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean =
                oldItem == newItem
        }
    }

    interface Listener {
        fun onClickItem(item: UserUiModel)
    }
}