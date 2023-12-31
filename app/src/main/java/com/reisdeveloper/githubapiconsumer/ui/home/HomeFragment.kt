package com.reisdeveloper.githubapiconsumer.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.reisdeveloper.githubapiconsumer.R
import com.reisdeveloper.githubapiconsumer.base.BaseFragment
import com.reisdeveloper.githubapiconsumer.databinding.FragmentHomeBinding
import com.reisdeveloper.githubapiconsumer.ext.safeNavigate
import com.reisdeveloper.githubapiconsumer.ui.home.adapter.UserAdapter
import com.reisdeveloper.githubapiconsumer.ui.user.UserFragment
import com.reisdeveloper.githubapiconsumer.uiModel.UserDetailsUiModel
import com.reisdeveloper.githubapiconsumer.uiModel.UserUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {

    override val viewModel: HomeViewModel by viewModel()

    private var userAdapter = UserAdapter(
        object : UserAdapter.Listener {
            override fun onClickItem(item: UserUiModel) {
                findNavController().safeNavigate(
                    R.id.action_HomeFragment_to_SecondFragment,
                    Bundle().apply {
                        putString(UserFragment.EXTRA_USER_NAME, item.login)
                    }
                )
            }
        }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this) {
            onBackPressed()
        }

        setupAdapter()

        setupObservers()

        viewModel.getUsers()

        binding.btnSearch.setOnClickListener {
            viewModel.getUserByName(binding.textInputSearchUser.editText?.text.toString())
        }
        binding.userDataHome.setOnClickListener {
            findNavController().safeNavigate(
                R.id.action_HomeFragment_to_SecondFragment,
                Bundle().apply {
                    putString(UserFragment.EXTRA_USER_NAME, binding.userDataHome.login)
                }
            )
        }
    }

    private fun setupAdapter() {
        binding.rvUsers.adapter = userAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(binding.rvUsers.context)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUsers().collectLatest {
                    userAdapter.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }

        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when(state) {
                    is HomeViewModel.Screen.Loading -> onLoading(state.loading)
                    is HomeViewModel.Screen.UserByName -> setUserDetails(state.user)
                    is HomeViewModel.Screen.UserByNameError -> {
                        binding.userDataHome.isVisible = false
                        showError(getString(R.string.user_not_found))
                    }
                }
            }
        }
    }

    private fun onLoading(loading: Boolean) {
        binding.userProgress.isVisible = loading
    }

    private fun setUserDetails(userDetails: UserDetailsUiModel) {
        binding.userDataHome.isVisible = true
        binding.userDataHome.avatar = userDetails.avatarUrl
        binding.userDataHome.name = userDetails.name
        binding.userDataHome.url = userDetails.htmlUrl
        binding.userDataHome.login = userDetails.login
    }
}