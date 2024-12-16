# Weather Tracker

This is a simple weather tracking app built using Kotlin and Jetpack Compose. The app allows users to fetch weather information for a city and persist the selected city for future use.

---

## Tech Stack

### Language
- **Kotlin**

### UI
- **Jetpack Compose**

### Architecture
- **Clean Architecture** with **MVVM** for modular, testable, and maintainable code.

### Libraries Used
- **Dagger Hilt**: For Dependency Injection.  
- **Retrofit**: For networking and API integration.  
- **Coil**: For image loading (weather condition icons).  
- **DataStore**: For local persistent storage.

---

## Project Setup

### Prerequisites
- **Android Studio Flamingo** or higher.  
- **Gradle 8.0** or higher.  
- API Key from [WeatherAPI.com](https://www.weatherapi.com/).

### Steps to Run the Project
1. Clone the repository:
   ```bash
   git clone <repository-url>

	2.	Open the project in Android Studio.
	3.	Add your WeatherAPI key in the local.properties file:

WEATHER_API_KEY=your_api_key


	4.	Sync Gradle and build the project.
	5.	Run the app on an emulator or physical device.

How It Works

The app fetches weather information for a city using WeatherAPI.com and displays the details. The selected city is stored using DataStore and loaded automatically when the app restarts.

Screenshots
![wt_1](https://github.com/user-attachments/assets/ebed6dbd-3973-433c-8aac-9af74aaac12b)
![wt2](https://github.com/user-attachments/assets/ea09dfe5-0937-4985-8885-c1c6e0f2eec9)
![wt_3](https://github.com/user-attachments/assets/a2ca724f-9781-4ab6-8790-64c7e3ddb8dc)

