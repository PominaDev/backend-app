package com.pomina.security.sysservice;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.utils.NumberUtil;
import com.pomina.common.utils.PhoneUtil;
import com.pomina.commonservices.notification.zns.enums.ZaloZnsTemplate;
import com.pomina.commonservices.notification.zns.model.request.ZaloZNSRequest;
import com.pomina.commonservices.notification.zns.service.ZNSService;
import com.pomina.security.config.JwtIssuer;
import com.pomina.security.config.UserPrincipal;
import com.pomina.security.mapper.SysUserMapper;
import com.pomina.security.model.SysUser;
import com.pomina.security.sysmodel.LoginResponse;
import com.pomina.security.sysmodel.otp_based.OtpRequest;
import com.pomina.security.sysmodel.otp_based.OtpResponse;
import com.pomina.security.sysmodel.otp_based.VerifyOtpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final PasswordEncoder passwordEncoder;

    private final RedisTemplate<String, String> redisTemplate;

    private final JwtIssuer jwtIssuer;

    private final SysUserMapper sysUserMapper;

    private final ZNSService znsService;

    private static final String PREFIX = "[OTP]:";

    /**
     * Gửi mã OTP xác thực
     *
     * @param otpRequest {@link OtpRequest}
     * @return {@link OtpResponse}
     */
    public OtpResponse sendOtp(OtpRequest otpRequest) {

        String phoneNumber = otpRequest.getPhoneNumber();
        String phoneNumberNormalize = PhoneUtil.normalizePhoneNumber(phoneNumber);

        if (!PhoneUtil.isValidPhoneNumber(phoneNumberNormalize)) {
            throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
        }

        // Sinh OTP 6 số ngẫu nhiên
        String otpCode = NumberUtil.generateNumber(6);

        // Sinh otpToken để hạn chế bị đánh cắp token
        Map<String, Object> templateData = Map.of("otp", otpCode);

        // Sinh tracking id để đối soát với Zalo ZNS
        String trackingId = UUID.randomUUID().toString();

        ZaloZNSRequest zaloZNSRequest = ZaloZNSRequest.builder()
                .mode("development") // Môi trường DEV
                .phone(phoneNumberNormalize)
                .templateId(ZaloZnsTemplate.ZALO_OTP.getTemplateId())
                .templateData(ZaloZnsTemplate.ZALO_OTP.buildTemplateData(templateData))
                .trackingId(trackingId)
                .build();
        znsService.sendZaloZNS(zaloZNSRequest);

        // Sinh otpToken để hạn chế bị đánh cắp token
        String otpToken = UUID.randomUUID().toString();

        // Save Redis
        saveOtp(phoneNumber, otpCode, otpToken, TimeUnit.MINUTES.toSeconds(60)); // 1 Phút

        return OtpResponse.builder()
                .otp(otpCode)
                .otpToken(otpToken)
                .build();
    }

    /**
     * Xác thực mã OTP để đăng ký tài khoản
     *
     * @param verifyOtpRequest {@link VerifyOtpRequest}
     * @return {@link LoginResponse}
     */
    public LoginResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {

        String phoneNumber = verifyOtpRequest.getPhoneNumber();
        String phoneNumberNormalize = PhoneUtil.normalizePhoneNumber(phoneNumber);

        // Get Redis and verify OTP
        boolean isVerifyStoredOtp = verifyOtpWithOtpRedis(phoneNumber, verifyOtpRequest.getOtp(), verifyOtpRequest.getOtpToken());
        if (!isVerifyStoredOtp) {
            throw new AppException(ErrorCode.OTP_FAILED);
        }

        if (!PhoneUtil.isValidPhoneNumber(phoneNumberNormalize)) {
            throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
        }

        // Delete Redis
        deleteOtp(phoneNumber, verifyOtpRequest.getOtpToken());

        SysUser sysUser = getSysUserByPhone(phoneNumber)
                .orElseGet(() -> createNewUser(phoneNumber));

        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .sysUser(sysUser)
                .userId(sysUser.getUserId())
                .userName(sysUser.getUsername())
                .hoVaTen(sysUser.getHoVaTen())
                .phoneNumber(sysUser.getPhoneNumber())
                .password(sysUser.getPassword())
                .authorities(authorities)
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtIssuer.issue(
                JwtIssuer.Request.builder()
                        .userId(sysUser.getUserId())
                        .userName(sysUser.getUsername())
                        .fullName(sysUser.getHoVaTen())
                        .phoneNumber(phoneNumber)
                        .roles(authorities
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList())
                        .tokenType(JwtIssuer.TokenType.ACCESS_TOKEN)
                        .build()
        );

        return LoginResponse.builder()
                .accessToken(accessToken)
                .username(userPrincipal.getUsername())
                .fullName(userPrincipal.getHoVaTen())
                .phoneNumber(userPrincipal.getPhoneNumber())
                .roleName(userPrincipal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
                )
                .build();
    }


    private Optional<SysUser> getSysUserByPhone(String phoneNumber) {
        return Optional.ofNullable(sysUserMapper.findByUserName(phoneNumber));
    }


    private SysUser createNewUser(String phoneNumber) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(phoneNumber);
        sysUser.setPhoneNumber(phoneNumber);
        sysUser.setHoVaTen("Người dùng mới");
        sysUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        sysUser.setRoleId(6); // default ROLE_USER
        sysUser.setIsActive(true); // default true

        sysUserMapper.insert(sysUser);
        return sysUser;
    }

    private boolean verifyOtpWithOtpRedis(String phoneNumber, String otp, String otpToken) {

        String storedOtp = getOtp(phoneNumber);
        if (storedOtp == null) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }

        return storedOtp.equals(otp + otpToken);
    }

    // Redis flow
    private void saveOtp(String phoneNumber, String otp, String otpToken, long ttlSeconds) {
        redisTemplate.opsForValue().set(
                PREFIX + phoneNumber, otp + otpToken,
                Duration.ofSeconds(ttlSeconds)
        );
    }

    private void deleteOtp(String phoneNumber, String otpToken) {
        redisTemplate.delete(PREFIX + phoneNumber + otpToken);
    }

    private String getOtp(String phoneNumber) {
        return redisTemplate.opsForValue().get(PREFIX + phoneNumber);
    }
}