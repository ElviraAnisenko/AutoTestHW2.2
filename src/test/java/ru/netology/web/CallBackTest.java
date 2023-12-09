package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CallBackTest {

    //Задание 1
    @Test
    void shouldTestIfValidData() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(formattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfCityNotFromList() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Ахметлей");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfCityInLatin() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Moscow");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfCityInSpecialCharacters() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("@#$%");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfNameCityInNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("111111");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfNameCityEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestIfDatePlus3Days() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(3);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(formattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfDatePlus2Days() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(2);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldTestIfLastDate() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().minusDays(1);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").sendKeys(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldTestIfDateWithoutPoint() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(3);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        String ValidFormattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(byText(ValidFormattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfDateWithSlash() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(3);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        String ValidFormattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(byText(ValidFormattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfDateInFormatDdMmYy() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(3);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestIfDateInLetters() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue("Январь");
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestIfDateInSpecialCharacters() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue("@#%");
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestIfDateEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue("");
        form.$("[data-test-id='name'] input").setValue("Ольга Петрова");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestWithValidFirstSurnameSecondName() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(formattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestWithValidHyphenatedName() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга-Мария");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(formattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestWithNameInLatin() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Petrova Olga");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithNameInNumericFormat() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("111111111");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithNameWithApostrophe() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Д'артаньян Иванов");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithNameWithSpecialCharacters() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Ив@н Иванов");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldTestWithNamAndWithoutSurname() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Эльвира");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithEmptyName() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestWithEmptyCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+79988778899");
        form.$(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }

    @Test
    void shouldTestWithEmptyPhone() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }


    @Test
    void shouldTestWithSpacesInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+7 998 877 88 99");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestWithParenthesisInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+7(998)8778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestWithDashInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+7-998-877-88-99");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestWithoutPlusInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestIf12PhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+799888778899");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestIf10PhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+7998887788");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestIfPhoneNumberInLatin() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+qqqqqqqqqqq");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestIfPhoneNumberWithSpecialCharacters() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime futureDate = LocalDateTime.now().plusDays(4);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(formattedDate);
        form.$("[data-test-id='name'] input").setValue("Петрова Ольга");
        form.$("[data-test-id='phone'] input").setValue("+@#$%^&*@#$%");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    //Задание 2


}

