/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package rpc.thrift.vaccum;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2020-04-26")
public class Config implements org.apache.thrift.TBase<Config, Config._Fields>, java.io.Serializable, Cloneable, Comparable<Config> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Config");

  private static final org.apache.thrift.protocol.TField AUTO_CHARGE_FIELD_DESC = new org.apache.thrift.protocol.TField("autoCharge", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField AUTO_GARBAGE_DISPOSE_FIELD_DESC = new org.apache.thrift.protocol.TField("autoGarbageDispose", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField AUTO_CLEAN_FIELD_DESC = new org.apache.thrift.protocol.TField("autoClean", org.apache.thrift.protocol.TType.BOOL, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ConfigStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ConfigTupleSchemeFactory();

  public boolean autoCharge; // required
  public boolean autoGarbageDispose; // required
  public boolean autoClean; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AUTO_CHARGE((short)1, "autoCharge"),
    AUTO_GARBAGE_DISPOSE((short)2, "autoGarbageDispose"),
    AUTO_CLEAN((short)3, "autoClean");

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
        case 1: // AUTO_CHARGE
          return AUTO_CHARGE;
        case 2: // AUTO_GARBAGE_DISPOSE
          return AUTO_GARBAGE_DISPOSE;
        case 3: // AUTO_CLEAN
          return AUTO_CLEAN;
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
  private static final int __AUTOCHARGE_ISSET_ID = 0;
  private static final int __AUTOGARBAGEDISPOSE_ISSET_ID = 1;
  private static final int __AUTOCLEAN_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AUTO_CHARGE, new org.apache.thrift.meta_data.FieldMetaData("autoCharge", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.AUTO_GARBAGE_DISPOSE, new org.apache.thrift.meta_data.FieldMetaData("autoGarbageDispose", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.AUTO_CLEAN, new org.apache.thrift.meta_data.FieldMetaData("autoClean", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Config.class, metaDataMap);
  }

  public Config() {
  }

  public Config(
    boolean autoCharge,
    boolean autoGarbageDispose,
    boolean autoClean)
  {
    this();
    this.autoCharge = autoCharge;
    setAutoChargeIsSet(true);
    this.autoGarbageDispose = autoGarbageDispose;
    setAutoGarbageDisposeIsSet(true);
    this.autoClean = autoClean;
    setAutoCleanIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Config(Config other) {
    __isset_bitfield = other.__isset_bitfield;
    this.autoCharge = other.autoCharge;
    this.autoGarbageDispose = other.autoGarbageDispose;
    this.autoClean = other.autoClean;
  }

  public Config deepCopy() {
    return new Config(this);
  }

  @Override
  public void clear() {
    setAutoChargeIsSet(false);
    this.autoCharge = false;
    setAutoGarbageDisposeIsSet(false);
    this.autoGarbageDispose = false;
    setAutoCleanIsSet(false);
    this.autoClean = false;
  }

  public boolean isAutoCharge() {
    return this.autoCharge;
  }

  public Config setAutoCharge(boolean autoCharge) {
    this.autoCharge = autoCharge;
    setAutoChargeIsSet(true);
    return this;
  }

  public void unsetAutoCharge() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AUTOCHARGE_ISSET_ID);
  }

  /** Returns true if field autoCharge is set (has been assigned a value) and false otherwise */
  public boolean isSetAutoCharge() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AUTOCHARGE_ISSET_ID);
  }

  public void setAutoChargeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AUTOCHARGE_ISSET_ID, value);
  }

  public boolean isAutoGarbageDispose() {
    return this.autoGarbageDispose;
  }

  public Config setAutoGarbageDispose(boolean autoGarbageDispose) {
    this.autoGarbageDispose = autoGarbageDispose;
    setAutoGarbageDisposeIsSet(true);
    return this;
  }

  public void unsetAutoGarbageDispose() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AUTOGARBAGEDISPOSE_ISSET_ID);
  }

  /** Returns true if field autoGarbageDispose is set (has been assigned a value) and false otherwise */
  public boolean isSetAutoGarbageDispose() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AUTOGARBAGEDISPOSE_ISSET_ID);
  }

  public void setAutoGarbageDisposeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AUTOGARBAGEDISPOSE_ISSET_ID, value);
  }

  public boolean isAutoClean() {
    return this.autoClean;
  }

  public Config setAutoClean(boolean autoClean) {
    this.autoClean = autoClean;
    setAutoCleanIsSet(true);
    return this;
  }

  public void unsetAutoClean() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AUTOCLEAN_ISSET_ID);
  }

  /** Returns true if field autoClean is set (has been assigned a value) and false otherwise */
  public boolean isSetAutoClean() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AUTOCLEAN_ISSET_ID);
  }

  public void setAutoCleanIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AUTOCLEAN_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case AUTO_CHARGE:
      if (value == null) {
        unsetAutoCharge();
      } else {
        setAutoCharge((java.lang.Boolean)value);
      }
      break;

    case AUTO_GARBAGE_DISPOSE:
      if (value == null) {
        unsetAutoGarbageDispose();
      } else {
        setAutoGarbageDispose((java.lang.Boolean)value);
      }
      break;

    case AUTO_CLEAN:
      if (value == null) {
        unsetAutoClean();
      } else {
        setAutoClean((java.lang.Boolean)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case AUTO_CHARGE:
      return isAutoCharge();

    case AUTO_GARBAGE_DISPOSE:
      return isAutoGarbageDispose();

    case AUTO_CLEAN:
      return isAutoClean();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case AUTO_CHARGE:
      return isSetAutoCharge();
    case AUTO_GARBAGE_DISPOSE:
      return isSetAutoGarbageDispose();
    case AUTO_CLEAN:
      return isSetAutoClean();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Config)
      return this.equals((Config)that);
    return false;
  }

  public boolean equals(Config that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_autoCharge = true;
    boolean that_present_autoCharge = true;
    if (this_present_autoCharge || that_present_autoCharge) {
      if (!(this_present_autoCharge && that_present_autoCharge))
        return false;
      if (this.autoCharge != that.autoCharge)
        return false;
    }

    boolean this_present_autoGarbageDispose = true;
    boolean that_present_autoGarbageDispose = true;
    if (this_present_autoGarbageDispose || that_present_autoGarbageDispose) {
      if (!(this_present_autoGarbageDispose && that_present_autoGarbageDispose))
        return false;
      if (this.autoGarbageDispose != that.autoGarbageDispose)
        return false;
    }

    boolean this_present_autoClean = true;
    boolean that_present_autoClean = true;
    if (this_present_autoClean || that_present_autoClean) {
      if (!(this_present_autoClean && that_present_autoClean))
        return false;
      if (this.autoClean != that.autoClean)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((autoCharge) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((autoGarbageDispose) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((autoClean) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(Config other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAutoCharge()).compareTo(other.isSetAutoCharge());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAutoCharge()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.autoCharge, other.autoCharge);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAutoGarbageDispose()).compareTo(other.isSetAutoGarbageDispose());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAutoGarbageDispose()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.autoGarbageDispose, other.autoGarbageDispose);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAutoClean()).compareTo(other.isSetAutoClean());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAutoClean()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.autoClean, other.autoClean);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Config(");
    boolean first = true;

    sb.append("autoCharge:");
    sb.append(this.autoCharge);
    first = false;
    if (!first) sb.append(", ");
    sb.append("autoGarbageDispose:");
    sb.append(this.autoGarbageDispose);
    first = false;
    if (!first) sb.append(", ");
    sb.append("autoClean:");
    sb.append(this.autoClean);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ConfigStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ConfigStandardScheme getScheme() {
      return new ConfigStandardScheme();
    }
  }

  private static class ConfigStandardScheme extends org.apache.thrift.scheme.StandardScheme<Config> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Config struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AUTO_CHARGE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.autoCharge = iprot.readBool();
              struct.setAutoChargeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // AUTO_GARBAGE_DISPOSE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.autoGarbageDispose = iprot.readBool();
              struct.setAutoGarbageDisposeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // AUTO_CLEAN
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.autoClean = iprot.readBool();
              struct.setAutoCleanIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Config struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(AUTO_CHARGE_FIELD_DESC);
      oprot.writeBool(struct.autoCharge);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(AUTO_GARBAGE_DISPOSE_FIELD_DESC);
      oprot.writeBool(struct.autoGarbageDispose);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(AUTO_CLEAN_FIELD_DESC);
      oprot.writeBool(struct.autoClean);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ConfigTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ConfigTupleScheme getScheme() {
      return new ConfigTupleScheme();
    }
  }

  private static class ConfigTupleScheme extends org.apache.thrift.scheme.TupleScheme<Config> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Config struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAutoCharge()) {
        optionals.set(0);
      }
      if (struct.isSetAutoGarbageDispose()) {
        optionals.set(1);
      }
      if (struct.isSetAutoClean()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetAutoCharge()) {
        oprot.writeBool(struct.autoCharge);
      }
      if (struct.isSetAutoGarbageDispose()) {
        oprot.writeBool(struct.autoGarbageDispose);
      }
      if (struct.isSetAutoClean()) {
        oprot.writeBool(struct.autoClean);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Config struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.autoCharge = iprot.readBool();
        struct.setAutoChargeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.autoGarbageDispose = iprot.readBool();
        struct.setAutoGarbageDisposeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.autoClean = iprot.readBool();
        struct.setAutoCleanIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

