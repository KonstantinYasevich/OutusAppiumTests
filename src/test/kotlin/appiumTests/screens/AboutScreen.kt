package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class AboutScreen {
    val screenTitle: SelenideElement
        get() = element(By.xpath("(//android.widget.FrameLayout)[4]"))

    val helpText: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/helpMeSelectionText\"]"))

    val rateTitle: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/rateTitleText\"]"))

    val rateText: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/rateText\"]"))

    val rateImage: SelenideElement
        get() = element(By.xpath("//android.widget.ImageView[@resource-id=\"com.marktka.calculatorYou:id/rateImage\"]"))

    //ИТД, описывать весь экран не вижу смысла

    @Step("Проверить экран О приложении")

    fun checkScreen(){
        screenTitle.should(Condition.visible, Condition.attribute("content-desc", "О приложении"))
        helpText.should(Condition.visible, Condition.text("Помощь"))
        rateTitle.should(Condition.visible, Condition.text("Оценить"))
        rateText.should(Condition.visible, Condition.text("Написать отзыв о приложении"))
        rateImage.should(Condition.visible)
    }

}