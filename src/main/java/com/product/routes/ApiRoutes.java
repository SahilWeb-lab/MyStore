package com.product.routes;

import org.springframework.util.AntPathMatcher;

public class ApiRoutes {

	public static final String[] PUBLIC_ENDPOINTS = { 
		"/api/v1/auth/**", 
		"/api/v1/product/public/**", 
		"/api/v1/category/public/**",
		"/api/v1/brand/public/**",
	};

	public static final String[] ADMIN_ENDPOINTS = {
		"/api/v1/product/**",
		"/api/v1/category/**",
		"/api/v1/brand/**"
	};

	public static final String[] USER_ENDPOINTS = {
		"/api/v1/wishlist/**",
		"/api/v1/cart/**",
		"/api/v1/address/**",
		"/api/v1/order/**",
	};
	

	private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
	
    // Utility method to check if a path is public
    public static boolean isPublicPath(String path) {
    	System.out.println("Method is Running...");
        for (String pattern : PUBLIC_ENDPOINTS) {
            if(PATH_MATCHER.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }

}
