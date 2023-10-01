package com.example.sportandcharityadmin.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportandcharityadmin.databinding.FragmentNotificationsBinding
import com.example.sportandcharityadmin.ui.dashboard.rateRV.RatingVerticalSpaceItemDecoration
import com.example.sportandcharityadmin.ui.notifications.projectRV.Project
import com.example.sportandcharityadmin.ui.notifications.projectRV.ProjectsItemTouchHelperCallback
import com.example.sportandcharityadmin.ui.notifications.projectRV.ProjectsRVAdapter
import com.example.sportandcharityadmin.ui.request.Request
import com.example.sportandcharityadmin.ui.request.requestRV.RequestItemTouchHelperCallback
import com.example.sportandcharityadmin.ui.request.requestRV.RequestRVAdapter

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    lateinit var list: MutableList<Project>
    private val projectAdapter = ProjectsRVAdapter()
    private val callback: ProjectsItemTouchHelperCallback =
        ProjectsItemTouchHelperCallback(projectAdapter)
    private val touchHelper = ItemTouchHelper(callback)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
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

        list = mutableListOf<Project>(
            Project(
                "000",
                "За каждый км 1 балл!",
                "Бегайте и помогайте!"
            ), Project(
                "111",
                "За каждую велосипедную поездку 1 балл",
                "Катайтесь чаще!"
            ), Project(
                "222",
                "Растяжка за 100 баллов!",
                "Сделайте!"
            )
        )

        projectAdapter.submit(list, binding.rvProjects)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}