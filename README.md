
Personal Expense Management App
Overview
This project is a Personal Expense Management application developed using Java with JavaFX for the graphical user interface and SQLite for the database. The application allows users to manage their personal finances by providing functionalities such as user registration, login, adding expenses, categorizing expenses, generating analytics, and exporting data.

Features
User Authentication:

Register: Allows new users to create an account.
Login: Allows existing users to log in to their account.
Expense Management:

Add Expense: Users can add expenses along with details such as amount, category, and date.
Categories: Expenses can be categorized for better organization (e.g., Food, Travel, Entertainment).
Analytics:

View Analytics: Users can view graphical representations of their spending habits over different periods (daily, weekly, monthly).
Category Analytics: Users can see how much they spend on each category.
Data Export:

Export Data: Users can export their expense data to a CSV file for backup or further analysis.
Prerequisites
Java Development Kit (JDK) 8 or later
NetBeans IDE 8.2 or later
JavaFX SDK
SQLite JDBC Driver
Installation
Clone the repository:

sh
Copiar código
git clone https://github.com/yourusername/expense-management-app.git
Open the project in NetBeans:

Open NetBeans IDE.
Click on File -> Open Project.
Navigate to the cloned repository and select it.
Add JavaFX library:

Right-click on the project in the Projects tab.
Select Properties.
Go to Libraries and click on Add Library.
Add the JavaFX SDK library.
Add SQLite JDBC Driver:

Download the SQLite JDBC driver from SQLite JDBC.
Right-click on the project in the Projects tab.
Select Properties.
Go to Libraries and click on Add JAR/Folder.
Add the downloaded SQLite JDBC JAR file.
Database Configuration
The SQLite database file (expenses.db) is located in the src/resources directory.
The database schema includes the following tables:
users: Stores user credentials.
expenses: Stores details of the expenses.
categories: Stores expense categories.
Running the Application
Build the project:

Right-click on the project in the Projects tab.
Select Clean and Build.
Run the project:

Right-click on the project in the Projects tab.
Select Run.
Project Structure
src/main/java: Contains the Java source files.

controller: Contains the controller classes for handling user input.
model: Contains the data model classes.
view: Contains the FXML files for the UI layout.
util: Contains utility classes for database connection and other helper functions.
src/resources: Contains resources such as the SQLite database file and CSS files for styling.

Usage
Register a new account: Open the application and click on the "Register" button to create a new account.
Login: Use your credentials to log in.
Add an expense: Navigate to the "Add Expense" section and fill in the details.
View analytics: Go to the "Analytics" section to view your spending patterns.
Export data: Click on the "Export" button to save your data as a CSV file.
License
This project is licensed under the MIT License - see the LICENSE file for details.

Contributing
Fork the repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -m 'Add some feature').
Push to the branch (git push origin feature-branch).
Create a new Pull Request.
Contact
For any inquiries or feedback, please contact your-email@example.com.

This README provides a comprehensive overview of the Personal Expense Management application, including its features, installation instructions, project structure, and usage guidelines.
