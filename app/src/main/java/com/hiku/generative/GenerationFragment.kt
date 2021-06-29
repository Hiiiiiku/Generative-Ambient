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
import kotlinx.android.synthetic.main.fragment_generation.*
import kotlinx.coroutines.*
import java.sql.Types.NULL
import kotlin.random.Random


class GenerationFragment : Fragment() {

    private val sharedViewModel : MainViewModel by activityViewModels()
    private var binding: FragmentGenerationBinding? = null
    private var startVertex = 0
    private var isClicked = 0
    private val soundNames = Constants.SOUND_NAMES
    private var isInfoClicked = 0



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

    fun playSound(){



        if(isClicked == 0) {
            isClicked = 1
            if (mMediaPlayer != null) {
                mMediaPlayer!!.release()
                mMediaPlayer = null
            }

            //MarkovChain().sumMatrix(Constants.MARKOV_MATRIX_RAW)



            var job = GlobalScope.launch(Dispatchers.Main) {
                while (true) {
                    var randomValue = Random.nextInt(100,4000).toLong()
                    startVertex = MarkovChain().main(startVertex)
                    println(startVertex)
                    println(soundNames[startVertex])
                    var id = resources.getIdentifier(soundNames[startVertex], "raw",
                            activity?.packageName
                    )
                    println(id)


                    var mediaPlayer = MediaPlayer.create(context, id)
                    delay(randomValue)
                    mediaPlayer?.start()

                    var job2 = GlobalScope.launch(Dispatchers.Main){
                        delay(5000)
                        mediaPlayer?.reset()
                        mediaPlayer?.release()
                    }

                }
            }
            button2.visibility = View.INVISIBLE;
            button.visibility = View.VISIBLE

            button.setOnClickListener() {
                job?.cancel()
                println("Job canceled")
                isClicked = 0
                button2.visibility = View.VISIBLE;
                button.visibility = View.INVISIBLE;

            }
        }

    }

    fun backgoundButton() {
        var job = GlobalScope.launch(Dispatchers.Main) {
            startVertex = MarkovChain().main(startVertex)
            println(startVertex)
            println(soundNames[startVertex])
            var id = resources.getIdentifier(soundNames[startVertex], "raw",
                    activity?.packageName
            )
            println(id)


            var mediaPlayer = MediaPlayer.create(context, id)
            mediaPlayer?.start()

            var job2 = GlobalScope.launch(Dispatchers.Main) {
                delay(2000)
                mediaPlayer?.reset()
                mediaPlayer?.release()
            }
        }
    }

    fun info(){
        if(isInfoClicked == 0)
        {
            button6.visibility = View.INVISIBLE
            back.visibility = View.VISIBLE
            button.visibility = View.INVISIBLE
            button2.visibility = View.INVISIBLE;
            textView.visibility = View.INVISIBLE
            bgbuttonup.visibility = View.INVISIBLE
            bgbuttonright.visibility = View.INVISIBLE
            bgbuttonleft.visibility = View.INVISIBLE
            bgbuttondown.visibility = View.INVISIBLE
            textView2.visibility = View.VISIBLE
            isInfoClicked = 1
        }else{
            if(isClicked == 1)
            {
                button.visibility = View.VISIBLE
            }else
            {
                button2.visibility = View.VISIBLE;
            }
            button6.visibility = View.VISIBLE
            textView2.visibility = View.INVISIBLE
            textView.visibility = View.VISIBLE
            bgbuttonup.visibility = View.VISIBLE
            bgbuttonright.visibility = View.VISIBLE
            bgbuttonleft.visibility = View.VISIBLE
            bgbuttondown.visibility = View.VISIBLE
            back.visibility = View.INVISIBLE
            isInfoClicked = 0
        }

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

