package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import testng.ContactManagerTestNG;



@Data
public class AddContactPage {
    public AddContactPage(){
        PageFactory.initElements(new AppiumFieldDecorator(ContactManagerTestNG.Driver), this);
    }

    @AndroidFindBy(id = "android:id/title")
    private MobileElement title;

    @AndroidFindBys({@AndroidFindBy(id="title"),@AndroidFindBy(tagName = "title")})
    private MobileElement titleBys;

    @AndroidFindBy(id = "accountSpinner")
    private MobileElement targetAccountField;

    @AndroidFindBy(id = "accountSpinner" + "text1")
    private MobileElement targetAccountText;

    @AndroidFindBy(id = "contactNameEditText")
    private MobileElement contactNameField;

    @AndroidFindBy(id = "contactPhoneEditText")
    private MobileElement contactPhoneField;

    @AndroidFindBy(id = "contactPhoneTypeSpinner")
    private MobileElement contactPhoneDrop;

    @AndroidFindBy(xpath= "//*[@text='Home']")
    private MobileElement contactTypeDropHome;

    @AndroidFindBy(xpath= "//*[@text='Work']")
    private MobileElement contactTypeDropWork;

    @AndroidFindBy(xpath= "//*[@text='Mobile']")
    private MobileElement contactTypeDropMobile;

    @AndroidFindBy(xpath= "//*[@text='Other']")
    private MobileElement contactTypeDropOther;

    @AndroidFindBy(id = "contactEmailEditText")
    private MobileElement contactEmailField;

    @AndroidFindBy(id = "contactEmailTypeSpinner")
    private MobileElement contactEmailDrop;

    @AndroidFindBy(id = "contactSaveButton")
    private MobileElement contactSaveBtn;

    @AndroidFindBy(id ="firstAccountLine")
    private MobileElement targetAccount;

    @AndroidFindBy(id = "select_dialog_listview")
    private MobileElement selectList;
    
    @AndroidFindBy(id = "contactList")
    private MobileElement contactList;





}
