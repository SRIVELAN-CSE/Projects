from flask import Blueprint, request, jsonify
from services.database import add_book, remove_book, get_all_books, get_transactions

admin_routes = Blueprint('admin_routes', __name__)

@admin_routes.route('/admin/add_book', methods=['POST'])
def add_new_book():
    data = request.json
    title = data.get('title')
    author = data.get('author')
    isbn = data.get('isbn')
    if add_book(title, author, isbn):
        return jsonify({"message": "Book added successfully!"}), 201
    return jsonify({"message": "Failed to add book."}), 400

@admin_routes.route('/admin/remove_book/<int:book_id>', methods=['DELETE'])
def remove_existing_book(book_id):
    if remove_book(book_id):
        return jsonify({"message": "Book removed successfully!"}), 200
    return jsonify({"message": "Failed to remove book."}), 404

@admin_routes.route('/admin/books', methods=['GET'])
def list_books():
    books = get_all_books()
    return jsonify(books), 200

@admin_routes.route('/admin/transactions', methods=['GET'])
def list_transactions():
    transactions = get_transactions()
    return jsonify(transactions), 200