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
        public static final String REGISTER = "/register";
        public static final String REFRESH_TOKEN = "/refresh-token";

        public static final String OTP_SEND = "/otp/send";
        public static final String OTP_VERIFY = "/otp/verify";

        private ApiAuth() {}
    }

    public static final class ApiNotification {
        public static final String BASE = VERSION_V1 + "/notifications";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiNotification() {}
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

    public static final class ApiProduct {
        public static final String BASE = VERSION_V1 + "/products";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiProduct() {
        }
    }

    public static final class ApiClientManager {
        public static final String BASE = VERSION_V1 + "/users";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiClientManager() {
        }
    }

    public static final class ApiRoleManager {
        public static final String BASE = VERSION_V1 + "/roles";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiRoleManager() {
        }
    }

    public static final class ApiLocation {
        public static final String BASE = VERSION_V1 + "/locations";
        public static final String REGISTER = "/register";
        public static final String VALIDATE = "/validate";

        private ApiLocation() {}
    }

    public static final class ApiMasterMenu {
        public static final String BASE = VERSION_V1 + "/master-menus";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";
        public static final String GET_ALL = "/all";

        private ApiMasterMenu() {}
    }

    public static final class ApiMasterPermission {
        public static final String BASE = VERSION_V1 + "/master-permissions";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";
        public static final String GET_ALL = "/all";

        private ApiMasterPermission() {}
    }
}
