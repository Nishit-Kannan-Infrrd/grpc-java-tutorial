from flask import request, jsonify
import prime_numbers_pb2_grpc, prime_numbers_pb2

import os

from flask import Flask
import grpc

app = Flask(__name__)

prime_number_host = os.getenv("RECOMMENDATIONS_HOST", "localhost")
prime_number_channel = grpc.insecure_channel(
    f"{prime_number_host}:5050"
)
client = prime_numbers_pb2_grpc.NumberServiceStub(prime_number_channel)


def make_stream(product_id):
    return prime_numbers_pb2.Number(number=product_id)


def generate_messages(db):
    messages = [(make_stream(res['_source']['product_id'])) for res in db]
    print(f"no. of messages are {len(messages)}")
    for index, msg in enumerate(messages):
        print("Sending %s at %s" % (index, msg))
        yield msg

def generate_client_stream(req_obj):
    for num in range(2, req_obj.number):
        yield prime_numbers_pb2.Number(number=num)


def prime_numbers_unary(req_obj):
    response = []
    print(req_obj.number)
    for num in range(2, req_obj.number):
        request = prime_numbers_pb2.Number(number=num)
        server_response = client.checkIfPrime(request)
        if server_response.result:
            response.append(num)
    print(f"response for checkIfPrime method is {response}")
    return response


def prime_numbers_server_stream(req_obj):
    request = prime_numbers_pb2.Number(number=req_obj.number)
    response = client.findPrimes(request)
    print(f"response for FindPrime method is {response}")
    return response


def prime_numbers_client_stream(req_obj):
    response = client.primeList(generate_client_stream(req_obj))
    return response


def prime_numbers_bidi_stream(req_obj):
    response = client.primeStream(generate_client_stream(req_obj))
    return response

