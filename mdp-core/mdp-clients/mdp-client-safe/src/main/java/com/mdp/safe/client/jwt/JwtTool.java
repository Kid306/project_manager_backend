package com.mdp.safe.client.jwt;

import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.nimbusds.jose.JWSAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTool {
    @Value(value = "${mdp.jwt.secret:}")
    String jwtSecret="";


    public static String defaultJwtSecret=".mdp.jwt.fdp&$#_+lajfadsjfasjgiiAkdkdkfjjk883u7idfaldfjdskaglakjfdaksfasdpqkxkdfjkdasdjfdk;agjas;jdfadskjfairrekdkkdfa;fjasdf";
    static String jwtSecret64 =DatatypeConverter.printBase64Binary(defaultJwtSecret.getBytes(StandardCharsets.UTF_8));

    static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret64);

    /**
     *
     * @param userid
     * @param claims
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String userid, Map<String,Object> claims, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getValue());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(userid)
                .setIssuedAt(now)
                .setSubject(userid)
                .setIssuer("mdp")
                .addClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(apiKeySecretBytes)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
    public static User jwt2User(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(apiKeySecretBytes)
                .parseClaimsJws(jwt).getBody();
        User user=new User();
        user.initByString((String) claims.get("userInfo"));
        user.setUserid(claims.getSubject());
        return user;
    }

    @PostConstruct
    public void init(){
        if(ObjectTools.isNotEmpty(this.jwtSecret)){
            JwtTool.jwtSecret64 =DatatypeConverter.printBase64Binary(this.jwtSecret.getBytes(StandardCharsets.UTF_8));

            JwtTool.apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret64);
        }

    }

    /**
     * {
     *     "sub": "4hinb8m16",
     *     "aud": "oauth2client",
     *     "userInfo": "陈裕财||1|202106038463||||qqkj_001|广州擎勤网络科技有限公司||13610336198|2|staff|||chenyc_002|https://www.qingqinkj.com/api/m1/arc/rand1670902291236/arc/image/user/4hinb8m16.jpg|4gq2fb5y32|||||||1|1|1,0,1,0,0|3|D|2|0",
     *     "nbf": 1692606536,
     *     "roles": "user,普通用户,1",
     *     "scope": [
     *         "all"
     *     ],
     *     "exp": 1692779336,
     *     "iat": 1692606536,
     *     "jti": "9454a86e-4287-4364-aa6e-06003b081897"
     * }
     * @param args
     */
    public static void main(String[] args) {


        Map<String,Object> claims=new HashMap<>();
        claims.put("userInfo","陈裕财||1|202106038463||||qqkj_001|广州擎勤网络科技有限公司||13610336198|2|staff|||chenyc_002|https://www.qingqinkj.com/api/m1/arc/rand1670902291236/arc/image/user/4hinb8m16.jpg|4gq2fb5y32|||||||1|1|1,0,1,0,0|3|D|2|0");
        claims.put("roles","user,普通用户,1");
        String jwt=JwtTool.createJWT("4hinb8m16",claims,10000L);
        System.out.println("jwt-->"+jwt);
        Claims claims2=JwtTool.parseJWT(jwt);
        System.out.println("claims2--->" + claims2.toString());

        String defaultJwtSecret=JwtTool.defaultJwtSecret;
        String jwtSecret64 =DatatypeConverter.printBase64Binary(defaultJwtSecret.getBytes(StandardCharsets.UTF_8));

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret64);

         SecretKey signingKey = new SecretKeySpec(apiKeySecretBytes, JWSAlgorithm.HS256.getName());
        JwtDecoder jwtDecoder= NimbusJwtDecoder.withSecretKey(signingKey).build();
        Jwt jwt1=jwtDecoder.decode(jwt);
        System.out.println(jwt1.toString());



    }
}
