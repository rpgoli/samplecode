package com.uiframework.orgname.codeexcercise.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiframework.orgname.codeexcercise.helper.logger.LoggerHelper;

public class WaitHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	@SuppressWarnings("deprecation")
	public void setImplicitWait(long timeout, TimeUnit unit)
	{
		log.info("Implicit Wait has been set to: "+timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
}
	
	private WebDriverWait getWait(Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		return wait;
	}
	
	public void WaitForElementVisible(WebElement element, Duration timeOutInSecods)
	{
		log.info("waiting for :" +element.toString()+" for:"+ timeOutInSecods+" seconds");
		WebDriverWait wait = getWait(timeOutInSecods);		
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
}
}