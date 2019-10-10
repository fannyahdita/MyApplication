package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import java.lang.ClassCastException

import com.example.myapplication.databinding.ItemJokeBinding

class JokesFragment : Fragment() {

    private lateinit var title: Array<String>
    private lateinit var description: Array<String>
    private lateinit var listener: Listener

    companion object {
        fun newInstance() : JokesFragment {
            return JokesFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is Listener) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement Listener")
        }

        title = context.resources.getStringArray(R.array.title)
        description = context.resources.getStringArray(R.array.description)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerview.adapter = ListAdapter(activity)
    }

    inner class ListAdapter(context: Context?) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerbinding =
                    ItemJokeBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerbinding.root, recyclerbinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val joke = Joke(title[position], description[position])
            holder.setData(joke)
            holder.itemView.setOnClickListener { listener.onJokeClick(joke) }
        }

        override fun getItemCount(): Int {
            return title.size
        }
    }

    inner class ViewHolder
    constructor(view: View, private val recyclerItem: ItemJokeBinding)
        : RecyclerView.ViewHolder(view) {
        fun setData(joke: Joke) {
            recyclerItem.joke = joke
        }
    }

    interface Listener {
        fun onJokeClick(joke: Joke)
    }

}