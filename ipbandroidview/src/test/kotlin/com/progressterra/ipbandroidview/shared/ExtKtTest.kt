package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.entities.Id
import org.junit.Assert
import org.junit.Test

class ExtKtTest {

    private data class FakeIdHolder(override val id: String, val name: String) : Id

    @Test
    fun `success update by id`() {
        var mockList =
            listOf(FakeIdHolder(id = "1", name = "Alex"), FakeIdHolder(id = "2", name = "John"))
        mockList = mockList.updateById(FakeIdHolder("1", "Mary")) {
            it.copy(name = "Kyle")
        }
        Assert.assertEquals(
            listOf(FakeIdHolder(id = "1", name = "Kyle"), FakeIdHolder(id = "2", name = "John")),
            mockList
        )
    }
}