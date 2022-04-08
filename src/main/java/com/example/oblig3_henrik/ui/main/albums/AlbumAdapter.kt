package com.example.oblig3_henrik.ui.main.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3_henrik.databinding.AlbumLineBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumsViewHolder>() {

    var albums: MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder(
            AlbumLineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    fun setAlbumListItems(albums: MutableList<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    class AlbumsViewHolder(private val binding: AlbumLineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.album = album
            binding.albumLine.setOnClickListener {
                val action = AlbumsFragmentDirections.actionAlbumFragmentToPhotosFragment(album.id)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}