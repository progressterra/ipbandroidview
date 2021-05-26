package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.android.ipbandroidview.bonuses_details.TransactionAdapter
import com.progressterra.android.ipbandroidview.bonuses_details.TransactionResponse
import com.progressterra.android.ipbandroidview.bonuses_details.TypeOperation
import com.progressterra.ipbandroidview.R

class BonusesTabTransactionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_bonuses_transaction_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView =view.findViewById<RecyclerView>(R.id.bonus_transaction_rv)
        recyclerView.adapter = TransactionAdapter(listOf(
            TransactionResponse().apply {
                dateEvent
                quantity = 123
                typeBonusName = "asdf"
                typeOperation = TypeOperation.BOURING
            }
        ))
    }
}