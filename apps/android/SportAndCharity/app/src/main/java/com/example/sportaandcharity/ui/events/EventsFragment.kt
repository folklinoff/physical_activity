package com.example.sportaandcharity.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportaandcharity.R
import com.example.sportaandcharity.databinding.FragmentEventsBinding
import com.example.sportaandcharity.ui.entities.Project
import com.example.sportaandcharity.ui.fond.fondRV.FondItemTouchHelperCallback
import com.example.sportaandcharity.ui.fond.fondRV.FondRVAdapter
import com.example.sportaandcharity.ui.projectRV.ProjectsItemTouchHelperCallback
import com.example.sportaandcharity.ui.projectRV.ProjectsRVAdapter
import com.example.todolist.ui.projectRV.VerticalSpaceItemDecoration

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    private val projectAdapter = ProjectsRVAdapter()
    private val callback: ProjectsItemTouchHelperCallback =
        ProjectsItemTouchHelperCallback(projectAdapter)
    private val touchHelper = ItemTouchHelper(callback)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val eventsFragmentViewModel =
            ViewModelProvider(this).get(EventsFragmentViewModel::class.java)

        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding.rvProjects) {
            touchHelper.attachToRecyclerView(this)
            adapter = projectAdapter
            layoutManager = LinearLayoutManager(requireContext())
                .apply {
                    addItemDecoration(
                        VerticalSpaceItemDecoration(50)
                    )
                }
        }

        val list = listOf<Project>( Project("111",
            "Благотворительный забег",
            null,
           false,
           R.drawable.icon, "За каждые 10 км 200 баллов!"), Project("222",
            "Необычные виды спорта!",
            null,
            false,
            R.drawable.icon2,"За новый вид спорта 200 баллов!") )

        projectAdapter.submit(list, binding.rvProjects)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}