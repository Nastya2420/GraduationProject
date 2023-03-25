package tests;

import Page.BuyWithCard;
import Page.BuyWithCredit;
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


public class TestCreditCard {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);

    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }


    @SneakyThrows
    @Test
    void test1() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getApprovedCard());
        buyWithCredit.waitNotificationOk();
        String actual = SQL.getCreditStatus();
        assertEquals("APPROVED", actual);
    }

    @SneakyThrows
    @Test
    void test2() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getDeclinedCard());
        buyWithCredit.waitNotificationError();
        assertEquals("DECLINED", SQL.getCreditStatus());
    }

    @Test
    void test3() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCredit.waitNotificationOk();
    }

    @Test
    void test4() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        buyWithCredit.waitNotificationError();
    }

    @Test
    void test5() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getEmptyForm());
        buyWithCredit.getInputInvalid();
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test6() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test7() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test8() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test9() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test10() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test11() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test12() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test13() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test14() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test15() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test16() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test17() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("Ошибка! Банк отказал в проведении операции.", debitPage.getInputInvalid());
    }

    @SneakyThrows
    @Test
    void test18() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationError();
        assertEquals("null", SQL.getAmountStatus());
    }
}