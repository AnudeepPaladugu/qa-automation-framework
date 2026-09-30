<div align="center">

# QA Automation Framework

### Web UI Test Automation with Selenium, TestNG & Maven

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=18&duration=2800&pause=900&center=true&vCenter=true&width=720&lines=Manual+Testing+%E2%86%92+Automation+Candidates;Page+Object+Model+%7C+Reusable+Configuration;Regression+Coverage+%7C+Failure+Screenshots;TestNG+%7C+Selenium+%7C+Allure+Reporting" alt="Typing animation" />

<br/>

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-4.35.0-43B02A?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)
![Allure](https://img.shields.io/badge/Allure-Reporting-FF6A00?style=for-the-badge)

</div>

---

## What This Project Demonstrates

This repository demonstrates how a manual regression flow can be converted into a maintainable UI automation framework.

The focus is not simply on writing Selenium scripts. It shows the complete QA thinking behind the automation:

**Understand the flow → identify repeatable regression scenarios → decide what is worth automating → design reusable page objects → execute the suite → capture results.**

---

## 🔄 Manual Testing → Automation

~~~
flowchart LR
    A[Manual Test Cases] --> B{Automation Candidate?}
    B -->|Yes| C[Stable & Repeatable]
    B -->|No| D[Keep Manual]
    C --> E[High Regression Value]
    E --> F[Deterministic Expected Result]
    F --> G[Automate]
    G --> H[Add to Regression Suite]
    H --> I[Run with TestNG]
    I --> J[Allure Results]
~~~

### How were the automation candidates selected?

A manual scenario was considered a good automation candidate when it was:

| Selection factor | Why it matters |
|---|---|
| **Repeated frequently** | Saves manual execution time |
| **Part of regression testing** | Needs to be checked after regular changes |
| **Stable and predictable** | Produces consistent automation results |
| **Clear expected result** | Can be verified programmatically |
| **Business-critical** | Failure has meaningful impact |
| **Data-driven or repetitive** | Automation handles repeated combinations efficiently |
| **Time-consuming manually** | Gives a clear return on automation effort |

### Scenarios better kept manual

Exploratory testing, one-time checks, rapidly changing functionality, subjective visual assessment, and scenarios requiring human judgment are generally better candidates for manual testing.

---

## 🧪 What Is Automated?

The current suite covers the main user journey:

~~~
Login
  ↓
Product Listing
  ↓
Product Details / Sorting
  ↓
Cart
  ↓
Checkout
  ↓
Order Review
  ↓
Order Placement
~~~

The test classes are organized around these functional areas:

- **LoginTest**
- **ProductTest**
- **CartTest**
- **CheckoutTest**
- **CheckoutOverviewTest**

---

## 🏗️ Framework Architecture

~~~
flowchart TB
    T[TestNG Test Classes]
    P[Page Objects]
    B[BaseTest]
    C[ConfigReader]
    R[config.properties]
    H[FailureScreenshotListener]
    W[Selenium WebDriver]
    A[Allure Results]

    T --> P
    T --> B
    B --> W
    P --> W
    T --> C
    C --> R
    H --> T
    H --> A
    T --> A
~~~

### Project Structure

~~~
ecommerce-qa-automation/
│
├── src/test/java/
│   ├── base/
│   │   └── BaseTest.java
│   │
│   ├── hooks/
│   │   └── FailureScreenshotListener.java
│   │
│   ├── pages/
│   │   ├── LoginPage.java
│   │   ├── ProductPage.java
│   │   ├── CartPage.java
│   │   ├── CheckoutPage.java
│   │   └── CheckoutOverviewPage.java
│   │
│   ├── tests/
│   │   ├── LoginTest.java
│   │   ├── ProductTest.java
│   │   ├── CartTest.java
│   │   ├── CheckoutTest.java
│   │   └── CheckoutOverviewTest.java
│   │
│   └── utils/
│       └── ConfigReader.java
│
├── src/test/resources/
│   └── config/
│       └── config.properties
│
├── pom.xml
└── testng.xml
~~~

---

## ⚙️ Configuration

Test configuration is kept in:

**src/test/resources/config/config.properties**

This keeps environment details outside the test classes and makes the framework easier to maintain.

Typical configuration includes:

- Application URL
- Browser
- Test credentials
- Other environment-level settings

---

## ▶️ Run the Full Suite

From the project directory:

~~~
mvn clean test
~~~

The TestNG suite is defined in:

**testng.xml**

The suite currently groups the functional tests into a regression execution.

---

## 📊 Reporting

The framework produces Allure-compatible test results.

~~~
allure serve allure-results
~~~

Failure handling is implemented through:

**FailureScreenshotListener.java**

This allows failed tests to capture evidence that can be reviewed during debugging.

---

## 📚 QA Documentation

The repository also contains the supporting QA work used before and alongside automation.

| Document | Purpose |
|---|---|
| **QA Test Cases** | Manual functional test scenarios and expected results |
| **QA Bug Reports** | Defect documentation and tracking examples |
| **QA Test Strategy & Automation Observations** | Testing approach, automation suitability, and how manual scenarios are evaluated for automation |
| **QA Project Observations & Strategy** | Project-level QA observations and testing approach |

### Files

- [QA Test Cases](QA_Test_Cases.xlsx)
- [QA Bug Reports](QA_Bug_Reports.xlsx)
- [QA Test Strategy & Automation Observations](QA_Test_Strategy_and_Automation_Observations.xlsx)
- [QA Test Strategy & Automation Observations - Document](QA_Test_Strategy_and_Automation_Observations.docx)
- [QA Project Observations & Strategy](QA_Project_Observations_and_Strategy.docx)

---

## 🧩 Design Principles

### Page Object Model

Locators and page actions are kept inside page classes so tests remain focused on **what is being verified**, rather than how the UI is operated.

### Reusable Configuration

Environment-specific values are read through **ConfigReader** and **config.properties**.

### Separation of Concerns

~~~
Tests       → What to verify
Pages       → How to interact with the UI
Base        → Driver lifecycle
Hooks       → Test events / failure evidence
Utils       → Reusable support logic
Resources   → Configuration
~~~

### Maintainability

The framework avoids putting locators, credentials, and repeated WebDriver logic directly into individual test cases.

---

## 🛠️ Tech Stack

| Tool | Version / Purpose |
|---|---|
| Java | 17 |
| Selenium WebDriver | 4.35.0 |
| TestNG | 7.11.0 |
| Cucumber | 7.27.2 |
| Allure | 2.29.1 |
| AShot | 1.5.4 |
| Maven | Build & dependency management |

---

## 🎯 QA Workflow Represented in This Repository

~~~
Requirements
     ↓
Manual Test Design
     ↓
Functional Execution
     ↓
Bug Identification
     ↓
Regression Candidate Selection
     ↓
Automation Feasibility Review
     ↓
Page Object Design
     ↓
Selenium Automation
     ↓
TestNG Regression Suite
     ↓
Failure Evidence & Reporting
~~~

---

<div align="center">

### Built as a practical QA automation portfolio project

**Manual QA thinking + Automation + Maintainable Framework Design**

</div>
