package tests;


import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDebitCard {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @SneakyThrows
    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
        SqlHelper.clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @SneakyThrows
    @Test
    void test1() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("APPROVED", SqlHelper.getAmountStatus());
    }

    @SneakyThrows
    @Test
    void test2() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getDeclinedCard());
        buyWithCard.waitNotificationError();
        assertEquals("DECLINED", SqlHelper.getAmountStatus());
    }

    @Test
    void test3() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCard.waitNotificationOk();
    }

    @Test
    void test4() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        debitPage.waitNotificationError();
    }

    @Test
    void test5() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getEmptyForm());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test6() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test7() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test8() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test9() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test10() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test11() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test12() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test13() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }


    @Test
    void test14() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }


    @Test
    void test15() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("Ошибка! Банк отказал в проведении операции.", debitPage.getInputInvalid());
    }

    @SneakyThrows
    @Test
    void test16() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("45000",SqlHelper.getAmountStatus());
    }
}