package ru.netology.domain.Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.domain.Data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");
    //private ElementsCollection topUpAccounts = $$(".list__item button.button"); //кнопка пополнить
    //private SelenideElement reloadAccountPage = $("[data-test-id='action-reload']"); //кнопка обновить счета
    private SelenideElement transferAmount = $("[data-test-id='amount'] input"); //сумма
    private SelenideElement transferFrom = $("[data-test-id='from'] input"); //откуда
    //private SelenideElement transferTo = $("[data-test-id='to'] input"); //куда
    private SelenideElement transferButton = $("[data-test-id='action-transfer'].button"); //пополнить
    //private SelenideElement transferCancel = $("[data-test-id='action-cancel'].button"); //отмена
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() { //контруктор проверки, что личный кабинет виден
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(String cardId) {
        // TODO: перебрать все карты и найти по атрибуту data-test-id
        String text = "0";
        for (SelenideElement card : cards) {
            String textSearch = card.attr("data-test-id");
            if (textSearch.equals(cardId)) {
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

    public int getIdAccountBalance(DataHelper.Card card) {
        return getCardBalance(card.getId());
    }

    public DashboardPage transferMoney(DataHelper.Card cardTo, String cardFrom, int sum) {
        for (SelenideElement card : cards) {
            String text = card.attr("data-test-id");
            if (text.equals(cardTo.getId())) {
                System.out.println("Счёт " + cardTo.getCardNumber() + " найден");
                card.$("button.button").click();
                transferAmount.setValue(String.valueOf(sum));
                transferFrom.setValue(cardFrom);
                transferButton.click();
                break;
            }
        }
        return new DashboardPage();
    }
}
