package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GeneralPage {

    private final SelenideElement buttonBuy = $(By.xpath("span[text()='Купить']"));
    private final SelenideElement buttonBuyByCredit = $(By.xpath("//button[2]//span[@class='button__text']"));

    private final SelenideElement buttonContinue = $(By.xpath("//div[4]//span[@class='button__text']"));

    public GeneralPage () {
        buttonContinue.shouldBe(Condition.not(Condition.visible));
    }

    public BuyByCreditPage buyByCredit() {
    buttonBuyByCredit.click();
    buttonContinue.shouldBe(Condition.visible);
        return new BuyByCreditPage();
    }

    public BuyByCardPage buyByCard() {
        buttonBuy.click();
        buttonContinue.shouldBe(Condition.visible);
        return new BuyByCardPage();
    }
}
