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

<table style="width: 100%;">
  <tr>
    <th>Flavor</th>
    <th>API</th>
    <th>Theme Color</th>
    <th>Demo</th>
  </tr>
  <tr>
    <td><strong>Pokemon</strong></td>
    <td><a href="https://pokeapi.co/">PokéAPI</a></td>
    <td>
      <img src="https://placehold.co/15x15/91E8F3/91E8F3.png" alt="color"> Aqua Blue <code>#91E8F3</code>
    </td>
    <td><img src="https://github.com/user-attachments/assets/f0ff954c-9245-4a67-852e-153f800fd191" alt="Pokemon App Demo" width="200"></td>
  </tr>
  <tr>
    <td><strong>Digimon</strong></td>
    <td><a href="https://digi-api.com/">Digi-API</a></td>
    <td>
      <img src="https://placehold.co/15x15/F5C3B8/F5C3B8.png" alt="color"> Peach Pink <code>#F5C3B8</code>
    </td>
    <td><img src="https://github.com/user-attachments/assets/acfbe93f-baad-40eb-9814-b6d6fc897872" alt="Digimon App Demo" width="200"></td>
  </tr>
</table>

## Tech Stack

- **UI**: Jetpack Compose, Material 3
- **Architecture**: MVI, Clean Architecture, Multi-module
- **DI**: Koin
- **Networking**: Retrofit, Moshi
- **Async**: Kotlin Coroutines, Flow
- **Pagination**: Paging 3
- **Image Loading**: Coil
- **Navigation**: Compose Navigation with Type Safety

## Features

### List Screen
- Grid layout with Paging 3 integration
- Infinite scroll with automatic page loading
- Pull-to-refresh support
- Creature cards with images and types
- Loading states and error handling with retry

### Detail Screen
- Creature image and basic info
- Stats visualization with animated bars
- Abilities and descriptions

## License

This project is created for educational purposes.
