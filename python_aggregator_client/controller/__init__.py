from flask_restx import Api

from controller import prime_number_finder


def register_routes(api: Api):
    api.add_namespace(prime_number_finder.api)


