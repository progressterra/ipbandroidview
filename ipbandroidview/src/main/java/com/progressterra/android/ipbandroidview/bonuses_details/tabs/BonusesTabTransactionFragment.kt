package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.android.ipbandroidview.bonuses_details.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.R

class BonusesTabTransactionFragment : Fragment() {
    private lateinit var viewmodel: BonusesDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_bonuses_transaction_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel =
            ViewModelProvider(requireActivity()).get(BonusesDetailsViewModel::class.java)


        val recyclerView =view.findViewById<RecyclerView>(R.id.bonus_transaction_rv)

        viewmodel.transactionList.observe(this){
            recyclerView.adapter = TransactionAdapter(it.transactions)
        }

    }

    override fun onResume() {
        super.onResume()
        view?.requestLayout()
    }
}