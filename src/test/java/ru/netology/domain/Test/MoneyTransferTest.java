package ru.netology.domain.Test;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Data.DataHelper;
import ru.netology.domain.Page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldFirstCardBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldFirstIdCardBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        //verificationPage.validVerify(verificationCode);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getFirst());
    }

    @Test
    void shouldSecondIdCardBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        //verificationPage.validVerify(verificationCode);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getSecond());
    }

    @Test
    void shouldTransferMoneyTest12() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getFirst());
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getSecond());
        dashboardPage.transferMoney(DataHelper.getCardInfo(authInfo).getFirst(), "100", "5559 0000 0000 0002", "5559 0000 0000 0001");
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getFirst());
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getSecond());
    }

    @Test
    void shouldTransferMoneyTest21() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getFirst());
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getSecond());
        dashboardPage.transferMoney(DataHelper.getCardInfo(authInfo).getSecond(), "100", "5559 0000 0000 0001", "5559 0000 0000 0002");
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getFirst());
        dashboardPage.getIdAccountBalance(DataHelper.getCardInfo(authInfo).getSecond());
    }
}
