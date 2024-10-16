# Educational Software (2024) - JSLearner Android App

## Project Overview

The JSLearner Mobile App is an educational platform designed to teach JavaScript programming through a dynamic, interactive and adaptive user interface. The app is developed using Kotlin and Jetpack Compose for Android, offering lessons, quizzes and progress tracking to enhance user learning at various levels of difficulty. It supports beginners, intermediate learners, and advanced users, providing personalized content based on performance and progress.

This project was developed for the "Educational Software" course in the 8th semester at the University of Piraeus, Department of Informatics (Academic Year 2023-2024).

## Course Information

- **Institution:** University of Piraeus
- **Department:** Department of Informatics
- **Course:** Educational Software (2024)
- **Semester:** 8th

## Technologies Used

- Kotlin
- Jetpack Compose
- Firebase Authentication
- Firebase Realtime Database
- Firestore

## App Features

1. **Learning Modules and Difficulty Levels**

    The app supports three levels of learning:
   - **Beginner:** Introduces the basic of JavaScript, such as syntax, variables and functions.
   - **Intermediate:** Covers more complex topics like data manipulation, arrays and loops.
   - **Advanced:** Focuses on advanced JavaScript features such as asynchronous programming and event handling.

    Each module contains lessons, examples and interactive exercises to reinforce learning.

2. **Quizzes and Self-Assessment**

   - **Multiple Question Types:** Quizzes include multiple-choice questions, true/false and fill-in-the-blank exercises.
   - **Instant Feedback:** Users receive immediate feedback on their answer, which helps reinforce learning.
   - **Roadmap Overview:** Users can track their completed lessons and visualize their learning path through a roadmap.
  
3. **Progress Tracking and Leaderboard**

   - **Experience Points (XP):** The app tracks user progress with an XP system. Users earn or lose points based on their quiz performance.
   - **Leaderboard:** A global leaderboard ranks users based on their total XP. Users can compete with others in real-time.
   - **Roadmap Overview:** Users can track their completed lessons and visualize their learning path through a roadmap.

## Setup Instructions

1. Clone the repository:
    ```bash
    git clone  https://github.com/ApostolisSiampanis/JSLearner.git
    ```

2. Open the project in Android Studio.
3. Configure Firebase by adding the necessary credentials (Firebase configuration JSON).
4. Build and run the app on an Android device or emulator.

## Related Repositories
This project is part of a larger system, consisting of multiple repositories that work together to provide a complete application:

**[JSLearner Backend:](https://github.com/thkox/JSLearner-backend)** This repository contains the backend infrastructure for the JSLearner Android App, built using Firebase Cloud Functions and Cloud Scheduler. It serves as the core system responsible for amanaging user data, processing experience scores and maintaining leaderboards. The backend leverages Firebase Realtime Database, Firestore and Firebase Functions to trigger real-time updates and manage user experience levels dynamically.

## Contributors

<table>
  <tr>
    <td align="center"><a href="https://github.com/ApostolisSiampanis"><img src="https://avatars.githubusercontent.com/u/75365398?v=4" width="100px;" alt="Apostolis Siampanis"/><br /><sub><b>Apostolis Siampanis</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/thkox"><img src="https://avatars.githubusercontent.com/u/79880468?v=4" width="100px;" alt="Theodore Koxanoglou"/><br /><sub><b>Theodore Koxanoglou</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/AlexanderCholis"><img src="https://avatars.githubusercontent.com/u/66769337?v=4" width="100px;" alt="Alexander Cholis"/><br /><sub><b>Alexander Cholis</b></sub></a><br /></td>
  </tr>
</table>

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
