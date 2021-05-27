package com.hiku.generative

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hiku.generative.databinding.FragmentGenerationBinding
import com.hiku.generative.ui.main.MainViewModel
import java.util.*


class GenerationFragment : Fragment() {

    private val sharedViewModel : MainViewModel by activityViewModels()
    private var binding: FragmentGenerationBinding? = null

    private lateinit var viewModel: MainViewModel
    var mMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentGenerationBinding.inflate(inflater, container, false)
        binding = fragmentBinding

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

    fun playSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        val soundNames = arrayOf("b", "c", "des", "f")
            var rangeA: Double
            var rangeB: Double
            //        double [][] myNumbers = {{0.1, 0.2, 0.3, 0.4},
//                {0.1, 0.2, 0.3, 0.4},
//                {0.1, 0.2, 0.3, 0.4},
//                {0.1, 0.2, 0.3, 0.4}};

            val myNumbers = arrayOf(
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0)
            )
            var k = 0
            var vertex = 0
//            val myNumbers = doubleArrayOf(0.6, 0.7, 0.8, 1.0)
//            while(k != 10) {
                var r = Random()
                var randomValue = 0 + (1 - 0) * r.nextDouble()
//                        rangeA=0.0000000000000001;

                for (j in myNumbers.indices) {
                    if (j != 0) {
//                rangeA = myNumbers[i-1];
                    }
                    rangeB = myNumbers[vertex][j]
                    if (randomValue <= rangeB) {
//                        vertex = j
                        var id = getResources().getIdentifier(soundNames[j], "raw",
                            getActivity()?.getPackageName()
                        )
                        println(id)
                        println(getActivity()?.getPackageName())
                        println(j)
//                        val mediaPlayer = MediaPlayer.create(context, id)
//                        mediaPlayer.start()

                        val mediaPlayer = MediaPlayer.create(context, id)
                        mediaPlayer.start()
                        mediaPlayer?.release()

                        break
                    }
                }
//                k += 1
//            }
//        var mediaPlayer = MediaPlayer.create(context, raw.ges)
//        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}

