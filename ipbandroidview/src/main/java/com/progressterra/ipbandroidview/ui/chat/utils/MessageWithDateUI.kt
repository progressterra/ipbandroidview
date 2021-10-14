package com.progressterra.ipbandroidview.ui.chat.utils

import java.util.*

class MessageWithDateUI(
    val message: Message?,
    val dayOfMessageSending: String?
) {
    companion object {
        /**
         * Метод необходимый для конвертации списка с сообщений в список сообщений содержащий даты к которым относятся
         * последующие сообщения. В виде: Дата - Сообщение- Сообщение - и тд.
         */
        fun convertToTransactionsWithDate(rawMessages: List<Message>?): List<MessageWithDateUI> {
            // проверяем список на пустоту
            if (rawMessages.isNullOrEmpty()) return emptyList()

            val messageWithDate = mutableListOf<MessageWithDateUI>()
            // получаем текушую дату
            val now = Date(System.currentTimeMillis())

            // если дата первого сообщения совпадает с текущей, помещаем первым итемом в наш список
            // объект даты с текстом "Сегодня"
            if (rawMessages.firstOrNull()?.rawDate?.date?.compareTo(now.date) == 0) {
                messageWithDate.add(MessageWithDateUI(null, "Сегодня"))
            } else {
                // если нет то первым в список помещаем дату соответствующую первому сообщению
                messageWithDate.add(
                    MessageWithDateUI(
                        null,
                        rawMessages.firstOrNull()?.dateCreate
                    )
                )
            }

            // итерируясь по списку проверяем дату в последующем и текущем сообщении, если дата в последующем
            // отличается от даты в текущем то добавляем в наш список объект даты от последующего сообщения

            for (i in rawMessages.indices) {
                messageWithDate.add(MessageWithDateUI(rawMessages[i], null))

                if (rawMessages[i].dateCreate != rawMessages.getOrNull(i + 1)?.dateCreate) {
                    rawMessages.getOrNull(i + 1)?.let {

                        if (it.rawDate?.date?.compareTo(now.date) == 0) {
                            messageWithDate.add(
                                MessageWithDateUI(
                                    null,
                                    "Сегодня"
                                )
                            )
                        } else {
                            messageWithDate.add(
                                MessageWithDateUI(
                                    null,
                                    it.dateCreate
                                )
                            )
                        }
                    }
                }
            }
            return messageWithDate
        }
    }
}
