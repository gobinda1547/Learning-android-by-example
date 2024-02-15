package com.gobinda.hilt.sampleproject3.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.gobinda.hilt.sampleproject3.R
import com.gobinda.hilt.sampleproject3.app.LOG_TAG
import com.gobinda.hilt.sampleproject3.app.PASS_KEY_ACTIVITY_SIZE
import com.gobinda.hilt.sampleproject3.module.LocalSource
import com.gobinda.hilt.sampleproject3.services.DataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var mInterface: MainFragmentInterface? = null

    @Inject
    @LocalSource
    lateinit var dataSource: DataSource

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mInterface = context as MainFragmentInterface?
    }

    override fun onDetach() {
        mInterface = null
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: invoked [$dataSource]")
        Log.d(TAG, "onCreate: invoked [${dataSource.fetchData()}]")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false).apply {
        initializeUI(this)
        addHandlersAndListeners(this)
    }

    private fun initializeUI(currentView: View) {
        val countAsText = arguments?.getInt(PASS_KEY_ACTIVITY_SIZE)?.toString() ?: ""
        currentView.findViewById<TextView>(R.id.activity_count_text_view).text = countAsText
    }

    private fun addHandlersAndListeners(currentView: View) {
        currentView.findViewById<Button>(R.id.new_activity_button).setOnClickListener {
            mInterface?.startNewActivity()
        }
        currentView.findViewById<Button>(R.id.new_fragment_button).setOnClickListener {
            mInterface?.startNewFragment()
        }
    }

    companion object {

        private const val TAG = "$LOG_TAG -> MainFragment"

        fun getNewObject(count: Int): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().also {
                    it.putInt(PASS_KEY_ACTIVITY_SIZE, count)
                }
            }
        }
    }
}