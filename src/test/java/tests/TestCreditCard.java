package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StartPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCreditCard {

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);

    }



    @Test
    void creditingToTheCardIsApproved() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getApprovedCard());
        buyWithCredit.waitNotificationOk();
        assertEquals("APPROVED",SqlHelper.getCreditRequestEntityData());
    }

    @Test
    void creditingToTheCardIsDeclined()  {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getDeclinedCard());
        buyWithCredit.waitNotificationError();
        assertEquals("DECLINED", SqlHelper.getCreditRequestEntityData());
    }

    @Test
    void shouldCreditByCardWithStatusApproved() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCredit.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCredit.waitNotificationError();
    }

    @Test
    void thereShouldBeMessagesOfAnEmptyForm() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getEmptyForm());
        buyWithCredit.getInputInvalid();
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void  shouldInvalidFieldMessageBygoneMonthDeclined() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }



    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApprovedCard() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclinedCard() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApprovedCard() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclinedCard() {
        var startPage = new StartPage();
        var buyWithCredit = startPage.openBuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidDebitCard() {
        var startPage = new StartPage();
        var debitPage = startPage.openBuyWithCredit();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("Ошибка! Банк отказал в проведении операции.", debitPage.getInputInvalid());
    }


    @Test
    void shouldAmountByCardWithDeclined() {
        var startPage = new StartPage();
        var buyWithCard = startPage.openBuyWithCredit();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationError();
        assertEquals("null", SqlHelper.getCreditRequestEntityData());
    }


}