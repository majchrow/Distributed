/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package rpc.thrift.vaccum;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2020-04-26")
public enum Room implements org.apache.thrift.TEnum {
  KITCHEN(0),
  BATHROOM(1),
  LIVINGROOM(2);

  private final int value;

  private Room(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static Room findByValue(int value) { 
    switch (value) {
      case 0:
        return KITCHEN;
      case 1:
        return BATHROOM;
      case 2:
        return LIVINGROOM;
      default:
        return null;
    }
  }
}
