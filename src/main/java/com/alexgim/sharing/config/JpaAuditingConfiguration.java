package com.alexgim.sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // 따로 설정 파일을 만든 후 JpaAuditing 활성화를 해야 테스트시 오류가 발생하지 않음
public class JpaAuditingConfiguration {
}
