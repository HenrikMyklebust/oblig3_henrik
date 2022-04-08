package com.example.oblig3_henrik.ui.main.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.oblig3_henrik.databinding.PhotoLineBinding

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotosViewHolder>() {

    var photos: MutableList<Photo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            PhotoLineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun setPhotoListItems(photos: MutableList<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    class PhotosViewHolder(private val binding: PhotoLineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
            val url = GlideUrl(
                photo.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "android")
                    .build()
            )
            Glide.with(itemView.context).load(url).into(binding.ivThumbnail)
            binding.photoLine.setOnClickListener {
                val action = PhotosFragmentDirections.actionPhotosFragmentToPhotoFragment(photo)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}