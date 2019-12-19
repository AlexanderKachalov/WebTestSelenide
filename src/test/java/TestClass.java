import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestClass {
    private SelenideElement form;

    @Test
    void testFormIfValidateAllInputData() {
        openForm();
        inputCityName("Волгоград");
        inputData(10);
        inputNameUser("Василий");
        inputPhoneNumber("+79035783484");
        formClick();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    private void openForm() {
        open("http://localhost:9999");
        form = $(By.className("App_appContainer__3jRx1"));
    }

    private void inputCityName (String city) {
        form.$("[data-test-id=city] input").setValue(city);
    }

    private void inputNameUser (String name) {
        form.$("[data-test-id=name] input").setValue(name);
    }

    private void inputPhoneNumber (String phone) {
        form.$("[data-test-id=phone] input").setValue(phone);
    }

    private void inputData (int plusDays) {
        LocalDate dates = LocalDate.now().plusDays(plusDays);
        String futureDay = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(dates);
//        String futureDay = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(dates);
        form.$("[placeholder='Дата встречи']").sendKeys("\b\b\b\b\b\b\b\b\b\b");
//        form.$("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        form.$("[placeholder='Дата встречи']").setValue(futureDay).click();

    }

    private void formClick() {
        form.$("[data-test-id=agreement]").click();
        form.$$("button").find(exactText("Забронировать")).click();
    }
}

