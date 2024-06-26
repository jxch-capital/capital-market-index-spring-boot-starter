package io.github.jxch.market.index.config;

import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Data
@Configuration
@ComponentScan("io.github.jxch.market.index")
public class MarketIndexAutoConfig {
    public final static String OK_HTTP_CLIENT = "OK_HTTP_CLIENT";
    public final static String MARKET_INDEX_API = "MARKET_INDEX_API";
    public final static String MARKET_INDEX_CONVERSION_SERVICE = "MARKET_INDEX_CONVERSION_SERVICE";
    @Value("${capital.proxy.enable:false}")
    private boolean useProxy;
    @Value("${capital.proxy.host:localhost}")
    private String proxyHost;
    @Value("${capital.proxy.port:10809}")
    private Integer proxyPort;
    @Value("${capital.proxy.type:HTTP}")
    private String proxyType;

    @Bean(OK_HTTP_CLIENT)
    @ConditionalOnMissingBean(name = OK_HTTP_CLIENT)
    public OkHttpClient stock4jOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (useProxy) {
            builder.proxy(new Proxy(Proxy.Type.valueOf(proxyType), new InetSocketAddress(proxyHost, proxyPort)));
            LogFactory.get().log(Level.INFO, "开启 {} 代理 {}:{}", proxyType, proxyHost, proxyPort);
        }

        return builder.build();
    }

    @Bean(MARKET_INDEX_CONVERSION_SERVICE)
    public ConversionService conversionService(ApplicationContext context) {
        DefaultConversionService service = new DefaultConversionService();
        context.getBeansOfType(Converter.class).values().forEach(service::addConverter);
        return service;
    }

    @Bean
    @ConditionalOnMissingBean(PropertySourcesPlaceholderConfigurer.class)
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
