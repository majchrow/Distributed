package rpc.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import rpc.server.handlers.aircondition.AirConditionProHandler;
import rpc.server.handlers.leds.BasicLedHandler;
import rpc.server.handlers.leds.BrightnessLedHandler;
import rpc.server.handlers.leds.ModeLedHandler;
import rpc.server.handlers.smarthome.SmartHomeHandler;
import rpc.server.handlers.vaccum.VaccumRobotProHandler;
import rpc.thrift.aircondition.AirConditionPro;
import rpc.thrift.leds.BasicLed;
import rpc.thrift.leds.BrightnessLed;
import rpc.thrift.leds.ModeLed;
import rpc.thrift.smarthome.DeviceType;
import rpc.thrift.smarthome.SmartHome;
import rpc.thrift.vaccum.Room;
import rpc.thrift.vaccum.VaccumRobotPro;

import java.util.HashMap;
import java.util.Map;

public class ThriftServer {
    public final static Map<String, DeviceType> devicesIds = new HashMap<String, DeviceType>() {{
        put("air_condition", DeviceType.AirConditionPro);
        put("vaccum_robot", DeviceType.VaccumRobotPro);
        put("kitchen_led", DeviceType.BasicLed);
        put("bathroom_led", DeviceType.BrightnessLed);
        put("livingroom_led", DeviceType.ModeLed);
    }};

    public static void main(String[] args) {
        try {
            Runnable multiplex = ThriftServer::multiplex;
            new Thread(multiplex).start();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void multiplex() {
        try {

            SmartHome.Processor<SmartHomeHandler> smartHomeHandlerProcessor =
                    new SmartHome.Processor<>(new SmartHomeHandler(devicesIds));
            AirConditionPro.Processor<AirConditionProHandler> airConditionProHandlerProcessor =
                    new AirConditionPro.Processor<>(new AirConditionProHandler(-10, 30));
            VaccumRobotProHandler vaccumRobotProHandler = new VaccumRobotProHandler();
            vaccumRobotProHandler.addUnreachableRooms(Room.BATHROOM); // Cannot clean bathroom right now
            VaccumRobotPro.Processor<VaccumRobotProHandler> vaccumRobotProHandlerProcessor =
                    new VaccumRobotPro.Processor<>(vaccumRobotProHandler);
            BasicLed.Processor<BasicLedHandler> kitchenLedHandlerProcessor =
                    new BasicLed.Processor<>(new BasicLedHandler());
            BrightnessLed.Processor<BrightnessLedHandler> bathRoomLedHandlerProcessor =
                    new BrightnessLed.Processor<>(new BrightnessLedHandler());
            ModeLed.Processor<ModeLedHandler> livingRoomLedHandlerProcessor =
                    new ModeLed.Processor<>(new ModeLedHandler());


            TServerTransport serverTransport = new TServerSocket(9090);
            TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();

            TMultiplexedProcessor multiplex = new TMultiplexedProcessor();
            multiplex.registerProcessor("smart_home", smartHomeHandlerProcessor);
            multiplex.registerProcessor("air_condition", airConditionProHandlerProcessor);
            multiplex.registerProcessor("vaccum_robot", vaccumRobotProHandlerProcessor);
            multiplex.registerProcessor("kitchen_led", kitchenLedHandlerProcessor);
            multiplex.registerProcessor("bathroom_led", bathRoomLedHandlerProcessor);
            multiplex.registerProcessor("livingroom_led", livingRoomLedHandlerProcessor);

            TServer server = new TSimpleServer(new Args(serverTransport).protocolFactory(protocolFactory).processor(multiplex));
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
