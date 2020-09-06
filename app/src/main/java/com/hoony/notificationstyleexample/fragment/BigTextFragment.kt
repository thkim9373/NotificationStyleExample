package com.hoony.notificationstyleexample.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hoony.notificationstyleexample.R
import com.hoony.notificationstyleexample.databinding.FragmentBigTextBinding

class BigTextFragment : Fragment() {

    private lateinit var binding: FragmentBigTextBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_big_text, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bigText.setText(
            "살어리 살어리랏다\n" +
                    "청산에 살어리랏다\n" +
                    "멀위랑 다래랑 먹고\n" +
                    "청산에 살어리랏다\n" +
                    "얄리얄리얄라셩얄라리얄라\n" +
                    "\n" +
                    "우러라 우러라 새야\n" +
                    "자고니러 우러라 새야\n" +
                    "널라와 시름 한 나도\n" +
                    "자고니러 우니노라\n" +
                    "얄리얄리얄라셩얄라리얄라\n"
        )
    }

    fun getBigText(): String = binding.bigText.editableText.toString()
}