# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: prime-numbers.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='prime-numbers.proto',
  package='',
  syntax='proto3',
  serialized_options=b'\n#com.crazynerve.grpctutorials.modelsP\001',
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\x13prime-numbers.proto\"\x18\n\x06Number\x12\x0e\n\x06number\x18\x01 \x01(\x05\"\x1d\n\nNumberList\x12\x0f\n\x07numbers\x18\x01 \x03(\x05\"\x1e\n\x0c\x42oolResponse\x12\x0e\n\x06result\x18\x01 \x01(\x08\x32\xa3\x01\n\rNumberService\x12&\n\x0c\x63heckIfPrime\x12\x07.Number\x1a\r.BoolResponse\x12 \n\nfindPrimes\x12\x07.Number\x1a\x07.Number0\x01\x12#\n\tprimeList\x12\x07.Number\x1a\x0b.NumberList(\x01\x12#\n\x0bprimeStream\x12\x07.Number\x1a\x07.Number(\x01\x30\x01\x42\'\n#com.crazynerve.grpctutorials.modelsP\x01\x62\x06proto3'
)




_NUMBER = _descriptor.Descriptor(
  name='Number',
  full_name='Number',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='number', full_name='Number.number', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=23,
  serialized_end=47,
)


_NUMBERLIST = _descriptor.Descriptor(
  name='NumberList',
  full_name='NumberList',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='numbers', full_name='NumberList.numbers', index=0,
      number=1, type=5, cpp_type=1, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=49,
  serialized_end=78,
)


_BOOLRESPONSE = _descriptor.Descriptor(
  name='BoolResponse',
  full_name='BoolResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='result', full_name='BoolResponse.result', index=0,
      number=1, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=80,
  serialized_end=110,
)

DESCRIPTOR.message_types_by_name['Number'] = _NUMBER
DESCRIPTOR.message_types_by_name['NumberList'] = _NUMBERLIST
DESCRIPTOR.message_types_by_name['BoolResponse'] = _BOOLRESPONSE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

Number = _reflection.GeneratedProtocolMessageType('Number', (_message.Message,), {
  'DESCRIPTOR' : _NUMBER,
  '__module__' : 'prime_numbers_pb2'
  # @@protoc_insertion_point(class_scope:Number)
  })
_sym_db.RegisterMessage(Number)

NumberList = _reflection.GeneratedProtocolMessageType('NumberList', (_message.Message,), {
  'DESCRIPTOR' : _NUMBERLIST,
  '__module__' : 'prime_numbers_pb2'
  # @@protoc_insertion_point(class_scope:NumberList)
  })
_sym_db.RegisterMessage(NumberList)

BoolResponse = _reflection.GeneratedProtocolMessageType('BoolResponse', (_message.Message,), {
  'DESCRIPTOR' : _BOOLRESPONSE,
  '__module__' : 'prime_numbers_pb2'
  # @@protoc_insertion_point(class_scope:BoolResponse)
  })
_sym_db.RegisterMessage(BoolResponse)


DESCRIPTOR._options = None

_NUMBERSERVICE = _descriptor.ServiceDescriptor(
  name='NumberService',
  full_name='NumberService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_start=113,
  serialized_end=276,
  methods=[
  _descriptor.MethodDescriptor(
    name='checkIfPrime',
    full_name='NumberService.checkIfPrime',
    index=0,
    containing_service=None,
    input_type=_NUMBER,
    output_type=_BOOLRESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='findPrimes',
    full_name='NumberService.findPrimes',
    index=1,
    containing_service=None,
    input_type=_NUMBER,
    output_type=_NUMBER,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='primeList',
    full_name='NumberService.primeList',
    index=2,
    containing_service=None,
    input_type=_NUMBER,
    output_type=_NUMBERLIST,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='primeStream',
    full_name='NumberService.primeStream',
    index=3,
    containing_service=None,
    input_type=_NUMBER,
    output_type=_NUMBER,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
])
_sym_db.RegisterServiceDescriptor(_NUMBERSERVICE)

DESCRIPTOR.services_by_name['NumberService'] = _NUMBERSERVICE

# @@protoc_insertion_point(module_scope)
