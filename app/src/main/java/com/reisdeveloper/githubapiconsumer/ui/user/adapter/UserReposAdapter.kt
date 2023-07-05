package com.reisdeveloper.githubapiconsumer.ui.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reisdeveloper.githubapiconsumer.databinding.ItemRepositoriesBinding
import com.reisdeveloper.githubapiconsumer.uiModel.UserReposUiModel

class UserReposAdapter() : RecyclerView.Adapter<UserReposAdapter.ViewHolder>() {

    private val repos = mutableListOf<UserReposUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRepositoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    fun setItems(list: List<UserReposUiModel>) {
        val lastIndex = repos.lastIndex
        notifyItemRangeRemoved(0, repos.size)

        repos.clear()
        repos.addAll(list)

        notifyItemRangeInserted(lastIndex, list.size)
    }

    class ViewHolder(
        private val binding: ItemRepositoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserReposUiModel) {
            with(binding) {
                txtRepoName.text = item.name
                txtRepoDescription.text = item.description
                txtRepoLanguage.text = item.language
                txtRepoStars.text = item.stargazersCount.toString()
                txtRepoForks.text = item.forksCount.toString()
            }
        }
    }
}