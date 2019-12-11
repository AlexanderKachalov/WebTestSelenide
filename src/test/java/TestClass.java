
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestClass {
    SelenideElement form;

    @Test
    void testFormIfValidateAllInputData() {
        inputData("Волгоград", "25", "Василий", "+79035783484");
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }


    private void inputData (String city, String date, String name, String phone) {
        open("http://localhost:9999");
        form = $(By.className("App_appContainer__3jRx1"));
        form.$("[data-test-id=city] input").setValue(city);
        form.$("[data-test-id=date]").click();
        form.$(By.xpath("//*[text()='" + date + "']")).click();
        form.$("[data-test-id=name] input").setValue(name);
        form.$("[data-test-id=phone] input").setValue(phone);
        form.$("[data-test-id=agreement]").click();
        form.$$("button").find(exactText("Забронировать")).click();
    }
}

