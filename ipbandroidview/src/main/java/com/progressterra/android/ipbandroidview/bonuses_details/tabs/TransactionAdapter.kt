package com.progressterra.android.ipbandroidview.bonuses_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import java.util.*

class TransactionAdapter(var transactions: List<TransactionResponse>) :
    RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.bonus_train_operations_name)
    private val sum: TextView = itemView.findViewById(R.id.bonus_train_operations_sum)
    private val action: TextView = itemView.findViewById(R.id.bonus_train_operations_action)
    private val date: TextView = itemView.findViewById(R.id.bonus_train_operations_date)


    fun bind(item: TransactionResponse) {
        //Контекст
        val context = itemView.context

        //Установка значений
        name.text = item.typeBonusName
        date.text = ""

        //Тип операции
        action.text = when (item.typeOperation!!) {
            TypeOperation.SPENDING -> context.getString(R.string.type_operation_spending)
            TypeOperation.BOURING -> context.getString(R.string.type_operation_burning)
            TypeOperation.CHARGING -> context.getString(R.string.type_operation_charging)
        }

        //Сумма операции
        if (item.quantity!! <= 0) {
            //Указание цвета текста
            //sum.setTextColor(ContextCompat.getColor(context, R.color.carnation))
            sum.text = String.format(
                context.getString(R.string.negative_sum), kotlin.math.abs(item.quantity!!)
                    .toString()
            )
        } else {
            //Указание цвета текста
            //sum.setTextColor(ContextCompat.getColor(context, R.color.apple_green))
            sum.text = String.format(
                context.getString(R.string.positive_sum), kotlin.math.abs(item.quantity!!)
                    .toString()
            )
        }
    }
}

class TransactionResponse {

    /**
     * Имя типа бонусов
     */
    var typeBonusName: String? = null

    /**
     * Дата
     */
    var dateEvent: Date? = null

    /**
     * Тип операции
     */
    var typeOperation: TypeOperation? = null

    /**
     * Значение
     */
    var quantity: Int? = null

}

enum class TypeOperation {

    CHARGING,
    SPENDING,
    BOURING
}