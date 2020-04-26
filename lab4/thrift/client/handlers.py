import logging

from thrift.protocol.TMultiplexedProtocol import TMultiplexedProtocol

from thrift_python.aircondition import AirConditionPro
from thrift_python.leds import BasicLed, BrightnessLed, ModeLed
from thrift_python.leds.ttypes import Colour, Mode
from thrift_python.smarthome.ttypes import DeviceType
from thrift_python.vaccum import VaccumRobotPro
from thrift_python.vaccum.ttypes import Config, Room
from utils import Singleton


class SmartHomeClientHandler(metaclass=Singleton):
    def __init__(self, devices, protocol):
        self.devices = devices
        self.protocol = protocol
        self.devices_id = None
        self._parse_devices()

    def _parse_devices(self):
        keys = sorted(self.devices.keys())
        self.devices_id = {
            device_id: keys[device_id] for device_id in range(len(keys))
        }

    def _print_usage(self):
        for device_id, device in self.devices_id.items():
            print(str(device_id) + "->" + device)

    def start(self):
        while True:
            self._print_usage()
            try:
                device_id = int(input('Choose device: '))
                if device_id not in self.devices_id:
                    raise ValueError
            except ValueError:
                logging.warning("Wrong device")
                continue
            device_name = self.devices_id[device_id]
            device = self.devices[self.devices_id[device_id]]
            if device == DeviceType.AirConditionPro:
                AirConditionProHandler(device_name, self.protocol).start()
            elif device == DeviceType.VaccumRobotPro:
                VaccumRobotProHandler(device_name, self.protocol).start()
            elif device == DeviceType.BasicLed:
                BasicLedHandler(device_name, self.protocol).start()
            elif device == DeviceType.BrightnessLed:
                BrightnessLedHandler(device_name, self.protocol).start()
            elif device == DeviceType.ModeLed:
                ModeLedHandler(device_name, self.protocol).start()
            else:
                logging.warning("Device not supported")


class AirConditionProHandler(metaclass=Singleton):

    def __init__(self, device_name, protocol):
        self.device_name = device_name
        self.protocol = protocol
        self.client = AirConditionPro.Client(TMultiplexedProtocol(self.protocol, self.device_name))

    def _print_usage(self):
        print("0 - turn on")
        print("1 - turn off")
        print("2 - change temperature")
        print("3 - check air pollution")
        print("4 - exit")

    def _handle_temperature(self):
        inp = input('Choose temperature: ')
        try:
            temperature = float(inp)
            result = self.client.changeTemperature(temperature)
            logging.info(result)
        except ValueError:
            logging.warning("Wrong input " + inp)

    def start(self):
        while True:
            self._print_usage()
            try:
                inp = input('Choose option: ')
                order = inp.strip()
                if order == "0":
                    result = self.client.turnOn()
                elif order == "1":
                    result = self.client.turnOff()
                elif order == "2":
                    self._handle_temperature()
                    continue
                elif order == "3":
                    result = self.client.checkAirPollution()
                elif order == "4":
                    break
                else:
                    logging.warning("Wrong input " + inp)
                    continue
                logging.info(result)
            except Exception as e:
                logging.warning(e)


class VaccumRobotProHandler(metaclass=Singleton):

    def __init__(self, device_name, protocol):
        self.device_name = device_name
        self.protocol = protocol
        self.client = VaccumRobotPro.Client(TMultiplexedProtocol(self.protocol, self.device_name))

    def _print_usage(self):
        print("0 - turn on")
        print("1 - turn off")
        print("2 - get config")
        print("3 - set config")
        print("4 - choose rooms to be cleaned")
        print("5 - exit")

    def _handle_config(self):
        inp = input('Choose config [1-true, 0-false] `autoCharge, autoGarbageDispose, autoClean`: ')
        try:
            if len(inp.split(",")) != 3:
                raise ValueError
            config = [number.strip() == "1" for number in inp.split(",") if number.strip() in {"0", "1"}]
            if len(config) != 3:
                raise ValueError
            result = self.client.setConfig(Config(*config))
            logging.info(result)
        except ValueError:
            logging.warning("Wrong input " + inp)

    def _handle_rooms(self):
        inp = input('Choose rooms, separated by commas (k-kitchen, b-bathroom, l-living room): ')
        room_map = {
            "k": Room.KITCHEN,
            "b": Room.BATHROOM,
            "l": Room.LIVINGROOM
        }
        try:
            if len(inp.split(",")) > 3:
                raise ValueError
            rooms = {room_map[letter] for letter in inp.split(",") if letter.strip() in {"k", "b", "l"}}
            if len(rooms) == 0:
                raise ValueError
            result = self.client.cleanRooms(rooms)
            logging.info(result)
        except ValueError:
            logging.warning("Wrong input " + inp)

    def start(self):
        while True:
            self._print_usage()
            try:
                inp = input('Choose option: ')
                order = inp.strip()
                if order == "0":
                    result = self.client.turnOn()
                elif order == "1":
                    result = self.client.turnOff()
                elif order == "2":
                    result = self.client.getConfig()
                elif order == "3":
                    self._handle_config()
                    continue
                elif order == "4":
                    self._handle_rooms()
                    continue
                elif order == "5":
                    break
                else:
                    logging.warning("Wrong input " + inp)
                    continue
                logging.info(result)
            except Exception as e:
                logging.warning(e)


