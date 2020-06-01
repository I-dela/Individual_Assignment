package com.example.workoutassistant.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutassistant.R
import com.example.workoutassistant.adapter.BodyPartAdapter
import com.example.workoutassistant.model.BodyPart
import com.example.workoutassistant.ui.viemodels.BodyPartsViewModel
import kotlinx.android.synthetic.main.fragment_workout.*

class WorkoutFragment : Fragment() {

    private val bodyParts  = arrayListOf<BodyPart>()
    private val adapter  = BodyPartAdapter(bodyParts)
    private val viewModel: BodyPartsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        loadData()
        rvWorkouts.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvWorkouts.adapter = adapter
        viewModel.bodyParts.observe(viewLifecycleOwner, Observer {bodyParts ->
            this.bodyParts.clear()
            this.bodyParts.addAll(bodyParts)
            adapter.notifyDataSetChanged()

        })





    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_workouts, menu)
        val searchItem = menu.findItem(R.id.wSearch)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.queryHint = "Search here..."


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })

        }


        super.onCreateOptionsMenu(menu, inflater)
    }
}