package com.example.oblig3_henrik.ui.main.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oblig3_henrik.R
import com.example.oblig3_henrik.databinding.FragmentPhotosBinding


class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private lateinit var binding: FragmentPhotosBinding
    lateinit var viewModel: PhotosViewModel
    val args: PhotosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        viewModel.downloadPhotos(args.albumId)


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PhotoAdapter()
        binding.rvPhotos.adapter = adapter
        binding.rvPhotos.layoutManager = LinearLayoutManager(context)

        viewModel.photos.observe(viewLifecycleOwner) { adapter.setPhotoListItems(viewModel.photos.value!!) }

        return binding.root
    }
}