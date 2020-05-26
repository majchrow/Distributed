package third.requests;

public class HttpReviewRequest {
    final String product;

    public HttpReviewRequest(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}
