package rpc.server.handlers.leds;

import rpc.thrift.leds.BrightnessLed;
import rpc.thrift.leds.InvalidPercentException;
import rpc.thrift.shared.DeviceNotActiveException;

public class BrightnessLedHandler extends BasicLedHandler implements BrightnessLed.Iface {

    public double percent;

    public BrightnessLedHandler() {
        super();
        this.percent = 100.;
    }

    public void brightnessCheck(double percent) throws InvalidPercentException {
        if (percent > 100 || percent < 0) {
            throw new InvalidPercentException(percent, String.format(
                    "Percent should be in range [%f, %f]", 0., 100.));
        }
    }

    @Override
    public double setBrightness(double percent) throws DeviceNotActiveException, InvalidPercentException {
        System.out.println("Set Brightness call");
        this.ifNotActiveCheck();
        this.brightnessCheck(percent);
        this.percent = percent;
        return percent;
    }

    @Override
    public double getBrightness() throws DeviceNotActiveException {
        System.out.println("Get Brightness call");
        this.ifNotActiveCheck();
        return percent;
    }
}
