package rpc.server.handlers.leds;

import rpc.thrift.leds.Mode;
import rpc.thrift.leds.ModeLed;
import rpc.thrift.shared.DeviceNotActiveException;

public class ModeLedHandler extends BasicLedHandler implements ModeLed.Iface {

    public Mode mode;

    public ModeLedHandler() {
        super();
        this.mode = Mode.NONE;
    }

    @Override
    public Mode setMode(Mode mode) throws DeviceNotActiveException {
        System.out.println("Set Mode call");
        this.ifNotActiveCheck();
        this.mode = mode;
        return mode;
    }

    @Override
    public Mode getMode() throws DeviceNotActiveException {
        System.out.println("Get Mode call");
        this.ifNotActiveCheck();
        return this.mode;
    }
}
