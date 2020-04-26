package rpc.server.handlers.aircondition;

import rpc.server.handlers.shared.BaseDeviceHandler;
import rpc.thrift.aircondition.AirCondition;
import rpc.thrift.aircondition.InvalidTemperatureException;
import rpc.thrift.shared.DeviceNotActiveException;

public class AirConditionHandler extends BaseDeviceHandler implements AirCondition.Iface {

    public double minTemperature;
    public double maxTemperature;

    public void temperatureCheck(double temperature) throws InvalidTemperatureException {
        if (temperature > maxTemperature || temperature < minTemperature) {
            throw new InvalidTemperatureException(temperature, String.format("This device only supports temperatures [%f, %f]",
                    minTemperature, maxTemperature));
        }
    }

    public AirConditionHandler(double minTemperature, double maxTemperature) {
        super();
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public double changeTemperature(double temperature) throws InvalidTemperatureException, DeviceNotActiveException {
        System.out.println("Change Temperature call");
        this.ifNotActiveCheck();
        this.temperatureCheck(temperature);
        return temperature;
    }
}
