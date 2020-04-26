// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cv.proto

package grpc.proto;

/**
 * Protobuf type {@code Request}
 */
public  final class Request extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Request)
    RequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Request.newBuilder() to construct.
  private Request(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Request() {
    action_ = 0;
    country_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Request();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Request(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            int rawValue = input.readEnum();

            action_ = rawValue;
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            country_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return grpc.proto.CVProto.internal_static_Request_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return grpc.proto.CVProto.internal_static_Request_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.proto.Request.class, grpc.proto.Request.Builder.class);
  }

  /**
   * Protobuf enum {@code Request.Action}
   */
  public enum Action
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>START = 0;</code>
     */
    START(0),
    /**
     * <code>STOP = 1;</code>
     */
    STOP(1),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>START = 0;</code>
     */
    public static final int START_VALUE = 0;
    /**
     * <code>STOP = 1;</code>
     */
    public static final int STOP_VALUE = 1;


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
    public static Action valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static Action forNumber(int value) {
      switch (value) {
        case 0: return START;
        case 1: return STOP;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Action>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Action> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Action>() {
            public Action findValueByNumber(int number) {
              return Action.forNumber(number);
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
      return grpc.proto.Request.getDescriptor().getEnumTypes().get(0);
    }

    private static final Action[] VALUES = values();

    public static Action valueOf(
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

    private Action(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:Request.Action)
  }

  public static final int ACTION_FIELD_NUMBER = 1;
  private int action_;
  /**
   * <code>.Request.Action action = 1;</code>
   * @return The enum numeric value on the wire for action.
   */
  public int getActionValue() {
    return action_;
  }
  /**
   * <code>.Request.Action action = 1;</code>
   * @return The action.
   */
  public grpc.proto.Request.Action getAction() {
    @SuppressWarnings("deprecation")
    grpc.proto.Request.Action result = grpc.proto.Request.Action.valueOf(action_);
    return result == null ? grpc.proto.Request.Action.UNRECOGNIZED : result;
  }

  public static final int COUNTRY_FIELD_NUMBER = 2;
  private int country_;
  /**
   * <code>.Country country = 2;</code>
   * @return The enum numeric value on the wire for country.
   */
  public int getCountryValue() {
    return country_;
  }
  /**
   * <code>.Country country = 2;</code>
   * @return The country.
   */
  public grpc.proto.Country getCountry() {
    @SuppressWarnings("deprecation")
    grpc.proto.Country result = grpc.proto.Country.valueOf(country_);
    return result == null ? grpc.proto.Country.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (action_ != grpc.proto.Request.Action.START.getNumber()) {
      output.writeEnum(1, action_);
    }
    if (country_ != grpc.proto.Country.US.getNumber()) {
      output.writeEnum(2, country_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (action_ != grpc.proto.Request.Action.START.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, action_);
    }
    if (country_ != grpc.proto.Country.US.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, country_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof grpc.proto.Request)) {
      return super.equals(obj);
    }
    grpc.proto.Request other = (grpc.proto.Request) obj;

    if (action_ != other.action_) return false;
    if (country_ != other.country_) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ACTION_FIELD_NUMBER;
    hash = (53 * hash) + action_;
    hash = (37 * hash) + COUNTRY_FIELD_NUMBER;
    hash = (53 * hash) + country_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.proto.Request parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.proto.Request parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.proto.Request parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.proto.Request parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.proto.Request parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.proto.Request parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.proto.Request parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.proto.Request parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.proto.Request parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static grpc.proto.Request parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.proto.Request parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.proto.Request parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(grpc.proto.Request prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Request}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Request)
      grpc.proto.RequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return grpc.proto.CVProto.internal_static_Request_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return grpc.proto.CVProto.internal_static_Request_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.proto.Request.class, grpc.proto.Request.Builder.class);
    }

    // Construct using grpc.proto.Request.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      action_ = 0;

      country_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return grpc.proto.CVProto.internal_static_Request_descriptor;
    }

    @java.lang.Override
    public grpc.proto.Request getDefaultInstanceForType() {
      return grpc.proto.Request.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.proto.Request build() {
      grpc.proto.Request result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.proto.Request buildPartial() {
      grpc.proto.Request result = new grpc.proto.Request(this);
      result.action_ = action_;
      result.country_ = country_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof grpc.proto.Request) {
        return mergeFrom((grpc.proto.Request)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.proto.Request other) {
      if (other == grpc.proto.Request.getDefaultInstance()) return this;
      if (other.action_ != 0) {
        setActionValue(other.getActionValue());
      }
      if (other.country_ != 0) {
        setCountryValue(other.getCountryValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      grpc.proto.Request parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (grpc.proto.Request) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int action_ = 0;
    /**
     * <code>.Request.Action action = 1;</code>
     * @return The enum numeric value on the wire for action.
     */
    public int getActionValue() {
      return action_;
    }
    /**
     * <code>.Request.Action action = 1;</code>
     * @param value The enum numeric value on the wire for action to set.
     * @return This builder for chaining.
     */
    public Builder setActionValue(int value) {
      action_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.Request.Action action = 1;</code>
     * @return The action.
     */
    public grpc.proto.Request.Action getAction() {
      @SuppressWarnings("deprecation")
      grpc.proto.Request.Action result = grpc.proto.Request.Action.valueOf(action_);
      return result == null ? grpc.proto.Request.Action.UNRECOGNIZED : result;
    }
    /**
     * <code>.Request.Action action = 1;</code>
     * @param value The action to set.
     * @return This builder for chaining.
     */
    public Builder setAction(grpc.proto.Request.Action value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      action_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.Request.Action action = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearAction() {
      
      action_ = 0;
      onChanged();
      return this;
    }

    private int country_ = 0;
    /**
     * <code>.Country country = 2;</code>
     * @return The enum numeric value on the wire for country.
     */
    public int getCountryValue() {
      return country_;
    }
    /**
     * <code>.Country country = 2;</code>
     * @param value The enum numeric value on the wire for country to set.
     * @return This builder for chaining.
     */
    public Builder setCountryValue(int value) {
      country_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.Country country = 2;</code>
     * @return The country.
     */
    public grpc.proto.Country getCountry() {
      @SuppressWarnings("deprecation")
      grpc.proto.Country result = grpc.proto.Country.valueOf(country_);
      return result == null ? grpc.proto.Country.UNRECOGNIZED : result;
    }
    /**
     * <code>.Country country = 2;</code>
     * @param value The country to set.
     * @return This builder for chaining.
     */
    public Builder setCountry(grpc.proto.Country value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      country_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.Country country = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearCountry() {
      
      country_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Request)
  }

  // @@protoc_insertion_point(class_scope:Request)
  private static final grpc.proto.Request DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.proto.Request();
  }

  public static grpc.proto.Request getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Request>
      PARSER = new com.google.protobuf.AbstractParser<Request>() {
    @java.lang.Override
    public Request parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Request(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Request> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Request> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.proto.Request getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

