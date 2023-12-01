package com.example.tp5.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.R
import com.example.tp5.core.database.BusScheduleApplication
import com.example.tp5.core.database.models.Schedule
import com.example.tp5.core.viewmodels.BusScheduleViewModel
import com.example.tp5.core.viewmodels.BusScheduleViewModelFactory
import com.example.tp5.databinding.FragmentDetailBinding
import com.example.tp5.ui.adapters.BusStopAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [detail_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            print("hola")
        }
        recyclerView.adapter = busStopAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stopName = arguments?.getString("stopName")

        var list: LiveData<List<Schedule>>
        if(stopName != null){
            Thread {
                list = viewModel.scheduleForStopName(stopName)
                Handler(Looper.getMainLooper()).post {
                    viewModel.scheduleForStopName(stopName).observe(this) {

                        busStopAdapter.updateList(it)
                    }
                }
            }.start()
        }
        }
private val viewModel : BusScheduleViewModel by viewModels() {
    BusScheduleViewModelFactory(
        (requireActivity().application as
                BusScheduleApplication).database.scheduleDao()
    )}

}