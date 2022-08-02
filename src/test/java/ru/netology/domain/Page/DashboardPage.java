package ru.netology.domain.Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");
    private ElementsCollection topUpAccounts = $$(".list__item button.button");
    //private SelenideElement firstAccountDescription = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']"); //описание счета 5559 0000 0000 0001
    //private SelenideElement topUpFirstAccount = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button.button") //пополнить счет 5559 0000 0000 0001
    //private SelenideElement secondAccountDescription = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']"); //описание счета 5559 0000 0000 0002
    //private SelenideElement topUpSecondAccount = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button.button"); //пополнить счет 5559 0000 0000 0002
    private SelenideElement reloadAccountPage = $("[data-test-id='action-reload']"); //кнопка обновить счета
    private SelenideElement transferAmount = $("[data-test-id='amount'] input"); //сумма
    private SelenideElement transferFrom = $("[data-test-id='from'] input"); //откуда
    private SelenideElement transferTo = $("[data-test-id='to'] input"); //куда
    private SelenideElement transferButton = $("[data-test-id='action-transfer'].button"); //пополнить
    private SelenideElement transferCancel = $("[data-test-id='action-cancel'].button"); //отмена
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getCardBalance(String id) {
        // TODO: перебрать все карты и найти по атрибуту data-test-id
        String text = "0";
        SelenideElement findElement;
        for(SelenideElement card : cards) {
            text = card.attr("data-test-id");//!!!!!
            if (text.equals(id)) {
                System.out.println("Карта найдена");
                text = card.text();
                break;
            }
        }
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage getFirstAccountBalance() {
        System.out.println("Баланс первой карты: " + getFirstCardBalance());
        return new DashboardPage();
    }

    public DashboardPage getIdAccountBalance(String id) {//добавить вместо id номер карты или последние четыре цифры
        //System.out.println(getCardBalance(id));
        System.out.println("На счету " + id + " денег " + getCardBalance(id));
        return new DashboardPage();
    }

    public DashboardPage transferMoney(String id, String sum, String cardFrom, String cardTo) {//cardTo не используется
        String text = "0";
        SelenideElement findElement;
        for(SelenideElement card : cards) {
            text = card.attr("data-test-id");//!!!!!
            if (text.equals(id)) {
                System.out.println("Аккаунт найден");
                text = card.text();//!!!!!!!!!!!!!!!!!!!!!!!!
                card.$("button.button").click();
                transferAmount.setValue(sum);
                transferFrom.setValue(cardFrom);
                //transferTo.setValue(cardTo);
                transferButton.click();
                break;
            }
        }
        return new DashboardPage();
    }
}
