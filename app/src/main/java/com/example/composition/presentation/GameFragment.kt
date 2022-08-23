package com.example.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level
import com.example.composition.presentation.models.GameViewModel
import com.example.composition.presentation.models.GameViewModelFactory

class GameFragment : Fragment() {
    //private lateinit var level: Level
    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application)
    }
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

//    private val tvOptions by lazy {
//        mutableListOf<TextView>().apply {
//            add(binding.tvOption1)
//            add(binding.tvOption2)
//            add(binding.tvOption3)
//            add(binding.tvOption4)
//            add(binding.tvOption5)
//            add(binding.tvOption6)
//        }
//    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        //setClickListenersToOptions()
    }

//    private fun setClickListenersToOptions() {
//
//        for (tvOption in tvOptions) {
//            tvOption.setOnClickListener {
//                viewModel.chooseAnswer(tvOption.text.toString().toInt())
//            }
//        }
//    }

    private fun observeViewModel() {

//        viewModel.question.observe(viewLifecycleOwner) {
//            binding.tvSum.text = it.sum.toString()
//            binding.tvLeftNumber.text = it.visibleNumber.toString()
//
//            for (i in 0 until tvOptions.size) {
//                tvOptions[i].text = it.options[i].toString()
//            }
//        }

//        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
//            binding.progressBar.setProgress(it, true)
//        }

//        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
//            binding.tvAnswersProgress.setTextColor(getColorByState(it))
//        }
//        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
//            val color = getColorByState(it)
//            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }
//        viewModel.formattedTime.observe(viewLifecycleOwner) {
//            binding.tvTimer.text = it
//        }
//        viewModel.minPercent.observe(viewLifecycleOwner) {
//            binding.progressBar.secondaryProgress = it
//        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
//        viewModel.progressAnswers.observe(viewLifecycleOwner) {
//            binding.tvAnswersProgress.text = it
//        }
    }

//    private fun getColorByState(goodState: Boolean): Int {
//        val colorResID = if (goodState) {
//            android.R.color.holo_green_light
//        } else {
//            android.R.color.holo_red_light
//        }
//        return ContextCompat.getColor(requireContext(), colorResID)
//    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
//            .addToBackStack(null)
//            .commit()
//        val args = Bundle().apply {
//            putParcelable(GameFinishedFragment.KEY_GAME_RESULT, gameResult)
//        }
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishedFragment(
                gameResult
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // parsArgs()
    }

//    private fun parsArgs() {
//        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
//            level = it
//        }
//    }

//    companion object {
//
//        private const val KEY_LEVEL = "level"
//        private const val NAME = "GameFragment"
//
//        fun newInstance(level: Level): GameFragment {
//            return GameFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_LEVEL, level)
//                }
//            }
//        }
//
//    }
}
