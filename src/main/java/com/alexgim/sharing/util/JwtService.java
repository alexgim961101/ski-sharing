package com.alexgim.sharing.util;

import com.alexgim.sharing.config.secret.Secret;
import com.alexgim.sharing.handler.ex.BaseException;
import com.alexgim.sharing.handler.ex.BaseResponseStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtService {

    // JWT 생성
    public String createJwt(int userId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("userId", userId)
                .setIssuedAt(now)                                                                 // 발급 일자
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*60*24*365)))        // 만료 기간 : 생성 후 1년
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                .compact();
    }

    // Header에서 Jwt 토큰 추출 (Header : X-ACCESS-TOKEN)
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    // JWT에서 userId 추출
    public int getUserId() throws BaseException {
        String accessToken = getJwt();
        if(accessToken == null || accessToken.length() == 0) throw new BaseException(BaseResponseStatus.EMPTY_JWT);

        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INVALID_JWT);
        }

        return claims.getBody().get("userId", Integer.class);
    }
}
