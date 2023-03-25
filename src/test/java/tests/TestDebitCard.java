package tests;

import Page.BuyWithCard;
import Page.StartPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQL;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        SQL.clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @SneakyThrows
    @Test
    void test1() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("APPROVED", SQL.getDebitStatus());
    }

    @SneakyThrows
    @Test
    void test2() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getDeclinedCard());
        buyWithCard.waitNotificationError();
        assertEquals("DECLINED", SQL.getDebitStatus());
    }

    @Test
    void test3() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCard.waitNotificationOk();
    }

    @Test
    void test4() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        debitPage.waitNotificationError();
    }

    @Test
    void test5() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getEmptyForm());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test6() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test7() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test8() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test9() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void test10() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test11() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test12() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void test213() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }


    @Test
    void test14() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }


    @Test
    void test15() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("Ошибка! Банк отказал в проведении операции.", debitPage.getInputInvalid());
    }

    @SneakyThrows
    @Test
    void test16() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("45000", SQL.getAmountStatus());
    }
}