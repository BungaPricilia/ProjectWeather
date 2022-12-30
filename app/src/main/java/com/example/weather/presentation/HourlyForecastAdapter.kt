package com.example.weather.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weather.databinding.ItemHourlyForecastBinding
import com.example.weather.presentation.HourlyForecastAdapter.ListViewHolder
import com.example.weather.base.BaseAdapter
import com.example.weather.base.BaseViewHolder
import com.example.weather.data.weather.model.HourlyForecastResponse
import com.example.weather.util.ObjectDiffUtil
import com.example.weather.util.setImageWeather

class HourlyForecastAdapter(private val onItemClicked: ((HourlyForecastResponse) -> Unit)? = null)
    : BaseAdapter<HourlyForecastResponse, ItemHourlyForecastBinding, ListViewHolder>
    (ObjectDiffUtil.hourlyDiffUtil) {

    inner class ListViewHolder(mBinding: ItemHourlyForecastBinding):
        BaseViewHolder<HourlyForecastResponse>(mBinding) {
        override fun bind(data: HourlyForecastResponse) {
            with(binding as ItemHourlyForecastBinding){
                imgWeather.setImageWeather(data.status.toString())
                tvHour.text = data.time
                tvTemperature.text = "${data.temp.toString()}°"
                root.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemHourlyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}