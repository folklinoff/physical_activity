package com.example.sportandcharityadmin.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportandcharityadmin.R
import com.example.sportandcharityadmin.databinding.FragmentDashboardBinding
import com.example.sportandcharityadmin.ui.dashboard.rateRV.Rating
import com.example.sportandcharityadmin.ui.dashboard.rateRV.RatingItemTouchHelperCallback
import com.example.sportandcharityadmin.ui.dashboard.rateRV.RatingRVAdapter
import com.example.sportandcharityadmin.ui.dashboard.rateRV.RatingVerticalSpaceItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val projectAdapter = RatingRVAdapter()
    private val callback: RatingItemTouchHelperCallback =
        RatingItemTouchHelperCallback(projectAdapter)
    private val touchHelper = ItemTouchHelper(callback)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


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

        val list = listOf<Rating>(
            Rating(
                "000",
                "Каримова Зарина Рустамовна", 1, 1230
            ), Rating(
                "111",
                "Каримова Лейла Рустамовна",
                2,
                1150
            ), Rating(
                "222",
                "Митяев Макс Рустамович",
                3,
                1100
            ), Rating(
                "333",
                "Митяева Камилла Филовна",
                4,
                1000
            )
        )

        projectAdapter.submit(list, binding.rvProjects)

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectAdapter.onProjectClickListener = {
            val popup = PopupMenu(requireActivity(), projectAdapter.itemRatingBinding.button)
            popup.menuInflater
                .inflate(R.menu.popup_menu_edit, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                projectAdapter.itemRatingBinding.textViewPoints.text =
                   "0"
                true
            }
            popup.show() //showing popup menu

           /* Snackbar.make(view, "Щелкните для изменени баланса", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}