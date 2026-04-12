# 🌍 Météo Desktop — Europe & Maroc

> Application desktop de météo en temps réel pour les villes d'Europe et du Maroc, construite avec Electron + React et alimentée par une API Spring Boot.


---

## 📸 Screenshots

### 🌍 Vue Générale — Europe & Maroc
<img width="959" height="505" alt="App" src="https://github.com/user-attachments/assets/4e746cc9-35bc-4716-bceb-4a0562c58721" />



### 🇲🇦 Vue Régionale — Maroc
<img width="959" height="503" alt="App2" src="https://github.com/user-attachments/assets/a52472d4-3fcc-4bd9-bb82-6bce60421dac" />


## 📸 Aperçu

```
┌─────────────────────────────────────────────────────────────┐
│  🌍 MÉTÉO EUROPE & MAROC          [Rechercher...]   ✅ 48 villes │
├──────────────┬──────────────────────────┬───────────────────┤
│  🇲🇦 Maroc   │                          │  Rabat            │
│  🇫🇷 France  │     🗺️ Carte Interactive  │  🌤 22°C          │
│  🇪🇸 Espagne │        (Leaflet)         │  💧 65%  💨 3m/s  │
│  🇮🇹 Italie  │                          │  ───────────────  │
│  🇬🇧 UK      │   [Marqueurs météo       │  Lever  🌅 06:12  │
│              │    colorés par temp.]    │  Coucher 🌇 19:45 │
└──────────────┴──────────────────────────┴───────────────────┘
```

---

## 🏗️ Architecture

```
Météo Desktop
├── 🖥️  Frontend          →  Electron + React + TypeScript
│   ├── Carte interactive  →  React-Leaflet
│   ├── Liste des villes   →  CityCard component
│   ├── Panneau détail     →  DetailPanel component
│   └── Recherche          →  SearchBar component
│
└── ⚙️  Backend           →  Java 21 + Spring Boot 3
    ├── REST API           →  Spring Web
    ├── Validation         →  Spring Validation
    └── Modèles            →  Lombok
```

---

## 🚀 Stack Technique

### Frontend
| Technologie | Version | Rôle |
|---|---|---|
| Electron | 35 | Application desktop native |
| React | 19 | Interface utilisateur |
| TypeScript | 5.8 | Typage statique |
| React-Leaflet | 5.0 | Carte interactive |
| Axios | 1.x | Appels API REST |
| Webpack | 5 | Bundler |

### Backend
| Technologie | Version | Rôle |
|---|---|---|
| Java | 21 | Langage backend |
| Spring Boot | 3.x | Framework REST API |
| Spring Web | - | Endpoints HTTP |
| Spring Validation | - | Validation des données |
| Lombok | - | Réduction du boilerplate |

---

## ✨ Fonctionnalités

- 🗺️ **Carte interactive** avec marqueurs météo colorés selon la température
- 🌡️ **Données en temps réel** — température, humidité, vent, pression, nuages
- 🔍 **Recherche** de villes en temps réel
- 🌍 **Filtres par région** — Maroc, France, Espagne, Italie, Royaume-Uni
- 📊 **Panneau détaillé** — ressenti, min/max, lever/coucher du soleil, visibilité
- 🎨 **Couleurs dynamiques** selon la température (bleu → rouge)
- 🏙️ **Multi-pays** — villes d'Europe et du Maroc

---

## 📁 Structure du Projet

```
Meteo-Front-End/
├── src/
│   ├── main/               # Processus principal Electron
│   ├── renderer/
│   │   ├── App.tsx         # Composant racine + utilitaires partagés
│   │   ├── index.tsx       # Point d'entrée React
│   │   └── App.css         # Styles globaux
│   ├── components/
│   │   ├── MapMaroc.jsx    # Carte Leaflet interactive
│   │   ├── CityCard.jsx    # Carte ville dans la liste
│   │   ├── DetailPanel.jsx # Panneau météo détaillé
│   │   └── SearchBar.jsx   # Barre de recherche
│   ├── services/
│   │   └── weatherService.ts  # Appels API
│   └── types.ts            # Interfaces TypeScript (City, etc.)
├── .erb/
│   └── configs/            # Configuration Webpack
├── assets/                 # Icônes et ressources
└── package.json
```

---

## ⚡ Installation & Lancement

### Prérequis
- Node.js >= 18
- Java 21
- npm >= 9

### 1. Cloner le projet
```bash
git clone https://github.com/ton-username/meteo-front-end.git
cd meteo-front-end
```

### 2. Lancer le Backend Spring Boot
```bash
# Dans le dossier backend
cd meteo-backend
./mvnw spring-boot:run
# API disponible sur http://localhost:8080
```

### 3. Lancer le Frontend Electron
```bash
npm install
npm start
```

---

## 🔌 API Endpoints (Spring Boot)

| Méthode | Endpoint | Description |
|---|---|---|
| `GET` | `/api/weather/all` | Toutes les villes |
| `GET` | `/api/weather/region/{region}` | Villes par région |
| `GET` | `/api/weather/{city}` | Météo d'une ville |

### Exemple de réponse
```json
{
  "city": "Rabat",
  "temperature": 22.5,
  "condition": "Partly cloudy",
  "humidity": 65,
  "windSpeed": 3.2,
  "windDegree": 180,
  "pressure": 1013,
  "visibility": 10000,
  "cloudiness": 40,
  "feelsLike": 21.8,
  "tempMin": 18.0,
  "tempMax": 25.0,
  "sunrise": 1712896800,
  "sunset": 1712943600,
  "lat": 34.01,
  "lon": -6.83,
  "icon": "02d"
}
```

---

## 🗺️ Régions Supportées

| Région | Pays |
|---|---|
| 🇲🇦 Maroc | Rabat, Casablanca, Marrakech, Fès, Tanger, Tétouan... |
| 🇫🇷 France | Paris, Lyon, Marseille, Bordeaux, Nice... |
| 🇪🇸 Espagne | Madrid, Barcelone, Séville, Valence... |
| 🇮🇹 Italie | Rome, Milan, Florence, Naples... |
| 🇬🇧 Royaume-Uni | Londres, Manchester, Édimbourg... |

---

## 🛠️ Configuration

### tsconfig.json
```json
{
  "ts-node": { "transpileOnly": true },
  "compilerOptions": {
    "module": "CommonJS",
    "moduleResolution": "node",
    "target": "es2022"
  }
}
```

### Variables d'environnement
```env
PORT=1212
API_URL=http://localhost:8080
```

---

## 👨‍💻 Auteur

**Mohamed Chaabi** — [@ton-github](https://github.com/mChaabi)

---
