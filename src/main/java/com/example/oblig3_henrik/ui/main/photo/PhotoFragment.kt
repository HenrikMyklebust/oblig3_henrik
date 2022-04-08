package com.example.oblig3_henrik.ui.main.photo

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.oblig3_henrik.R
import com.example.oblig3_henrik.databinding.FragmentPhotoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private lateinit var binding: FragmentPhotoBinding
    lateinit var viewModel: PhotoViewModel
    private val args: PhotoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        viewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
        val url = GlideUrl(
            args.photo.url, LazyHeaders.Builder()
                .addHeader("User-Agent", "android")
                .build()
        )
        Glide.with(this).load(url).into(binding.photo)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_meny, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuDelete -> {
                lifecycleScope.launch(Dispatchers.Main) {
                    val response = viewModel.deletePhoto(args.photo.id)
                    if (response) {
                        Toast.makeText(context, R.string.deleteYes, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, R.string.deleteNo, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.menuEdit -> {
                val builder = AlertDialog.Builder(context)
                val dialogLayout = layoutInflater.inflate(R.layout.photo_edit, null)
                with(builder) {
                    setTitle("Edit photo title: ")
                    setPositiveButton("OK") { dialog, which ->
                        lifecycleScope.launch(Dispatchers.Main) {
                            val photo = args.photo
                            photo.title =
                                dialogLayout.findViewById<EditText>(R.id.etTitle).text.toString()

                            val response = viewModel.editPhoto(photo)
                            if (response) {
                                Toast.makeText(
                                    context, "Title was successfully set to: "
                                            + photo.title, Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                Toast.makeText(context, R.string.editNo, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    setNegativeButton("Cancel") { dialog, which ->

                    }
                    setView(dialogLayout)
                    show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}