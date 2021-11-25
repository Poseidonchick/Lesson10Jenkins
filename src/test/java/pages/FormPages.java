package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormPages {
    SelenideElement firstName = $("#firstName");
    SelenideElement lastName = $("#lastName");
    SelenideElement userEmail = $("#userEmail");
    SelenideElement userNumber = $("#userNumber");
    SelenideElement userSubject = $("[id=subjectsInput]");
    SelenideElement userAddress = $("[id=currentAddress]");
    SelenideElement userGender = $("[for=gender-radio-3]");
    SelenideElement userDateOfBirth = $("#dateOfBirthInput");
    SelenideElement userState = $("#state");
    SelenideElement userCity = $("#city");
    SelenideElement userPhoto = $("#uploadPicture");

    ElementsCollection labelBody = $$("tbody tr");
    @Step("Открываем главную страницу")
    public void openWebsite() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Вводим имя")
    public void inputFirstName(String value) {
        firstName.val(value);
    }

    @Step("Вводим фамилию")
    public void inputLastName(String value) {
        lastName.val(value);
    }

    @Step("Вводим имейл")
    public void inputUserEmail(String value) {
        userEmail.val(value);
    }

    @Step("Вводим номер телефона")
    public void inputUserNumber(String value) {
        userNumber.val(value);
    }

    @Step("Выбираем значение из списка")
    public void inputSubject(String value) {
        userSubject.val(value).pressEnter();
    }

    @Step("Вводим адрес")
    public void inputAddress(String value) {
        userAddress.val(value).pressEnter();
    }

    @Step("Выбираем гендер")
    public void chooseGender() {
        userGender.click();
    }

    @Step("Выбираем все хобби")
    public void chooseAllHobbies() {
        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();
    }

    @Step("Выбираем дату рождения")
    public void chooseDayOfBirth(String year, String month, String day) {
        userDateOfBirth.click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day)).click();
    }

    @Step("Заполняем адрес")
    public void chooseUserStateAndCity(String state, String city) {
        userState.scrollIntoView(true);
        userState.click();
        $(byText(state)).click();
        userCity.click();
        $(byText(city)).click();
    }

    @Step("Загружаем фото")
    public void uploadUserPhoto(String filePath) {
        userPhoto.uploadFile(new File(filePath));
    }

    @Step("Нажимаем сабмит")
    public void clickSubmit() {
        $("[id=submit]").click();
    }

    @Step("Проверяем результат")
    public void assertFormLabel(String userFirstName,
                                String userLastName,
                                String userEmail,
                                String userGender,
                                String userNumber,
                                String userSubject,
                                String userCurrentAddress,
                                String userState, String userCity,
                                String yearOfBirth, String monthOfBirth,
                                String dayOfBirth) {
        labelBody.filter(text("Student name")).shouldHave(texts(userFirstName + " " + userLastName));
        labelBody.filter(text("Student Email")).shouldHave(texts(userEmail));
        labelBody.filter(text("Gender")).shouldHave(texts(userGender));
        labelBody.filter(text("Mobile")).shouldHave(texts(userNumber));
        labelBody.filter(text("Date of Birth")).shouldHave(texts(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        labelBody.filter(text("Subjects")).shouldHave(texts(userSubject));
        labelBody.filter(text("Hobbies")).shouldHave(texts("Sports, Reading, Music"));
        labelBody.filter(text("Picture")).shouldHave(texts("harold.jpg"));
        labelBody.filter(text("Address")).shouldHave(texts(userCurrentAddress));
        labelBody.filter(text("State and City")).shouldHave(texts(userState + " " + userCity));
    }
}
