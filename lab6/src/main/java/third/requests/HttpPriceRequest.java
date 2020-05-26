package third.requests;

public class HttpPriceRequest {
    final String product;

    public HttpPriceRequest(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}