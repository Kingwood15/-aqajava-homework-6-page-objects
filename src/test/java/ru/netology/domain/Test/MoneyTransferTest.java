package ru.netology.domain.Test;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Data.DataHelper;
import ru.netology.domain.Page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldAutorizationPositive() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldLookFirstCardsBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.getIdAccountBalance(DataHelper.getFirstCardInfo(authInfo));
    }

    @Test
    void shouldLookSecondCardsBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.getIdAccountBalance(DataHelper.getSecondCardInfo(authInfo));
    }

    @Test
    void shouldTransferMoneyInCardFirstToCardSecondTest() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.lookAllBalance(authInfo);
        dashboardPage.transferMoney(
                DataHelper.getFirstCardInfo(authInfo),
                DataHelper.getSecondCardInfo(authInfo).getCardNumber(),
                "100");
        dashboardPage.lookAllBalance(authInfo);
    }

    @Test
    void shouldTransferMoneyInCardSecondToCardFirstTest() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.lookAllBalance(authInfo);
        dashboardPage.transferMoney(
                DataHelper.getSecondCardInfo(authInfo),
                DataHelper.getFirstCardInfo(authInfo).getCardNumber(),
                "100");
        dashboardPage.lookAllBalance(authInfo);
    }
}
