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
        String date = generateDate(5, "dd.MM.yyyy");
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Саратов");
        $(byText("Саратов")).shouldBe(Condition.visible).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Павел Цыганков");
        $("[data-test-id=phone] input").setValue("+79876542345");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $x("//*[@class='notification__content']").shouldHave(Condition.exactText("Встреча успешно забронирована на " + date), Duration.ofMillis(15000));
    }

    @Test
    void happyPathTest2() {
//        Configuration.holdBrowserOpen = true;
        String date = generateDate(7, "dd.MM.yyyy");
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Саратов")).shouldBe(Condition.visible).click();
        $("[data-test-id=date] input").shouldBe(Condition.visible, Duration.ofMillis(1000)).sendKeys(Keys.DOWN, Keys.DOWN, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.ENTER);
        $("[data-test-id=name] input").setValue("Павел Цыганков");
        $("[data-test-id=phone] input").setValue("+79876542345");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $x("//*[@class='notification__content']").shouldHave(Condition.exactText("Встреча успешно забронирована на " + date), Duration.ofMillis(15000));
    }
}
