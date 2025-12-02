# Encyclopedia App

A unified Android encyclopedia application for exploring Pokémon and Digimon, built with modern Android development practices and Jetpack Compose.

## Highlights

- **Single Codebase, Dual Apps**: Pokemon and Digimon apps built from the same source using Product Flavors
- **Clean Architecture**: Modular design with clear separation of concerns
- **Modern UI**: 100% Jetpack Compose with Material 3 Design
- **Type-Safe Navigation**: Kotlin Serialization for navigation arguments

## Architecture

### Modular Structure
```
├── app/                    # Application entry point & navigation
├── core/
│   └── common/            # Shared utilities, themes, DI modules
└── feature/
    └── creature/          # Main feature module
        ├── main/          # Shared domain & presentation layers
        ├── pokemon/       # Pokemon-specific data implementation
        └── digimon/       # Digimon-specific data implementation
```

### MVI Pattern

- **Model**: Immutable state representing UI state
- **View**: Composable functions observing state
- **Intent**: User actions triggering state updates

### Clean Architecture Layers

- **Presentation**: Compose UI + ViewModels + MVI
- **Domain**: Business logic, Use Cases, Repository interfaces
- **Data**: API integration, DTOs, Repository implementations

## Product Flavors

Two app variants from a single codebase:

| Flavor | API | Theme Color |
|--------|-----|-------------|
| **Pokemon** | [PokéAPI](https://pokeapi.co/) | Red (#91E8F3) |
| **Digimon** | [Digi-API](https://digi-api.com/) | Blue (#F5C3B8) |

## Tech Stack

- **UI**: Jetpack Compose, Material 3
- **Architecture**: MVI, Clean Architecture, Multi-module
- **DI**: Koin
- **Networking**: Retrofit, Moshi
- **Async**: Kotlin Coroutines, Flow
- **Image Loading**: Coil
- **Navigation**: Compose Navigation with Type Safety

## Features

### List Screen
- Grid layout with pagination
- Infinite scroll with load more
- Creature cards with images and types
- Loading states and error handling

### Detail Screen
- Creature image and basic info
- Stats visualization with animated bars
- Abilities and descriptions

## License

This project is created for educational purposes.