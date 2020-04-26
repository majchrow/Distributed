package rpc.server.handlers.vaccum;

import rpc.server.handlers.shared.BaseDeviceHandler;
import rpc.thrift.shared.DeviceNotActiveException;
import rpc.thrift.vaccum.InvalidOrderException;
import rpc.thrift.vaccum.Room;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class VaccumRobotHandler extends BaseDeviceHandler implements rpc.thrift.vaccum.VaccumRobot.Iface {

    Set<Room> rooms;
    Set<Room> unreachableRooms;

    public VaccumRobotHandler() {
        super();
        this.rooms = new ConcurrentSkipListSet<>();
        this.unreachableRooms = new ConcurrentSkipListSet<>();
    }

    public void addUnreachableRooms(Room room) {
        this.unreachableRooms.add(room);
    }

    public void validOrderCheck(Set<Room> rooms) throws InvalidOrderException {
        for (Room room : rooms) {
            if (this.unreachableRooms.contains(room)) {
                throw new InvalidOrderException(rooms, "Room " + room + " is unreachable right now");
            }
        }
    }


    @Override
    public Set<Room> cleanRooms(Set<Room> rooms) throws DeviceNotActiveException, InvalidOrderException {
        System.out.println("Clean Rooms Call");
        this.ifNotActiveCheck();
        this.validOrderCheck(rooms);
        this.rooms = rooms;
        return rooms;
    }
}
