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
import static ru.netology.sql.SqlMethods.getResultSetRowCountForCredit;

public class BuyByCreditCardPageTest {

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
        int numRows = getResultSetRowCountForCredit();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.successfullyBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatusForCredit()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCredit());
    }

    @Test
    void shouldCanceledByBankTest() { // Баг репорт составлен - https://github.com/Skitovich/qa-diplom/issues/1
        int numRows = getResultSetRowCountForCredit();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.canceledBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatusForCredit()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCredit());
    }

    @Test
    void shouldErrorByFieldNumberOfCardTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByFieldCardNumberBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCardTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCardNumberBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test
    void shouldErrorByFieldOwnerTest() {// Баг репорт составлен https://github.com/Skitovich/qa-diplom/issues/2
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldOwnerBuyByCredit("00");
    }

    @Test
    void shouldCheckByEmptyFieldOwnerTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorFieldOwnerBuyByCredit("");
        BuyByCreditPage.isAlert("Владелец", "Поле обязательно для заполнения");
    }

    @Test
    void shouldErrorByEmptyFieldYearTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.emptyFieldYearBuyByCredit("");
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByIncorrectValueFieldYearTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldYearBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldMonthTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.byEmptyFieldMonthBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test
    void shouldErrorByFieldMonthTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldMonthBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldCvvCvvTest() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCvcCvvBuyByCredit("");
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }


}
