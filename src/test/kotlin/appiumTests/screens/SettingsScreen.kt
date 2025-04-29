package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class SettingsScreen {
    val backButton: SelenideElement
        get() = element(By.xpath("//android.widget.ImageButton"))

    val settingsTitle: SelenideElement
        get() = element(By.xpath("(//android.widget.FrameLayout)[4]"))

    val themeSectionText: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/themeSelectionText\"]"))

    val themeTitle: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/chooseThemeTitleText\"]"))

    val defaultTheme: SelenideElement
        get() = element(By.xpath("//android.widget.CompoundButton[@resource-id=\"com.marktka.calculatorYou:id/chooseThemeAutoButton\"]"))

    val lightTheme: SelenideElement
        get() = element(By.xpath("//android.widget.CompoundButton[@resource-id=\"com.marktka.calculatorYou:id/chooseThemeLightButton\"]"))

    val darkTheme: SelenideElement
        get() = element(By.xpath("//android.widget.CompoundButton[@resource-id=\"com.marktka.calculatorYou:id/chooseThemeDarkButton\"]"))

    //Полностью описывать экран смысла не вижу, цель у нас не в этом

    @Step("Проверить экран настроек")
    fun checkSettingsScreen(){
        settingsTitle.should(Condition.text("Настройки"))
        themeSectionText.should(Condition.text("Тема"))
        themeTitle.should(Condition.text("Выбрать тему"))
        defaultTheme.should(Condition.visible)
        lightTheme.should(Condition.visible)
        darkTheme.should(Condition.visible)
    }

    @Step("Выбрать светлую тему")
    fun setLightTheme(){
        lightTheme.should(Condition.visible).click()
    }

    @Step("Выбрать темную тему")
    fun setDarkTheme(){
        darkTheme.should(Condition.visible).click()
    }

    @Step("Выбрать тему по умолчанию")
    fun setDefaultTheme(){
        defaultTheme.should(Condition.visible).click()
    }

    @Step("Проверить что выбрана светлая тема")
    fun checkLightTheme(){
        lightTheme.should(attribute("checked", "true"))
    }

    @Step("Проверить что выбрана темная тема")
    fun checkDarkTheme(){
        darkTheme.should(attribute("checked", "true"))
    }

    @Step("Проверить что выбрана дефолтная тема")
    fun checkDefaultTheme(){
        defaultTheme.should(attribute("checked", "true"))
    }
}