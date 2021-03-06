#
# Autogenerated by Thrift Compiler (0.12.0)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TFrozenDict, TException, TApplicationException
from thrift.protocol.TProtocol import TProtocolException
from thrift.TRecursive import fix_spec

import sys

from thrift.transport import TTransport
all_structs = []


class DeviceType(object):
    AirCondition = 0
    AirConditionPro = 1
    VaccumRobot = 2
    VaccumRobotPro = 3
    BasicLed = 4
    BrightnessLed = 5
    ModeLed = 6

    _VALUES_TO_NAMES = {
        0: "AirCondition",
        1: "AirConditionPro",
        2: "VaccumRobot",
        3: "VaccumRobotPro",
        4: "BasicLed",
        5: "BrightnessLed",
        6: "ModeLed",
    }

    _NAMES_TO_VALUES = {
        "AirCondition": 0,
        "AirConditionPro": 1,
        "VaccumRobot": 2,
        "VaccumRobotPro": 3,
        "BasicLed": 4,
        "BrightnessLed": 5,
        "ModeLed": 6,
    }
fix_spec(all_structs)
del all_structs
