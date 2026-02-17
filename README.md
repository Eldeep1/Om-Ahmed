# Om Ahmed - Recipe Discovery App 🍳

A modern Android application for discovering, saving, and planning your favorite meals. Browse recipes from around the world, save your favorites, and plan your weekly meals with ease.

## Features 

###  Home
- Daily meal recommendations
- Browse meals by category
- View popular recipes
- Quick access to favorites

###  Search
- Search recipes by name
- Filter by category (Breakfast, Lunch, Dinner, Dessert, etc.)
- Filter by country/cuisine (American, Italian, Chinese, Egyptian, etc.)
- Real-time search with debouncing
- Grid view of search results

### Favorites
- Save your favorite recipes
- Quick access to saved meals
- Toggle favorites on/off
- Persistent storage

###  Meal Planner
- Plan meals for specific dates
- Calendar view to select dates
- View planned meals for each day
- Organize your weekly meal schedule

###  Recipe Details
- High-quality recipe images
- Complete ingredient lists with measurements
- Step-by-step cooking instructions
- Video tutorials (YouTube integration)


## Technology Stack 

- **Language**: Java
- **Architecture**: MVP (Model-View-Presenter)
- **Networking**: Retrofit, RxJava3
- **Authintication**: Firebase Authentication
- **Local Database**: Room
- **Remote Database**: Firestore
- **Image Loading**: Glide
- **Navigation**: Android Navigation Component
- **Video Player**: AndroidYouTubePlayer
- **Async Operations**: RxJava
- **UI Components**: Material Design 3

## API 

This app uses [TheMealDB API](https://www.themealdb.com/api.php) to fetch recipe data.

**Important:** The app requires an **active internet connection** for the best experience:
-  Fetching recipes and categories
-  Loading recipe images
-  Streaming cooking videos
-  Getting latest meal recommendations

Some features work offline:
-  Viewing saved favorites
-  Accessing meal plans
-  Viewing previously loaded recipes

## Requirements 

- Android Studio Arctic Fox or later
- Android SDK 24+ (Android 7.0+)
- Target SDK 34 (Android 14)
- Java 8+

## Installation 

1. **Clone the repository**
   ```bash
   git clone https://github.com/Eldeep1/Om-Ahmed.git
   cd om-ahmed
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory
   - Wait for Gradle sync to complete

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click "Run" or press `Shift + F10`
   - Make sure your device has internet connectivity

## Key Features Implementation 

### MVP Architecture
Each screen follows the MVP pattern:
- **Model**: Data layer (repositories, database, API)
- **View**: UI layer (Fragments, Activities)
- **Presenter**: Business logic layer

### RxJava Integration
- Asynchronous data fetching
- Search debouncing (300ms delay)
- Stream processing for filtering

### Room Database
- Offline storage for favorites
- Meal planning persistence
- Type converters for Date objects

### Material Design
- Material 3 components
- Custom themes and colors
- Smooth animations and transitions

## Configuration 

### API Configuration
The app uses TheMealDB API. No API key is required for the free tier.

### Database Configuration
Room database is automatically created on first app launch.

## Usage Tips 

1. **First Launch**: Make sure you have an active internet connection to load initial data
2. **Offline Mode**: Previously loaded recipes and favorites are available offline
3. **Search**: Use filters to narrow down results by category and country
4. **Meal Planning**: Select dates in the calendar to plan meals for specific days
5. **Favorites**: Tap the heart icon on any recipe to save it to favorites




## Copyrights

All Copyrights reserved for deopogramming company

## Acknowledgments 

- [TheMealDB](https://www.themealdb.com/) for providing the recipe API
- [Material Design](https://material.io/) for design guidelines
- All open-source libraries used in this project



---

 **Note**: This app works best with an active internet connection. While some features are available offline, an internet connection is required for the full experience including recipe discovery, images, and video tutorials.

Made with ❤️ by Depogramming