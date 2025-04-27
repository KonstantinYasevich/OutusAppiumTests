import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.qameta.allure.Allure
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

class MySteps {
    companion object {
        val client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }

        @Step("Получить метод {method} для числа {number}")
        suspend fun fetchNumberTrivia(method: String, number: Any): HttpResponse {
            val url = "http://numbersapi.com/$number/$method"
            val response: HttpResponse = client.get(url)
            return response
        }

        @Step("Проверить 200 статус ответа")
        suspend fun checkOkStatus(response: HttpResponse) {
            assertTrue(response.status.isSuccess(), "Статус ответа не успешный: ${response.status}")
        }

        @Step("Проверить 400 статус ответа")
        suspend fun checkErrorStatus(response: HttpResponse) {
            assertFalse(response.status.isSuccess(), "Статус спешный: ${response.status}")
        }

        @Step("Проверить, что ответ содержит число {number}")
        fun verifyResponseContainsNumber(response: String, number: Int) {
            assertTrue(response.contains(number.toString()), "Ответ не содержит число $number")
        }

        @Step("Прикрепить ответ в отчет Allure")
        fun attachResponseToAllure(name: String, response: String) {
            Allure.addAttachment(name, "text/plain", response)
        }
    }
}