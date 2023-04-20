package tests;


import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDebitCard {

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
        SqlHelper.clearData();
    }
    @AfterEach
    public void cleanBase() {
        SqlHelper.clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }




    @Test
    void shouldDebitByCardWithApproved() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("APPROVED", SqlHelper.getPaymentsEntityData());
    }
    @Test
    void shouldDebitByCardWithDecline() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getDeclinedCard());
        buyWithCard.waitNotificationError();
        assertEquals("DECLINED", SqlHelper.getPaymentsEntityData());
    }

    @Test
    void shouldShortNameInOwnerApproved() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCard.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        debitPage.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getEmptyForm());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }
    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }
    @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        var startPage = new StartPage();
        var debitPage =  startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclined() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApproved() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }
    @Test
    void shouldOneCharacterInFieldOwnerApproved() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }
    @Test
    void shouldOneCharacterInFieldOwnerDeclined() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApproved() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclined() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }


    @Test
    void shouldInvalidDebitCard() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("Ошибка! Банк отказал в проведении операции.", debitPage.getInputInvalid());
    }

}