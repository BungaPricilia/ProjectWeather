package com.example.weather.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.presentation.HourlyForecastAdapter
import com.example.weather.base.BaseFragment
import com.example.weather.databinding.FragmentJayapuraWeatherBinding
import com.example.weather.presentation.SevenDaysForecastAdapter
import com.example.weather.util.*
import com.example.weather.util.gone
import com.example.weather.util.visible
import com.example.weather.viewmodel.WeatherViewModel
import org.koin.android.ext.android.inject

class JayapuraWeatherFragment : BaseFragment<FragmentJayapuraWeatherBinding>() {
    private val weatherViewModel: WeatherViewModel by inject()
    private val hourlyForecastAdapter = HourlyForecastAdapter()
    private val sevenDaysForecastAdapter = SevenDaysForecastAdapter()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentJayapuraWeatherBinding {
        return FragmentJayapuraWeatherBinding.inflate(layoutInflater, container, false)
    }

    override fun setupIntent() {}

    override fun setupUI() {
        binding.rvHourlyForecast.apply {
            adapter = hourlyForecastAdapter
            layoutManager = GridLayoutManager(requireContext(), 11)
        }

        binding.rvSevenDaysForecast.apply {
            adapter = sevenDaysForecastAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        binding.tvDate.text = getCurrentDateInString()

        loadData()
    }

    override fun setupAction() {
        binding.btnRetry.setOnClickListener { loadData() }
    }

    override fun setupProcess() {}

    private fun loadData() {
        if (hasNetwork(requireContext()) == true) {
            try {
                showOnlineMode()
                weatherViewModel.getHourlyForecast(API_KEY, TOKEN, "JAYAPURA")
                weatherViewModel.getSevenDayForecast(API_KEY, TOKEN, "JAYAPURA")
            } catch (e: Exception) {
                showOfflineMode()
            }
        } else {
            showOfflineMode()
        }
    }

    private fun showOfflineMode() {
        binding.containerOffline.visible()
    }

    private fun showOnlineMode() {
        binding.containerOffline.gone()
    }

    override fun setupObserver() {
        weatherViewModel.listHourlyForecast.observe(this,
            onLoading = {
                showCancelableDialog("Loading...")
            },
            onError = {
                dismissDialog()
                requireContext().showShortToast("Error: $it")
            },
            onSuccess = {
                dismissDialog()
                val list = it.sortedBy { response ->
                    response.id
                }
                hourlyForecastAdapter.submitList(list)
            }
        )

        weatherViewModel.listSevenDayForecast.observe(this,
            onLoading = {
                showCancelableDialog("Loading...")
            },
            onError = {
                dismissDialog()
                requireContext().showShortToast("Error: $it")
            },
            onSuccess = {
                dismissDialog()
                val list = it.sortedBy { id }
                sevenDaysForecastAdapter.submitList(list)
            }
        )
    }
}
