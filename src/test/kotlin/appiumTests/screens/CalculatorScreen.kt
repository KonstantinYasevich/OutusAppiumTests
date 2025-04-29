package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class CalculatorScreen {
    val unitConverterButton: SelenideElement
        get() = element(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.marktka.calculatorYou:id/id_unit_converter\"]"))

    val calculatorButton: SelenideElement
        get() = element(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.marktka.calculatorYou:id/id_calculator\"]"))

    val historyButton: SelenideElement
        get() = element(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.marktka.calculatorYou:id/id_histor\"]"))

    val moreButton: SelenideElement
        get() = element(By.xpath("(//android.widget.ImageView)[4]"))

    val settingsButton: SelenideElement
        get() = element(By.xpath("(//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/title\"])[1]"))

    val aboutButton: SelenideElement
        get() = element(By.xpath("(//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/title\"])[2]"))

    val textCalc: SelenideElement
        get() = element(By.xpath("//android.widget.EditText[@resource-id=\"com.marktka.calculatorYou:id/expressionEditText\"]"))

    val divisionButton: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/divisionButton\"]"))

    val multiplicationButton: SelenideElement
        get() = element(By.xpath("new UiSelector().resourceId(\"com.marktka.calculatorYou:id/multiplicationButton\")"))

    val minusButton: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/minusButton\"]"))

    val plusButton: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/plusButton\"]"))

    val equalsButton: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/equalsButton\"]"))

    val texbackspasetCalc: SelenideElement
        get() = element(By.xpath("//android.widget.ImageButton[@resource-id=\"com.marktka.calculatorYou:id/backspaceButton\"]"))

    val clearButton: SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/clearButton\"]"))

    fun getNumberButton(number: Char): SelenideElement {
        return element(By.xpath("//android.widget.TextView[@resource-id=\"com.marktka.calculatorYou:id/digit${number}Button\"]"))
    }

    @Step("Сложить 2 числа и вернуть результат")
    fun getPlusNumbersResult(number1: Int, number2: Int): String {
        for (digit in number1.toString()) {
            getNumberButton(digit).should(Condition.visible).click()
        }
        plusButton.should(Condition.visible).click()
        for (digit in number2.toString()) {
            getNumberButton(digit).should(Condition.visible).click()
        }
        equalsButton.should(Condition.visible).click()
        return textCalc.text
    }

    @Step("Отнять 2 числа и вернуть результат")
    fun getMinusNumbersResult(number1: Int, number2: Int): String {
        for (digit in number1.toString()) {
            getNumberButton(digit).should(Condition.visible).click()
        }
        minusButton.should(Condition.visible).click()
        for (digit in number2.toString()) {
            getNumberButton(digit).should(Condition.visible).click()
        }
        equalsButton.should(Condition.visible).click()
        return textCalc.text
    }

    @Step("Перейти на страницу настроек")
    fun goToSettings(){
        moreButton.should(Condition.visible).click()
        settingsButton.should(Condition.visible).click()
    }

    @Step("Перейти на страницу О приложении")
    fun goToAbout(){
        moreButton.should(Condition.visible).click()
        aboutButton.should(Condition.visible).click()
    }
}