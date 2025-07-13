package com.pomina.erpapp.appbaohanh.common.constant;

public class ApiConstants {

    private ApiConstants() {
    }

    public static final String BASE_API = "/api";

    public static final String VERSION_V1 = BASE_API + "/v1";

    public static final class ApiAuth {
        public static final String BASE = VERSION_V1 + "/auth";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";

        private ApiAuth() {}
    }

    public static final class ApiCustomer {
        public static final String BASE = VERSION_V1 + "/customers";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiCustomer() {}
    }

    public static final class ApiClientManager {
        public static final String BASE = VERSION_V1 + "/client-manager";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiClientManager() {
        }
    }

    public static final class ApiRoleManager {
        public static final String BASE = VERSION_V1 + "/role-manager";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiRoleManager() {
        }
    }
}
