package com.example.usershilt.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usershilt.databinding.UsersListBinding
import com.example.usershilt.features.view.adapter.UserAdapter
import com.example.usershilt.features.viewModel.UserViewModel
import com.example.usershilt.networks.models.UserModel
import com.example.usershilt.utils.Resource
import com.example.usershilt.utils.showToast

class UsersFragment : Fragment() {


    private var _binding: UsersListBinding? = null
    private val binding: UsersListBinding get() = _binding!!

    private val viewModel: UserViewModel by activityViewModels()


    private val userAdapter by lazy {
        UserAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = UsersListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            jsonRv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }


            viewModel.users.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false

                        jsonRv.adapter = userAdapter
                        userAdapter.submitList(response.data as List<UserModel>)
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                        showToast(response.msg)
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}