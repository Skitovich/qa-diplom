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
import static ru.netology.sql.SqlMethods.getResultSetRowCount;

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
    void shouldBuyByCreditCard() {
        int numRows = getResultSetRowCount();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormSuccessfullyBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCount());
    }

    @Test
    void shouldCanceledBuyByCreditCard() { // Тест падает из-за дефекта системы TOdo: add num of issue
        int numRows = getResultSetRowCount();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormCanceledBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCount());
    }

    @Test
    void shouldErrorByFieldNumberOfCard_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByFieldCardNumberBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCard_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByEmptyFieldCardNumberBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test
    void shouldErrorByFieldOwner_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.ErrorByEmptyFieldOwnerBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldYear_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormEmptyFieldYearBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByIncorrectValueFieldYear_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorValueByFieldYearBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldMonth_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormByEmptyFieldMonthBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test
    void shouldErrorByFieldMonth_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorValueByFieldMonthBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldCvvCvv_BuyByCreditCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.ErrorByEmptyFieldCvcCvvBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }


}
