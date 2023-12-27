package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {
    private Subject target;
//    private String cacheValue;
   private ThreadLocal<String> cacheValue = new ThreadLocal<>();
    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if(cacheValue.get() == null){
            cacheValue.set(target.operation());
        }
        return cacheValue.get();
    }
}
