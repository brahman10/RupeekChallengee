package com.example.rupeekchallengee.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rupeekchallengee.R
import com.example.rupeekchallengee.data.apimodel.PlaceOfInterestModel
import com.example.rupeekchallengee.ui.PlaceAdapter
import com.example.rupeekchallengee.ui.viewmodel.PlaceOfInterestViewModel
import com.example.rupeekchallengee.utils.LoadingBar
import com.example.rupeekchallengee.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var placeOfInterestViewModel: PlaceOfInterestViewModel
    lateinit var placeAdapter: PlaceAdapter
    lateinit var loadingBar: LoadingBar
    val filteredList = ArrayList<PlaceOfInterestModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingBar = LoadingBar(this)
        placeOfInterestViewModel = ViewModelProvider(this).get(PlaceOfInterestViewModel::class.java)
        placeAdapter = PlaceAdapter(emptyList())

        rec_places.apply {
            layoutManager=GridLayoutManager(this@MainActivity,2)
            adapter = placeAdapter
        }

        placeOfInterestViewModel.placeOfInterestData.observe(this, Observer {
            placeAdapter.placeData = it
            placeAdapter.notifyDataSetChanged()
        })
        placeOfInterestViewModel.progress.observe(this, Observer {
            if (it)
            {
                loadingBar.show()
            }
            else
            {
                loadingBar.dismiss()
            }
        })
        placeOfInterestViewModel.getPlaceOfInterest()

        rec_places.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                rec_places,
                object : RecyclerTouchListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val intent = Intent(this@MainActivity,MapActivity::class.java)
                        intent.putExtra("Data",placeAdapter.placeData[position])
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {}
                })
        )

        et_search.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val str = s.toString()
                if (str.length>0)
                {
                    filterList(str)
                }
                else
                {
                    placeAdapter.placeData = placeOfInterestViewModel.placeOfInterestData.value!!
                    placeAdapter.notifyDataSetChanged()
                }
            }

        })
    }
    fun filterList(str:String){
        filteredList.clear()
        for (i in placeOfInterestViewModel.placeOfInterestData.value!!)
        {
            if (i.name.contains(str , ignoreCase = true))
            {
                filteredList.add(i)
            }
        }
        placeAdapter.placeData = filteredList
        placeAdapter.notifyDataSetChanged()
    }
}