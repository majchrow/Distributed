include "shared.thrift"

namespace java rpc.thrift.vaccum
namespace py thrift_python.vaccum

enum Room {
  KITCHEN,
  BATHROOM,
  LIVINGROOM
}

typedef set<Room> Rooms

exception InvalidOrderException {
  1: Rooms rooms,
  2: string reason
}

struct Config {
    1: bool autoCharge;
    2: bool autoGarbageDispose;
    3: bool autoClean;
}

service VaccumRobot extends shared.BaseDevice {
    Rooms cleanRooms(1: Rooms rooms) throws (1:shared.DeviceNotActiveException deviveNotActiveException,
                                                      2:InvalidOrderException invalidOrderException),
}

service VaccumRobotPro extends VaccumRobot {
    Config setConfig(1: Config config) throws (1:shared.DeviceNotActiveException deviveNotActiveException),
    Config getConfig() throws (1:shared.DeviceNotActiveException deviveNotActiveException),
}
