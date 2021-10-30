package mail.yandex;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage {

    public WebDriver driver;
    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    //Определение поля фильтрации сообщений
    @FindBy(xpath = "//input[@class='textinput__control']")
    private WebElement filterMail;

    //Определение кнопки фильтра "Папки"
    @FindBy(xpath = "//span[contains (text(),'Папки')]//ancestor::button")
    private WebElement foldersMail;

    //Определение кнопки "Входящие" в фильтрации поиска
    @FindBy(xpath = "//span[@class = 'menu__text' and text()='Входящие']")
    private WebElement inputMail;

    //Определение поля, в котором содержится инфомация по количеству сообщений
    @FindBy(xpath = "//span[@class = 'mail-MessagesSearchInfo-Title_misc nb-with-xs-left-gap']")
    private WebElement countMale;

    //Определение кнопки "Написать" для создания нового сообщения
    @FindBy(xpath = "//a[@class='mail-ComposeButton js-main-action-compose']")
    private WebElement newMail;

    //Определение поля для ввода адресата
    @FindBy(xpath = "//div[@class = 'MultipleAddressesDesktop ComposeRecipients-MultipleAddressField ComposeRecipients-ToField tst-field-to']/div/div/div/div")
    private WebElement addressMail;

    //Определение поля для ввода темы сообщения
    @FindBy(xpath = "//input[@class = 'composeTextField ComposeSubject-TextField']")
    private WebElement themeMail;

    //Определение поля ввода сообщения
    @FindBy(xpath = "//div[@class = 'cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr cke_htmlplaceholder']")
    private WebElement textMail;

    //Определение поля написания письма
    @FindBy(xpath = "//div//[@class = 'composeReact__scrollable-content']")
    private WebElement writeMail;

    //Определение кнопки "Отправить"
    @FindBy(xpath = "//button[@class = 'Button2 Button2_pin_circle-circle Button2_view_default Button2_size_l']")
    private WebElement sendMail;

    //Определение кнопки "Вернуться во входящие"
    @FindBy(xpath = "//a[@class = 'control link link_theme_normal ComposeDoneScreen-Link']")
    private WebElement returnBack;

    //Метод для получения количества сообщений
    public String getSizeMail(){
        String sizemail = countMale.getText();
        return sizemail;
    }

    @Step
    //Ввод значения для фильтрации
    public void fiterByTheme() {
        filterMail.sendKeys("Simbirsoft theme");
        filterMail.submit();
    }

    @Step
    //Сортировка писем по "Входящим"
    public void sortMail(){
        foldersMail.click();
        inputMail.click();
    }

    @Step
    //Нажатие кнопки "Написать
    public void clickNew() {
        newMail.click();
    }

    @Step
    //Ввод эмэйл адресата
    public void inputAddress() {
        addressMail.sendKeys("test013ivanov@yandex.ru");
    }

    @Step
    //Ввод темы сообщения
    public void inputeTheme(){
        themeMail.sendKeys("Simbirsoft theme");
    }

    @Step
    //Ввод нового сообщения
    public void message(){
        textMail.sendKeys("Найдено ", getSizeMail());
    }

    @Step
    //Нажатие кнопки отправить
    public void sendMessage(){
        sendMail.click();
    }

    // countsizeBefore = Integer.parseInt(getSizeMail().replaceAll("[^0-9]", ""));

    @Step
    //Вернуться во входящие
    public void setReturnBack() {
        returnBack.click();
    }


}

