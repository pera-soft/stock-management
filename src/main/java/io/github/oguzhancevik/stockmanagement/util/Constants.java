package io.github.oguzhancevik.stockmanagement.util;

public class Constants {

    public static final class ENTITY {
        public static final String SEQUENCE_GENERATOR = "sm_sequence_generator";
        public static final String SEQUENCE_NAME = "sm_sequence";
    }

    public static final class API {
        public static final String BASE_MAPPING = "/api";
        public static final String CATEGORY_MAPPING = BASE_MAPPING + "/categories";
        public static final String SUB_CATEGORY_MAPPING = BASE_MAPPING + "/subcategories";
        public static final String PRODUCT_MAPPING = BASE_MAPPING + "/products";
        public static final String SHOPPING_CART_MAPPING = BASE_MAPPING + "/shopping-cart";
    }

    public static final class MAPPER {
        public static final String COMPONENT_MODEL = "spring";
    }

    public static final class SWAGGER {
        public static final String PATH_REGEX = "/api.*";
    }

    public static final class CACHE {
        public static final String STOCK = "stock";
        public static final String SEPARATOR = "::";
        public static final String STOCK_KEY = STOCK + SEPARATOR;
    }

    public static final class PROFILE {
        public static final String LOCAL = "local";
    }

}
