package com.hoony.notificationstyleexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hoony.notificationstyleexample.R
import com.hoony.notificationstyleexample.databinding.FragmentMessageBinding

class MessagingFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)
        return binding.root
    }

    fun isInlineSwitchChecked(): Boolean = binding.inLineSwitch.isChecked
}