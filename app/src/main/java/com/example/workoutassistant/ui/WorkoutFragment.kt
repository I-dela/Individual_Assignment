package com.example.workoutassistant.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutassistant.R
import com.example.workoutassistant.adapter.DataAdapter
import com.example.workoutassistant.model.BodyPart
import com.example.workoutassistant.ui.viemodels.BodyPartsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_workout.*
import kotlinx.android.synthetic.main.fragment_workout.view.*
import kotlinx.android.synthetic.main.item_level.view.*

class WorkoutFragment : Fragment() {

    private val bodyParts  = arrayListOf<BodyPart>()
    private val adapter  = DataAdapter(bodyParts) { bodyPart -> onBodyPartClick(bodyPart as BodyPart) }
    private val viewModel: BodyPartsViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)

        val view: View = inflater.inflate(R.layout.fragment_workout, container, false)

        view.button.setOnClickListener{
            initRecyclerViewWithFavourites()
        }

        view.buttonAll.setOnClickListener{
            initRecyclerViewWithAll()
        }




        // Return the fragment view/layout
        return view
    }







    private fun initRecyclerViewWithFavourites (){
        viewModel.favourites.observe(viewLifecycleOwner, Observer {bodyPartsFavourite ->
            this.bodyParts.clear()
            this.bodyParts.addAll(bodyPartsFavourite)
            adapter.notifyDataSetChanged()

        })

    }

    private fun initRecyclerViewWithAll(){
        viewModel.bodyParts.observe(viewLifecycleOwner, Observer {bodyParts ->
            this.bodyParts.clear()
            this.bodyParts.addAll(bodyParts)
            adapter.notifyDataSetChanged()

        })

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

            createItemTouchHelper().attachToRecyclerView(rvWorkouts)



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

    private fun onBodyPartClick(bodyPart: BodyPart) {
        val intent  = Intent(context, ChooseLevelActivity::class.java)
        intent.putExtra("BodyPart", bodyPart.name)
        intent.putExtra("image", bodyPart.imageName)
        startActivity(intent)
    }



    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                if(direction == ItemTouchHelper.RIGHT) {

                    val position = viewHolder.adapterPosition
                    val bodyPartSetFavourite = bodyParts[position]



                        val id = bodyPartSetFavourite.id
                        if (id != null) {
                            viewModel.setAsFavourite(id)
                        }
                    if(bodyPartSetFavourite.isFavourite){
                        Snackbar.make(rvWorkouts, "Already marked as favourite", Snackbar.LENGTH_LONG).show()


                    }else {
                        Snackbar.make(rvWorkouts, "Marked as favourite", Snackbar.LENGTH_LONG)
                            .show()
                    }

                } else{


                    val position = viewHolder.adapterPosition
                    val bodyPartToUnsetFavourite = bodyParts[position]



                        val id = bodyPartToUnsetFavourite.id

                        if (id != null) {
                            viewModel.unSetAsFavourite(id)
                        }
                    if(!bodyPartToUnsetFavourite.isFavourite){
                        Snackbar.make(rvWorkouts, "Error: this one is not in your favourites list ", Snackbar.LENGTH_LONG).show()


                    }else {
                        Snackbar.make(
                            rvWorkouts,
                            "Deleted from My Favourites ",
                            Snackbar.LENGTH_LONG
                        ).show()

                    }

                }



            }
        }
        return ItemTouchHelper(callback)
    }


}