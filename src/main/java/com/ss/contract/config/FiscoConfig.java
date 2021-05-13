package com.ss.contract.config;

import lombok.Data;
import lombok.ToString;
import org.fisco.bcos.sdk.config.model.AmopTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: xush
 * @Date: 2021-5-12
 * @Version: v1.0
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "fisco")
public class FiscoConfig {

    private Map<String, Object> cryptoMaterial;
    public Map<String, List<String>> network;
    public List<AmopTopic> amop;
    public Map<String, Object> account;
    public Map<String, Object> threadPool;
}