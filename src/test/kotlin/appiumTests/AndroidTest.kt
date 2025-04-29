package appiumTests

import appiumTests.screens.AboutScreen
import appiumTests.screens.CalculatorScreen
import appiumTests.screens.SettingsScreen
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import kotlin.test.assertEquals


class AndroidTest {

    val calculatorScreen = CalculatorScreen()
    val settingsScreen = SettingsScreen()
    val aboutScreen = AboutScreen()

    @BeforeEach
    fun beforEach() {
        val appPath = "${System.getProperty("user.dir")}/app/app.apk"
        val caps = DesiredCapabilities()
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android")
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554")
        caps.setCapability(MobileCapabilityType.APP, appPath)
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
        caps.setCapability(MobileCapabilityType.FULL_RESET, true);
        caps.setCapability("newCommandTimeout", 300)

        //Тут можно добавить /wd/hub по необходимости
        val driver = AndroidDriver(URL("http://127.0.0.1:4723"), caps)

        WebDriverRunner.setWebDriver(driver)
    }

    @Test
    fun plusTest() {
        // Складываем 0 + 1 и проверяем результат
        assertEquals("1", calculatorScreen.getPlusNumbersResult(0, 1))
        calculatorScreen.clearButton.click()
        // Складываем 1 + 10 и проверяем результат
        assertEquals("11", calculatorScreen.getPlusNumbersResult(1, 10))
        calculatorScreen.clearButton.click()
        // Складываем 9 + 1000 и проверяем результат
        assertEquals("1,009", calculatorScreen.getPlusNumbersResult(9, 1000))
        calculatorScreen.clearButton.click()
        // Складываем 1000 + 10 и проверяем результат
        assertEquals("1,010", calculatorScreen.getPlusNumbersResult(1000, 10))
        calculatorScreen.clearButton.click()
    }

    @Test
    fun plusFailedTest() {
        // Складываем 0 + 0 и проверяем результат
        assertEquals("0", calculatorScreen.getPlusNumbersResult(0, 0))
        calculatorScreen.clearButton.click()
    }

    @Test
    fun minusTest() {
        // Вычитаем 0 - 1 и проверяем результат
        assertEquals("–1", calculatorScreen.getMinusNumbersResult(0, 1))
        calculatorScreen.clearButton.click()
        // Вычитаем 0 - 1000 и проверяем результат
        assertEquals("–1,000", calculatorScreen.getMinusNumbersResult(0, 1000))
        calculatorScreen.clearButton.click()
        // Вычитаем 1000 - 0 и проверяем результат
        assertEquals("1,000", calculatorScreen.getMinusNumbersResult(1000, 0))
        calculatorScreen.clearButton.click()
    }

    @Test
    fun filedMinusTest(){
        // Вычитаем 1000 - 1000 и проверяем результат
        assertEquals("0", calculatorScreen.getMinusNumbersResult(1000, 1000))
        calculatorScreen.clearButton.click()
    }

    @Test
    fun settingsTest(){
        calculatorScreen.goToSettings()
        settingsScreen.setLightTheme()
        settingsScreen.checkLightTheme()
        settingsScreen.setDarkTheme()
        settingsScreen.checkDarkTheme()
        settingsScreen.setDefaultTheme()
        settingsScreen.checkDefaultTheme()
    }

    @Test
    fun checkAboutTest(){
        calculatorScreen.goToAbout()
        aboutScreen.checkScreen()
    }


    @AfterEach
    fun tearDown() {
        Selenide.closeWebDriver()
    }
}