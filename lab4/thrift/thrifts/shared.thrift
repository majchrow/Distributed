namespace java rpc.thrift.shared
namespace py thrift_python.shared

exception DeviceNotActiveException {
  1: string reason
}

exception DeviceActiveException {
  1: string reason
}

enum DeviceState {
  OFF,
  ON,
}

service BaseDevice {
   DeviceState turnOn() throws (1: DeviceActiveException deviceActiveException),
   DeviceState turnOff() throws (1: DeviceNotActiveException deviceNotActiveException),
}
