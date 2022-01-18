from flask import request, jsonify
from flask_accepts import accepts, responds
from flask_restx import Resource, Namespace
from dataclasses import dataclass, field
from typing import Type, List, Any
import prime_number_client
import json

import marshmallow_dataclass

api = Namespace("grpc")


def get_schema(typee: Type):
    return marshmallow_dataclass.class_schema(typee)


@dataclass()
class DataPrepareRequest:
    number: int = 0


@dataclass
class DataPrepareResponse:
    result: bool = True


@dataclass()
class DataPrepareResponseList:
    numberList: List[int]


@api.route("/unary")
class checkIfPrime(Resource):
    @accepts(schema=get_schema(DataPrepareRequest), api=api)
    @responds(schema=get_schema(DataPrepareResponse), api=api)
    def post(self):
        data_prepare_obj = request.parsed_obj
        res = prime_number_client.prime_numbers_unary(data_prepare_obj)
        return jsonify(res)

@api.route("/server_stream")
class findPrimes(Resource):
    @accepts(schema=get_schema(DataPrepareRequest), api=api)
    @responds(schema=get_schema(DataPrepareRequest), api=api)
    def post(self):
        response = []
        data_prepare_obj = request.parsed_obj
        res = prime_number_client.prime_numbers_server_stream(data_prepare_obj)
        for item in res:
            response.append(item.number)
        return jsonify(response)


@api.route("/client_stream")
class primeList(Resource):
    @accepts(schema=get_schema(DataPrepareRequest), api=api)
    @responds(schema=get_schema(DataPrepareResponseList), api=api)
    def post(self):
        response = []
        data_prepare_obj = request.parsed_obj
        res = prime_number_client.prime_numbers_client_stream(data_prepare_obj)
        for item in res.numbers:
            response.append(item)
        return jsonify(response)


@api.route("/bidi_stream")
class primeStream(Resource):
    @accepts(schema=get_schema(DataPrepareRequest), api=api)
    @responds(schema=get_schema(DataPrepareRequest), api=api)
    def post(self):
        response = []
        data_prepare_obj = request.parsed_obj
        res = prime_number_client.prime_numbers_bidi_stream(data_prepare_obj)
        for item in res:
            response.append(item.number)
        return jsonify(response)

