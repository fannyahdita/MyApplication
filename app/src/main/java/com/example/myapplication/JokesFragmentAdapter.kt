package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentDetailsBinding

class JokesFragmentAdapter : Fragment() {
    companion object {
        private const val JOKE = "model"

        fun newInstance(joke: Joke): JokesFragmentAdapter {
            val args = Bundle()
            args.putSerializable(JOKE, joke)
            val fragment = JokesFragmentAdapter()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentDetailBinding = FragmentDetailsBinding.inflate(inflater, container, false)

        val model = arguments?.getSerializable(JOKE) as Joke
        fragmentDetailBinding.joke = model
        return fragmentDetailBinding.root
    }
}