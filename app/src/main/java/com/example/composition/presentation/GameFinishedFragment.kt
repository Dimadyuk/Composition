package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult


class GameFinishedFragment : Fragment() {
    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //parsArgs()
    }

    private fun retryGame() {
//        requireActivity().supportFragmentManager.popBackStack(
//            GameFragment.NAME,
//            FragmentManager.POP_BACK_STACK_INCLUSIVE
//        )
        findNavController().navigate(R.id.action_gameFinishedFragment_to_chooseLevelFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            }
        )
        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            tvRequiredAnswers.text = String.format(
                resources.getString(R.string.requires_answers),
                args.gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                resources.getString(R.string.score_answers),
                args.gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                resources.getString(R.string.required_percentage),
                args.gameResult.gameSettings.minPercentOfRightAnswers
            )
            val percent =
                (args.gameResult.countOfRightAnswers / args.gameResult.countOfQuestions.toDouble() * 100).toInt()
            tvScorePercentage.text = String.format(
                resources.getString(R.string.score_percentage),
                percent
            )
            if (args.gameResult.winner) {
                emojiResult.setImageResource(R.drawable.ic_smile)
            } else {
                emojiResult.setImageResource(R.drawable.ic_sad)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun parsArgs() {
//        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
//            gameResult = it
//        }
//    }

//    companion object {
//
//        private const val KEY_GAME_RESULT = "game_result"
//
//        fun newInstance(gameResult: GameResult): GameFinishedFragment {
//            return GameFinishedFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_GAME_RESULT, gameResult)
//                }
//            }
//        }
//    }

}