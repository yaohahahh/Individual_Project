## University Sustainability Assessment Platform

### Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Contact](#contact)

---

### Introduction
The Sustainability Assessment System is a comprehensive tool designed to help higher education institutions evaluate and improve their sustainability practices. By integrating effective assessment frameworks and tools, this platform enables universities to better understand and manage their performance across environmental, social, and economic dimensions. The platform aims to encourage more sustainable practices within universities and contribute to a globally sustainable future.

### Features
- **Data Collection and Management**: A user-friendly interface for universities to collect and manage sustainability data. 
- **Data Analysis and Visualization**: Rich interactive data visualization features, including various types of charts and graphs, allowing users to deeply analyze and understand sustainability data.
- **User Roles and Permissions Management**: Designed with three user roles (guest, registered user, and admin), each with different levels of access and functionality.
- **Report Generation and Sharing**: Users can generate detailed sustainability reports and share them with stakeholders. Reports include visual charts and analysis results to help universities identify strengths and weaknesses in their sustainability practices.


### Installation 
1. **Clone the Repository**
   ```sh
   git clone https://github.com/yaohahahh/Sustainability_Assessment_Platform.git
   cd Sustainability_Assessment_Platform
   ```

2. **Install Dependencies**
   ```sh
   npm install
   ```

3. **Set Up Environment Variables**
   Create a `.env` file in the root directory and add the necessary environment variables:
   ```sh
   DATABASE_URL=jdbc:mysql://localhost:3306/Sustainability_Data
   driver-class-name= com.mysql.cj.jdbc.Driver  
   ```

4. **Run the Application**
   ```sh
   npm start
   ```

### Usage
1. **Access the Platform**
   Open your web browser and go to `http://localhost:5173.

2. **Sign Up/Log In**
   Register as a new user or log in with your existing credentials.
   Admin role: 
	   username: admin
	   password: admin
   User role:
	   username: 1
	   password: 3

3. **Framework process**
The process flowchart of the framework is illustrated. 
<img width="908" alt="process" src="https://github.com/user-attachments/assets/2ced0229-16b7-4cc7-8b1b-bff5b6562f55">

Here's the step-by-step explanation.

When the user enters the Higher Education Sustainability Assessment System, they are taken to the Dashboard which acts as the central hub. From the dashboard, the user has the option to go to the Home page (default), the Report module and the Comparison module.

The Home page contains the welcome screen and login or registration options, as well as the ability to switch between other modules. After logging in, the administrator role can manage collected data, ensuring it's properly stored and organised. The sustainability data of the organisation is collected mainly from STARS (https://stars.aashe.org), and the management of this data is done mainly in the List and Report pages of the Report module.

Selecting the Reporting module takes users first to the list page, where the administrator role manages basic information about the institution. The view also provides tools for searching and filtering organisations by various criteria. From the list given on this page, users can click on an organisation's name to access the report page to view the organisation's detailed sustainability data, which can be modified by the administrator. All users on the report page can click on the generate report button to access the generate page, which provides a detailed overview of the sustainability performance of the institution. On this page the user can generate detailed reports based on the visualised data. Finally, by clicking on the generate PDF button, the user can share the generated reports with stakeholders.

In the Comparison module, the chooseb page is accessed first, allowing the user to dynamically select organisations to compare and view detailed reports. View performance metrics based on interactive and customisable tools such as radar charts and line graphs, and access detailed credit metrics via collapsed tables.

At the back end of the system, there are also some preparation steps involved that are essential to the system. The process begins with the collection of data from a variety of sources, primarily the STARS system. This includes data relating to the environmental, social and economic performance of the institution. The collected data is stored in a central database called Sustainability Data. Administrators can manage this data through the previously mentioned pages. For the data to be better understood by the user, it needs to be categorised into different types and impact areas, and each category further classified into high, medium and low impact levels based on predefined criteria. Finally, the categorised data needs to be analysed to assess the sustainability performance of each university. This involved calculating scores and ratings based on various indicators.


### Contact
For questions, suggestions, or feedback, please contact:
- **Your Name**: k23018110@kcl.ac.uk
- **Project Repository**: [GitHub Repository](https://github.com/yaohahahh/Sustainability_Assessment_Platform)

---

Thank you for using the University Sustainability Assessment Platform! Together, we can make a difference in promoting sustainable practices in higher education institutions.
