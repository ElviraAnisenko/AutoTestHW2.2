package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CallBackTest {

    @BeforeEach
    public void setupTest() {
        open("http://localhost:9999");

    }


    //Задание 1;
    @Test
    void shouldTestIfValidData() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(choiceDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfCityNotFromList() {
        $("[data-test-id='city'] input").setValue("Ахметлей");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfCityInLatin() {
        $("[data-test-id='city'] input").setValue("Moscow");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        ;
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfCityInSpecialCharacters() {

        $("[data-test-id='city'] input").setValue("@#$%");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfNameCityInNumber() {

        $("[data-test-id='city'] input").setValue("111111");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestIfNameCityEmpty() {
        $("[data-test-id='city'] input").setValue("");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestIfDatePlus3Days() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(choiceDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfDatePlus2Days() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldTestIfLastDate() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldTestIfDateWithoutPoint() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime choiceDate = LocalDateTime.now().plusDays(3);
        String formattedDate = choiceDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formattedDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        String validFormattedDate = choiceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(byText(validFormattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfDateWithSlash() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDateTime choiceDate = LocalDateTime.now().plusDays(3);
        String formattedDate = choiceDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formattedDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        String validFormattedDate = choiceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(byText(validFormattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldTestIfDateInFormatDdMmYy() {

        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestIfDateInLetters() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue("Январь");
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestIfDateInSpecialCharacters() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue("@#%");
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestIfDateEmpty() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue("");
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldTestWithValidFirstSurnameSecondName() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(choiceDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestWithValidHyphenatedName() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга-Мария");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(choiceDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestWithNameInLatin() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Petrova Olga");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithNameInNumericFormat() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("111111111");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithNameWithApostrophe() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Д'артаньян Иванов");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithNameWithSpecialCharacters() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ив@н Иванов");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldTestWithNamAndWithoutSurname() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Эльвира");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldTestWithEmptyName() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestWithEmptyCheckbox() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }

    @Test
    void shouldTestWithEmptyPhone() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }


    @Test
    void shouldTestWithSpacesInPhoneNumber() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+7 998 877 88 99");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestWithParenthesisInPhoneNumber() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+7(998)8778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestWithDashInPhoneNumber() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+7-998-877-88-99");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-d='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestWithoutPlusInPhoneNumber() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestIf12PhoneNumber() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+799888778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestIf10PhoneNumber() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+7998887788");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void shouldTestIfPhoneNumberInLatin() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+qqqqqqqqqqq");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestIfPhoneNumberWithSpecialCharacters() {
        $("[data-test-id='city'] input").setValue("Москва");
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Петрова Ольга");
        $("[data-test-id='phone'] input").setValue("+@#$%^&*@#$%");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    //Задание 2

    @Test
    void shouldShowListIf2SymbolOfNameCity() {
        $("[data-test-id='city'] input").setValue("Мо");
        $(withText("Москва")).click();
        String choiceDate = LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(choiceDate);
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(choiceDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        String value = $("[data-test-id='city'] input").getValue();
        value.equals("Москва");
    }

    @Test
    void shouldChoiceDateFromCalenderOpenByClick() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").click();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime choiceDate = startDate.plusDays(6);
        Calendar calendar = Calendar.getInstance();
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = startDate.getDayOfMonth();
        if (maxDay - currentDay < 7) {
            $(".popup_target_anchor").find(".calendar__arrow_direction_right", 1).click();
        }
        $(byText(String.valueOf(choiceDate.getDayOfMonth()))).click();
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        String formattedDate = choiceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(byText(formattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }


    @Test
    void shouldChoiceDateFromCalenderOpenByClickIcon() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date']").find(".icon_name_calendar").click();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime choiceDate = startDate.plusDays(6);
        Calendar calendar = Calendar.getInstance();
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = startDate.getDayOfMonth();
        if (maxDay - currentDay < 7) {
            $(".popup_target_anchor").find(".calendar__arrow_direction_right", 1).click();
        }
        $(byText(String.valueOf(choiceDate.getDayOfMonth()))).click();
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        String formattedDate = choiceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(byText(formattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }


    @Test
    void shouldChoiceDateForward7DaysFromCalender() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date']").find(".icon_name_calendar").click();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime choiceDate = LocalDateTime.now().plusDays(7);
        Calendar calendar = Calendar.getInstance();
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = startDate.getDayOfMonth();
        if (maxDay - currentDay < 7) {
            $(".popup_target_anchor").find(".calendar__arrow_direction_right", 1).click();
        }
        $(byText(String.valueOf(choiceDate.getDayOfMonth()))).click();
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        String formattedDate = choiceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(byText(formattedDate)).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldChoiceDateForward8DaysFromCalender() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date']").find(".icon_name_calendar").click();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime choiceDate = startDate.now().plusDays(8);
        Calendar calendar = Calendar.getInstance();
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = startDate.getDayOfMonth();
        if (maxDay - currentDay < 7) {
            $(".popup_target_anchor").find(".calendar__arrow_direction_right", 1).click();
        }
        $(byText(String.valueOf(choiceDate.getDayOfMonth()))).click();
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }


    @Test
    void shouldFlippingCalendarForwardIfNoNecessaryDay() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date']").find(".icon_name_calendar").click();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime choiceDate = startDate.plusDays(25);
        Calendar calendar = Calendar.getInstance();
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = startDate.getDayOfMonth();
        if (maxDay - currentDay < 25) {
            $(".popup_target_anchor").find(".calendar__arrow_direction_right", 1).click();
        }
        $(byText(String.valueOf(choiceDate.getDayOfMonth()))).click();
        $("[data-test-id='name'] input").setValue("Ольга Петрова");
        $("[data-test-id='phone'] input").setValue("+79988778899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        String formattedDate = choiceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String value = $("[data-test-id='date'] input").getValue();
        value.equals(formattedDate);
        //$("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));


    }


}




