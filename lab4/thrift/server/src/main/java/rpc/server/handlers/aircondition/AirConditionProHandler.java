package rpc.server.handlers.aircondition;

import rpc.thrift.aircondition.AirConditionPro;
import rpc.thrift.aircondition.AirPollution;
import rpc.thrift.shared.DeviceNotActiveException;

public class AirConditionProHandler extends AirConditionHandler implements AirConditionPro.Iface {

    public AirConditionProHandler(double minTemperature, double maxTemperature) {
        super(minTemperature, maxTemperature);
    }

    @Override
    public AirPollution checkAirPollution() throws DeviceNotActiveException {
        System.out.println("Change AirPollution call");
        this.ifNotActiveCheck();
        java.util.Random random = new java.util.Random();
        switch (random.nextInt(3)) {
            case 0:
                return AirPollution.BAD;
            case 1:
                return AirPollution.NORMAL;
            case 2:
                return AirPollution.GOOD;
        }
        return AirPollution.NORMAL;
    }


}
