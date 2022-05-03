package hu.ait.weatherinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.ait.weatherinfo.R
import hu.ait.weatherinfo.data.City

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder> {

    var cityItems = mutableListOf<City>(
        City("New York"),
        City("Budapest")
    )

    override fun getItemCount(): Int {
        return cityItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.city_row, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cityItems[position]
        holder.tvCityName.text = city.cityName
    }

    val context : Context
    constructor(context: Context) : super() {
        this.context = context
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCityName: TextView = itemView.findViewById(R.id.tvCityName)
    }
}