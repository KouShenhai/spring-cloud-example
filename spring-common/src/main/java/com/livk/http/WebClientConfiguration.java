package com.livk.http;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.DefaultSslContextSpec;

import java.time.Duration;
import java.util.function.Function;

/**
 * <p>
 * WebClientConfig
 * </p>
 *
 * @author livk
 * @date 2022/5/9
 */
@AutoConfiguration
@ConditionalOnClass(WebClient.class)
public class WebClientConfiguration {

    @Bean
    public ReactorResourceFactory reactorResourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        return factory;
    }

    @Bean
    public WebClientCustomizer ReactorClientWebClientCustomizer(ReactorResourceFactory reactorResourceFactory) {
        Function<HttpClient, HttpClient> function = httpClient ->
                httpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3_000)
                        .responseTimeout(Duration.ofSeconds(15))
                        .secure(sslContextSpec -> sslContextSpec.sslContext(
                                DefaultSslContextSpec.forClient()
                                        .configure(builder -> builder.trustManager(InsecureTrustManagerFactory.INSTANCE))))
                        .doOnConnected(connection ->
                                connection.addHandlerLast(new ReadTimeoutHandler(20))
                                        .addHandlerLast(new WriteTimeoutHandler(20)));
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(reactorResourceFactory, function);
        return webClientBuilder -> webClientBuilder.clientConnector(connector);
    }

    @Bean
    @ConditionalOnBean(LoadBalancedExchangeFilterFunction.class)
    public WebClientCustomizer loadBalanceWebClientCustomizer(LoadBalancedExchangeFilterFunction filterFunction) {
        return webClientBuilder -> webClientBuilder.filter(filterFunction);
    }


    /**
     * spring官方建议使用{@link WebClient} <a href=
     * "https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#webmvc-client">Spring文档</a>
     *
     * @return WebClient
     */
    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.build();
    }
}
