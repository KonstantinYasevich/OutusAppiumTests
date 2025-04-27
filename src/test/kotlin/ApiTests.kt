import MySteps.Companion.attachResponseToAllure
import MySteps.Companion.checkErrorStatus
import MySteps.Companion.checkOkStatus
import MySteps.Companion.fetchNumberTrivia
import MySteps.Companion.verifyResponseContainsNumber
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import Utils.Companion.generateRandomYear
import Utils.Companion.getRandomInt
import Utils.Companion.getRandomStr
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ApiTests {

    @Test
    @DisplayName("Проверка trivia для числа")
    fun testNumberTrivia() {
        runBlocking {
            val method = "trivia"
            val number = getRandomInt()
            val response = fetchNumberTrivia(method, number)
            checkOkStatus(response)
            verifyResponseContainsNumber(response.bodyAsText(), number)
            attachResponseToAllure(method, response.bodyAsText())
            println(response.bodyAsText())
        }
    }

    @Test
    @DisplayName("Проверка math для числа")
    fun testNumberMath() {
        runBlocking {
            val method = "math"
            val number = getRandomInt()
            val response = fetchNumberTrivia(method, number)
            checkOkStatus(response)
            verifyResponseContainsNumber(response.bodyAsText(), number)
            attachResponseToAllure(method, response.bodyAsText())
            println(response.bodyAsText())
        }
    }

    @Test
    @DisplayName("Проверка trivia для не числа")
    fun testNotNumberTrivia() {
        runBlocking {
            val method = "trivia"
            val notNumber = getRandomStr()
            val response = fetchNumberTrivia(method, notNumber)
            checkErrorStatus(response)
            attachResponseToAllure(method, response.status.toString())
            println(response.status.value)
        }
    }

    @Test
    @DisplayName("Проверка math для не числа")
    fun testNotNumberMath() {
        runBlocking {
            val method = "match"
            val notNumber = getRandomStr()
            val response = fetchNumberTrivia(method, notNumber)
            checkErrorStatus(response)
            attachResponseToAllure(method, response.status.toString())
            println(response.status.value)
        }
    }

    @Test
    @DisplayName("Проверка year для рандомного числа")
    fun testNumberYear() {
        runBlocking {
            runBlocking {
                val method = "year"
                val number = generateRandomYear();
                val response = fetchNumberTrivia(method, number)
                checkOkStatus(response)
                verifyResponseContainsNumber(response.bodyAsText(), number)
                attachResponseToAllure(method, response.bodyAsText())
                println(response.bodyAsText())
            }
        }
    }

    @Test
    @DisplayName("Проверка year для не числа")
    fun testNotNumberYear() {
        runBlocking {
            val method = "year"
            val notNumber = getRandomStr()
            val response = fetchNumberTrivia(method, notNumber)
            checkErrorStatus(response)
            attachResponseToAllure(method, response.status.toString())
            println(response.status.value)
        }
    }

    @Test
    @DisplayName("Проверка несуществующего метода для числа")
    fun testRandomMethod() {
        runBlocking {
            val method = getRandomStr()
            val notNumber = getRandomInt()
            val response = fetchNumberTrivia(method, notNumber)
            checkErrorStatus(response)
            attachResponseToAllure(method, response.status.toString())
            println(response.status.value)
        }
    }

    @Test
    @DisplayName("Падающий тест")
    fun failedTest() {
        runBlocking {
            val method = "year"
            val notNumber = generateRandomYear()
            val response = fetchNumberTrivia(method, notNumber)
            MySteps.checkErrorStatus(response)
            MySteps.attachResponseToAllure(method, response.status.toString())
            println(response.status.value)
        }
    }
}