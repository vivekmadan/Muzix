import { AppPage } from './app.po';
import { browser, logging, by, element } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

//   it('should display welcome message', () => {
//     page.navigateTo();
//     expect(page.getTitleText()).toEqual('Welcome to MuzixApplication!');
//   });

//   afterEach(async () => {
//     // Assert that there are no errors emitted from the browser
//     const logs = await browser.manage().logs().get(logging.Type.BROWSER);
//     expect(logs).not.toContain(jasmine.objectContaining({
//       level: logging.Level.SEVERE,
//     } as logging.Entry));
//   });
// });

  it('should display the title of application', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MuzixApplication');
  });

  it("should be redirected to /login route", () => {
    browser.element(by.css(".register-user")).click();
    expect(browser.getCurrentUrl()).toContain("/register");
    browser.driver.sleep(1000);
  });

  it("should be able to register user", () => {
    browser.element(by.id("username")).sendKeys("test123");
    browser.element(by.id("email")).sendKeys("test123");
    browser.element(by.id("password")).sendKeys("test123");
    browser.element(by.css(".register-user")).click();
    browser.driver.sleep(1000);
  });

  it("should be able to login user", () => {
    browser.element(by.id("username")).sendKeys("test123");
    browser.element(by.id("password")).sendKeys("test123");
    browser.element(by.css(".login-click")).click();
    browser.driver.sleep(1000);
  });

  it('should be able to click menu item for India', () => {
      browser.element(by.css('.mat-button')).click();
      browser.driver.sleep(1000);
      browser.element(by.css('.mat-menu-item-india')).click();
      expect(browser.getCurrentUrl()).toContain('/India');
      browser.driver.sleep(1000);
  });

  it('should be able to save Indian track to wishlist', () =>{
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(1000);
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to click menu item for Spain', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-spain')).click();
    expect(browser.getCurrentUrl()).toContain('/Spain');
    browser.driver.sleep(1000);
});

it('should be able to save Indian track to wishlist', () =>{
  browser.driver.manage().window().maximize();
  browser.driver.sleep(1000);
  const tracks = element.all(by.css('.example-card'));
  browser.driver.sleep(1000);
  browser.element(by.css('.addbutton')).click();
  browser.driver.sleep(1000);
});

it('should be able to get all data from Wishlist', () => {
  browser.driver.sleep(1000);
  browser.element(by.css(".mat-button-wishlist")).click();
  expect(browser.getCurrentUrl()).toContain("/WishList");
  browser.driver.sleep(1000);
});

it('should be able to delete data from wishlist', () => {
  browser.driver.sleep(1000);
  const tracks = element.all(by.css(".example-card"));
  browser.sleep(1000);
  browser.element(by.css(".deleteButton")).click();
  browser.sleep(1000);
});

it('should be able to open dialog box to update comment from wishlist', () => {
  browser.sleep(500);
  const tracks = element.all(by.css(".example-card"));
  browser.sleep(500);
  browser.element(by.css(".commentButton")).click();
  browser.sleep(1000);
});

it('should be able to save updated comments to wishlist', () => {
browser.sleep(500);
browser.element(by.css(".matInput")).sendKeys("New Comments");
browser.sleep(1000);
browser.element(by.css(".updateCommentDemo")).click();
browser.sleep(1000);
});

it("should be able to logout from application", () => {
  browser.driver.sleep(500);
  browser.element(by.css(".mat-button-logout")).click();
  browser.driver.sleep(5000);
});

});