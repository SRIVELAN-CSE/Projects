document.addEventListener('DOMContentLoaded', function() {
    // Function to handle user registration form submission
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Perform form validation and submission logic here
            alert('Registration form submitted!');
        });
    }

    // Function to handle user login form submission
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Perform login validation and submission logic here
            alert('Login form submitted!');
        });
    }

    // Function to dynamically load book data
    function loadBooks() {
        // Fetch book data from the backend and update the UI
        fetch('/api/books')
            .then(response => response.json())
            .then(data => {
                const bookList = document.getElementById('bookList');
                bookList.innerHTML = '';
                data.forEach(book => {
                    const listItem = document.createElement('li');
                    listItem.textContent = `${book.title} by ${book.author}`;
                    bookList.appendChild(listItem);
                });
            })
            .catch(error => console.error('Error loading books:', error));
    }

    // Load books on page load
    loadBooks();
});