package rpc.server.handlers.leds;

import rpc.server.handlers.shared.BaseDeviceHandler;
import rpc.thrift.leds.BasicLed;
import rpc.thrift.leds.Colour;
import rpc.thrift.leds.InvalidColourException;
import rpc.thrift.shared.DeviceNotActiveException;

public class BasicLedHandler extends BaseDeviceHandler implements BasicLed.Iface {

    public Colour colour;

    public BasicLedHandler() {
        super();
        this.colour = new Colour(0, 0, 0);
    }

    public boolean ifNotValidRange(double channel) {
        return channel < 0. || channel > 255.;
    }

    public void colourCheck(Colour color) throws InvalidColourException {
        if (ifNotValidRange(color.red) || ifNotValidRange(color.blue) || ifNotValidRange(color.green)) {
            throw new InvalidColourException(color, String.format(
                    "All RGB channels should be in range [%f, %f]", 0., 255.));
        }
    }

    @Override
    public Colour changeColour(Colour colour) throws DeviceNotActiveException, InvalidColourException {
        System.out.println("ChangeColour call");
        this.ifNotActiveCheck();
        this.colourCheck(colour);
        this.colour = colour;
        return colour;
    }
}
