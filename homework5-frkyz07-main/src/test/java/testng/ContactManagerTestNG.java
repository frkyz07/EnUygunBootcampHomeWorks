package testng;


import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import devices.DeviceFarm;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.core.annotation.Order;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.HomePage;
import utility.DeviceFarmUtility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ContactManagerTestNG  {

    // needed variables added
    public static AppiumDriver<?> Driver;
    HomePage homePage;
    AddContactPage addContactPage;
    String oreo;
    DesiredCapabilities capabilities;
    String specialCharacters = "&%+^^";
    String myMail = "frkyz07.13@gmail.com";
    Faker faker = new Faker();


   // in here I create a method that takes the element text and finds the element with that text in the
    // homepage.
    public MobileElement elementFinder(String elementName){
       return (MobileElement) ContactManagerTestNG.Driver
               .findElement(By.xpath("//android.widget.TextView[@text='"+elementName+"']"));
    }

    // in here we are giving our apk path
    public ContactManagerTestNG() {
        oreo = DeviceFarm.ANDROID_OREO.path;
    }
    @BeforeClass
    public void setup() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities = DeviceFarmUtility.pathToDesiredCapabilitites(this.oreo);
        capabilities.setCapability("app", new File("/Users/farukayaz/Applications/ContactManager.apk").getAbsolutePath());
        Driver = new AndroidDriver(new URL("http://127.0.0.1:8080/wd/hub"), capabilities);
        Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage = new HomePage();
        addContactPage = new AddContactPage();
    }
    // in here I write the tests in the feature file with every possible options
    // check the add contact button is working or not
    @Order(1)
    @Test
    public void openAddContactOnOreo() throws NullPointerException, InterruptedException {
        try {
            homePage.getAddContactBtn().click();
            Thread.sleep(4000);
        }catch(Exception e){
            System.out.println("Couldnt find the locator"+e.getMessage());
        }
    }
    //User checks title whether it is "Add Contact"
    @Order(2)
    @Test
    public void checkAddContactTitle(){

        try {
            Assert.assertEquals(addContactPage.getTitle().getText(), "Add Contact");
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }
    }
    // checking the target account if it is correct or not
    @Order(3)
    @Test
    public void targetAccountCheck(){
        try {
            homePage.getAddContactBtn().click();
            Assert.assertEquals(addContactPage.getTargetAccountText().getText(), myMail);
        }catch(Exception e){
            System.out.println("Couldnt find the locator"+e.getMessage());
        }
    }
    // user adding different type of phone and email
    @Order(4)
    @Test
    public void userAddsHomePhoneHomeMailContact(){

        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
            //User adds a work account successfully
        try {
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e){
            System.out.println("Couldnt find the element in the home page"+e.getMessage());
        }
        try {
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactSaveBtn().click();
            
        }catch(NoSuchElementException e){
            System.out.println("Couldn't find the element in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(5)
    @Test
    public void userAddsHomePhoneWorkMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page"+e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactSaveBtn().click();
        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(6)
    @Test
    public void userAddsHomePhoneHomeMobileContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();

        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropMobile().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(7)
    @Test
    public void userAddsHomePhoneHomeOtherContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(8)
    @Test
    public void userAddsWorkPhoneHomeMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(9)
    @Test
    public void userAddsWorkPhoneWorkMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }


    }
    // user adding different type of phone and email
    @Order(10)
    @Test
    public void userAddsWorkPhoneMobileMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropMobile().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(11)
    @Test
    public void userAddsWorkPhoneWorkOtherContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(12)
    @Test
    public void userAddsMobilePhoneHomeMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropMobile().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(13)
    @Test
    public void userAddsMobilePhoneWorkMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropMobile().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(14)
    @Test
    public void userAddsMobilePhoneMobileMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropMobile().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropMobile().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(15)
    @Test
    public void userAddsMobilePhoneOtherMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(16)
    @Test
    public void userAddsOtherPhoneHomeMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropHome().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(17)
    @Test
    public void userAddsOtherPhoneWorkMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropWork().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(18)
    @Test
    public void userAddsOtherPhoneMobileMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropMobile().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add contact page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // user adding different type of phone and email
    @Order(19)
    @Test
    public void userAddsOtherPhoneOtherMailContact(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page"+e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch (Exception e){
            System.out.println("Couldn't find the locator in the add account page"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // User checks required fields for add account page
    @Order(20)
    @Test
    public void requiredAreasEmptyContactName(){
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue("");
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("You should enter a name "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(phoneNumber).getText(),phoneNumber);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }


    }
   // User checks required fields for add account page
    @Order(21)
    @Test
    public void requiredAreasEmptyPhone(){
        String firstName =  faker.name().firstName();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue("");
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("You should enter a phone "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }


    }
    // User checks required fields for add account page
    @Order(22)
    @Test
    public void requiredAreasEmptyEmail(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page"+e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue("");
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("You should enter a mail "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    //User checks special characters limit for contact name
    @Order(23)
    @Test
    public void nameWithSpecialCharacters(){
        String firstName =  faker.name().firstName();
        String specialName = specialCharacters+firstName;
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page"+e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(specialName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("You should enter a valid name "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(specialName).getText(),specialName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }


    }
    //User checks special characters limit for contact email field
    @Order(23)
    @Test
    public void emailWithSpecialCharacters(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        String specialEmail = specialCharacters+email;
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(specialEmail);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("You should enter a valid name "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    //User checks special characters limit for contact phone field
    @Order(24)
    @Test
    public void phoneWithSpecialCharacters(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String specailPhone = specialCharacters+phoneNumber;
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(specailPhone);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("You should enter a valid phone number "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(firstName).getText(),firstName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }


    }
    //User checks special characters limit for contact name
    @Order(25)
    @Test
    public void rangeTestingOfNameField(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        String bigName = firstName+firstName+firstName+firstName;
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page"+e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(bigName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("The name is out of range "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(bigName).getText(),bigName);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }


    }
    //User checks special characters limit for contact phone
    @Order(26)
    @Test
    public void rangeTestingOfPhoneField(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String email = fakeValuesService.bothify("????##@gmail.com");
        String bigPhoneNumber =phoneNumber+phoneNumber+phoneNumber+phoneNumber;
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(bigPhoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("The phone number is out of range "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(bigPhoneNumber).getText(),bigPhoneNumber);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }


    }
    //User checks special characters limit for contact email
    @Order(27)
    @Test
    public void rangeTestingOfEmailField(){
        String firstName =  faker.name().firstName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        String bigEmail = email+email+email+email+email;
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(firstName);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(bigEmail);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("The email is out of range "+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(bigEmail).getText(),bigEmail);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }
    // User checks whether contact name accepts number and digit
    @Order(28)
    @Test
    public void nameFieldWithNumber(){

        String phoneNumber = faker.phoneNumber().phoneNumber();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        try {
            //User adds a work account successfully
            homePage.getAddContactBtn().click();
        }catch(NoSuchElementException e) {
            System.out.println("Couldn't find the element in the home page" +e.getMessage());
        }
        try{
            addContactPage.getContactNameField().setValue(phoneNumber);
            addContactPage.getContactPhoneField().setValue(phoneNumber);
            addContactPage.getContactPhoneDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactEmailField().setValue(email);
            addContactPage.getContactEmailDrop().click();
            addContactPage.getContactTypeDropOther().click();
            addContactPage.getContactSaveBtn().click();

        }catch(Exception e ){
            System.out.println("Cant use number in the name field"+e.getMessage());
        }
        try{
            Assert.assertEquals(elementFinder(phoneNumber).getText(),phoneNumber);
        }catch(AssertionError e){
            System.out.println("Your expected value did not match with the actual value "+e.getMessage());
        }

    }


    @AfterClass
    public void waiter() throws InterruptedException {
        Thread.sleep(5000);
    }

}
