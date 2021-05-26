package com.progressterra.android.ipbandroidview.bonuses_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import java.math.BigDecimal
import java.util.*

class OrderAdapter(var orders: List<Purchase>) : RecyclerView.Adapter<OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}

class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val moll: TextView = itemView.findViewById(R.id.bonus_shopping_list_moll)
    private val data: TextView = itemView.findViewById(R.id.bonus_shopping_list_data)
    private val sum: TextView = itemView.findViewById(R.id.bonus_shopping_list_sum)
    private val chargingSum: TextView = itemView.findViewById(R.id.bonus_shopping_list_charging_sum)
    private val spendingSum: TextView = itemView.findViewById(R.id.bonus_shopping_list_spending_sum)

    fun bind(item: Purchase) {

        //Название магазина
        moll.text = item.shopName

        //Дата покупки
        //data.text = DateUtils.getFormatDate(item.purchaseDate!!, DateUtils.FORMAT_DAY)

        //Сумма покупки
        sum.text = String.format(
            itemView.context.resources.getString(R.string.price_value), item.purchaseSum!!
        )

        //Начисленные бонусы
        chargingSum.text = "+" + item.addedBonusesSum.toString()

        //Использованные бонусы
        spendingSum.text = "-" + item.spentBonusesSum.toString()

    }
}

class Purchase {

    /**
     * Ид покупки
     */
    var purchaseId: String? = null

    /**
     * Дата покупки
     */
    var purchaseDate: Date? = null

    /**
     * Название магазина
     */
    var shopName: String? = null

    /**
     * Сумма покупки
     */
    var purchaseSum: BigDecimal? = null

    /**
     * Оплаченно бонусами
     */
    var spentBonusesSum: Int? = null

    /**
     * Начисленно бонусов
     */
    var addedBonusesSum: Int? = null
}