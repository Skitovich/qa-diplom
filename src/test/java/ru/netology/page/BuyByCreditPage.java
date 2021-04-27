package ru.netology.page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BuyByCreditPage {

    private final SelenideElement monthCardExpired = $(By.cssSelector("[placeholder='08']"));
    private final SelenideElement yearCardExpired = $(By.cssSelector("[placeholder='22']"));
    private final SelenideElement ownerCard = $(By.cssSelector("fieldset > div:nth-of-type(3) > span:nth-of-type(1) > span:nth-of-type(1) input:nth-of-type(1)"));
    private final SelenideElement cvcCvvCard = $(By.cssSelector("[placeholder='999']"));
    private final SelenideElement cardNumber = $(By.cssSelector("[placeholder='0000 0000 0000 0000']"));
    private final SelenideElement buttonContinue = $(By.cssSelector("div:nth-of-type(4) .button__text"));
    private final SelenideElement popupSuccessfully = $(By.xpath("//div[text()='Операция одобрена Банком.']"));
    private final SelenideElement popupErrorCanceledByBank = $(By.xpath("//div[text()='Ошибка! Банк отказал в проведении операции.']"));
    private final SelenideElement errorFiledNumberOfCard = $(By.xpath("//span[text()='Неверный формат']"));
    private final SelenideElement errorFiledMonth = $(By.xpath("//span[text()='Неверно указан срок действия карты']"));

    public BuyByCreditPage() {
        SelenideElement checkBuyByCredit = $(byText("Кредит по данным карты"));
        checkBuyByCredit.shouldBe(Condition.visible);
    }

    public void successfullyBuyByCredit () {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC("999"));
        buttonContinue.click();
        popupSuccessfully.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void canceledBuyByCredit () {
        cardNumber.setValue(DataHelper.getCardNumber("4442"));
        yearCardExpired.setValue(DataHelper.generateYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC("999"));
        buttonContinue.click();
        popupErrorCanceledByBank.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void errorByFieldCardNumber () {
        cardNumber.setValue(DataHelper.getCardNumber("1111"));
        yearCardExpired.setValue(DataHelper.generateYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC("999"));
        buttonContinue.click();
        errorFiledNumberOfCard.shouldBe(Condition.visible);
    }

    public void errorByFieldMonth () {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC("999"));
        buttonContinue.click();
        errorFiledMonth.shouldBe(Condition.visible);
    }


}
