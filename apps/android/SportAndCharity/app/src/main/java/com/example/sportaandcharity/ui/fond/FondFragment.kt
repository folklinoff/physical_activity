package com.example.sportaandcharity.ui.fond

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportaandcharity.R
import com.example.sportaandcharity.databinding.FragmentFondBinding
import com.example.sportaandcharity.ui.entities.Fond
import com.example.sportaandcharity.ui.fond.fondRV.FondItemTouchHelperCallback
import com.example.sportaandcharity.ui.fond.fondRV.FondRVAdapter
import com.example.todolist.ui.projectRV.VerticalSpaceItemDecoration

class FondFragment : Fragment() {

    private var _binding: FragmentFondBinding? = null
    private val binding get() = _binding!!

    private val projectAdapter = FondRVAdapter()
    private val callback: FondItemTouchHelperCallback =
        FondItemTouchHelperCallback(projectAdapter)
    private val touchHelper = ItemTouchHelper(callback)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFondBinding.inflate(inflater, container, false)







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

        val list = listOf<Fond>( Fond("111",
            "Фонд защиты тигров",
            R.drawable.icon3, "Защитим амурских тигров вместе!"), Fond("222",
            "Фонд помощи всем",
            R.drawable.icon,
            "Сделайте доброе дело сегодня!") )

        projectAdapter.submit(list, binding.rvProjects)


        return binding.root
    }



}