from flask import Blueprint, request, jsonify
from services.database import get_books, borrow_book, return_book, get_user_books

user_routes = Blueprint('user_routes', __name__)

@user_routes.route('/books', methods=['GET'])
def list_books():
    books = get_books()
    return jsonify(books)

@user_routes.route('/borrow/<int:book_id>', methods=['POST'])
def borrow(book_id):
    user_id = request.json.get('user_id')
    result = borrow_book(user_id, book_id)
    return jsonify(result)

@user_routes.route('/return/<int:book_id>', methods=['POST'])
def return_book_route(book_id):
    user_id = request.json.get('user_id')
    result = return_book(user_id, book_id)
    return jsonify(result)

@user_routes.route('/my-books', methods=['GET'])
def my_books():
    user_id = request.args.get('user_id')
    books = get_user_books(user_id)
    return jsonify(books)