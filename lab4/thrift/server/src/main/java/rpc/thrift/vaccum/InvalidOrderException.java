/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package rpc.thrift.vaccum;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2020-04-26")
public class InvalidOrderException extends org.apache.thrift.TException implements org.apache.thrift.TBase<InvalidOrderException, InvalidOrderException._Fields>, java.io.Serializable, Cloneable, Comparable<InvalidOrderException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("InvalidOrderException");

  private static final org.apache.thrift.protocol.TField ROOMS_FIELD_DESC = new org.apache.thrift.protocol.TField("rooms", org.apache.thrift.protocol.TType.SET, (short)1);
  private static final org.apache.thrift.protocol.TField REASON_FIELD_DESC = new org.apache.thrift.protocol.TField("reason", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new InvalidOrderExceptionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new InvalidOrderExceptionTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.Set<Room> rooms; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String reason; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROOMS((short)1, "rooms"),
    REASON((short)2, "reason");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ROOMS
          return ROOMS;
        case 2: // REASON
          return REASON;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROOMS, new org.apache.thrift.meta_data.FieldMetaData("rooms", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.SET        , "Rooms")));
    tmpMap.put(_Fields.REASON, new org.apache.thrift.meta_data.FieldMetaData("reason", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(InvalidOrderException.class, metaDataMap);
  }

  public InvalidOrderException() {
  }

  public InvalidOrderException(
    java.util.Set<Room> rooms,
    java.lang.String reason)
  {
    this();
    this.rooms = rooms;
    this.reason = reason;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public InvalidOrderException(InvalidOrderException other) {
    if (other.isSetRooms()) {
      java.util.Set<Room> __this__rooms = java.util.EnumSet.noneOf(Room.class);
      for (Room other_element : other.rooms) {
        __this__rooms.add(other_element);
      }
      this.rooms = __this__rooms;
    }
    if (other.isSetReason()) {
      this.reason = other.reason;
    }
  }

  public InvalidOrderException deepCopy() {
    return new InvalidOrderException(this);
  }

  @Override
  public void clear() {
    this.rooms = null;
    this.reason = null;
  }

  public int getRoomsSize() {
    return (this.rooms == null) ? 0 : this.rooms.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<Room> getRoomsIterator() {
    return (this.rooms == null) ? null : this.rooms.iterator();
  }

  public void addToRooms(Room elem) {
    if (this.rooms == null) {
      this.rooms = java.util.EnumSet.noneOf(Room.class);
    }
    this.rooms.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Set<Room> getRooms() {
    return this.rooms;
  }

  public InvalidOrderException setRooms(@org.apache.thrift.annotation.Nullable java.util.Set<Room> rooms) {
    this.rooms = rooms;
    return this;
  }

  public void unsetRooms() {
    this.rooms = null;
  }

  /** Returns true if field rooms is set (has been assigned a value) and false otherwise */
  public boolean isSetRooms() {
    return this.rooms != null;
  }

  public void setRoomsIsSet(boolean value) {
    if (!value) {
      this.rooms = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getReason() {
    return this.reason;
  }

  public InvalidOrderException setReason(@org.apache.thrift.annotation.Nullable java.lang.String reason) {
    this.reason = reason;
    return this;
  }

  public void unsetReason() {
    this.reason = null;
  }

  /** Returns true if field reason is set (has been assigned a value) and false otherwise */
  public boolean isSetReason() {
    return this.reason != null;
  }

  public void setReasonIsSet(boolean value) {
    if (!value) {
      this.reason = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ROOMS:
      if (value == null) {
        unsetRooms();
      } else {
        setRooms((java.util.Set<Room>)value);
      }
      break;

    case REASON:
      if (value == null) {
        unsetReason();
      } else {
        setReason((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROOMS:
      return getRooms();

    case REASON:
      return getReason();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROOMS:
      return isSetRooms();
    case REASON:
      return isSetReason();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof InvalidOrderException)
      return this.equals((InvalidOrderException)that);
    return false;
  }

  public boolean equals(InvalidOrderException that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_rooms = true && this.isSetRooms();
    boolean that_present_rooms = true && that.isSetRooms();
    if (this_present_rooms || that_present_rooms) {
      if (!(this_present_rooms && that_present_rooms))
        return false;
      if (!this.rooms.equals(that.rooms))
        return false;
    }

    boolean this_present_reason = true && this.isSetReason();
    boolean that_present_reason = true && that.isSetReason();
    if (this_present_reason || that_present_reason) {
      if (!(this_present_reason && that_present_reason))
        return false;
      if (!this.reason.equals(that.reason))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRooms()) ? 131071 : 524287);
    if (isSetRooms())
      hashCode = hashCode * 8191 + rooms.hashCode();

    hashCode = hashCode * 8191 + ((isSetReason()) ? 131071 : 524287);
    if (isSetReason())
      hashCode = hashCode * 8191 + reason.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(InvalidOrderException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRooms()).compareTo(other.isSetRooms());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRooms()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rooms, other.rooms);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetReason()).compareTo(other.isSetReason());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReason()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reason, other.reason);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("InvalidOrderException(");
    boolean first = true;

    sb.append("rooms:");
    if (this.rooms == null) {
      sb.append("null");
    } else {
      sb.append(this.rooms);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("reason:");
    if (this.reason == null) {
      sb.append("null");
    } else {
      sb.append(this.reason);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class InvalidOrderExceptionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public InvalidOrderExceptionStandardScheme getScheme() {
      return new InvalidOrderExceptionStandardScheme();
    }
  }

  private static class InvalidOrderExceptionStandardScheme extends org.apache.thrift.scheme.StandardScheme<InvalidOrderException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, InvalidOrderException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROOMS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set0 = iprot.readSetBegin();
                struct.rooms = java.util.EnumSet.noneOf(Room.class);
                @org.apache.thrift.annotation.Nullable Room _elem1;
                for (int _i2 = 0; _i2 < _set0.size; ++_i2)
                {
                  _elem1 = rpc.thrift.vaccum.Room.findByValue(iprot.readI32());
                  if (_elem1 != null)
                  {
                    struct.rooms.add(_elem1);
                  }
                }
                iprot.readSetEnd();
              }
              struct.setRoomsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REASON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.reason = iprot.readString();
              struct.setReasonIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, InvalidOrderException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.rooms != null) {
        oprot.writeFieldBegin(ROOMS_FIELD_DESC);
        {
          oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, struct.rooms.size()));
          for (Room _iter3 : struct.rooms)
          {
            oprot.writeI32(_iter3.getValue());
          }
          oprot.writeSetEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.reason != null) {
        oprot.writeFieldBegin(REASON_FIELD_DESC);
        oprot.writeString(struct.reason);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class InvalidOrderExceptionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public InvalidOrderExceptionTupleScheme getScheme() {
      return new InvalidOrderExceptionTupleScheme();
    }
  }

  private static class InvalidOrderExceptionTupleScheme extends org.apache.thrift.scheme.TupleScheme<InvalidOrderException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, InvalidOrderException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRooms()) {
        optionals.set(0);
      }
      if (struct.isSetReason()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetRooms()) {
        {
          oprot.writeI32(struct.rooms.size());
          for (Room _iter4 : struct.rooms)
          {
            oprot.writeI32(_iter4.getValue());
          }
        }
      }
      if (struct.isSetReason()) {
        oprot.writeString(struct.reason);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, InvalidOrderException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TSet _set5 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.rooms = java.util.EnumSet.noneOf(Room.class);
          @org.apache.thrift.annotation.Nullable Room _elem6;
          for (int _i7 = 0; _i7 < _set5.size; ++_i7)
          {
            _elem6 = rpc.thrift.vaccum.Room.findByValue(iprot.readI32());
            if (_elem6 != null)
            {
              struct.rooms.add(_elem6);
            }
          }
        }
        struct.setRoomsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.reason = iprot.readString();
        struct.setReasonIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

