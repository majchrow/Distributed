// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cv.proto

package grpc.proto;

public final class CVProto {
  private CVProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Request_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CurriculumVitae_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CurriculumVitae_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Job_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Job_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Person_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Person_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Birth_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Birth_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\010cv.proto\"d\n\007Request\022\037\n\006action\030\001 \001(\0162\017." +
      "Request.Action\022\031\n\007country\030\002 \001(\0162\010.Countr" +
      "y\"\035\n\006Action\022\t\n\005START\020\000\022\010\n\004STOP\020\001\"[\n\017Curr" +
      "iculumVitae\022\027\n\006person\030\001 \001(\0132\007.Person\022\032\n\014" +
      "previousJobs\030\002 \003(\0132\004.Job\022\023\n\013description\030" +
      "\003 \001(\t\"#\n\003Job\022\013\n\003job\030\001 \001(\t\022\017\n\007company\030\002 \001" +
      "(\t\"j\n\006Person\022\021\n\tfirstName\030\001 \001(\t\022\022\n\nsecon" +
      "dName\030\002 \001(\t\022\025\n\005birth\030\003 \001(\0132\006.Birth\022\r\n\005em" +
      "ail\030\004 \001(\t\022\023\n\013phoneNumber\030\005 \001(\t\"0\n\005Birth\022" +
      "\031\n\007country\030\001 \001(\0162\010.Country\022\014\n\004date\030\002 \001(\t" +
      "*A\n\007Country\022\006\n\002US\020\000\022\n\n\006Poland\020\001\022\013\n\007Germa" +
      "ny\020\002\022\n\n\006France\020\003\022\t\n\005Japan\020\00424\n\002CV\022.\n\014Sub" +
      "scription\022\010.Request\032\020.CurriculumVitae(\0010" +
      "\001B\027\n\ngrpc.protoB\007CVProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Request_descriptor,
        new java.lang.String[] { "Action", "Country", });
    internal_static_CurriculumVitae_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_CurriculumVitae_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CurriculumVitae_descriptor,
        new java.lang.String[] { "Person", "PreviousJobs", "Description", });
    internal_static_Job_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Job_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Job_descriptor,
        new java.lang.String[] { "Job", "Company", });
    internal_static_Person_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Person_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Person_descriptor,
        new java.lang.String[] { "FirstName", "SecondName", "Birth", "Email", "PhoneNumber", });
    internal_static_Birth_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_Birth_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Birth_descriptor,
        new java.lang.String[] { "Country", "Date", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
