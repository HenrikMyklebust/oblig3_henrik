package com.example.oblig3_henrik.ui.main.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3_henrik.databinding.UserLineBinding
import com.example.oblig3_henrik.domain.DevByteUser


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var users: List<DevByteUser> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserLineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun setUserListItems(users: List<DevByteUser>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: UserLineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(devByteUser: DevByteUser) {
            binding.user = devByteUser
            binding.userLine.setOnClickListener {
                val action = UsersFragmentDirections.actionUsersFragmentToAlbumFragment(devByteUser.id)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}