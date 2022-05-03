package hu.ait.weatherinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.ait.weatherinfo.R
import hu.ait.weatherinfo.data.City
import hu.ait.weatherinfo.databinding.CityRowBinding

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder> {
    var cityItems = mutableListOf<City>(
        City("New York"),
        City("Budapest")
    )

    val context : Context
    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getItemCount(): Int {
        return cityItems.size
    }

    fun addCity(newCity: City) {
        cityItems.add(newCity)
        notifyItemInserted(cityItems.lastIndex) // refreshes the recyclerview only where the new item was added
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cityBinding = CityRowBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(cityBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cityItems[position]
        holder.bind(city)
    }

    inner class ViewHolder(var binding: CityRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.tvCityName.text = city.cityName
        }
    }

}