package ru.netology.domain.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Data.DataHelper;
import ru.netology.domain.Page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyToFirstCardFromSecondCardTest() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int beforeBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int beforeBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        dashboardPage.transferMoney(
                DataHelper.getFirstCardInfo(authInfo),
                DataHelper.getSecondCardInfo(authInfo).getCardNumber(),
                100);
        int afterBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int afterBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        Assertions.assertEquals(beforeBalanceFirstCard + 100, afterBalanceFirstCard);
        Assertions.assertEquals(beforeBalanceSecondCard - 100, afterBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardTest() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int beforeBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int beforeBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        dashboardPage.transferMoney(
                DataHelper.getSecondCardInfo(authInfo),
                DataHelper.getFirstCardInfo(authInfo).getCardNumber(),
                100);
        int afterBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int afterBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        Assertions.assertEquals(beforeBalanceFirstCard - 100, afterBalanceFirstCard);
        Assertions.assertEquals(beforeBalanceSecondCard + 100, afterBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardZeroTest() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int beforeBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int beforeBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        dashboardPage.transferMoney(
                DataHelper.getSecondCardInfo(authInfo),
                DataHelper.getFirstCardInfo(authInfo).getCardNumber(),
                0);
        int afterBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int afterBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        Assertions.assertEquals(beforeBalanceFirstCard, afterBalanceFirstCard);
        Assertions.assertEquals(beforeBalanceSecondCard, afterBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardNegativeSumTest() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int beforeBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int beforeBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        dashboardPage.transferMoney(
                DataHelper.getSecondCardInfo(authInfo),
                DataHelper.getFirstCardInfo(authInfo).getCardNumber(),
                -100);
        int afterBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int afterBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        Assertions.assertEquals(beforeBalanceFirstCard - 100, afterBalanceFirstCard);
        Assertions.assertEquals(beforeBalanceSecondCard + 100, afterBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardHugeSumTest() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int beforeBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int beforeBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        dashboardPage.transferMoney(
                DataHelper.getSecondCardInfo(authInfo),
                DataHelper.getFirstCardInfo(authInfo).getCardNumber(),
                15000);
        int afterBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int afterBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        Assertions.assertEquals(beforeBalanceFirstCard, afterBalanceFirstCard);
        Assertions.assertEquals(beforeBalanceSecondCard, afterBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardHugeSum2Test() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int beforeBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int beforeBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        dashboardPage.transferMoney(
                DataHelper.getFirstCardInfo(authInfo),
                DataHelper.getSecondCardInfo(authInfo).getCardNumber(),
                15000);
        int afterBalanceFirstCard = dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
        int afterBalanceSecondCard = dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
        Assertions.assertEquals(beforeBalanceFirstCard, afterBalanceFirstCard);
        Assertions.assertEquals(beforeBalanceSecondCard, afterBalanceSecondCard);
    }
}
