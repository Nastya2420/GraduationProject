package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StartPage;

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
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getApprovedCard());
        buyWithCredit.waitNotificationOk();
        String actual = SqlHelper.getCreditStatus();
        assertEquals("APPROVED", actual);
    }

    @SneakyThrows
    @Test
    void test2() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getDeclinedCard());
        buyWithCredit.waitNotificationError();
        assertEquals("DECLINED", SqlHelper.getCreditStatus());
    }

    @Test
    void test3() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCredit.waitNotificationOk();
    }

    @Test
    void test4() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        buyWithCredit.waitNotificationError();
    }

    @Test
    void test5() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getEmptyForm());
        buyWithCredit.getInputInvalid();
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test6() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test7() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test8() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test9() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test10() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test11() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test12() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test13() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test14() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void test15() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test16() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void test17() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCredit();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("Ошибка! Банк отказал в проведении операции.", debitPage.getInputInvalid());
    }

    @SneakyThrows
    @Test
    void test18() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCredit();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationError();
        assertEquals("null", SqlHelper.getAmountStatus());
    }

    @Test
    void test19() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getApprovedCard());
        buyWithCredit.waitNotificationError();
        assertEquals("APPROVED", SqlHelper.getAmountStatus());

    }
}