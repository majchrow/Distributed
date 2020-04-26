package rpc.server.handlers.shared;

import rpc.thrift.shared.BaseDevice;
import rpc.thrift.shared.DeviceActiveException;
import rpc.thrift.shared.DeviceNotActiveException;
import rpc.thrift.shared.DeviceState;

public class BaseDeviceHandler implements BaseDevice.Iface {

    public DeviceState deviceState;

    public BaseDeviceHandler() {
        this.deviceState = rpc.thrift.shared.DeviceState.OFF;
    }

    public void ifActiveCheck() throws DeviceActiveException {
        if (this.deviceState.equals(rpc.thrift.shared.DeviceState.ON)) {
            throw new DeviceActiveException("Device is turned on");
        }
    }

    public void ifNotActiveCheck() throws DeviceNotActiveException {
        if (this.deviceState.equals(rpc.thrift.shared.DeviceState.OFF)) {
            throw new DeviceNotActiveException("Device is turned off");
        }
    }


    @Override
    public DeviceState turnOn() throws DeviceActiveException {
        System.out.println("Turn On Call");
        this.ifActiveCheck();
        this.deviceState = rpc.thrift.shared.DeviceState.ON;
        return this.deviceState;
    }

    @Override
    public DeviceState turnOff() throws DeviceNotActiveException {
        System.out.println("Turn Off Call");
        this.ifNotActiveCheck();
        this.deviceState = rpc.thrift.shared.DeviceState.OFF;
        return this.deviceState;
    }
}
