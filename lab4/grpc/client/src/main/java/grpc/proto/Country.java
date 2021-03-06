// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cv.proto

package grpc.proto;

/**
 * Protobuf enum {@code Country}
 */
public enum Country
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>US = 0;</code>
   */
  US(0),
  /**
   * <code>Poland = 1;</code>
   */
  Poland(1),
  /**
   * <code>Germany = 2;</code>
   */
  Germany(2),
  /**
   * <code>France = 3;</code>
   */
  France(3),
  /**
   * <code>Japan = 4;</code>
   */
  Japan(4),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>US = 0;</code>
   */
  public static final int US_VALUE = 0;
  /**
   * <code>Poland = 1;</code>
   */
  public static final int Poland_VALUE = 1;
  /**
   * <code>Germany = 2;</code>
   */
  public static final int Germany_VALUE = 2;
  /**
   * <code>France = 3;</code>
   */
  public static final int France_VALUE = 3;
  /**
   * <code>Japan = 4;</code>
   */
  public static final int Japan_VALUE = 4;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Country valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static Country forNumber(int value) {
    switch (value) {
      case 0: return US;
      case 1: return Poland;
      case 2: return Germany;
      case 3: return France;
      case 4: return Japan;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Country>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Country> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Country>() {
          public Country findValueByNumber(int number) {
            return Country.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return grpc.proto.CVProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final Country[] VALUES = values();

  public static Country valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Country(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:Country)
}

