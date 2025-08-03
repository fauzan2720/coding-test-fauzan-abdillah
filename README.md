# Coding Test - Fauzan Abdillah

Aplikasi Android sederhana menggunakan Kotlin yang mengimplementasikan fitur login, dashboard dengan daftar postingan, dan halaman bookmark. Arsitektur menggunakan MVP dengan pendekatan Clean Code Architecture, serta pengujian dilakukan dengan Unit Test dan Instrumentation Test.

---

## 📱 Fitur

### 🔐 Login
- Dummy login dengan username & password.
- Validasi hanya jika field **tidak kosong**.
- Username dikirim ke halaman dashboard melalui **Intent**.
- UI dibangun dengan **RelativeLayout**.

### 📋 Dashboard
- Menampilkan **username login**.
- Menampilkan daftar **post dari API**:  
  `https://jsonplaceholder.typicode.com/posts`
- Menampilkan gambar dari:  
  `https://picsum.photos/id/{id}/200/200`
- Menggunakan **Retrofit**, **Koin** untuk dependency injection, dan **MVP Architecture**.
- UI dibangun menggunakan **ConstraintLayout**.

### 🔖 Bookmarks
- Menampilkan daftar post yang telah disimpan ke local.
- Disimpan menggunakan **SharedPreferences**.
- UI menggunakan **LinearLayout**.

---

## 🧪 Implementasi Testing

### ✅ Unit Test
Menggunakan **JUnit + Mockito** untuk menguji:
- RemoteDatasource: mengecek apakah API mengembalikan data sesuai atribut `id`, `title`, dan `body`.
- Repository: memastikan data berhasil dimapping dari `PostResponseModel` ke `PostModel`.

### 📲 Instrumentation Test
Menggunakan **Espresso** untuk menguji interaksi UI:
- Mengetik pada form login dan klik tombol login.

---

## ⚙️ Build & Jalankan Proyek

### Persyaratan
- Android Studio Giraffe (atau terbaru)
- JDK 17
- Emulator / Perangkat fisik

### Cara Menjalankan
1. **Clone repositori:**
   ```bash
   git clone https://github.com/fauzan2720/coding-test-fauzan-abdillah.git
   cd coding-test-fauzan-abdillah
   ```
2. Buka di Android Studio, lalu sync Gradle.
3. Jalankan aplikasi via Run ‘app’ atau Shift + F10.

## 🔬 Menjalankan Tes

### Unit Test:
```bash
./gradlew testDebugUnitTest
```

### Instrumentation Test:
Pastikan emulator aktif.
```bash
./gradlew connectedDebugAndroidTest
```
