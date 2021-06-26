package com.hiku.generative

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.hiku.generative.databinding.FragmentGenerationBinding
import com.hiku.generative.ui.main.MainViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*


class GenerationFragment : Fragment() {

    private val sharedViewModel : MainViewModel by activityViewModels()
    private var binding: FragmentGenerationBinding? = null
    private var startVertex = 0


    private lateinit var viewModel: MainViewModel
    var mMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentGenerationBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        viewLifecycleOwner.lifecycleScope.launch {

        }


        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            generationfragment = this@GenerationFragment
        }
    }


    fun startGame()
    {
        findNavController().navigate(R.id.action_generationFragment3_to_mainFragment)
    }

    fun playSound(){
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }

        val soundNames = Constants.SOUND_NAMES

        GlobalScope.launch(Dispatchers.Main) {
            repeat(10) {

                startVertex = MarkovChain().main(startVertex)
                var id = resources.getIdentifier(soundNames[startVertex], "raw",
                        activity?.packageName
                )

                var mediaPlayer = MediaPlayer.create(context, id)
                mediaPlayer?.start()
                delay(3000)
                mediaPlayer?.reset()
                mediaPlayer?.release()
            }
            println("done")
        }

//            var rangeA: Double
        startVertex = MarkovChain().main(startVertex)

        var id = getResources().getIdentifier(soundNames[startVertex], "raw",
                getActivity()?.getPackageName()
        )
    }

//--------------- working delay template-----------------

//    fun main() {
//        GlobalScope.launch(Dispatchers.Main) {
//            for (i in 10 downTo 1) {
//                startVertex = MarkovChain().main(startVertex)
//                println("vertex: $startVertex")
//                delay(5000)
//            }
//            println("done")
//        }
//    }



    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}

