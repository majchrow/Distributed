package grpc.client;

import grpc.proto.CVGrpc;
import grpc.proto.Country;
import grpc.proto.CurriculumVitae;
import grpc.proto.Request;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;

public class CVStub {
    private final CVGrpc.CVStub cvStub;
    private StreamObserver<Request> stream;

    public CVStub(ManagedChannel channel) {
        this.cvStub = CVGrpc.newStub(channel);
        this.stream = createRequestStream();
    }

    public void createAndSendRequest(Request.Action action, Country country) {
        Request request = Request.newBuilder()
                .setAction(action)
                .setCountry(country)
                .build();
        stream.onNext(request);
    }


    private StreamObserver<Request> createRequestStream() {

        StreamObserver<CurriculumVitae> responseStreamObserver = new StreamObserver<CurriculumVitae>() {
            @Override
            public void onNext(CurriculumVitae curriculumVitae) {
                System.out.println(curriculumVitae.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
                if (throwable.getMessage().contains("UNAVAILABLE")){
                    System.out.println("Stream is closed");
                    stream = null;
                }

            }

            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }
        };
        StreamObserver<Request> requestStreamObserver;
        requestStreamObserver = cvStub.subscription(responseStreamObserver);
        return requestStreamObserver;
    }

    public boolean streamIsNotRunning(){
        return stream==null;
    }

}
