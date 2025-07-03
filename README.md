# Star Wars API Explorer
An Android application that lets you explore the Star Wars universe through the SWAPI (Star Wars API). Search for starships, films, or vehicles and view their details.

## What This App Does
Ever wondered about the technical specs of the Millennium Falcon or wanted to revisit the opening crawl of The Empire Strikes Back? This app has you covered. Simply type in what you're looking for - whether it's "starships", "films", or "vehicles" - and get comprehensive results from the Star Wars API.

## Key Features

- **Smart Search**: Enter your search term and get instant results
- **Comprehensive Data**: See everything from starship crew capacity to film release dates
- **Error Handling**: When you type in the wrong category, you'll get a helpful error message
- **Persistent Results**: Your last search persists until you either clear it or type something new
- **Responsive Design**: Works in both portrait and landscape modes
- **Sorting Options**: Organise results by name or title to find what you need quickly

## What You'll See

### For Starships & Vehicles
- Name and model
- Manufacturer
- Cost in credits (if you're in the market for a new ride)
- Length and crew capacity
- Passenger and cargo capacity

### For Films
- Title and episode number
- Director and producer credits
- Release date
- The iconic opening crawl text

## Architecture Highlights

This isn't just a quick hack - it's built with maintainability and expansion in mind:

- **Clean Architecture**: Separated concerns with data, domain, and presentation layers
- **MVVM Pattern**: ViewModels handle the business logic while keeping the UI clean
- **Repository Pattern**: Centralised data management for easy testing and future enhancements
- **Dependency Injection**: Using Hilt for clean, testable code
- **Network Layer**: Retrofit for robust API communication
- **Local Storage**: Datastore for persistent storage

## Project Structure

The code is organised into logical packages:
- `data/` - API calls, repositories, and data models
- `di/` - Dependency injection
- `domain/` - Business logic and use cases
- `ui/` - All the visual components and ViewModels
- `util/` - Helper classes and utilities

## Getting Started

1. Clone this repository
2. Open in Android Studio
3. Build and run on your device or emulator
4. Start exploring the Star Wars universe!

## Tech Stack

- **Kotlin** - Because modern Android development deserves modern language features
- **Jetpack Compose** - For building beautiful, reactive UIs
- **Retrofit** - Reliable networking
- **Hilt** - Dependency injection made simple
- **Datastore** - Persistent storage for app state
- **Coroutines** - Smooth asynchronous operations