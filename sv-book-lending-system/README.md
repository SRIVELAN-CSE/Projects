# SV Book Lending System

## Overview
The SV Book Lending System is a web application designed to facilitate the lending and borrowing of books. It provides functionalities for both users and administrators, allowing users to register, log in, browse available books, and manage their borrowing history. Administrators can manage books, monitor transactions, and oversee user registrations.

## Features
- User registration and authentication
- Admin login and dashboard for managing books and transactions
- User dashboard for browsing and borrowing books
- Responsive design using Bootstrap
- Database integration for storing user and book information

## Project Structure
```
sv-book-lending-system
├── backend
│   ├── app.py
│   ├── models
│   │   └── __init__.py
│   ├── routes
│   │   ├── admin_routes.py
│   │   ├── auth_routes.py
│   │   └── user_routes.py
│   ├── services
│   │   └── database.py
│   └── templates
│       ├── admin_dashboard.html
│       ├── login.html
│       ├── register.html
│       ├── user_dashboard.html
│       └── welcome.html
├── frontend
│   ├── css
│   │   └── styles.css
│   ├── js
│   │   └── scripts.js
│   └── assets
│       └── favicon.ico
├── requirements.txt
└── README.md
```

## Installation
1. Clone the repository:
   ```
   git clone <repository-url>
   cd sv-book-lending-system
   ```

2. Install the required Python packages:
   ```
   pip install -r requirements.txt
   ```

3. Run the backend application:
   ```
   python backend/app.py
   ```

4. Open your web browser and navigate to `http://localhost:5000` to access the application.

## Usage
- **User Registration**: New users can create an account via the registration page.
- **User Login**: Existing users and admins can log in using their credentials.
- **Admin Dashboard**: Admins can manage books and view transaction history.
- **User Dashboard**: Users can browse available books and view their borrowing history.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.