package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.BuyByCreditPage;
import ru.netology.page.GeneralPage;
import ru.netology.sql.SqlMethods;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.sql.SqlMethods.getResultSetRowCountForCard;
import static ru.netology.sql.SqlMethods.getResultSetRowCountForCredit;

public class BuyByCardTest {
    @BeforeAll
    static void setUp() {
        Configuration.browser = "firefox";
        Configuration.startMaximized = true;
    }

    @BeforeEach
    void openSetUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyByCreditCard() {
        int numRows = getResultSetRowCountForCard();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.successfullyBuyByCard();
        String status = Objects.requireNonNull(SqlMethods.getStatusForCard()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCard());
    }

    @Test
    void shouldCanceled() { // Тест падает из-за дефекта системы TOdo: add num of issue
        int numRows = getResultSetRowCountForCard();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.canceledBuyByCard();
        String status = Objects.requireNonNull(SqlMethods.getStatusForCard()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCard());
    }

    @Test
    void shouldErrorByFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.errorByFieldCardNumberBuyByCard();
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.errorByEmptyFieldCardNumberBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test
    void shouldErrorByFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.errorByEmptyFieldOwnerBuyByCard();
    }

    @Test
    void shouldCheckByEmptyFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.errorFieldOwnerBuyByCard("");
        BuyByCreditPage.isAlert("Владелец","Поле обязательно для заполнения");
    }

    @Test
    void shouldErrorByEmptyFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.emptyFieldYearBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByIncorrectValueFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.errorValueByFieldYearBuyByCard();
    }

    @Test
    void shouldErrorByEmptyFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.byEmptyFieldMonthBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test
    void shouldErrorByFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.errorValueByFieldMonthBuyByCard();
    }

    @Test
    void shouldErrorByEmptyFieldCvvCvv() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.errorByEmptyFieldCvcCvvBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }

}
