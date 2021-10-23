package com.pablo.cantina.error;

public class ErrorDetails extends Error {

    public static final class Builder {
        private String title;
        private String message;
        private int code;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle(this.title);
            errorDetails.setCode(this.code);
            errorDetails.setMessage(this.message);
            return errorDetails;
        }
    }
}
