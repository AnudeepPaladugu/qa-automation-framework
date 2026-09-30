# E-Commerce QA Automation

Selenium-based test automation framework for validating key e-commerce user journeys.

## Tech Stack

- Java 17
- Selenium WebDriver 4.35.0
- TestNG 7.11.0
- Cucumber 7.27.2
- Allure 2.29.1
- AShot 1.5.4
- Maven

## Test Coverage

The automation suite covers:

- Login validation
- Product listing
- Product details
- Product sorting
- Cart operations
- Checkout validation
- Checkout overview
- Order placement
- Screenshot capture for test results
- Test reporting with Allure

## Framework Structure

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

Project configuration is maintained in:

`src/test/resources/config/config.properties`

Browser, application URL, and test credentials can be managed from the properties file rather than hard-coding them in test classes.

## Run the Tests

Open a terminal in the `ecommerce-qa-automation` directory and run:

```bash
mvn clean test
```

The Maven Surefire plugin uses `testng.xml` to execute the regression suite.

## Allure Report

After test execution, Allure results are generated for reporting.

To generate and open the report locally:

```bash
allure serve allure-results
```

## Framework Design

The framework uses the Page Object Model to keep test logic separate from page interactions.

The project is organized into:

- **Base**: WebDriver setup and teardown
- **Pages**: Page-level locators and actions
- **Tests**: Functional and regression test cases
- **Hooks**: Test listeners and screenshot handling
- **Utils**: Reusable configuration and helper methods
- **Resources**: Environment and test configuration

## Build

The project uses Maven for dependency management and test execution.

Java source and target compatibility are configured to **Java 17** in `pom.xml`.

## Project Purpose

This project demonstrates a maintainable UI automation framework for an e-commerce application using Selenium, TestNG, Maven, and Allure reporting.
