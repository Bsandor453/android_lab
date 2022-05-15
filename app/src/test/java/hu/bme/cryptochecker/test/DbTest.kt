package hu.bme.cryptochecker.test

import hu.bme.cryptochecker.mock.data.MockDbModule
import org.junit.Assert
import org.junit.Test

class DbTest {

    private lateinit var mockDb: MockDbModule

    @Test
    fun exampleDbTest() {
        Assert.assertEquals(4, 2 + 2)
    }

}