<div align="center">

# QA Automation Framework

### Web UI Test Automation with Selenium, TestNG & Maven

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=18&duration=2600&pause=700&center=true&vCenter=true&width=820&lines=Manual+Test+Cases+%E2%86%92+Automation+Candidates;Stable+%2B+Repeatable+%2B+Regression+Value;Page+Objects+%2B+Reusable+Configuration;TestNG+%2B+Selenium+%2B+Allure+Reporting" alt="Automation workflow animation" />

<br/>

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-4.35.0-43B02A?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)
![Allure](https://img.shields.io/badge/Allure-Reporting-FF6A00?style=for-the-badge)

</div>

---

## What This Project Demonstrates

This repository demonstrates a practical QA workflow from **manual testing to UI automation**.

The important part is not only writing Selenium code. The repository shows how manual scenarios are reviewed, how automation candidates are selected, how the framework is structured, and how the regression suite is executed and reported.

**Manual testing → automation assessment → reusable framework → regression execution → failure evidence → reporting**

---

## 🔄 Manual Test Cases → Automation Selection

```mermaid
flowchart LR
    A["Manual Test Cases"] --> B{"Automation Candidate?"}
    B -->|"Yes"| C["Stable & Repeatable"]
    B -->|"No"| D["Keep Manual"]
    C --> E["Regression Value"]
    E --> F["Clear Expected Result"]
    F --> G["Automate"]
    G --> H["Regression Suite"]
    H --> I["TestNG Execution"]
    I --> J["Allure Report"]

    style A fill:#e8f4ff,stroke:#1976d2
    style B fill:#fff4cc,stroke:#d99a00
    style D fill:#f5f5f5,stroke:#777
    style G fill:#e8f7ee,stroke:#218739
    style J fill:#f3e8ff,stroke:#7b3fb3
```

> **Note:** GitHub renders Mermaid diagrams, but it does not support animated Mermaid nodes/edges inside a README. The animated typing workflow above provides the animation, while this Mermaid diagram gives the actual decision flow.

### How were automation candidates selected?

A manual test case was considered for automation when it met several practical criteria:

| Selection factor | Why it matters |
|---|---|
| **Frequently executed** | Reduces repeated manual effort |
| **Regression-focused** | Useful after application changes |
| **Stable functionality** | Less maintenance from changing UI behavior |
| **Clear expected result** | Easy to validate programmatically |
| **High business importance** | Protects important user journeys |
| **Repetitive/data-driven** | Automation handles repeated combinations efficiently |
| **Time-consuming manually** | Provides measurable execution savings |

### What should generally remain manual?

Some scenarios are better suited to manual testing, such as:

- Exploratory testing
- One-time checks
- Rapidly changing functionality
- Subjective visual assessment
- Scenarios requiring human judgment

The goal is **not to automate every manual test case**. The goal is to automate the scenarios where automation provides repeatable regression value.

---

## 🧪 Automated User Journey

```
Manual Test Cases
       │
       ▼
Automation Candidate Review
       │
       ▼
Login
       │
       ▼
Product Listing & Sorting
       │
       ▼
Cart
       │
       ▼
Checkout
       │
       ▼
Order Review
       │
       ▼
Order Placement
       │
       ▼
TestNG Regression Suite
       │
       ▼
Allure Results
```

Current test classes:

- **LoginTest**
- **ProductTest**
- **CartTest**
- **CheckoutTest**
- **CheckoutOverviewTest**

---

## 🏗️ Framework Architecture

```mermaid
flowchart TB
    T["TestNG Test Classes"]
    P["Page Objects"]
    B["BaseTest"]
    C["ConfigReader"]
    R["config.properties"]
    H["FailureScreenshotListener"]
    W["Selenium WebDriver"]
    A["Allure Results"]

    T --> P
    T --> B
    B --> W
    P --> W
    T --> C
    C --> R
    H --> T
    H --> A
    T --> A
```

### Project Structure

```text
ecommerce-qa-automation/
│
├── src/test/java/
│   ├── base/
│   │   └── BaseTest.java
│   ├── hooks/
│   │   └── FailureScreenshotListener.java
│   ├── pages/
│   │   ├── LoginPage.java
│   │   ├── ProductPage.java
│   │   ├── CartPage.java
│   │   ├── CheckoutPage.java
│   │   └── CheckoutOverviewPage.java
│   ├── tests/
│   │   ├── LoginTest.java
│   │   ├── ProductTest.java
│   │   ├── CartTest.java
│   │   ├── CheckoutTest.java
│   │   └── CheckoutOverviewTest.java
│   └── utils/
│       └── ConfigReader.java
│
├── src/test/resources/
│   └── config/
│       └── config.properties
│
├── pom.xml
└── testng.xml
```

---

## ⚙️ Configuration

Test configuration is maintained in:

**src/test/resources/config/config.properties**

The framework uses the existing properties-based configuration instead of putting environment values directly inside individual test classes.

Typical settings include:

- Application URL
- Browser
- Test credentials
- Environment-level configuration

---

## ▶️ Run the Full Suite

From the automation project directory:

```bash
mvn clean test
```

The TestNG suite is defined in:

**testng.xml**

The suite groups the functional tests into a regression execution.

---

## 📊 Reporting & Failure Evidence

The framework produces Allure-compatible test results.

```bash
allure serve allure-results
```

Failure handling is implemented through:

**FailureScreenshotListener.java**

This provides screenshot evidence when a test fails, making failures easier to investigate.

---

## 📚 Manual QA Documentation

The repository contains the manual QA work that supports the automation process.

| Document | Purpose |
|---|---|
| **Manual Test Cases** | Functional scenarios, test steps, test data and expected results |
| **QA Bug Reports** | Defect documentation and tracking examples |
| **Test Strategy & Automation Observations** | Testing approach and reasoning used to identify automation candidates |
| **Project Observations & Strategy** | Project-level QA observations and testing approach |

### Documentation

- [Manual Test Cases](Manual_Test_Cases.xlsx)
- [QA Bug Reports](QA_Bug_Reports.xlsx)
- [Test Strategy & Automation Observations](QA_Test_Strategy_and_Automation_Observations.xlsx)
- [Test Strategy & Automation Observations - Document](QA_Test_Strategy_and_Automation_Observations.docx)
- [Project Observations & Strategy](QA_Project_Observations_and_Strategy.docx)

---

## 🧩 Framework Design

### Page Object Model

Locators and page actions are kept inside page classes.

Tests therefore describe **what is being verified**, while page classes handle **how the application is interacted with**.

### Reusable Configuration

Environment-specific values are read through:

**ConfigReader → config.properties**

### Separation of Concerns

```
Tests       → What to verify
Pages       → How to interact with the UI
Base        → Driver lifecycle
Hooks       → Test events / failure evidence
Utils       → Reusable support logic
Resources   → Configuration
```

### Maintainability

The framework keeps:

- Locators inside page objects
- Configuration inside properties
- Driver setup inside the base layer
- Failure handling inside listeners
- Verification logic inside test classes

---

## 🛠️ Tech Stack

| Tool | Version / Purpose |
|---|---|
| Java | **21** |
| Selenium WebDriver | 4.35.0 |
| TestNG | 7.11.0 |
| Cucumber | 7.27.2 |
| Allure | 2.29.1 |
| AShot | 1.5.4 |
| Maven | Build & dependency management |

---

## 🎯 Complete QA Workflow

```mermaid
flowchart LR
    A["Requirements"] --> B["Manual Test Design"]
    B --> C["Functional Execution"]
    C --> D["Bug Identification"]
    D --> E["Regression Candidate Selection"]
    E --> F["Automation Feasibility Review"]
    F --> G["Page Object Design"]
    G --> H["Selenium Automation"]
    H --> I["TestNG Regression"]
    I --> J["Failure Evidence"]
    J --> K["Allure Reporting"]
```

---

<div align="center">

### QA Thinking + Automation + Maintainable Framework Design

</div>
