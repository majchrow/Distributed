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
import thrift_python.leds.BasicLed
import logging
from .ttypes import *
from thrift.Thrift import TProcessor
from thrift.transport import TTransport
all_structs = []


class Iface(thrift_python.leds.BasicLed.Iface):
    def setBrightness(self, percent):
        """
        Parameters:
         - percent

        """
        pass

    def getBrightness(self):
        pass


class Client(thrift_python.leds.BasicLed.Client, Iface):
    def __init__(self, iprot, oprot=None):
        thrift_python.leds.BasicLed.Client.__init__(self, iprot, oprot)

    def setBrightness(self, percent):
        """
        Parameters:
         - percent

        """
        self.send_setBrightness(percent)
        return self.recv_setBrightness()

    def send_setBrightness(self, percent):
        self._oprot.writeMessageBegin('setBrightness', TMessageType.CALL, self._seqid)
        args = setBrightness_args()
        args.percent = percent
        args.write(self._oprot)
        self._oprot.writeMessageEnd()
        self._oprot.trans.flush()

    def recv_setBrightness(self):
        iprot = self._iprot
        (fname, mtype, rseqid) = iprot.readMessageBegin()
        if mtype == TMessageType.EXCEPTION:
            x = TApplicationException()
            x.read(iprot)
            iprot.readMessageEnd()
            raise x
        result = setBrightness_result()
        result.read(iprot)
        iprot.readMessageEnd()
        if result.success is not None:
            return result.success
        if result.deviceNotActiveException is not None:
            raise result.deviceNotActiveException
        if result.invalidPercentException is not None:
            raise result.invalidPercentException
        raise TApplicationException(TApplicationException.MISSING_RESULT, "setBrightness failed: unknown result")

    def getBrightness(self):
        self.send_getBrightness()
        return self.recv_getBrightness()

    def send_getBrightness(self):
        self._oprot.writeMessageBegin('getBrightness', TMessageType.CALL, self._seqid)
        args = getBrightness_args()
        args.write(self._oprot)
        self._oprot.writeMessageEnd()
        self._oprot.trans.flush()

    def recv_getBrightness(self):
        iprot = self._iprot
        (fname, mtype, rseqid) = iprot.readMessageBegin()
        if mtype == TMessageType.EXCEPTION:
            x = TApplicationException()
            x.read(iprot)
            iprot.readMessageEnd()
            raise x
        result = getBrightness_result()
        result.read(iprot)
        iprot.readMessageEnd()
        if result.success is not None:
            return result.success
        if result.deviceNotActiveException is not None:
            raise result.deviceNotActiveException
        raise TApplicationException(TApplicationException.MISSING_RESULT, "getBrightness failed: unknown result")


