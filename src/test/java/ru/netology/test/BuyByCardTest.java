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
    void shouldBuyByCreditCardTest() {
        int numRows = getResultSetRowCountForCard();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.successfullyBuyByCard();
        String status = Objects.requireNonNull(SqlMethods.getStatusForCard()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCard());
    }

    @Test
    void shouldCanceledByBankTest() {
        int numRows = getResultSetRowCountForCard();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.canceledBuyByCard();
        String status = Objects.requireNonNull(SqlMethods.getStatusForCard()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCard());
    }

    @Test
    void shouldErrorByFieldNumberOfCardTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByFieldCardNumberBuyByCard();
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCardTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByEmptyFieldCardNumberBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test
    void shouldErrorByFieldOwnerTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByEmptyFieldOwnerBuyByCard("");
    }

    @Test
    void shouldCheckByEmptyFieldOwnerTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorFieldOwnerBuyByCard("");
        BuyByCreditPage.isAlert("Владелец", "Поле обязательно для заполнения");
    }

    @Test
    void shouldErrorByEmptyFieldYearTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.emptyFieldYearBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByIncorrectValueFieldYearTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorValueByFieldYearBuyByCard();
    }

    @Test
    void shouldErrorByEmptyFieldMonthTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.byEmptyFieldMonthBuyByCard("");
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test
    void shouldErrorByFieldMonthTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorValueByFieldMonthBuyByCard();
    }

    @Test
    void shouldErrorByEmptyFieldCvvCvvTest() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByEmptyFieldCvcCvvBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }

}
