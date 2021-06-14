package com.example.rupeekchallengee.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rupeekchallengee.R
import com.example.rupeekchallengee.data.apimodel.PlaceOfInterestModel
import com.example.rupeekchallengee.utils.DatabaseHandler
import com.example.rupeekchallengee.utils.DatabaseHandler.Companion.COLUMN_ID
import kotlinx.android.synthetic.main.item_place_of_interest.view.*

class PlaceAdapter(
    var placeData: List<PlaceOfInterestModel>
) : RecyclerView.Adapter<PlaceAdapter.MyViewHolder>() {

    lateinit var databaseHandler: DatabaseHandler
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_place_of_interest, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaceAdapter.MyViewHolder, position: Int) {

        holder.itemView.apply {
            databaseHandler = DatabaseHandler(context = context)
            val placeOfInterestModel = placeData[position]
            name_item.text= placeOfInterestModel!!.name
            address_item.text= placeOfInterestModel!!.address
            Glide.with(context).load(placeOfInterestModel.image).into(image_item)

            when {
                position%4==1 -> {
                    ll_item_detail.setBackgroundColor(resources.getColor(R.color.color_1))
                }
                position%4==2 -> {
                    ll_item_detail.setBackgroundColor(resources.getColor(R.color.color_2))
                }
                position%4==3 -> {
                    ll_item_detail.setBackgroundColor(resources.getColor(R.color.color_3))
                }
                else -> {
                    ll_item_detail.setBackgroundColor(resources.getColor(R.color.color_4))
                }
            }

            if(databaseHandler.isInLiked(placeOfInterestModel!!.id.toString()))
            {
                iv_liked.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
            }
            else
            {
                iv_liked.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            }

            iv_liked.setOnClickListener(View.OnClickListener {
                if (databaseHandler.isInLiked(placeOfInterestModel!!.id.toString()))
                {
                    databaseHandler.removePlace(placeOfInterestModel.id.toString())
                    iv_liked.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))

                }
                else
                {
                    if(databaseHandler.placeCount>=3)
                    {
                        Toast.makeText(context,"Can't Upvote More Than 3 Places",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        val map=HashMap<String,String>()
                        map.set(COLUMN_ID,placeOfInterestModel.id.toString())
                        databaseHandler.setLikedPlace(map)
                        iv_liked.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
                        Toast.makeText(context,"Place Upvoted",Toast.LENGTH_LONG).show()
                    }

                }
            })

        }
    }

    override fun getItemCount(): Int = placeData.size
}
