import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class deliveryTest {

    String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void happyPathTest() {
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id=name] input").setValue("Павел Цыганков");
        $("[data-test-id=phone] input").setValue("+79876542345");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @Test
    void happyPathTest2() {
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ка");
        $("[data-test-id=city] input").shouldBe(Condition.visible, Duration.ofMillis(1000)).sendKeys(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER);
        $("[data-test-id=date] input").shouldBe(Condition.visible, Duration.ofMillis(1000)).sendKeys(Keys.DOWN, Keys.DOWN, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.ENTER);
        $("[data-test-id=name] input").setValue("Павел Цыганков");
        $("[data-test-id=phone] input").setValue("+79876542345");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
    }
}
