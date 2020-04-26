include "shared.thrift"

namespace java rpc.thrift.leds
namespace py thrift_python.leds

struct Colour {
  1: double red,
  2: double green,
  3: double blue,
}

exception InvalidColourException {
  1: Colour colour,
  2: string reason,
}

exception InvalidPercentException {
  1: double percent,
  2: string reason,
}

enum Mode {
    NONE,
    CHANGING,
    FLASHING,
}

service BasicLed extends shared.BaseDevice {
    Colour changeColour(1: Colour colour) throws (1: shared.DeviceNotActiveException deviceNotActiveException,
                                                  2: InvalidColourException invalidColourException),
}

service ModeLed extends BasicLed {
   Mode setMode(1: Mode mode) throws (1: shared.DeviceNotActiveException deviceNotActiveException),
   Mode getMode() throws (1: shared.DeviceNotActiveException deviceNotActiveException),
}

service BrightnessLed extends BasicLed {
   double setBrightness(1: double percent) throws (1: shared.DeviceNotActiveException deviceNotActiveException,
                                                   2: InvalidPercentException invalidPercentException),
   double getBrightness() throws (1: shared.DeviceNotActiveException deviceNotActiveException),
}
