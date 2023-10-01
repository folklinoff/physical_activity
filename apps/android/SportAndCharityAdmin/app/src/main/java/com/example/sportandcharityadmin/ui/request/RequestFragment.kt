package com.example.sportandcharityadmin.ui.request

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportandcharityadmin.R
import com.example.sportandcharityadmin.databinding.FragmentHomeBinding
import com.example.sportandcharityadmin.databinding.FragmentRequestBinding
import com.example.sportandcharityadmin.ui.dashboard.rateRV.Rating
import com.example.sportandcharityadmin.ui.dashboard.rateRV.RatingItemTouchHelperCallback
import com.example.sportandcharityadmin.ui.dashboard.rateRV.RatingRVAdapter
import com.example.sportandcharityadmin.ui.dashboard.rateRV.RatingVerticalSpaceItemDecoration
import com.example.sportandcharityadmin.ui.request.requestRV.RequestItemTouchHelperCallback
import com.example.sportandcharityadmin.ui.request.requestRV.RequestRVAdapter
import kotlinx.coroutines.launch


class RequestFragment : Fragment() {
    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!
    lateinit var list: MutableList<Request>
    private val projectAdapter = RequestRVAdapter()
    private val callback: RequestItemTouchHelperCallback =
        RequestItemTouchHelperCallback(projectAdapter)
    private val touchHelper = ItemTouchHelper(callback)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvProjects) {
            touchHelper.attachToRecyclerView(this)
            adapter = projectAdapter
            layoutManager = LinearLayoutManager(requireContext())
                .apply {
                    addItemDecoration(
                        RatingVerticalSpaceItemDecoration(50)
                    )
                }
        }

        list = mutableListOf<Request>(
            Request(
                "000",
                "Сумо",
                20
            ), Request(
                "111",
                "Бокс",
                15
            ), Request(
                "222",
                "Растяжка",
                10
            )
        )

        projectAdapter.submit(list, binding.rvProjects)

        projectAdapter.onProjectSwipeListener = {
            list.remove(it)
        }

    }

}
