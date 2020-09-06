package com.hoony.notificationstyleexample.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hoony.notificationstyleexample.R
import com.hoony.notificationstyleexample.databinding.FragmentBigPictureBinding

class BigPictureFragment : Fragment() {

    private lateinit var binding: FragmentBigPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_big_picture, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage(Uri.parse(binding.urlEdit.text.toString()))
        setListener()
    }

    private fun setListener() {
        binding.urlEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loadImage(Uri.parse(charSequence.toString()))
            }
        })
    }

    private fun loadImage(uri: Uri) {
        Glide.with(this@BigPictureFragment)
            .load(uri)
            .into(binding.image)
    }

    fun getImageBitmap(): Bitmap = (binding.image.drawable as BitmapDrawable).bitmap
}