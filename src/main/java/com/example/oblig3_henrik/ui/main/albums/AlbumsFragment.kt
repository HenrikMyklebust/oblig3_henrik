package com.example.oblig3_henrik.ui.main.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oblig3_henrik.R
import com.example.oblig3_henrik.databinding.FragmentAlbumsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlbumsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumsBinding
    lateinit var viewModel: AlbumsViewModel
    val args: AlbumsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_albums, container, false)
        viewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)
        viewModel.downloadAlbums(args.userId)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = AlbumAdapter()
        binding.rvAlbums.adapter = adapter
        binding.rvAlbums.layoutManager = LinearLayoutManager(context)

        viewModel.albums.observe(viewLifecycleOwner) { adapter.setAlbumListItems(viewModel.albums.value!!) }

        return binding.root
    }
}