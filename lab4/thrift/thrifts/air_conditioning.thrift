include "shared.thrift"

namespace java rpc.thrift.aircondition
namespace py thrift_python.aircondition


enum AirPollution {
  BAD,
  NORMAL,
  GOOD,
}

exception InvalidTemperatureException {
  1: double temperature,
  2: string reason
}

service AirCondition extends shared.BaseDevice {
    double changeTemperature(1: double temperature) throws (1:shared.DeviceNotActiveException deviceNotActiveException,
                                                            2:InvalidTemperatureException invalidTemperatureException),
}

service AirConditionPro extends AirCondition{
   AirPollution checkAirPollution() throws (1:shared.DeviceNotActiveException deviceNotActiveException),
}
