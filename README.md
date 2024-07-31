## Higher Education Institution Sustainability Assessment Platform

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
   Open your web browser and go to http://localhost:5173.

2. **Sign Up/Log In**
   Register as a new user or log in with your existing credentials.
   Admin role: 
	   username: admin
	   password: admin
   User role:
	   username: 1
	   password: 3

3. **Collect and Manage Data**
   When log in as an admin role, go to List page.
   Using the `Add institution` button to input sustainability data for new institutions. 
   Using `Edit and Delete` button in Operation list to manage existing sustainability data. 
   
4. **Analyze Data**
   Using compare module, explore the data analysis and visualization tools to understand your university's sustainability performance.

5. **Generate Reports**
   Using report module, create and download detailed sustainability reports to share with stakeholders.


### Contact
For questions, suggestions, or feedback, please contact:
- **Your Name**: k23018110@kcl.ac.uk
- **Project Repository**: [GitHub Repository](https://github.com/yaohahahh/Sustainability_Assessment_Platform)

---

Thank you for using the University Sustainability Assessment Platform! Together, we can make a difference in promoting sustainable practices in higher education institutions.
