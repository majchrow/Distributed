namespace java rpc.thrift.smarthome
namespace py thrift_python.smarthome

enum DeviceType {
    AirCondition,
    AirConditionPro,
    VaccumRobot,
    VaccumRobotPro,
    BasicLed,
    BrightnessLed,
    ModeLed,
}

typedef map<string, DeviceType> devicesIds

service SmartHome {
     devicesIds getDevicesIds(),
}
