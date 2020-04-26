/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package rpc.thrift.leds;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2020-04-26")
public class Colour implements org.apache.thrift.TBase<Colour, Colour._Fields>, java.io.Serializable, Cloneable, Comparable<Colour> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Colour");

  private static final org.apache.thrift.protocol.TField RED_FIELD_DESC = new org.apache.thrift.protocol.TField("red", org.apache.thrift.protocol.TType.DOUBLE, (short)1);
  private static final org.apache.thrift.protocol.TField GREEN_FIELD_DESC = new org.apache.thrift.protocol.TField("green", org.apache.thrift.protocol.TType.DOUBLE, (short)2);
  private static final org.apache.thrift.protocol.TField BLUE_FIELD_DESC = new org.apache.thrift.protocol.TField("blue", org.apache.thrift.protocol.TType.DOUBLE, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ColourStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ColourTupleSchemeFactory();

  public double red; // required
  public double green; // required
  public double blue; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RED((short)1, "red"),
    GREEN((short)2, "green"),
    BLUE((short)3, "blue");

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
        case 1: // RED
          return RED;
        case 2: // GREEN
          return GREEN;
        case 3: // BLUE
          return BLUE;
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
  private static final int __RED_ISSET_ID = 0;
  private static final int __GREEN_ISSET_ID = 1;
  private static final int __BLUE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RED, new org.apache.thrift.meta_data.FieldMetaData("red", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.GREEN, new org.apache.thrift.meta_data.FieldMetaData("green", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.BLUE, new org.apache.thrift.meta_data.FieldMetaData("blue", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Colour.class, metaDataMap);
  }

  public Colour() {
  }

  public Colour(
    double red,
    double green,
    double blue)
  {
    this();
    this.red = red;
    setRedIsSet(true);
    this.green = green;
    setGreenIsSet(true);
    this.blue = blue;
    setBlueIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Colour(Colour other) {
    __isset_bitfield = other.__isset_bitfield;
    this.red = other.red;
    this.green = other.green;
    this.blue = other.blue;
  }

  public Colour deepCopy() {
    return new Colour(this);
  }

  @Override
  public void clear() {
    setRedIsSet(false);
    this.red = 0.0;
    setGreenIsSet(false);
    this.green = 0.0;
    setBlueIsSet(false);
    this.blue = 0.0;
  }

  public double getRed() {
    return this.red;
  }

  public Colour setRed(double red) {
    this.red = red;
    setRedIsSet(true);
    return this;
  }

  public void unsetRed() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RED_ISSET_ID);
  }

  /** Returns true if field red is set (has been assigned a value) and false otherwise */
  public boolean isSetRed() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RED_ISSET_ID);
  }

  public void setRedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RED_ISSET_ID, value);
  }

  public double getGreen() {
    return this.green;
  }

  public Colour setGreen(double green) {
    this.green = green;
    setGreenIsSet(true);
    return this;
  }

  public void unsetGreen() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __GREEN_ISSET_ID);
  }

  /** Returns true if field green is set (has been assigned a value) and false otherwise */
  public boolean isSetGreen() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __GREEN_ISSET_ID);
  }

  public void setGreenIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __GREEN_ISSET_ID, value);
  }

  public double getBlue() {
    return this.blue;
  }

  public Colour setBlue(double blue) {
    this.blue = blue;
    setBlueIsSet(true);
    return this;
  }

  public void unsetBlue() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __BLUE_ISSET_ID);
  }

  /** Returns true if field blue is set (has been assigned a value) and false otherwise */
  public boolean isSetBlue() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __BLUE_ISSET_ID);
  }

  public void setBlueIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __BLUE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case RED:
      if (value == null) {
        unsetRed();
      } else {
        setRed((java.lang.Double)value);
      }
      break;

    case GREEN:
      if (value == null) {
        unsetGreen();
      } else {
        setGreen((java.lang.Double)value);
      }
      break;

    case BLUE:
      if (value == null) {
        unsetBlue();
      } else {
        setBlue((java.lang.Double)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case RED:
      return getRed();

    case GREEN:
      return getGreen();

    case BLUE:
      return getBlue();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case RED:
      return isSetRed();
    case GREEN:
      return isSetGreen();
    case BLUE:
      return isSetBlue();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Colour)
      return this.equals((Colour)that);
    return false;
  }

  public boolean equals(Colour that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_red = true;
    boolean that_present_red = true;
    if (this_present_red || that_present_red) {
      if (!(this_present_red && that_present_red))
        return false;
      if (this.red != that.red)
        return false;
    }

    boolean this_present_green = true;
    boolean that_present_green = true;
    if (this_present_green || that_present_green) {
      if (!(this_present_green && that_present_green))
        return false;
      if (this.green != that.green)
        return false;
    }

    boolean this_present_blue = true;
    boolean that_present_blue = true;
    if (this_present_blue || that_present_blue) {
      if (!(this_present_blue && that_present_blue))
        return false;
      if (this.blue != that.blue)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(red);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(green);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(blue);

    return hashCode;
  }

  @Override
  public int compareTo(Colour other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRed()).compareTo(other.isSetRed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.red, other.red);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetGreen()).compareTo(other.isSetGreen());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGreen()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.green, other.green);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetBlue()).compareTo(other.isSetBlue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBlue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.blue, other.blue);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Colour(");
    boolean first = true;

    sb.append("red:");
    sb.append(this.red);
    first = false;
    if (!first) sb.append(", ");
    sb.append("green:");
    sb.append(this.green);
    first = false;
    if (!first) sb.append(", ");
    sb.append("blue:");
    sb.append(this.blue);
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

  private static class ColourStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ColourStandardScheme getScheme() {
      return new ColourStandardScheme();
    }
  }

  private static class ColourStandardScheme extends org.apache.thrift.scheme.StandardScheme<Colour> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Colour struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RED
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.red = iprot.readDouble();
              struct.setRedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GREEN
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.green = iprot.readDouble();
              struct.setGreenIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BLUE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.blue = iprot.readDouble();
              struct.setBlueIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Colour struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(RED_FIELD_DESC);
      oprot.writeDouble(struct.red);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(GREEN_FIELD_DESC);
      oprot.writeDouble(struct.green);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(BLUE_FIELD_DESC);
      oprot.writeDouble(struct.blue);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ColourTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ColourTupleScheme getScheme() {
      return new ColourTupleScheme();
    }
  }

  private static class ColourTupleScheme extends org.apache.thrift.scheme.TupleScheme<Colour> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Colour struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRed()) {
        optionals.set(0);
      }
      if (struct.isSetGreen()) {
        optionals.set(1);
      }
      if (struct.isSetBlue()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRed()) {
        oprot.writeDouble(struct.red);
      }
      if (struct.isSetGreen()) {
        oprot.writeDouble(struct.green);
      }
      if (struct.isSetBlue()) {
        oprot.writeDouble(struct.blue);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Colour struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.red = iprot.readDouble();
        struct.setRedIsSet(true);
      }
      if (incoming.get(1)) {
        struct.green = iprot.readDouble();
        struct.setGreenIsSet(true);
      }
      if (incoming.get(2)) {
        struct.blue = iprot.readDouble();
        struct.setBlueIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

