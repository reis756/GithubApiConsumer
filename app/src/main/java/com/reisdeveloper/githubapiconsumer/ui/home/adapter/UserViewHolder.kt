package com.reisdeveloper.githubapiconsumer.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.reisdeveloper.data.model.UserResponse
import com.reisdeveloper.githubapiconsumer.databinding.ItemUserBinding

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserResponse) {
        binding.apply {
            binding.userData.avatar = user.avatarUrl
            binding.userData.name = user.login
            binding.userData.url = user.htmlUrl
        }
    }
}