class Processor(thrift_python.leds.BasicLed.Processor, Iface, TProcessor):
    def __init__(self, handler):
        thrift_python.leds.BasicLed.Processor.__init__(self, handler)
        self._processMap["setBrightness"] = Processor.process_setBrightness
        self._processMap["getBrightness"] = Processor.process_getBrightness

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

    def process_setBrightness(self, seqid, iprot, oprot):
        args = setBrightness_args()
        args.read(iprot)
        iprot.readMessageEnd()
        result = setBrightness_result()
        try:
            result.success = self._handler.setBrightness(args.percent)
            msg_type = TMessageType.REPLY
        except TTransport.TTransportException:
            raise
        except thrift_python.shared.ttypes.DeviceNotActiveException as deviceNotActiveException:
            msg_type = TMessageType.REPLY
            result.deviceNotActiveException = deviceNotActiveException
        except InvalidPercentException as invalidPercentException:
            msg_type = TMessageType.REPLY
            result.invalidPercentException = invalidPercentException
        except TApplicationException as ex:
            logging.exception('TApplication exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = ex
        except Exception:
            logging.exception('Unexpected exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = TApplicationException(TApplicationException.INTERNAL_ERROR, 'Internal error')
        oprot.writeMessageBegin("setBrightness", msg_type, seqid)
        result.write(oprot)
        oprot.writeMessageEnd()
        oprot.trans.flush()

    def process_getBrightness(self, seqid, iprot, oprot):
        args = getBrightness_args()
        args.read(iprot)
        iprot.readMessageEnd()
        result = getBrightness_result()
        try:
            result.success = self._handler.getBrightness()
            msg_type = TMessageType.REPLY
        except TTransport.TTransportException:
            raise
        except thrift_python.shared.ttypes.DeviceNotActiveException as deviceNotActiveException:
            msg_type = TMessageType.REPLY
            result.deviceNotActiveException = deviceNotActiveException
        except TApplicationException as ex:
            logging.exception('TApplication exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = ex
        except Exception:
            logging.exception('Unexpected exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = TApplicationException(TApplicationException.INTERNAL_ERROR, 'Internal error')
        oprot.writeMessageBegin("getBrightness", msg_type, seqid)
        result.write(oprot)
        oprot.writeMessageEnd()
        oprot.trans.flush()

# HELPER FUNCTIONS AND STRUCTURES


class setBrightness_args(object):
    """
    Attributes:
     - percent

    """


    def __init__(self, percent=None,):
        self.percent = percent

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
                if ftype == TType.DOUBLE:
                    self.percent = iprot.readDouble()
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
        oprot.writeStructBegin('setBrightness_args')
        if self.percent is not None:
            oprot.writeFieldBegin('percent', TType.DOUBLE, 1)
            oprot.writeDouble(self.percent)
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
all_structs.append(setBrightness_args)
setBrightness_args.thrift_spec = (
    None,  # 0
    (1, TType.DOUBLE, 'percent', None, None, ),  # 1
)


class setBrightness_result(object):
    """
    Attributes:
     - success
     - deviceNotActiveException
     - invalidPercentException

    """


    def __init__(self, success=None, deviceNotActiveException=None, invalidPercentException=None,):
        self.success = success
        self.deviceNotActiveException = deviceNotActiveException
        self.invalidPercentException = invalidPercentException

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
                if ftype == TType.DOUBLE:
                    self.success = iprot.readDouble()
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
                    self.invalidPercentException = InvalidPercentException()
                    self.invalidPercentException.read(iprot)
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
        oprot.writeStructBegin('setBrightness_result')
        if self.success is not None:
            oprot.writeFieldBegin('success', TType.DOUBLE, 0)
            oprot.writeDouble(self.success)
            oprot.writeFieldEnd()
        if self.deviceNotActiveException is not None:
            oprot.writeFieldBegin('deviceNotActiveException', TType.STRUCT, 1)
            self.deviceNotActiveException.write(oprot)
            oprot.writeFieldEnd()
        if self.invalidPercentException is not None:
            oprot.writeFieldBegin('invalidPercentException', TType.STRUCT, 2)
            self.invalidPercentException.write(oprot)
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
all_structs.append(setBrightness_result)
setBrightness_result.thrift_spec = (
    (0, TType.DOUBLE, 'success', None, None, ),  # 0
    (1, TType.STRUCT, 'deviceNotActiveException', [thrift_python.shared.ttypes.DeviceNotActiveException, None], None, ),  # 1
    (2, TType.STRUCT, 'invalidPercentException', [InvalidPercentException, None], None, ),  # 2
)


class getBrightness_args(object):


    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('getBrightness_args')
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
all_structs.append(getBrightness_args)
getBrightness_args.thrift_spec = (
)


class getBrightness_result(object):
    """
    Attributes:
     - success
     - deviceNotActiveException

    """


    def __init__(self, success=None, deviceNotActiveException=None,):
        self.success = success
        self.deviceNotActiveException = deviceNotActiveException

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
                if ftype == TType.DOUBLE:
                    self.success = iprot.readDouble()
                else:
                    iprot.skip(ftype)
            elif fid == 1:
                if ftype == TType.STRUCT:
                    self.deviceNotActiveException = thrift_python.shared.ttypes.DeviceNotActiveException()
                    self.deviceNotActiveException.read(iprot)
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
        oprot.writeStructBegin('getBrightness_result')
        if self.success is not None:
            oprot.writeFieldBegin('success', TType.DOUBLE, 0)
            oprot.writeDouble(self.success)
            oprot.writeFieldEnd()
        if self.deviceNotActiveException is not None:
            oprot.writeFieldBegin('deviceNotActiveException', TType.STRUCT, 1)
            self.deviceNotActiveException.write(oprot)
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
all_structs.append(getBrightness_result)
getBrightness_result.thrift_spec = (
    (0, TType.DOUBLE, 'success', None, None, ),  # 0
    (1, TType.STRUCT, 'deviceNotActiveException', [thrift_python.shared.ttypes.DeviceNotActiveException, None], None, ),  # 1
)
fix_spec(all_structs)
del all_structs
