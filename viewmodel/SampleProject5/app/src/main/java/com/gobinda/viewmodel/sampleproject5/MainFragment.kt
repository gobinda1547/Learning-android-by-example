package com.gobinda.viewmodel.sampleproject5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
    }

    private lateinit var nextButton: Button
    private lateinit var replaceFragmentButton: Button
    private lateinit var numberTextView: TextView

    private val viewModel: MainFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextButton = view.findViewById(R.id.next_button)
        replaceFragmentButton = view.findViewById(R.id.replace_fragment_button)
        numberTextView = view.findViewById(R.id.number_textview)

        nextButton.setOnClickListener { handleNextButtonClicked() }
        replaceFragmentButton.setOnClickListener { handleReplaceFragmentButtonClicked() }

        // trying to display the number if exist, there can be 2 cases
        // 1. When fragment will be created first time then the actualNumber will be null,
        // so this function call won't have any effect.
        // 2. When the fragment will be restored then it will have the previous view
        // model's reference, so actual Number may not be null then. In that case we have
        // to show it to the user.
        displayTheNumber()
    }

    private fun handleReplaceFragmentButtonClicked() {
        activity?.supportFragmentManager?.let { fragmentManager ->
            val fragment = MainFragment()
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    /**
     * Since user clicked on the next button, so according to our requirement,
     * 1. We will first generate a new number,
     * 2. then we will show it to the user.
     */
    private fun handleNextButtonClicked() {
        viewModel.generateNewNumber()
        displayTheNumber()
    }

    private fun displayTheNumber() {
        viewModel.actualNumber?.let { numberTextView.text = it.toString() }
        Log.d(TAG, "displayTheNumber: invoked with ${viewModel.actualNumber}")
    }
}