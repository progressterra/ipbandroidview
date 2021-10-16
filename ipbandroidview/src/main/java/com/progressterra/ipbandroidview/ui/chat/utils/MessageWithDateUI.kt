package com.progressterra.ipbandroidview.ui.chat.utils

import android.text.format.DateUtils
import android.util.Log

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


            rawMessages.forEach {
                Log.d("myTag", "it = ${it.rawDate}")
            }

            val messageWithDate = mutableListOf<MessageWithDateUI>()
            // получаем текушую дату

            // если дата первого сообщения совпадает с текущей, помещаем первым итемом в наш список
            // объект даты с текстом "Сегодня"
            if (DateUtils.isToday(rawMessages.firstOrNull()?.rawDate?.time ?: 0)) {
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
            rawMessages.forEachIndexed { index, rawMessage ->
                Log.d("myTag", "new = ${rawMessage.rawDate}")
                messageWithDate.add(MessageWithDateUI(rawMessage, null))

                if (rawMessage.dateCreate != rawMessages.getOrNull(index + 1)?.dateCreate) {

                    // Добавляем следующую дату, если она не сегодня и еще не добавлена
                    rawMessages.getOrNull(index + 1)?.let { nextMsg ->
                        val isToday = DateUtils.isToday(nextMsg.rawDate?.time ?: 0)

                        // если не null - Значит дата уже добавлена
                        val dateAdded =
                            messageWithDate.find {
                                it.dayOfMessageSending == rawMessage.dateWoTime
                            } != null

                        // если не null - Значит дата уже добавлена
                        val todayAdded =
                            messageWithDate.find {
                                it.dayOfMessageSending == "Сегодня"
                            } != null

                        when {
                            isToday && !todayAdded -> messageWithDate.add(
                                MessageWithDateUI(
                                    null,
                                    "Сегодня"
                                )
                            )
                            !isToday && !dateAdded -> messageWithDate.add(
                                MessageWithDateUI(
                                    null,
                                    nextMsg.dateWoTime
                                )
                            )
                            else -> {
                            }
                        }
                    }
                }
            }

            return messageWithDate.asReversed()
        }
    }
}
