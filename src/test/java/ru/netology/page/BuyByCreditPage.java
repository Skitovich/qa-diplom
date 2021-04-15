package ru.netology.page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class BuyByCreditPage {

    private final SelenideElement monthCardExpired = $(By.xpath("/span[text()='Номер карты']"));
    private final SelenideElement yearCardExpired = $(By.xpath("span[text()='Год']"));
    private final SelenideElement ownerCard = $(By.xpath("span[text()='Владелец']"));
    private final SelenideElement cvcCvvCard = $(By.xpath("span[text()='CVC/CVV']"));
    private final SelenideElement cardNumber = $(By.xpath("span[text()='Номер карты']"));
    private final SelenideElement buttonBuy = $(By.xpath("span[text()='Купить']"));
    private final SelenideElement buttonBuyByCredit = $(By.xpath("span[text()='Купить в кредит']"));
    private final SelenideElement buttonContinue = $(By.xpath("span[text()='Продолжить']"));
    private final SelenideElement checkBuyByCredit = $(By.xpath("h3[text()='Кредит по данным карты']"));



    public BuyByCreditPage() {
        checkBuyByCredit.shouldBe(Condition.visible);
    }






}
