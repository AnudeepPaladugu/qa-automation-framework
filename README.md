# E-Commerce QA Automation

Selenium and TestNG automation framework for validating key e-commerce user journeys.

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Allure Reports

## Coverage

The suite covers:

- Login validation
- Product listing and product details
- Product sorting
- Cart operations
- Checkout flow
- Order review and placement
- Failure and success screenshots through Allure

## Project Structure

```text
ecommerce-qa-automation/
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   ├── hooks/
│       │   ├── pages/
│       │   ├── tests/
│       │   └── utils/
│       └── resources/
│           └── config/
├── pom.xml
└── testng.xml
```

## Configuration

Test data and browser settings are maintained in:

`src/test/resources/config/config.properties`

Example:

```properties
browser=chrome
url=https://www.saucedemo.com/

standard.username=standard_user
standard.password=secret_sauce
```

## Run the Test Suite

From the `ecommerce-qa-automation` directory:

```bash
mvn clean test
```

The TestNG suite is configured through `testng.xml`.

## Test Reports

TestNG output is generated under `test-output/`.

Allure results are generated under `allure-results/`.

To open an Allure report locally:

```bash
allure serve allure-results
```

## Framework Design

The framework follows the Page Object Model and separates:

- Test cases
- Page interactions
- Driver setup and teardown
- Configuration
- Test listeners and reporting

This keeps the automation suite organized and easier to maintain.
