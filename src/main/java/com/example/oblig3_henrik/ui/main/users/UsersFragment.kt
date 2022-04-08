package com.example.oblig3_henrik.ui.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oblig3_henrik.R
import com.example.oblig3_henrik.databinding.FragmentUsersBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding
    lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.downloadUsers()
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = UserAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = LinearLayoutManager(context)

        viewModel.users.observe(viewLifecycleOwner) { adapter.setUserListItems(viewModel.users.value!!) }

        return binding.root
    }
}