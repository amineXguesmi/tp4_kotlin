package com.example.tp5.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.R
import com.example.tp5.core.database.BusScheduleApplication
import com.example.tp5.core.viewmodels.BusScheduleViewModel
import com.example.tp5.core.viewmodels.BusScheduleViewModelFactory
import com.example.tp5.databinding.FragmentListFragmentBinding
import com.example.tp5.ui.adapters.BusStopAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * A simple [Fragment] subclass.
 * Use the [list_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter

    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (requireActivity().application as BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("ListFragment", "onCreateView")

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            findNavController().navigate(
                R.id.action_list_fragment_to_detail,
                Bundle().apply {
                    putString("stopName", schedule.stop_name)
                }
            )
        }

        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(viewLifecycleOwner) { schedules ->
            busStopAdapter.updateList(schedules)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // To avoid memory leaks
    }
}
