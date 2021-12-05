package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.FormPages;

import java.util.Locale;

@Tag("properties")
public class LessonTenJenkins extends TestBase {
    FormPages formPages = new FormPages();
    String userSubject = "Physics";
    String userState = "Uttar Pradesh";
    String userCity = "Agra";
    String filePath = "src/test/resources/harold.jpg";
    String yearOfBirth = "1900";
    String monthOfBirth = "January";
    String dayOfBirth = "01";
    String userGender = "Other";

    Faker faker = new Faker(new Locale("ru"));
    Faker fakerSecond = new Faker(new Locale("en"));
    public String userFirstName = faker.name().firstName(), // задаем имя
            userLastName = faker.name().lastName(), // задаем фамилию
            userNumber = faker.phoneNumber().subscriberNumber(10), // задаем телефонный номер
            userCurrentAddress = faker.address().fullAddress(); // задаем адрес
    public String userEmail = fakerSecond.internet().emailAddress(); // еще один faker на английском, для почты

    @Test
    void successfulSubmitForm() {
        formPages.openWebsite();
        formPages.inputFirstName(userFirstName); // вводим имя
        formPages.inputLastName(userLastName); // вводим фамилию
        formPages.inputUserEmail(userEmail); // вводим почту
        formPages.inputUserNumber(userNumber); // вводим номер телефона
        formPages.inputSubject(userSubject); // вводим значение и выбираем из списка
        formPages.inputAddress(userCurrentAddress); // вводим адрес
        formPages.chooseGender(); // выбираем гендер
        formPages.chooseAllHobbies(); // выбираем радиобаттоны c хобби
        formPages.chooseDayOfBirth(yearOfBirth, monthOfBirth, dayOfBirth); // выбираем год, месяц, день рождения в календаре
        formPages.chooseUserStateAndCity(userState, userCity); // выбираем штат и город из выпадающих списков
        formPages.uploadUserPhoto(filePath);// загружаем файл
        formPages.clickSubmit(); // кликаем на кнопку подтверждения

        formPages.assertFormLabel(
                userFirstName,
                userLastName,
                userEmail,
                userGender,
                userNumber,
                userSubject,
                userCurrentAddress,
                userState,
                userCity,
                yearOfBirth,
                monthOfBirth,
                dayOfBirth
        ); // проверяем форму
    }
}
