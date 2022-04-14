package com.example.oblig3_henrik.ui.main.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3_henrik.databinding.UserLineBinding
import com.example.oblig3_henrik.domain.DevByteUser
import kotlinx.android.synthetic.main.user_line.view.*


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
            val preferenceManager = PreferenceManager.getDefaultSharedPreferences(itemView.context)
            binding.user = devByteUser
            if (!preferenceManager.getBoolean("cbEmail", true))
                binding.userLine.tvEmail.isGone = true
            binding.userLine.setOnClickListener {
                val action =
                    UsersFragmentDirections.actionUsersFragmentToAlbumFragment(devByteUser.id)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}