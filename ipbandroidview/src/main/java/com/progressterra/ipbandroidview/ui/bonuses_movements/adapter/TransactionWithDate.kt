package com.progressterra.ipbandroidview.ui.bonuses_movements.adapter

import java.util.*

internal class TransactionWithDate(
    val transaction: Transaction?,
    val dateType: String?
) {

    companion object {
        // добавляет в список даты к которым относятся транзации

        fun convertToTransactionsWithDate(transactions: List<Transaction>): List<TransactionWithDate> {
            if (transactions.isEmpty()) return emptyList()

            val transactionsWithDate = mutableListOf<TransactionWithDate>()

            val now = Date(System.currentTimeMillis())

            if (transactions.firstOrNull()?.rawDate?.date?.compareTo(now.date) == 0) {
                transactionsWithDate.add(TransactionWithDate(null, "Сегодня"))
            } else {
                transactionsWithDate.add(
                    TransactionWithDate(
                        null,
                        transactions.firstOrNull()?.formattedDate
                    )
                )
            }

            for (i in transactions.indices) {
                transactionsWithDate.add(TransactionWithDate(transactions[i], null))

                if (transactions[i].formattedDate != transactions.getOrNull(i + 1)?.formattedDate) {
                    transactions.getOrNull(i + 1)?.let {
                        transactionsWithDate.add(
                            TransactionWithDate(
                                null,
                                it.formattedDate
                            )
                        )
                    }
                }
            }
            return transactionsWithDate

        }
    }
}


