
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDate;

public class TestClass {
    private SelenideElement form;
    private LocalDate date = LocalDate.now();
    private int day = date.getDayOfMonth();

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
        form.$("[data-test-id=date]").click();
        form.$(By.xpath("//*[text()='" + (day + 5) + "']")).click();
    }

    private void formClick() {
        form.$("[data-test-id=agreement]").click();
        form.$$("button").find(exactText("Забронировать")).click();
    }
}

