package com.example.autoaid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VehicleAdapter: RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {
    private var vehList: ArrayList<VehModelClass> = ArrayList()
    private var onClickItem:((VehModelClass)->Unit)? = null
    private var onClickDeleteItem:((VehModelClass)->Unit)? = null

    fun addItems(items:ArrayList<VehModelClass>) {
        this.vehList = items
        notifyDataSetChanged()
    }

    fun setOnClickDeleteItem(callback:(VehModelClass)->Unit){
        this.onClickDeleteItem = callback
    }

    fun setOnClickItem(callback: (VehModelClass) -> Unit) {
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VehicleViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_vehicles, parent, false)
    )
    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int){
        val veh = vehList[position]
        holder.bindView(veh)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(veh) }
        holder.btndelete.setOnClickListener{ onClickDeleteItem?.invoke(veh) }
    }

    override fun getItemCount(): Int {
        return vehList.size
    }

    class VehicleViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var make = view.findViewById<TextView>(R.id.tvMake)
        private var model = view.findViewById<TextView>(R.id.tvModel)
        private var year = view.findViewById<TextView>(R.id.tvYear)
        var btndelete = view.findViewById<Button>(R.id.btnDelete)

        fun bindView(veh:VehModelClass){
            id.text = veh.id.toString()
            make.text = veh.make
            model.text = veh.model
            year.text = veh.year

        }
    }

}