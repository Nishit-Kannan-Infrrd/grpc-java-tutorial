import os

from flask import Flask
from flask_cors import CORS
from flask_restx import Api

from controller import register_routes
from healthcheck import HealthCheck


def create_app():
    application = Flask(__name__)
    application.config['JSON_SORT_KEYS'] = False
    health = HealthCheck()
    CORS(application)
    application.add_url_rule("/data/healthcheck", "healthcheck", view_func=lambda: health.run())

    return application


app = create_app()
api = Api(app, version="0.2", title="GRPC", description="API for esting GRPC calls")
register_routes(api)

if __name__ == "__main__":
    app.run(debug=False, host='localhost', port=8080)