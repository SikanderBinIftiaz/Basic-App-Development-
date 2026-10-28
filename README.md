# Sikander App - Android App (Java + XML)

**Assignment 2: Android Application Development**
**Author:** Sikander Bin Iftiaz

A simple Android application that demonstrates the common screens found in most apps: splash, login, registration, password recovery, a dashboard and a profile. Authentication and password recovery are simulated locally, so no backend is required.

## Implemented Features

### Screens
| Screen | Description |
|---|---|
| Splash | Shows the app logo and name, then opens Login (or Dashboard if the user is already logged in) |
| Login | Email/username and password inputs with Login, Register and Forgot Password options |
| Register | Name, email, password and confirm password with validation |
| Forgot Password | Accepts an email and starts a simulated password recovery |
| Reset Password | New password and confirm password with validation |
| Dashboard | Main modules shown as cards in a grid, each with an icon and text |
| Profile | Displays user information and provides a Logout option |

### Technical Requirements
- **Resources:** default resources in `strings.xml` and `values/colors.xml`, plus an alternative night-mode palette in `values-night/colors.xml`
- **Layouts:** `LinearLayout` (Login, Register, Forgot, Reset, Dashboard root), `GridLayout` (Dashboard cards) and `ConstraintLayout` (Splash, Profile)
- **Input controls:** `textEmailAddress` for email, `textPassword` (with show/hide toggle) for passwords and `textPersonName` for names
- **Button clicks and validation:** required fields, email format, minimum password length (6) and password match checks, with error messages shown under each field
- **Navigation:** explicit Intents between all Activities
- **Consistent UI:** one theme, one color palette and the same button and input styles on every screen

## Navigation Flow

```
Splash
  |
  v
Login ----------------------> Register --(success)--> Login
  |
  |----> Forgot Password --> Reset Password --(success)--> Login
  |
  |--(success)--> Dashboard --> Profile --(Logout)--> Login
```

1. **Splash** opens **Login** after 2 seconds.
2. **Login** leads to **Register**, **Forgot Password** or, on a successful login, **Dashboard**.
3. **Register** returns to **Login** after a successful registration.
4. **Forgot Password** opens **Reset Password**, which returns to **Login** after the password is saved.
5. **Dashboard** opens **Profile** from the Profile card.
6. **Profile** logs the user out and returns to **Login**.

## Demo Account
The app uses simulated, local authentication.

| Field | Value |
|---|---|
| Email | `demo@example.com` |
| Username | `demo` |
| Password | `Demo@123` |

Registering a new account replaces the demo account.

## Project Structure
```
app/src/main/
├── java/com/example/commonscreens/
│   ├── SplashActivity.java
│   ├── LoginActivity.java
│   ├── RegisterActivity.java
│   ├── ForgotPasswordActivity.java
│   ├── ResetPasswordActivity.java
│   ├── DashboardActivity.java
│   ├── ProfileActivity.java
│   ├── UserStore.java        (simulated local user storage)
│   └── Validators.java       (input validation helpers)
├── res/
│   ├── layout/               (one XML layout per screen)
│   ├── drawable/             (logo and icons)
│   ├── values/               (strings.xml, colors.xml, themes.xml)
│   └── values-night/         (night-mode colors.xml)
└── AndroidManifest.xml
```

## How to Run
1. Clone the repository: `git clone https://github.com/SikanderBinIftiaz/Basic-App-Development-.git`
2. Open the project in **Android Studio**.
3. Wait for the Gradle sync to finish.
4. Run the app on an emulator or a connected Android device.

## Tech Stack
- Language: Java
- UI: XML layouts with Material Components
- Minimum SDK: API 24
- IDE: Android Studio
