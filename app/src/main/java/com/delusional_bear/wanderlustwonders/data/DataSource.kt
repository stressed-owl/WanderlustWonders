package com.delusional_bear.wanderlustwonders.data

import android.content.Context
import com.delusional_bear.wanderlustwonders.R

class DataSource(context: Context) {
    private val citiesList = mutableListOf(
        City(
            0,
            context.resources.getString(R.string.new_york_city),
            context.resources.getString(R.string.usa),
            context.resources.getString(R.string.new_york),
            "https://images.unsplash.com/photo-1527267207156-3372670819dc?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=688&q=80",
            R.string.new_york_city_description,
        ),
        City(
            1,
            context.resources.getString(R.string.los_angeles),
            context.resources.getString(R.string.usa),
            context.resources.getString(R.string.california),
            "https://images.unsplash.com/photo-1580655653885-65763b2597d0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
            R.string.los_angeles_description,
        ),
        City(
            2,
            context.resources.getString(R.string.boston),
            context.resources.getString(R.string.usa),
            context.resources.getString(R.string.massachusetts),
            "https://images.unsplash.com/photo-1573524949339-b830334a31ee?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1331&q=80",
            R.string.boston_description,
        ),
        City(
            3,
            context.resources.getString(R.string.orlando),
            context.resources.getString(R.string.usa),
            context.resources.getString(R.string.florida),
            "https://cdn.britannica.com/07/201607-050-0B5774CB/Orlando-Florida-aerial-cityscape-towards-Eola-Lake.jpg",
            R.string.orlando_description,
        ),
        City(
            4,
            context.resources.getString(R.string.miami),
            context.resources.getString(R.string.usa),
            context.resources.getString(R.string.florida),
            "https://a.cdn-hotels.com/gdcs/production156/d397/81ce7a3b-5be9-4d0c-89e2-ab7920d94bb9.jpg?impolicy=fcrop&w=800&h=533&q=medium",
            R.string.miami_description,
        ),
        City(
            5,
            context.resources.getString(R.string.austin),
            context.resources.getString(R.string.usa),
            context.resources.getString(R.string.texas),
            "https://images.unsplash.com/photo-1557335200-a65f7f032602?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1179&q=80",
            R.string.austin_description,
        ),
        City(
            6,
            context.resources.getString(R.string.washington),
            context.resources.getString(R.string.usa),
            context.resources.getString(R.string.columbia),
            "https://images.unsplash.com/photo-1501466044931-62695aada8e9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=787&q=80",
            R.string.washington_description,
        ),
        City(
            7,
            context.resources.getString(R.string.kyiv),
            context.resources.getString(R.string.ukraine),
            null,
            "https://images.unsplash.com/photo-1602417805869-1bcbb38de8b2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1176&q=80",
            R.string.kyiv_description,
        ),
    )

    fun getCitiesList() = citiesList

    fun getSortedByCityList() = citiesList.sortedBy { it.name }

    fun getCityById(id: Int): City? {
        for (i in citiesList.indices) {
            if (citiesList[i].id == id) return citiesList[i]
        }
        return null
    }

}