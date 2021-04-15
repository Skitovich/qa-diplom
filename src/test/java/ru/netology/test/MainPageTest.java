package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.BuyByCreditPage;

import static com.codeborne.selenide.Selenide.open;

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
    void shouldFillFirstCardSuccessfully() {
    val mainPage = new BuyByCreditPage();

    }
}
