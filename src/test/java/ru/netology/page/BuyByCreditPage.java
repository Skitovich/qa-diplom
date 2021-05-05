package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.TimeoutException;
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
    private final SelenideElement errorFieldNumberOfCard = $(By.xpath("//span[text()='Неверный формат']"));
    private final SelenideElement errorFieldMonth = $(By.xpath("//span[text()='Неверно указан срок действия карты']"));
    private final SelenideElement errorFieldYear = $(By.xpath("//span[text()='Истёк срок действия карты']"));
    private final SelenideElement errorFieldOwner = $(By.xpath("//span[text()='Поле обязательно для заполнения']"));


    public BuyByCreditPage() {
        SelenideElement checkBuyByCredit = $(byText("Кредит по данным карты"));
        checkBuyByCredit.shouldBe(Condition.visible);
    }


    public static boolean isAlert(String fieldName, String errorText) {
        try {
            $(By.xpath("//span[text() = '" + fieldName + "']//following::span[text() = '" + errorText + "']"))
                    .shouldBe(Condition.visible, Duration.ofSeconds(10));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void fillFormSuccessfullyBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        popupSuccessfully.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void fillFormCanceledBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("4442"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        popupErrorCanceledByBank.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void fillFormErrorByFieldCardNumber() {
        cardNumber.setValue(DataHelper.getCardNumber("1111"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldNumberOfCard.shouldBe(Condition.visible);
    }

    public void fillFormErrorByEmptyFieldCardNumber() {
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void fillFormByEmptyFieldMonth() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }
    public void fillFormErrorValueByFieldMonth() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldMonth.shouldBe(Condition.visible);
    }

    public void fillFormEmptyFieldYear() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void fillFormErrorValueByFieldYear() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldYear.shouldBe(Condition.visible);
    }

    public void fillFormErrorByEmptyFieldOwner() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }

    public void fillFormErrorByEmptyFieldCvcCvv() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }



//    public void fillCardNum(String cardNum) {
//        $(By.xpath("//span[text()= \"Номер Карты\"]")).setValue(cardNum);
//    }
//
//    public void fillCardNum() {
//        $(By.xpath("//span[text()= \"Номер Карты\"]")).setValue(DataHelper.getCardNumber("4441"));
//    }

    //    public void fillCardNum(String n) {
//        $(By.xpath("//span[text()= \"Номер Карты\"]")).setValue(cardNum))
//    }
    //    public void fillCardNum(String n) {
//        $(By.xpath("//span[text()= \"Номер Карты\"]")).setValue(cardNum))
//    }
    //    public void fillCardNum(String n) {
//        $(By.xpath("//span[text()= \"Номер Карты\"]")).setValue(cardNum))
//    }
    //    public void fillCardNum(String n) {
//        $(By.xpath("//span[text()= \"Номер Карты\"]")).setValue(cardNum))
//    }
//
//
//    public void fillForm(String cardNum, String errorByMonth, String errorByYear, String errorByOwner,
//                         String errorByCvcCvv) {
//        if (cardNum == null) {
//            fillCardNum();
//        } else {
//            fillCardNum(cardNum);
//        }
//        $(By.xpath("//span[text()= " + errorByMonth + "")).shouldBe(Condition.visible);
//        $(By.xpath("//span[text()= " + errorByYear + "")).shouldBe(Condition.visible);
//        $(By.xpath("//span[text()= " + errorByOwner + "")).shouldBe(Condition.visible);
//        $(By.xpath("//span[text()= " + errorByCvcCvv + "")).setValue(DataHelper.generateCVC("999"));
//    }
//

}
