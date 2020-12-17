package edu.bth.ma.passthebomb.client

import edu.bth.ma.passthebomb.client.utils.RoundRobinScheduler
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RoundRobinSchedulerTest {
    @Test
    fun linearSchedulingTest() {
        val scheduler = RoundRobinScheduler(3, false, {})
        for(i in 0..8){
            assertEquals((i+1)%3, scheduler.peekNextElement())
            assertEquals((i+1)%3, scheduler.peekNextElement())
            assertEquals((i+1)%3, scheduler.nextElement())
        }
    }

    @Test
    fun randomSchedulingTest(){
        val scheduler = RoundRobinScheduler(3, true, {})
        val counts = ArrayList<Int>(listOf(0,0,0))
        for(i in 0..8){
            counts[scheduler.currentElement]++
            val peeked = scheduler.peekNextElement()
            assertEquals(peeked, scheduler.peekNextElement())
            val actual = scheduler.nextElement()
            assertEquals(peeked,actual)
            if(i%3==2){
                assertNotEquals(scheduler.currentElement, scheduler.peekNextElement())
            }
        }
        assertEquals(3, counts[0])
        assertEquals(3, counts[1])
        assertEquals(3, counts[2])
    }
}