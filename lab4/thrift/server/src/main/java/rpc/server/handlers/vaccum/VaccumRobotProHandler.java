package rpc.server.handlers.vaccum;

import rpc.thrift.shared.DeviceNotActiveException;
import rpc.thrift.vaccum.Config;
import rpc.thrift.vaccum.VaccumRobotPro;

public class VaccumRobotProHandler extends VaccumRobotHandler implements VaccumRobotPro.Iface {

    public Config config;

    public VaccumRobotProHandler() {
        super();
        this.config = new Config(false, false, false);
    }

    @Override
    public Config setConfig(Config config) throws DeviceNotActiveException {
        System.out.println("Set Config Call");
        ifNotActiveCheck();
        this.config = config;
        return config;
    }

    @Override
    public Config getConfig() throws DeviceNotActiveException {
        System.out.println("Get Config Call");
        ifNotActiveCheck();
        return this.config;
    }
}
