#
# Autogenerated by Thrift Compiler (0.12.0)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TFrozenDict, TException, TApplicationException
from thrift.protocol.TProtocol import TProtocolException
from thrift.TRecursive import fix_spec

import sys
import thrift_python.shared.BaseDevice
import logging
from .ttypes import *
from thrift.Thrift import TProcessor
from thrift.transport import TTransport
all_structs = []


class Iface(thrift_python.shared.BaseDevice.Iface):
    def changeColour(self, colour):
        """
        Parameters:
         - colour

        """
        pass


class Client(thrift_python.shared.BaseDevice.Client, Iface):
    def __init__(self, iprot, oprot=None):
        thrift_python.shared.BaseDevice.Client.__init__(self, iprot, oprot)

    def changeColour(self, colour):
        """
        Parameters:
         - colour

        """
        self.send_changeColour(colour)
        return self.recv_changeColour()

    def send_changeColour(self, colour):
        self._oprot.writeMessageBegin('changeColour', TMessageType.CALL, self._seqid)
        args = changeColour_args()
        args.colour = colour
        args.write(self._oprot)
        self._oprot.writeMessageEnd()
        self._oprot.trans.flush()

    def recv_changeColour(self):
        iprot = self._iprot
        (fname, mtype, rseqid) = iprot.readMessageBegin()
        if mtype == TMessageType.EXCEPTION:
            x = TApplicationException()
            x.read(iprot)
            iprot.readMessageEnd()
            raise x
        result = changeColour_result()
        result.read(iprot)
        iprot.readMessageEnd()
        if result.success is not None:
            return result.success
        if result.deviceNotActiveException is not None:
            raise result.deviceNotActiveException
        if result.invalidColourException is not None:
            raise result.invalidColourException
        raise TApplicationException(TApplicationException.MISSING_RESULT, "changeColour failed: unknown result")


class Processor(thrift_python.shared.BaseDevice.Processor, Iface, TProcessor):
    def __init__(self, handler):
        thrift_python.shared.BaseDevice.Processor.__init__(self, handler)
        self._processMap["changeColour"] = Processor.process_changeColour

    def process(self, iprot, oprot):
        (name, type, seqid) = iprot.readMessageBegin()
        if name not in self._processMap:
            iprot.skip(TType.STRUCT)
            iprot.readMessageEnd()
            x = TApplicationException(TApplicationException.UNKNOWN_METHOD, 'Unknown function %s' % (name))
            oprot.writeMessageBegin(name, TMessageType.EXCEPTION, seqid)
            x.write(oprot)
            oprot.writeMessageEnd()
            oprot.trans.flush()
            return
        else:
            self._processMap[name](self, seqid, iprot, oprot)
        return True

    def process_changeColour(self, seqid, iprot, oprot):
        args = changeColour_args()
        args.read(iprot)
        iprot.readMessageEnd()
        result = changeColour_result()
        try:
            result.success = self._handler.changeColour(args.colour)
            msg_type = TMessageType.REPLY
        except TTransport.TTransportException:
            raise
        except thrift_python.shared.ttypes.DeviceNotActiveException as deviceNotActiveException:
            msg_type = TMessageType.REPLY
            result.deviceNotActiveException = deviceNotActiveException
        except InvalidColourException as invalidColourException:
            msg_type = TMessageType.REPLY
            result.invalidColourException = invalidColourException
        except TApplicationException as ex:
            logging.exception('TApplication exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = ex
        except Exception:
            logging.exception('Unexpected exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = TApplicationException(TApplicationException.INTERNAL_ERROR, 'Internal error')
        oprot.writeMessageBegin("changeColour", msg_type, seqid)
        result.write(oprot)
        oprot.writeMessageEnd()
        oprot.trans.flush()

# HELPER FUNCTIONS AND STRUCTURES


class changeColour_args(object):
    """
    Attributes:
     - colour

    """


    def __init__(self, colour=None,):
        self.colour = colour

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.STRUCT:
                    self.colour = Colour()
                    self.colour.read(iprot)
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('changeColour_args')
        if self.colour is not None:
            oprot.writeFieldBegin('colour', TType.STRUCT, 1)
            self.colour.write(oprot)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(changeColour_args)
changeColour_args.thrift_spec = (
    None,  # 0
    (1, TType.STRUCT, 'colour', [Colour, None], None, ),  # 1
)


class changeColour_result(object):
    """
    Attributes:
     - success
     - deviceNotActiveException
     - invalidColourException

    """


    def __init__(self, success=None, deviceNotActiveException=None, invalidColourException=None,):
        self.success = success
        self.deviceNotActiveException = deviceNotActiveException
        self.invalidColourException = invalidColourException

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 0:
                if ftype == TType.STRUCT:
                    self.success = Colour()
                    self.success.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 1:
                if ftype == TType.STRUCT:
                    self.deviceNotActiveException = thrift_python.shared.ttypes.DeviceNotActiveException()
                    self.deviceNotActiveException.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRUCT:
                    self.invalidColourException = InvalidColourException()
                    self.invalidColourException.read(iprot)
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('changeColour_result')
        if self.success is not None:
            oprot.writeFieldBegin('success', TType.STRUCT, 0)
            self.success.write(oprot)
            oprot.writeFieldEnd()
        if self.deviceNotActiveException is not None:
            oprot.writeFieldBegin('deviceNotActiveException', TType.STRUCT, 1)
            self.deviceNotActiveException.write(oprot)
            oprot.writeFieldEnd()
        if self.invalidColourException is not None:
            oprot.writeFieldBegin('invalidColourException', TType.STRUCT, 2)
            self.invalidColourException.write(oprot)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(changeColour_result)
changeColour_result.thrift_spec = (
    (0, TType.STRUCT, 'success', [Colour, None], None, ),  # 0
    (1, TType.STRUCT, 'deviceNotActiveException', [thrift_python.shared.ttypes.DeviceNotActiveException, None], None, ),  # 1
    (2, TType.STRUCT, 'invalidColourException', [InvalidColourException, None], None, ),  # 2
)
fix_spec(all_structs)
del all_structs

