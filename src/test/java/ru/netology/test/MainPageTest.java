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
    void shouldBuyByCredit()  {
        int numRows = getResultSetRowCount();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormSuccessfullyBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1,getResultSetRowCount());
    }

    @Test
    void shouldCanceledBuyByCredit() {
        int numRows = getResultSetRowCount();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormCanceledBuyByCredit();
        String status = Objects.requireNonNull(SqlMethods.getStatus()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1,getResultSetRowCount());
    }
    @Test
    void shouldErrorByFieldNumberOfCard () {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByFieldCardNumber();
    }

    @Test
    void shouldErrorByFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByFieldMonth();
    }

    @Test
    void shouldErrorByFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByFieldYear();
        Assertions.assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.fillFormErrorByFieldOwner();
//        buyByCreditPage.fillForm(generateInvalidMonthCardExpired());
//        buyByCreditPage.fillCreditForm(null, DataHelper.generateCVC(), 21, )
//        Assert.(buyByCreditPage.isAlertMessageByFIeldName("Номер карты"), true)
    }
}
