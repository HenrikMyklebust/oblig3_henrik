package com.example.oblig3_henrik.ui.main.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3_henrik.databinding.UserLineBinding


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var users: MutableList<User> = mutableListOf()

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

    fun setUserListItems(users: MutableList<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: UserLineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.userLine.setOnClickListener {
                val action = UsersFragmentDirections.actionUsersFragmentToAlbumFragment(user.id)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}