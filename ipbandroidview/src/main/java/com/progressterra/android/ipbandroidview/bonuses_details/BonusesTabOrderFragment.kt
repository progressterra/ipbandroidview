package com.progressterra.android.ipbandroidview.bonuses_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import java.math.BigDecimal

class BonusesTabOrderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_bonuses_order_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.order_list_rv)
        recycler.adapter = OrderAdapter(listOf(
            Purchase().apply {
                addedBonusesSum = 100
                shopName = "Мем"
                purchaseId = "1234"
                spentBonusesSum = 100
                purchaseSum = BigDecimal(100)
            }
        ))
    }
}