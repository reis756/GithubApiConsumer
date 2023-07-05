package com.reisdeveloper.githubapiconsumer.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.reisdeveloper.githubapiconsumer.databinding.ItemUserBinding
import com.reisdeveloper.githubapiconsumer.uiModel.UserUiModel

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserUiModel) {
        binding.apply {
            binding.userData.avatar = user.avatarUrl
            binding.userData.name = user.login
            binding.userData.url = user.htmlUrl
        }
    }
}