class BasicLedHandler(metaclass=Singleton):

    def __init__(self, device_name, protocol):
        self.device_name = device_name
        self.protocol = protocol
        self.client = BasicLed.Client(TMultiplexedProtocol(self.protocol, self.device_name))

    def _print_usage(self):
        print("0 - turn on")
        print("1 - turn off")
        print("2 - change colour")
        print("3 - exit")

    def _handleColour(self):
        inp = input('Choose colour `R, G, B`: ')
        try:
            if len(inp.split(",")) != 3:
                raise ValueError
            config = [float(number.strip()) for number in inp.split(",")]
            result = self.client.changeColour(Colour(*config))
            logging.info(result)
        except ValueError:
            logging.warning("Wrong input " + inp)

    def start(self):
        while True:
            self._print_usage()
            try:
                inp = input('Choose option: ')
                order = inp.strip()
                if order == "0":
                    result = self.client.turnOn()
                elif order == "1":
                    result = self.client.turnOff()
                elif order == "2":
                    self._handleColour()
                    continue
                elif order == "3":
                    break
                else:
                    logging.warning("Wrong input " + inp)
                    continue
                logging.info(result)
            except Exception as e:
                logging.warning(e)


class BrightnessLedHandler(metaclass=Singleton):

    def __init__(self, device_name, protocol):
        self.device_name = device_name
        self.protocol = protocol
        self.client = BrightnessLed.Client(TMultiplexedProtocol(self.protocol, self.device_name))

    def _print_usage(self):
        print("0 - turn on")
        print("1 - turn off")
        print("2 - change colour")
        print("3 - set brightness")
        print("4 - get brightness")
        print("5 - exit")

    def _handle_colour(self):
        inp = input('Choose colour `R, G, B`: ')
        try:
            if len(inp.split(",")) != 3:
                logging.warning("Wrong input " + inp)
            else:
                rgb = [float(number.strip()) for number in inp.split(",")]
                result = self.client.changeColour(Colour(*rgb))
                logging.info(result)
        except ValueError as e:
            logging.warning("Wrong input " + inp)

    def _handle_brightness(self):
        inp = input('Choose brightness `percent`: ')
        try:
            percent = float(inp)
            result = self.client.setBrightness(percent)
            logging.info(result)
        except ValueError:
            logging.warning("Wrong input " + inp)

    def start(self):
        while True:
            self._print_usage()
            try:
                inp = input('Choose option: ')
                order = inp.strip()
                if order == "0":
                    result = self.client.turnOn()
                elif order == "1":
                    result = self.client.turnOff()
                elif order == "2":
                    self._handle_colour()
                    continue
                elif order == "3":
                    self._handle_brightness()
                    continue
                elif order == "4":
                    result = self.client.getBrightness()
                elif order == "5":
                    break
                else:
                    logging.warning("Wrong input " + inp)
                    continue
                logging.info(result)
            except Exception as e:
                logging.warning(e)


class ModeLedHandler(metaclass=Singleton):
    def __init__(self, device_name, protocol):
        self.device_name = device_name
        self.protocol = protocol
        self.client = ModeLed.Client(TMultiplexedProtocol(self.protocol, self.device_name))

    def _print_usage(self):
        print("0 - turn on")
        print("1 - turn off")
        print("2 - change colour")
        print("3 - set mode")
        print("4 - get mode")
        print("5 - exit")

    def _handle_colour(self):
        inp = input('Choose colour `R, G, B`: ')
        try:
            if len(inp.split(",")) != 3:
                raise ValueError
            config = [float(number.strip()) for number in inp.split(",")]
            result = self.client.changeColour(Colour(*config))
            logging.info(result)
        except ValueError:
            logging.warning("Wrong input " + inp)

    def _handle_mode(self):
        inp = input('Choose mode (n - None, c - Changing, f - Flashing): ')
        mode_map = {
            "n": Mode.NONE,
            "c": Mode.CHANGING,
            "f": Mode.FLASHING
        }
        try:
            mode = mode_map[inp.strip()]
            result = self.client.setMode(mode)
            logging.info(result)
        except KeyError:
            logging.warning("Wrong input " + inp)

    def start(self):
        while True:
            self._print_usage()
            try:
                inp = input('Choose option: ')
                order = inp.strip()
                if order == "0":
                    result = self.client.turnOn()
                elif order == "1":
                    result = self.client.turnOff()
                elif order == "2":
                    self._handle_colour()
                    continue
                elif order == "3":
                    self._handle_mode()
                    continue
                elif order == "4":
                    result = self.client.getMode()
                elif order == "5":
                    break
                else:
                    logging.warning("Wrong input " + inp)
                    continue
                logging.info(result)
            except Exception as e:
                logging.warning(e)
