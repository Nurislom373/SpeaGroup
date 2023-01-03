package org.khasanof.api_gateway.config;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/3/2023
 * <br/>
 * Time: 9:51 PM
 * <br/>
 * Package: org.khasanof.api_gateway.config
 */
public abstract class BaseUtils {

    public static final String[] AUTH_SERVICE_PATHS = {"/api/v1/auth_user/**", "/api/v1/auth_token/**",
            "/api/v1/blocked_for/**", "/api/v1/auth/**", "/api/v1/auth_follower/**", "api/v1/auth_following/**",
            "/api/v1/auth_info/**", "/api/v1/auth_invite/**", "/api/v1/education/**", "/api/v1/employment/**"};

    public static final String[] POST_SERVICE_PATHS = {"/api/v1/category/**", "/api/v1/post/**", "/api/v1/post_block/**",
            "/api/v1/post_category/**", "/api/v1/post_comment/**", "/api/v1/post_like/**", "/api/v1/post_rating/**",
            "/api/v1/post_report/**", "/api/v1/post_save/**", "/api/v1/post_share/**", "/api/v1/post_view/**"};

    public static final String[] QUESTION_SERVICE_PATHS = {"/api/v1/question/**", "/api/v1/question_category/**",
            "/api/v1/question_report/**", "/api/v1/question_view/**"};

    public static final String[] UPLOAD_SERVICE_PATHS = {"/api/v1/upload/**"};

}
