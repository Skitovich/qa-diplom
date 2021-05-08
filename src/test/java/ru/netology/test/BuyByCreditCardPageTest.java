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
        buyByCreditPage.successfullyBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCount());
    }

    @Test
    void shouldCanceled() { // Тест падает из-за дефекта системы TOdo: add num of issue
        int numRows = getResultSetRowCount();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.canceledBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCount());
    }

    @Test
    void shouldErrorByFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByFieldCardNumberBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCardNumberBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test
    void shouldErrorByFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldOwnerBuyByCredit();
    }

    @Test
    void shouldCheckByEmptyFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorFieldOwnerBuyByCredit("");
        BuyByCreditPage.isAlert("Владелец","Поле обязательно для заполнения");
    }

    @Test
    void shouldErrorByEmptyFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.emptyFieldYearBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByIncorrectValueFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldYearBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.byEmptyFieldMonthBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test
    void shouldErrorByFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldMonthBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldCvvCvv() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCvcCvvBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }


}
