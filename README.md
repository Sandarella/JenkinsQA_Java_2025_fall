# JenkinsQA_Java_2025_fall

<p align="center">
  <img src="https://img.shields.io/github/commit-activity/m/RedRoverSchool/JenkinsQA_Java_2025_fall">
  <img src="https://img.shields.io/github/last-commit/RedRoverSchool/JenkinsQA_Java_2025_fall">
  <img src="https://img.shields.io/github/contributors/RedRoverSchool/JenkinsQA_Java_2025_fall">
  <img src="https://github.com/RedRoverSchool/JenkinsQA_Java_2025_fall/actions/workflows/ci.yml/badge.svg">
  <img src="https://img.shields.io/github/issues/RedRoverSchool/JenkinsQA_Java_2025_fall">
  <img src="https://img.shields.io/github/issues-pr/RedRoverSchool/JenkinsQA_Java_2025_fall">
</p>

<h1 align="center">Project for Testing the CI/CD Tool Jenkins</h1>
<p align="center">
  <img src="/media/jenkins.svg" alt="Jenkins logo" width="100"/>
</p>

> Jenkins is an automation tool designed for Continuous Integration (CI) and Continuous Deployment (CD). It allows automatic execution of build, testing, and deployment processes whenever code changes are made in the repository.

##  Content:

- <a href="#settings"> Settings</a>
- <a href="#autotests"> Running Tests Locally</a>

## Tech Stack

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/TestNG-FF8C00?style=for-the-badge&logo=testng&logoColor=white"/>
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white"/>
  <img src="https://img.shields.io/badge/RestAssured-6DB33F?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/HTTP-005571?style=for-the-badge&logo=http&logoColor=white"/>
  <img src="https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
</p>

____
<a id="settings"></a>
## ️ Settings

To configure the project locally, follow these steps:

1. **Run Jenkins locally**
    - Install and start Jenkins on your machine.
    - Ensure all required plugins are installed.

2. **Configure the settings file**
    - Copy `.properties.TEMPLATE`.
    - Rename it to `.properties` (remove `TEMPLATE`).
    - Fill in the necessary values with your local configuration.

After completing these steps, your project should be ready to use! 🚀

____
<a id="autotests"></a>
## Running Tests Locally

To run automated tests locally, use the following commands:

1. **Run all tests:**
    - ```mvn test```

2. **Run specific tests:**
    - ```mvn test -Dtest=TestName```

