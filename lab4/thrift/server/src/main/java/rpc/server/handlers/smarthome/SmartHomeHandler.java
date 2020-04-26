package rpc.server.handlers.smarthome;

import rpc.thrift.smarthome.DeviceType;
import rpc.thrift.smarthome.SmartHome;

import java.util.Map;

public class SmartHomeHandler implements SmartHome.Iface {

    public Map<String, DeviceType> devicesIds;

    public SmartHomeHandler(Map<String, DeviceType> devicesIds) {
        this.devicesIds = devicesIds;
    }

    @Override
    public Map<String, DeviceType> getDevicesIds() {
        System.out.println("Get Divices Call");
        return devicesIds;
    }
}
