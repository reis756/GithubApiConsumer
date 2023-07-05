package com.reisdeveloper.githubapiconsumer.ui.user

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.githubapiconsumer.R
import com.reisdeveloper.githubapiconsumer.base.BaseFragment
import com.reisdeveloper.githubapiconsumer.databinding.FragmentUserDetailBinding
import com.reisdeveloper.githubapiconsumer.ext.toPx
import com.reisdeveloper.githubapiconsumer.ui.user.adapter.UserReposAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment<FragmentUserDetailBinding, UserViewModel>(
    FragmentUserDetailBinding::inflate
) {
    override val viewModel: UserViewModel by viewModel()

    private val userName by lazy {
        arguments?.getString(EXTRA_USER_NAME)
    }

    private val userRepoAdapter = UserReposAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this) {
            onBackPressed()
        }

        setupObservers()

        setupAdapter()

        userName?.let {
            viewModel.getUserByName(it)
            viewModel.getUserRepos(it)
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is UserViewModel.Screen.Loading ->
                        onLoading(state.loading)
                    is UserViewModel.Screen.UserByName ->
                        setUserDetails(state.user)
                    is UserViewModel.Screen.UserByNameError ->
                        showError(getString(R.string.user_not_found))
                    is UserViewModel.Screen.UserRepos ->
                        userRepoAdapter.setItems(state.repos)
                    is UserViewModel.Screen.UserReposError ->
                        showError(getString(R.string.repos_not_found))
                }
            }
        }
    }

    private fun onLoading(loading: Boolean) {
        binding.userProgress.isVisible = loading
    }

    private fun setupAdapter() {
        binding.rvUserRepos.adapter = userRepoAdapter
        binding.rvUserRepos.layoutManager = LinearLayoutManager(context)
    }

    private fun setUserDetails(userDetails: UserDetailsResponse) {
        Glide.with(this)
            .load(userDetails.avatarUrl)
            .error(R.drawable.ic_person)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(
                RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(36.toPx(binding.root.context))
                )
            )
            .into(binding.imgUser)
        binding.txtUserName.text = userDetails.name
        binding.txtUserRepos.counter = userDetails.repos
        binding.txtUserFollowers.counter = userDetails.followers
        binding.txtUserFollowing.counter = userDetails.following
    }

    companion object {
        const val EXTRA_USER_NAME = "EXTRA_USER_NAME"
    }
}