package com.brodgate.marvelapi

import com.brodgate.marvelapi.repository.CharacterApiDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getCharacters(){
        runBlocking {
            withContext(Dispatchers.IO){
                val data = CharacterApiDataManager().getCharacters()
                assert(data.code==200)
            }
        }
    }
}