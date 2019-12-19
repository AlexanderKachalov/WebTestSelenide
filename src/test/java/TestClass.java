import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestClass {
    private SelenideElement form;
//    LocalDate date = LocalDate.now();

    @Test
    void testFormIfValidateAllInputData() {
        openForm();
        inputCityName("Волгоград");
        inputNameUser("Василий");
        inputPhoneNumber("+79035783484");
        inputData();
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

    private void inputData () {
        LocalDate date = LocalDate.now().plusDays(3);
        String day = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(date);
        LocalDate dates = LocalDate.now().plusDays(5);
        String futureDay = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(dates);
        String currentDay = form.$("[placeholder='Дата встречи']").getValue();
 //       form.$("[data-test-id=date]").click();
        form.$("[placeholder='Дата встречи']").setValue(futureDay);
        System.out.println("currentDay = " + currentDay);
        System.out.println("futureDay = " + futureDay);
    }

    private void formClick() {
        form.$("[data-test-id=agreement]").click();
        form.$$("button").find(exactText("Забронировать")).click();
    }
}

