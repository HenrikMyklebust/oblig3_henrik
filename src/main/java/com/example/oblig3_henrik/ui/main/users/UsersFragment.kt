package com.example.oblig3_henrik.ui.main.users

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oblig3_henrik.R
import com.example.oblig3_henrik.databinding.FragmentUsersBinding

class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding
    lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = UserAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = LinearLayoutManager(context)


        viewModel.users.observe(viewLifecycleOwner) { adapter.setUserListItems(viewModel.users.value!!) }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.users_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuRefresh -> viewModel.refreshDataFromRepository()
            R.id.menuExit -> System.exit(-1)

            R.id.menuSettings -> {
                val action = UsersFragmentDirections.actionUsersFragmentToSettingsFragment()
                findNavController().navigate(action)
            }

            R.id.menuAbout -> {
                val action = UsersFragmentDirections.actionUsersFragmentToAboutFragment()
                findNavController().navigate(action)
            }


        }
        return super.onOptionsItemSelected(item)
    }
}