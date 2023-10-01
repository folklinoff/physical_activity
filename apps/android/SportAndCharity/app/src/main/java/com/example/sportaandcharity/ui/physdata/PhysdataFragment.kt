package com.example.sportaandcharity.ui.physdata

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sportaandcharity.R
import com.example.sportaandcharity.databinding.FragmentPhysdataBinding
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
import com.github.mikephil.charting.utils.ColorTemplate


class PhysdataFragment : Fragment() {

    //lateinit var scatteredChart: ScatterChart
    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet

    // on below line we are creating array list for bar data
    lateinit var barEntriesList: ArrayList<BarEntry>


    private fun getBarChartData() {
        barEntriesList = ArrayList()

        barEntriesList.add(BarEntry(4f, 4f))
        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(1f, 1f))
        barEntriesList.add(BarEntry(5f, 5f))

    }

    private var _binding: FragmentPhysdataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhysdataBinding.inflate(inflater, container, false)
        // on below line we are initializing
        // our variable with their ids.
        // on below line we are calling get bar
        // chart data to add data to our array list
        getBarChartData()
        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(
            barEntriesList,
            "Рейтинг активностей (бег, тренировки, самокат, велосипед, необычные активности)"
        )
        // on below line we are initializing our bar data
        barData = BarData(barDataSet)
        // on below line we are setting data to our bar chart
        binding.idBarChart.data = barData
        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.BLACK
        // on below line we are setting color for our bar data set
        barDataSet.setColor(resources.getColor(R.color.cocos_color_trans))
        // on below line we are setting text size
        barDataSet.valueTextSize = 16f
        // on below line we are enabling description as false
        binding.idBarChart.description.isEnabled = false


            // on below line we are disabling the
            // description of our scattered chart
        binding.idChart.description.isEnabled = false
            // on below line we are setting
            // draw grid background as false
        binding.idChart.setDrawGridBackground(false)
            // on below line we are setting
            // touch enabled for our chart
        binding.idChart.setTouchEnabled(true)
            // on below line we are setting
            // max highlight distance
        binding.idChart.maxHighlightDistance = 50f
            // on below line we are setting
            // drag enabled for our chart
        binding.idChart.isDragEnabled = true
            // on below line we are enabling
            // scale for our chart
        binding.idChart.setScaleEnabled(true)
            // on below line we are setting
            // max visibility value count
        binding.idChart.setMaxVisibleValueCount(200)
            // on below line we are setting
            // pinch zoom for our chart
        binding.idChart.setPinchZoom(true)
            // on below line we are creating a
            // variable to get out legend for our chart
            val legend = binding.idChart.legend
            // on below line we are setting vertical, horizontal
            // and orientation for our legend
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.orientation = Legend.LegendOrientation.VERTICAL
            // on below line we are setting
            // draw inside for our legend
            legend.setDrawInside(false)
            // on below line we are setting
            // offset for our legend
            legend.xOffset = 5f
            // on below line we are getting
            // y axis for our chart
            val yl = binding.idChart.axisLeft
            // on below line we are
            // setting axis minimum
            yl.axisMinimum = 0f
            // on below line we are enabling
            // our right axis for our chart
        binding.idChart.axisRight.isEnabled = false
            // on below line we are getting x axis of our chart
            val xl = binding.idChart.xAxis
            // on below line we are setting draw grid lines
            xl.setDrawGridLines(false)
            // in below line we are creating an array list
            // for each entry of our chart.
            // we will be representing three values in our charts.
            // below is the line where we are creating three
            // lines for our chart.
            val values1: ArrayList<Entry> = ArrayList()
            val values2: ArrayList<Entry> = ArrayList()
            val values3: ArrayList<Entry> = ArrayList()
            // on below line we are adding data to our charts.
            // on below line we are adding data to our charts.
            for (i in 0..10) {
                values1.add(Entry(i.toFloat(), ((i * 2).toFloat())))
            }
            // on below line we are adding data to our charts for value 2.
            for (i in 11..21) {
                values2.add(Entry(i.toFloat(), ((i * 3).toFloat())))
            }
            // on below line we are adding data to our charts for value 3
            // on below line we are adding data to our charts.
            for (i in 21..31) {
                values3.add(Entry(i.toFloat(), ((i * 4).toFloat())))
            }
            // on below line we are creating a data set and giving it a type
            val set1 = ScatterDataSet(values1, "Низкий рейтинг")
            // on below line we are setting share of our point on our graph
            set1.setColor(ColorTemplate.COLORFUL_COLORS[0])
            // on below line we are creating a new point
            // for our scattered chart
            val set2 = ScatterDataSet(values2, "Средний рейтинг")
            // on below line we are setting out shape to circle
            set2.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
            // on below line we are setting color to our point in chart
            set2.scatterShapeHoleColor = ColorTemplate.COLORFUL_COLORS[3]
            // on below line we are setting scatter shape holder radius
            set2.scatterShapeHoleRadius = 3f
            // on below line we are setting color for our set
            set2.setColor(ColorTemplate.COLORFUL_COLORS[1])
            // on below line we are creating third data set for our chart
            val set3 = ScatterDataSet(values3, "Высокий рейтинг")
            // on below line we are setting color for set 3
            set3.setColor(ColorTemplate.COLORFUL_COLORS[2])
            // on below line we are setting
            // shape size for all sets
            set1.scatterShapeSize = 8f
            set2.scatterShapeSize = 8f
            set3.scatterShapeSize = 8f
            // on below line we are creating a new list for our data set
            val dataSet: ArrayList<IScatterDataSet> = ArrayList()
            // on below line we are adding
            // all sets to our data sets
            dataSet.add(set1)
            dataSet.add(set2)
            dataSet.add(set3)
            // on below line we are creating a
            // new object for scattered data
            val data: ScatterData = ScatterData(dataSet)
            // on below line we are setting
            // data to our chart
        binding.idChart.data = data
            // on below line we are calling
            // invalidate to display our chart
        binding.idChart.invalidate()

        return binding.root
    }


}