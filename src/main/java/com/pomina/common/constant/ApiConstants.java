package com.pomina.common.constant;

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

        private ApiNotification() {}
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

    public static final class ApiWarranty {
        public static final String BASE = VERSION_V1 + "/warranties";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiWarranty() {
        }
    }

    public static final class ApiClientManager {
        public static final String BASE = VERSION_V1 + "/users";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";

        private ApiClientManager() {
        }
    }

    public static final class ApiRoleManager {
        public static final String BASE = VERSION_V1 + "/roles";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";

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
        public static final String CREATE_LIST = "/create-list";
        public static final String UPDATE = "/update/{id}";
        public static final String UPDATE_LIST = "/update-list";
        public static final String DELETE = "/delete/{id}";
        public static final String DELETE_LIST = "/delete-list";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";
        public static final String GET_ALL = "/all";

        private ApiMasterMenu() {}
    }

    public static final class ApiMasterPermission {
        public static final String BASE = VERSION_V1 + "/master-permissions";
        public static final String CREATE = "/create";
        public static final String CREATE_LIST = "/create-list";
        public static final String UPDATE = "/update/{id}";
        public static final String UPDATE_LIST = "/update-list";
        public static final String DELETE = "/delete/{id}";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";

        private ApiMasterPermission() {}
    }

    public static final class ApiUserMenuPermission {
        public static final String BASE = VERSION_V1 + "/menu-permission-by-userId";
        public static final String GET_BY_USER_ID = "/{userId}";

        private ApiUserMenuPermission() {}
    }

    public static final class ApiSysUserManagement {
        public static final String BASE = VERSION_V1 + "/user-management";
        public static final String GET_USER_ACTIVE_ROLE_WEB = "/get-user-active-role-web";

        private ApiSysUserManagement() {}
    }
}
