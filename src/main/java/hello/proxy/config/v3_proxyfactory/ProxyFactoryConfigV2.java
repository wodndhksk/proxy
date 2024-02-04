package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {
    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(logTrace));
        ProxyFactory factory = new ProxyFactory(orderController);
        factory.addAdvice(getAdvisor(logTrace));

        OrderControllerV2 proxy = (OrderControllerV2) factory.getProxy();
        proxyFactoryLog(proxy.getClass(), orderController.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2(logTrace));
        ProxyFactory factory = new ProxyFactory(orderService);
        factory.addAdvice(getAdvisor(logTrace));

        OrderServiceV2 proxy = (OrderServiceV2) factory.getProxy();
        proxyFactoryLog(proxy.getClass(), orderService.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
        OrderRepositoryV2 orderRepository = new OrderRepositoryV2();
        ProxyFactory factory = new ProxyFactory(orderRepository);
        factory.addAdvice(getAdvisor(logTrace));

        OrderRepositoryV2 proxy = (OrderRepositoryV2) factory.getProxy();
        proxyFactoryLog(proxy.getClass(), orderRepository.getClass());
        return proxy;
    }

    /**
     * pointcut
     * @param logTrace
     * @return
     */
    private Advice getAdvisor(LogTrace logTrace) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        //advise
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        return advice;
    }

    /**
     * log 메소드
     * @param proxy
     * @param orderClass
     */
    private void proxyFactoryLog(Class<?> proxy, Class<?> orderClass) {
        log.info("proxyFactory proxy={} target={}", proxy, orderClass);
    }
}
