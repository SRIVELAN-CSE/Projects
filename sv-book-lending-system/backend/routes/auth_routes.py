from flask import Blueprint, request, jsonify
from services.database import create_user, get_user_by_email, verify_user

auth_routes = Blueprint('auth_routes', __name__)

@auth_routes.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    email = data.get('email')
    password = data.get('password')
    
    if create_user(email, password):
        return jsonify({"message": "User registered successfully."}), 201
    return jsonify({"message": "User registration failed."}), 400

@auth_routes.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    email = data.get('email')
    password = data.get('password')
    
    user = get_user_by_email(email)
    if user and verify_user(user, password):
        return jsonify({"message": "Login successful."}), 200
    return jsonify({"message": "Invalid credentials."}), 401