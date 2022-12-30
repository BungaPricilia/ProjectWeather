package com.example.weather.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weather.base.BaseAdapter
import com.example.weather.base.BaseViewHolder
import com.example.weather.data.weather.model.SevenDayForecastResponse
import com.example.weather.databinding.ItemSevenDaysForecastBinding
import com.example.weather.presentation.SevenDaysForecastAdapter.ListViewHolder
import com.example.weather.util.ObjectDiffUtil
import com.example.weather.util.setImageWeather

class SevenDaysForecastAdapter(private val onItemClicked: ((SevenDayForecastResponse) -> Unit)? = null)
    : BaseAdapter<SevenDayForecastResponse, ItemSevenDaysForecastBinding, ListViewHolder>
    (ObjectDiffUtil.sevenDaysDiffUtil) {

    inner class ListViewHolder(mBinding: ItemSevenDaysForecastBinding):
        BaseViewHolder<SevenDayForecastResponse>(mBinding) {
        override fun bind(data: SevenDayForecastResponse) {
            with(binding as ItemSevenDaysForecastBinding){
                imgWeather.setImageWeather(data.status.toString())
                tvDay.text = data.day
                tvPercent.text = "${data.percent}%"
                tvTemperature.text = data.temp.toString()
                root.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemSevenDaysForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}