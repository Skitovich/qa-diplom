package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BuyByCardPage {
    private final SelenideElement monthCardExpired = $(By.xpath("/span[text()='Номер карты']"));
    private final SelenideElement yearCardExpired = $(By.xpath("span[text()='Год']"));
    private final SelenideElement ownerCard = $(By.xpath("span[text()='Владелец']"));
    private final SelenideElement cvcCvvCard = $(By.xpath("span[text()='CVC/CVV']"));
    private final SelenideElement cardNumber = $(By.xpath("span[text()='Номер карты']"));
    private final SelenideElement buttonContinue = $(By.xpath("span[text()='Продолжить']"));
    private final SelenideElement checkBuyByCard = $(By.xpath("h3[text()='Оплата по карте']"));
    private final SelenideElement popupSuccessfully = $(By.xpath("//div[text()='Операция одобрена Банком.']"));
    private final SelenideElement popupErrorCanceledByBank = $(By.xpath("//div[text()='Ошибка! Банк отказал в проведении операции.']"));

    public BuyByCardPage() {
        checkBuyByCard.shouldBe(Condition.visible);
    }




}
