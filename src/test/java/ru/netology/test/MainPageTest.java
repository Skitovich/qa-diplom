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

public class MainPageTest {

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
    void shouldBuyByCredit() {
        int numRows = getResultSetRowCount();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormSuccessfullyBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCount());
    }

    @Test
    void shouldCanceledBuyByCredit() {
        int numRows = getResultSetRowCount();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormCanceledBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCount());
    }

    @Test
    void shouldErrorByFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByFieldCardNumber();
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByEmptyFieldCardNumber();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test
    void shouldErrorByFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByEmptyFieldOwner();
    }

    // Тест на ошибку под полем, по не заполненному полю "Год". Ошибка "Неверный формат"
    @Test
    void shouldErrorByEmptyFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormEmptyFieldYear();
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByIncorrectValueFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorValueByFieldYear();
    }

    @Test
    void shouldErrorByEmptyFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormByEmptyFieldMonth();
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }


    @Test
    void shouldErrorByFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorValueByFieldMonth();
    }
}
