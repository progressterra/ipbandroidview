package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesOrderTabBinding
import java.math.BigDecimal

internal class BonusesTabOrderFragment : Fragment() {
    private lateinit var binding: FragmentDetailBonusesOrderTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_bonuses_order_tab,
            container,
            false
        )
        return binding.root
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