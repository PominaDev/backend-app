package com.pomina.erpapp.appbaohanh.common.constant;

public class ApiConstants {

    private ApiConstants() {
    }

    public static final String BASE_API = "/api";

    public static final String VERSION_V1 = BASE_API + "/v1";

    public static final class ApiCustomer {
        public static final String BASE = VERSION_V1 + "/customers";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiCustomer() {
        }
    }
}
