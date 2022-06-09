package com.example.bigScreen.config.jwt;

import com.example.bigScreen.entity.SysUserDetails;
import com.example.bigScreen.utils.JWTTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:18
 * @description:
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // 取出Token
        String token = request.getHeader(JWTConfig.tokenHeader);

        if (token != null && token.startsWith(JWTConfig.tokenPrefix)) {
            SysUserDetails sysUserDetails = JWTTokenUtil.parseAccessToken(token);

            if (sysUserDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        sysUserDetails, sysUserDetails.getId(), sysUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
