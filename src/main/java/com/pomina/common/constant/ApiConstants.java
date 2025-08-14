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
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
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

        // App
        public static final String ACTIVATE_BY_QR = "/activate-by-qr";
        public static final String GET_WARRANTY_INFO_HISTORY = "/history";

        // Web
        public static final String GET_WARRANTY_INFO_HISTORY_ALL = "/history/all";
        public static final String ACTIVATE_WARRANTY = "/activate/{id}";

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
        public static final String GET_USER_ACTIVE_ROLE_MOBILE = "/get-user-active-role-mobile";
        public static final String GET_USERS_PAGED = "/get-users-paged";
        public static final String GET_ALL_USERS = "/get-all-users";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";

        private ApiSysUserManagement() {}
    }

    public static final class ApiMasterLocation {
        public static final String BASE = VERSION_V1 + "/master-location-management";
        public static final String GET_ALL_MASTER_LOCATION = "/get-all-master-location";

        private ApiMasterLocation() {
        }
    }

    public static final class ApiSysRole {
        public static final String BASE = VERSION_V1 + "/user-role-management";
        public static final String GET_ALL_ROLE = "/get-all-role";

        private ApiSysRole() {
        }
    }

    public static final class ApiMobileRole {
        public static final String BASE = VERSION_V1 + "/mobile-role";
        public static final String GET_ALL_ROLE = "/get-all-role";

        private ApiMobileRole() {}
    }

    public static final class ApiProfile {
        public static final String BASE = VERSION_V1 + "/profiles";
        public static final String UPDATE = "/edit";

        private ApiProfile() {}
    }

    public static final class ApiMasterGroupUser {
        public static final String BASE = VERSION_V1 + "/master-group-users";
        public static final String CREATE = "/create";
        public static final String CREATE_LIST = "/create-list";
        public static final String UPDATE = "/update/{id}";
        public static final String UPDATE_LIST = "/update-list";
        public static final String DELETE = "/delete/{id}";
        public static final String DELETE_LIST = "/delete-list";
        public static final String GET_BY_ID = "/{id}";
        public static final String SEARCH = "/search";
        public static final String GET_ALL = "/all";
        public static final String GET_BY_GROUP_USER_CODE = "/get-by-group-user-code/{groupUserCode}";

        private ApiMasterGroupUser() {}
    }

    public static final class ApiStatisticsSysUerActiveByRole {
        public static final String BASE = VERSION_V1 + "/statistics-sys-uer-active-by-role";
        public static final String GET_ALL = "/all";

        private ApiStatisticsSysUerActiveByRole() {}
    }

    public static final class ApiStatisticsSysUerActiveByMLocation {
        public static final String BASE = VERSION_V1 + "/statistics-sys-uer-active-by-m-location";
        public static final String GET_ALL = "/all";

        private ApiStatisticsSysUerActiveByMLocation() {}
    }

    public static final class ApiStatisticsSysUerActiveByULocation {
        public static final String BASE = VERSION_V1 + "/statistics-sys-uer-active-by-u-location";
        public static final String GET_ALL = "/all";

        private ApiStatisticsSysUerActiveByULocation() {}
    }

    public static final class ApiPricingPolicyManagement {
        public static final String BASE = VERSION_V1 + "/pricing-policies";
        public static final String IMPORT_LIST = "/import";

        private ApiPricingPolicyManagement() {}
    }
}
