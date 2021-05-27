package com.hiku.generative.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hiku.generative.R
import com.hiku.generative.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_activity.*

class MainFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding? = null


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val fragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            mainFragment = this@MainFragment
        }
    }

    fun startGame()
    {

        findNavController().navigate(R.id.action_mainFragment_to_generationFragment3)
    }

}