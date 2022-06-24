import com.example.viewmodelbasicsimple.util.Utility
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UtilityTest {

    @Test
    fun `empty search word return false`(){
        val result = Utility.isValidSearchWord("", null)
        assertFalse(result)
    }

    @Test
    fun `null search word return false`(){
        val result = Utility.isValidSearchWord(null, null)
        assertFalse(result)
    }

    @Test
    fun `less than two digit search word return false`(){
        val result = Utility.isValidSearchWord("A", null)
        assertFalse(result)
    }

    @Test
    fun `search word is non-null, notEmpty and more than two digit return true`(){
        val result = Utility.isValidSearchWord("search word", null)
        assertTrue(result)
    }
}
