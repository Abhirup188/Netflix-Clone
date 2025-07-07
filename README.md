# 📺 Netflix Clone App

This is a simple Netflix-like clone built for my internship project.  
The app is developed in **Kotlin** using **Jetpack Compose**, **Retrofit**, **Room Database**, and **Firebase Authentication**.

---

## 🚀 Features

✅ **Login & Sign Up** — Secure authentication with Firebase  
✅ **Home Screen** — Displays trending, popular, top-rated, and upcoming movies using the TMDb API  
✅ **Search** — Search movies online & filter locally  
✅ **Watchlist** — Add/remove favorites stored locally using Room  
✅ **Detail Screen** — Click on a movie to see its details  
✅ **Profile Screen** — Displays your email & sign out option  
✅ **Bottom Navigation Bar** — Easily switch between Home, Search, Watchlist, and Profile  
✅ **Modern UI** — Built 100% with Jetpack Compose

---

## ⚠️ Important

> **To fetch movie data using the TMDb API, you must connect your Android device to a VPN!**

Some API endpoints may be blocked for your region.  
Connecting to a VPN (e.g., Proton VPN) ensures the API calls succeed.

---

## 🔑 Technologies Used

- **Kotlin** & **Jetpack Compose**  
- **Retrofit** for network calls  
- **Room** for local storage (favorites/watchlist)  
- **Firebase Authentication** (Email/Password)  
- **TMDb API** for movie data

---

## 🔗 Setup Instructions

1️⃣ Clone this repository  
2️⃣ Add your **TMDb API key** to `MovieApi` or `MovieRepository` as needed  
3️⃣ Make sure you have a Firebase project set up with **Authentication (Email/Password)** enabled  
4️⃣ Connect your device to a **VPN**  
5️⃣ Run the app on your physical device or emulator

---

## ✅ How to Use

- **Sign Up** with a valid email & password  
- **Browse movies** on the Home screen  
- **Search** for movies using keywords  
- **Add to Watchlist** by tapping the heart icon  
- **Access your Watchlist** from the bottom navigation  
- **View Details** of any movie by tapping its card  
- **Sign Out** from the Profile screen

---

## ⚡ Notes

- The watchlist is saved locally using Room Database — it persists even if the app is closed.
- The movie posters and details come live from the TMDb API.
- If the API is blocked in your region, you **must** use a VPN for the app to load movie data.

---

## 📧 Author

**Made by:** [Your Name Here]  
**For:** Internship Project Submission  
**College:** NIT Silchar

---

