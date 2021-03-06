package StepDefinitions;

import Pages.*;
import dataProvider.ConfigFileReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Properties;

import static org.testng.Assert.assertEquals;


public class stepDefinitions extends BaseClass {

    ConfigFileReader read = new ConfigFileReader();
    Properties pro = read.ConfigFile();
    Login log = new Login();
    CapitalGains capitalGains = new CapitalGains();
    DomesticExcise domesticExcise = new DomesticExcise();
    FringeBenefit fringeBenefit = new FringeBenefit();
    NonResident nonResident = new NonResident();
    ProvisionalPit provisionalPit = new ProvisionalPit();
    ProvisionalCIT provisionalCIT = new ProvisionalCIT();
    DomesticVat domesticVat = new DomesticVat();
    PAYE paye = new PAYE();
    Pit pit = new Pit();
    Cit cit = new Cit();
    Dividend dividend = new Dividend();
    Turnover turnover = new Turnover();

    @Given("Navigate to Portal login page")
    public void Navigate_to_Portal() {
        try {
            GetUrl(pro.getProperty("Url"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @When("^Enter username \"(.*)\" and Password \"(.*)\"$")
    public void enter_username_and_password(String uid, String pwd) {
        wait(20).until(ExpectedConditions.visibilityOf(log.username())).sendKeys(uid);
        implicitWait(2);
        log.password().sendKeys(pwd);

    }

    @And("Click on login button")
    public void click_on_log_in_button() {
        javascriptClick(log.Click_login_button());
    }

    @Then("Click on submit a return")
    public void clickOnSubmitAReturn() {
        wait(30).until(ExpectedConditions.elementToBeClickable(log.getSubmitReturn()));
        javascriptClick(log.getSubmitReturn());
    }

    @Then("Select Taxtype as {string}")
    public void selectTaxtypeAs(String taxtype) throws InterruptedException {
        implicitWait(3);
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),' Returns Summary ')]")));
        Thread.sleep(1000);
        wait(20).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[span='Capital Gain Tax(CGT)']/preceding-sibling::td[1]/span//span")));
        if(driver.findElements(By.xpath("//td[span='"+taxtype+"']/preceding-sibling::td[1]/span//span")).size()==0){
            driver.findElement(By.xpath("/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/p-paginator/div/span/a[2]")).click();
        }

        WebElement TaxTypeSelector = wait(20).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[span='"+taxtype+"']/preceding-sibling::td[1]/span//span")));
        Thread.sleep(1000);
        TaxTypeSelector.click();
    }

    @Then("Click continue")
    public void clickContinue() throws InterruptedException {
        Thread.sleep(1000);
        javascriptClick(capitalGains.getcontinueBtn());
    }

    @Then("Fill in particulars of the person acquiring the asset")
    public void fillInParticularsOfThePersonAcquiringTheAsset() throws InterruptedException {
        wait(60).until(ExpectedConditions.elementToBeClickable(capitalGains.getBuyerTin()));
        scrollIntoView(capitalGains.getBuyerTin());
        capitalGains.getBuyerTin().sendKeys("C0103712");
        Thread.sleep(200);
        capitalGains.getBuyerAddress().sendKeys("Malawi, Central Region, Lilongwe");
        Thread.sleep(200);
        capitalGains.getBuyerName().sendKeys("DR Maxipain amGG");
        Thread.sleep(200);
        capitalGains.getBuyerEmail().sendKeys("margiewambui11@gmail.com");
        Thread.sleep(200);
        capitalGains.getBuyerContact().sendKeys("+254707338839");
        Thread.sleep(200);
    }

    @Then("Click new under transaction details")
    public void clickNewUnderTransactionDetails() {
        javascriptClick(capitalGains.getBtnAdd());
    }

    @Then("Select type of asset and asset number")
    public void selectTypeOfAssetAndAssetNumber() throws InterruptedException {
        wait(10).until(ExpectedConditions.elementToBeClickable(capitalGains.getTypeOfAsset()));
        Thread.sleep(500);
        capitalGains.getTypeOfAsset().click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[span='Real estate (Land and Buildings)']")).click();
        Thread.sleep(500);
        capitalGains.getAssetNumber().click();
        actionDown();
        actionEnter();
    }

    @Then("Enter disposal selling date as {string}")
    public void enterDisposalSellingDateAs(String date) {
        implicitWait(2);
        capitalGains.getSellingDate().sendKeys(date);
        actionTab();
    }

    @Then("Enter selling price as {string}")
    public void enterSellingPriceAs(String price) {
        implicitWait(2);
        capitalGains.getSellingPrice().sendKeys(price);
    }

    @Then("Enter value at disposal as {string}")
    public void enterValueAtDisposalAs(String value) {
        implicitWait(2);
        capitalGains.getDisposalValue().sendKeys(value);
    }

    @Then("Enter acquisition date as {string}")
    public void enterAcquisitionDateAs(String date) {
        implicitWait(2);
        capitalGains.getAcquisitionDate().sendKeys(date);
        actionTab();
    }

    @Then("Enter acquisition cost as {string}")
    public void enterAcquisitionCostAs(String cost) {
        implicitWait(2);
        capitalGains.getAcquisitionCost().sendKeys(cost);
    }

    @Then("Click add to save transaction details")
    public void clickAddToSaveTransactionDetails() {
        capitalGains.getAdd().click();
    }

    @Then("Click next after adding transaction details")
    public void clickNextAfterTransactionDetails() throws InterruptedException {
        Thread.sleep(1000);
        capitalGains.getBtnNext().click();
    }

    @Then("Confirm information given for Capital gains tax is true")
    public void confirmInformationGivenForCapitalGainsTaxIsTrue() throws InterruptedException {
        wait(30).until(ExpectedConditions.elementToBeClickable(capitalGains.getConfirmCheckbox()));
        Thread.sleep(500);
        scrollIntoView(capitalGains.getConfirmCheckbox());
        capitalGains.getConfirmCheckbox().click();
    }

    @Then("Submit Capital Gains Tax returns")
    public void submitCapitalGainsTaxReturns() {
        wait(10).until(ExpectedConditions.elementToBeClickable(capitalGains.getSubmitReturn())).click();
    }

    @Then("Verify success message displayed as {string}")
    public void verifySuccessMessageDisplayedAs(String success) {
        WebElement message = wait(100).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + success + "')]")));
        Assert.assertTrue(message.isDisplayed());
    }

    @Then("Select period as {string}")
    public void selectPeriodAs(String period) throws InterruptedException {
        implicitWait(3);
        WebElement TaxTypeSelector = wait(30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[span='"+period+"']/preceding-sibling::td[1]/span//span")));
        Thread.sleep(1000);
        TaxTypeSelector.click();
    }

    @Then("Click continue to upload domestic excise uploads")
    public void clickContinueToUploadDomesticExciseUploads() {
        domesticExcise.getcontinueBtn().click();
    }

    @Then("Upload Transaction information on domestic excise tax computation on goods")
    public void uploadTransactionInformationOnDomesticExciseTaxComputationOnGoods() throws InterruptedException, AWTException {
        wait(30).until(ExpectedConditions.elementToBeClickable(domesticExcise.getUploadGoods()));
        implicitWait(2);
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Templates\\DOMESTIC EXCISE" + File.separator + "exGoodsItem_template.csv";
        domesticExcise.getUploadGoods().click();
        Thread.sleep(1000);
        Robot rb = new Robot();

        // copying File path to Clipboard
        System.out.println("The path is : "+path);
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(1000);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(600);
    }

    @Then("Upload Transaction information on raw materials used calculation for goods")
    public void uploadTransactionInformationOnRawMaterialsUsedCalculationForGoods() throws InterruptedException, AWTException {
        wait(30).until(ExpectedConditions.elementToBeClickable(domesticExcise.getUploadCalculation()));
        implicitWait(2);
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Templates\\DOMESTIC EXCISE" + File.separator + "exRawMaterialItem_template.csv";
        domesticExcise.getUploadCalculation().click();
        Thread.sleep(1000);
        Robot rb = new Robot();

        // copying File path to Clipboard
        System.out.println("The path is : "+path);
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(1000);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(600);
    }

    @Then("Upload Transaction information on domestic excise tax computation for services")
    public void uploadTransactionInformationOnDomesticExciseTaxComputationForServices() throws InterruptedException, AWTException {
        wait(30).until(ExpectedConditions.elementToBeClickable(domesticExcise.getUploadServices()));
        implicitWait(2);
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Templates\\DOMESTIC EXCISE" + File.separator + "exServiceItem_template.csv";
        domesticExcise.getUploadServices().click();
        Thread.sleep(1000);
        Robot rb = new Robot();

        // copying File path to Clipboard
        System.out.println("The path is : "+path);
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(1000);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(600);
    }

    @Then("Verify alert success message {string}")
    public void verifyAlertSuccessMessage(String success) {

        WebElement message = wait(100).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'"+success+"' )]")));
        Assert.assertTrue(message.isDisplayed());
    }

    @Then("Confirm information given for Domestic Excise tax is true")
    public void confirmInformationGivenForDomesticExciseTaxIsTrue() throws InterruptedException {wait(30).until(ExpectedConditions.elementToBeClickable(domesticExcise.getConfirmCheckbox()));
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(domesticExcise.getConfirmCheckbox()));
        scrollIntoView(domesticExcise.getConfirmCheckbox());
        domesticExcise.getConfirmCheckbox().click();
    }

    @Then("Submit Domestic Excise Tax returns")
    public void submitDomesticExciseTaxReturns() {
        domesticExcise.getSubmitReturn().click();
    }

    @Then("Click next after uploading domestic excise details")
    public void clickNextAfterUploadingDomesticExciseDetails() throws InterruptedException {
        Thread.sleep(1000);
        javascriptClick(domesticExcise.getBtnNext());
    }

    @Then("Click continue to fill fringe benefit details")
    public void clickContinueToFillFringeBenefitDetails() {
        implicitWait(2);
        fringeBenefit.getcontinueBtn().click();
    }

    @Then("Click new to add fringe benefit details")
    public void clickNewToAddFringeBenefitDetails() {
        wait(20).until(ExpectedConditions.visibilityOf(fringeBenefit.getNew()));
        javascriptClick(fringeBenefit.getNew());
    }

    @Then("Select fringe benefit as {string}")
    public void selectFringeBenefitAs(String arg0) throws InterruptedException {
        wait(10).until(ExpectedConditions.elementToBeClickable(fringeBenefit.getFringeBenefit()));
        Thread.sleep(500);
        fringeBenefit.getFringeBenefit().click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[span='"+arg0+"']")).click();
        Thread.sleep(500);
    }

    @Then("Enter benefit value as {string}")
    public void enterBenefitValueAs(String arg0) throws InterruptedException {
        Thread.sleep(1000);
        fringeBenefit.getBenefitValue().sendKeys(arg0);
    }

    @Then("Click add to save fringe benefit details")
    public void clickAddToSaveFringeBenefitDetails() {
        implicitWait(2);
        fringeBenefit.getAddBtn().click();
    }

    @Then("Click next after adding fringe benefit details")
    public void clickNextAfterAddingFringeBenefitDetails() throws InterruptedException {
        wait(10).until(ExpectedConditions.visibilityOf(fringeBenefit.getBtnNext()));
        Thread.sleep(600);
        javascriptClick(fringeBenefit.getBtnNext());
    }

    @Then("Confirm information given for Fringe benefit tax is true")
    public void confirmInformationGivenForFringeBenefitTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(fringeBenefit.getConfirmCheckbox()));
        scrollIntoView(fringeBenefit.getConfirmCheckbox());
        fringeBenefit.getConfirmCheckbox().click();
    }

    @Then("Submit Fringe Benefit Tax returns")
    public void submitFringeBenefitTaxReturns() {
        fringeBenefit.getSubmitReturn().click();
    }

    @Then("Upload NRT template")
    public void uploadNRTTemplate() throws InterruptedException, AWTException {
        wait(20).until(ExpectedConditions.visibilityOf(nonResident.getNrtUpload()));
        scrollIntoView(nonResident.getNrtUpload());
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Templates\\NRT" + File.separator + "NRT_template.csv";
        nonResident.getNrtUpload().click();
        Thread.sleep(1000);
        Robot rb = new Robot();

        // copying File path to Clipboard
        System.out.println("The path is : "+path);
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(1000);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(600);
    }

    @Then("Confirm information given for Non Resident tax is true")
    public void confirmInformationGivenForNonResidentTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(nonResident.getConfirmCheckbox()));
        scrollIntoView(nonResident.getConfirmCheckbox());
        nonResident.getConfirmCheckbox().click();
    }

    @Then("Submit Non Resident Tax returns")
    public void submitNonResidentTaxReturns() {
        nonResident.getSubmitReturn().click();
    }

    @Then("Upload PAYE template")
    public void uploadPAYETemplate() throws InterruptedException, AWTException {
        wait(20).until(ExpectedConditions.visibilityOf(paye.getPayeUpload()));
        scrollIntoView(paye.getPayeUpload());
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Templates\\PAYE" + File.separator + "PAYE_template.csv";
        paye.getPayeUpload().click();
        Thread.sleep(1000);
        Robot rb = new Robot();

        // copying File path to Clipboard
        System.out.println("The path is : "+path);
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(1000);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(600);
    }

    @Then("Confirm information given for paye tax is true")
    public void confirmInformationGivenForPayeTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(paye.getConfirmCheckbox()));
        scrollIntoView(paye.getConfirmCheckbox());
        paye.getConfirmCheckbox().click();
    }

    @Then("Submit PAYE Tax returns")
    public void submitPAYETaxReturns() {
        implicitWait(2);
        paye.getSubmitReturn().click();
    }

    @Then("Select Provisional PIT with period {string}")
    public void selectProvisionalPITWithPeriod(String period) throws InterruptedException {
        implicitWait(3);
        WebElement periodSelector = wait(30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[span='Provisional Tax(PIT) Return']/preceding-sibling::td/preceding-sibling::td[span='"+period+"']//preceding-sibling::td/span//span")));
        Thread.sleep(1000);
        periodSelector.click();
    }

    @Then("Confirm information given for Provisional PIT tax is true")
    public void confirmInformationGivenForProvisionalPITTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(provisionalPit.getConfirmCheckbox()));
        scrollIntoView(provisionalPit.getConfirmCheckbox());
        provisionalPit.getConfirmCheckbox().click();
    }

    @Then("Submit Provisional PIT Tax returns")
    public void submitProvisionalPITTaxReturns() {
        provisionalPit.getSubmitReturn().click();
    }

    @Then("Enter estimated chargable income or loss as {string}")
    public void enterEstimatedChargableIncomeOrLossAs(String amount) {
        wait(30).until(ExpectedConditions.visibilityOf(provisionalPit.getEstimatedIncome()));
        implicitWait(2);
        scrollIntoView(provisionalPit.getEstimatedIncome());
        provisionalPit.getEstimatedIncome().sendKeys(amount);
        implicitWait(1);
    }

    @Then("Enter quarter one Installment as {string}")
    public void enterQuarterOneInstallmentAs(String amount) throws InterruptedException {
        Thread.sleep(500);
        provisionalPit.getQuarterOne().sendKeys(amount);
    }

    @Then("Enter quarter two Installment as {string}")
    public void enterQuarterTwoInstallmentAs(String arg0) throws InterruptedException {
        Thread.sleep(500);
        provisionalPit.getQuarterTwo().sendKeys(arg0);
    }

    @Then("Enter quarter three Installment as {string}")
    public void enterQuarterThreeInstallmentAs(String arg0) throws InterruptedException {
        Thread.sleep(500);
        provisionalPit.getQuarterThree().sendKeys(arg0);
    }

    @Then("Enter quarter four Installment as {string}")
    public void enterQuarterFourInstallmentAs(String arg0) throws InterruptedException {
        Thread.sleep(500);
        provisionalPit.getQuarterFour().sendKeys(arg0);
    }

    @Then("Enter value of sales as {string}")
    public void enterValueOfSalesAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(domesticVat.getValueOfSales()));
        implicitWait(2);
        scrollIntoView(domesticVat.getValueOfSales());
        domesticVat.getValueOfSales().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter Vat on imported services as {string}")
    public void enterVatOnImportedServicesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getImportedServices().sendKeys(arg0);
    }

    @Then("Enter VAT on Disposal of Capital items as {string}")
    public void enterVATOnDisposalOfCapitalItemsAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getCapitalItems().sendKeys(arg0);
    }

    @Then("Enter Value of Supplies exported under Customs Control as {string}")
    public void enterValueOfSuppliesExportedUnderCustomsControlAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getCustomControls().sendKeys(arg0);
    }

    @Then("Enter Value of Zero Rated Supplies as {string}")
    public void enterValueOfZeroRatedSuppliesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getRatedSupplies().sendKeys(arg0);
    }

    @Then("Enter Value of Exempt Supplies {string}")
    public void enterValueOfExemptSupplies(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getExcemptSupplies().sendKeys(arg0);
    }

    @Then("Click next")
    public void clickNext() throws InterruptedException {
        wait(20).until(ExpectedConditions.visibilityOf(domesticVat.getBtnNext())).click();
        Thread.sleep(1000);
    }

    @Then("Enter Value of Local Purchases and Other Expenses as {string}")
    public void enterValueOfLocalPurchasesAndOtherExpensesAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(domesticVat.getValueOfLocalPurchasesAndOtherExpenses()));
        implicitWait(2);
        scrollIntoView(domesticVat.getValueOfLocalPurchasesAndOtherExpenses());
        domesticVat.getValueOfLocalPurchasesAndOtherExpenses().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter Value of Import Purchases as {string}")
    public void enterValueOfImportPurchasesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getValueOfImportedPurchases().sendKeys(arg0);
    }

    @Then("Enter Value of Zero Rated Purchases and Other Expenses as {string}")
    public void enterValueOfZeroRatedPurchasesAndOtherExpensesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getValueOfZeroRatedPurchases().sendKeys(arg0);
    }

    @Then("Enter Input VAT on Imported Services as {string}")
    public void enterInputVATOnImportedServicesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getVatOnImportedServices().sendKeys(arg0);
    }

    @Then("Enter Value of EFD Machines as {string}")
    public void enterValueOfEFDMachinesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getEfdMachines().sendKeys(arg0);
    }

    @Then("Enter Value of Exempt Purchases and Other Expenses as {string}")
    public void enterValueOfExemptPurchasesAndOtherExpensesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getValueOfExcemptPurchases().sendKeys(arg0);
    }

    @Then("Enter Value of Purchases from Non VAT Registered Traders as {string}")
    public void enterValueOfPurchasesFromNonVATRegisteredTradersAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getNonVatTraders().sendKeys(arg0);
    }

    @Then("Enter Value of Capital Goods as {string}")
    public void enterValueOfCapitalGoodsAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getValueOfCapitalGoods().sendKeys(arg0);
    }

    @Then("Enter Vat as {string}")
    public void enterVatAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        domesticVat.getVat().sendKeys(arg0);
    }

    @Then("Confirm information given for Domestic Vat tax is true")
    public void confirmInformationGivenForDomesticVatTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(domesticVat.getConfirmCheckbox()));
        scrollIntoView(domesticVat.getConfirmCheckbox());
        domesticVat.getConfirmCheckbox().click();
    }

    @Then("Submit Provisional Domestic Vat Tax returns")
    public void submitProvisionalDomesticVatTaxReturns() {
        domesticVat.getSubmitReturn().click();
    }

    @Then("Select PIT return with period {string}")
    public void selectPITReturnWithPeriod(String period) throws InterruptedException {
        implicitWait(3);
        WebElement periodSelector = wait(30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[span='Personal Income Tax(PIT) Return']/preceding-sibling::td/preceding-sibling::td[span='"+period+"']//preceding-sibling::td/span//span")));
        Thread.sleep(1000);
        periodSelector.click();
    }

    @Then("Confirm information given for PIT tax is true")
    public void confirmInformationGivenForPITTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(pit.getConfirmCheckbox()));
        scrollIntoView(pit.getConfirmCheckbox());
        pit.getConfirmCheckbox().click();
    }

    @Then("Submit PIT Tax returns")
    public void submitPITTaxReturns() {
        pit.getSubmitReturn().click();
    }

    @Then("Enter Business income as {string}")
    public void enterBusinessIncomeAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(pit.getBusinessIncome()));
        implicitWait(2);
        scrollIntoView(pit.getBusinessIncome());
        pit.getBusinessIncome().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter Opening stock as {string}")
    public void enterOpeningStockAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        pit.getOpeningStock().sendKeys(arg0);
    }

    @Then("Enter Purchases as {string}")
    public void enterPurchasesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        pit.getPurchases().sendKeys(arg0);
    }

    @Then("Enter Closing stock as {string}")
    public void enterClosingStockAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        pit.getClosingStock().sendKeys(arg0);
    }

    @Then("Enter Salary and wages as {string}")
    public void enterSalaryAndWagesAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(pit.getSalaryWages()));
        implicitWait(2);
        scrollIntoView(pit.getSalaryWages());
        pit.getSalaryWages().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter Opening capital as {string}")
    public void enterOpeningCapitalAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(pit.getOpeningCapital()));
        implicitWait(2);
        scrollIntoView(pit.getOpeningCapital());
        pit.getOpeningCapital().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter Net profit as {string}")
    public void enterNetProfitAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        pit.getNetProfit().sendKeys(arg0);
    }

    @Then("Enter Less drawings as {string}")
    public void enterLessDrawingsAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        pit.getLessDrawings().sendKeys(arg0);
    }

    @Then("Upload dividend tax csv template")
    public void uploadDividendTaxCsvTemplate() throws AWTException, InterruptedException {
        wait(20).until(ExpectedConditions.visibilityOf(dividend.getUploadTemplate()));
        scrollIntoView(dividend.getUploadTemplate());
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Templates\\DIVIDEND" + File.separator + "Dividend_template.csv";
        dividend.getUploadTemplate().click();
        Thread.sleep(1000);
        Robot rb = new Robot();

        // copying File path to Clipboard
        System.out.println("The path is : "+path);
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(1000);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(600);
    }

    @Then("Enter dividend declaration date as today")
    public void enterDividendDeclarationDateAsToday() {
        dividend.getDeclarationDate().sendKeys(todaysDate("dd/MM/yyy"));
        actionTab();
    }

    @Then("Confirm information given for Dividend tax is true")
    public void confirmInformationGivenForDividendTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        scrollIntoView(dividend.getConfirmCheckbox());
        dividend.getConfirmCheckbox().click();
    }

    @Then("Submit Dividend Tax returns")
    public void submitDividendTaxReturns() {
        dividend.getSubmitReturn().click();
    }

    @Then("Select Provisional CIT with period {string}")
    public void selectProvisionalCITWithPeriod(String arg0) throws InterruptedException {
        implicitWait(3);
        WebElement periodSelector = wait(30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[span='Provisional Tax(CIT) Return']/preceding-sibling::td/preceding-sibling::td[span='"+arg0+"']//preceding-sibling::td/span//span")));
        Thread.sleep(1000);
        periodSelector.click();
    }

    @Then("Enter estimated chargable income or loss for CIT as {string}")
    public void enterEstimatedChargableIncomeOrLossForCITAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(provisionalCIT.getEstimatedIncome()));
        implicitWait(2);
        scrollIntoView(provisionalCIT.getEstimatedIncome());
        provisionalCIT.getEstimatedIncome().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter CIT quarter one Installment as {string}")
    public void enterCITQuarterOneInstallmentAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        provisionalCIT.getQuarterOne().sendKeys(arg0);
    }

    @Then("Enter CIT quarter two Installment as {string}")
    public void enterCITQuarterTwoInstallmentAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        provisionalCIT.getQuarterTwo().sendKeys(arg0);
    }

    @Then("Enter CIT quarter three Installment as {string}")
    public void enterCITQuarterThreeInstallmentAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        provisionalCIT.getQuarterThree().sendKeys(arg0);
    }

    @Then("Enter CIT quarter four Installment as {string}")
    public void enterCITQuarterFourInstallmentAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        provisionalCIT.getQuarterFour().sendKeys(arg0);
    }

    @Then("Confirm information given for Provisional CIT tax is true")
    public void confirmInformationGivenForProvisionalCITTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(provisionalCIT.getConfirmCheckbox()));
        scrollIntoView(provisionalCIT.getConfirmCheckbox());
        provisionalCIT.getConfirmCheckbox().click();
    }

    @Then("Submit Provisional CIT Tax returns")
    public void submitProvisionalCITTaxReturns() {
        provisionalCIT.getSubmitReturn().click();
    }

    @Then("Select CIT return with period {string}")
    public void selectCITReturnWithPeriod(String period) throws InterruptedException {
        implicitWait(3);
        WebElement periodSelector = wait(30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[span='Company Income Tax(CIT) Return']/preceding-sibling::td/preceding-sibling::td[span='"+period+"']//preceding-sibling::td/span//span")));
        Thread.sleep(1000);
        periodSelector.click();
    }

    @Then("Check corporate income checkbox")
    public void checkCorporateIncomeCheckbox() {
        wait(30).until(ExpectedConditions.visibilityOf(cit.getCorporateIncome()));
        scrollIntoView(cit.getCorporateIncome());
        cit.getCorporateIncome().click();
    }

    @Then("Enter CIT Business income as {string}")
    public void enterCITBusinessIncomeAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(cit.getBusinessIncome()));
        implicitWait(2);
        scrollIntoView(cit.getBusinessIncome());
        cit.getBusinessIncome().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter CIT Opening stock as {string}")
    public void enterCitOpeningStockAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        cit.getOpeningStock().sendKeys(arg0);
    }

    @Then("Enter CIT Purchases as {string}")
    public void enterCitPurchasesAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        cit.getPurchases().sendKeys(arg0);
    }

    @Then("Enter CIT Closing stock as {string}")
    public void enterCitClosingStockAs(String arg0) throws InterruptedException {
        Thread.sleep(400);
        cit.getClosingStock().sendKeys(arg0);
    }

    @Then("Enter CIT Salary and wages as {string}")
    public void enterCitSalaryAndWagesAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(cit.getSalaryWages()));
        implicitWait(2);
        scrollIntoView(cit.getSalaryWages());
        cit.getSalaryWages().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Enter Share Capital as {string}")
    public void enterShareCapitalAs(String arg0) {
        wait(30).until(ExpectedConditions.visibilityOf(cit.getShareCapital()));
        implicitWait(2);
        scrollIntoView(cit.getShareCapital());
        cit.getShareCapital().sendKeys(arg0);
        implicitWait(1);
    }

    @Then("Upload directors pension fund information")
    public void uploadDirectorsPensionFundInformation() throws InterruptedException, AWTException {
        wait(20).until(ExpectedConditions.visibilityOf(cit.getCitUpload()));
        scrollIntoView(cit.getCitUpload());
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Templates\\CIT" + File.separator + "CITPENSIONFUND_Template.csv";
        cit.getCitUpload().click();
        Thread.sleep(1000);
        Robot rb = new Robot();

        // copying File path to Clipboard
        System.out.println("The path is : "+path);
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Thread.sleep(1000);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(600);
    }

    @Then("Confirm information given for CIT tax is true")
    public void confirmInformationGivenForCITTaxIsTrue() throws InterruptedException {
        Thread.sleep(500);
        wait(30).until(ExpectedConditions.visibilityOf(cit.getConfirmCheckbox()));
        scrollIntoView(cit.getConfirmCheckbox());
        cit.getConfirmCheckbox().click();
    }

    @Then("Submit CIT Tax returns")
    public void submitCITTaxReturns() {
        cit.getSubmitReturn().click();
    }

    @Then("Click new to add turnover details")
    public void clickNewToAddTurnoverDetails() {
        wait(30).until(ExpectedConditions.visibilityOf(turnover.getNew()));
        scrollIntoView(turnover.getNew());
        turnover.getNew().click();
    }

    @Then("Enter source of income as {string} description as {string} and amount as {string}")
    public void enterSourceOfIncomeAsDescriptionAsAndAmountAs(String arg0, String arg1, String arg2) throws InterruptedException {
        implicitWait(2);
        turnover.getSourceOfIncome().sendKeys(arg0);
        Thread.sleep(300);
        turnover.getDescription().sendKeys(arg1);
        Thread.sleep(300);
        turnover.getAmount().sendKeys(arg2);
        Thread.sleep(300);
    }

    @Then("Click add to save turnover details")
    public void clickAddToSaveTurnoverDetails() {
        turnover.getAddBtn().click();
    }

    @Then("Confirm information given for Turnover tax is true")
    public void confirmInformationGivenForTurnoverTaxIsTrue() throws InterruptedException {
        wait(30).until(ExpectedConditions.elementToBeClickable(turnover.getConfirmCheckbox()));
        Thread.sleep(500);
        scrollIntoView(turnover.getConfirmCheckbox());
        turnover.getConfirmCheckbox().click();
    }

    @Then("Submit Turnover Tax returns")
    public void submitTurnoverTaxReturns() {
        turnover.getSubmitReturn().click();
    }
}




