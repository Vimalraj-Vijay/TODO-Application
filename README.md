TODO Application

Overview
This project is an Single Page Android TODO application built using Jetpack Compose for the UI and Clean Architecture principles for structuring the codebase. The application allows users to manage their tasks efficiently with features such as adding, updating, deleting, and viewing tasks.
Features

Add new tasks
Update existing tasks
Delete tasks
View a list of tasks
Mark tasks as completed or pending

App ScreenShots: 

<img width="250" alt="Screenshot 2025-01-03 at 2 57 00 PM" src="https://github.com/user-attachments/assets/31163940-77f9-4ac9-a245-e75389be6be7" /> <img width="250" alt="Screenshot 2025-01-03 at 2 57 11 PM" src="https://github.com/user-attachments/assets/f754c4b8-9e79-4f73-8f3f-60c73236ff5b" />  <img width="250" alt="Screenshot 2025-01-03 at 3 01 36 PM" src="https://github.com/user-attachments/assets/c47f258b-3313-4901-b803-ba99772decc8" />


Architecture
The application follows the Clean Architecture approach, which divides the project into distinct layers, each with its own responsibility. This separation of concerns makes the codebase more modular, testable, and maintainable.

Tech Stack

Kotlin: Programming language used for the entire project.
Jetpack Compose: Modern toolkit for building native Android UI.
Hilt: Dependency Injection library.
Room: Persistence library for local database management.
Coroutines: For asynchronous programming.
ViewModel: Lifecycle-aware components to store and manage UI-related data.
Repository Pattern: For data management and abstraction.

Project Structure:

<img width="380" alt="Screenshot 2025-01-03 at 2 54 19 PM" src="https://github.com/user-attachments/assets/22460dc1-9f62-4ff6-b5e2-ac7badf35c22" />      <img width="380" alt="Screenshot 2025-01-03 at 2 55 23 PM" src="https://github.com/user-attachments/assets/c05d637b-4bb7-424a-92bc-09630f13d670" />


Getting Started
Prerequisites

Android Studio Arctic Fox or later
Kotlin 1.5.21 or later
Gradle 7.0.2 or later

Installation

Clone the repository:Bashgit clone https://github.com/Vimalraj-Vijay/TODO-Application.git

Open the project in Android Studio.
Sync the project with Gradle files.
Run the application on an emulator or physical device.

Configuration

Ensure you have the necessary dependencies in your build.gradle files.
Configure Hilt for dependency injection.
Set up Room database for local data storage.

Usage

Launch the application.
Use the UI to add, update, delete, and view tasks.
Mark tasks as completed or pending by tapping on them.

Contributing

Fork the repository.
Create a new branch (git checkout -b feature/your-feature-name).
Commit your changes (git commit -am 'Add some feature').
Push to the branch (git push origin feature/your-feature-name).
Create a new Pull Request.


Acknowledgements

Jetpack Compose documentation
Clean Architecture principles by Robert C. Martin
Android Developer documentation
