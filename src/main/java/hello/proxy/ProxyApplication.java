package hello.proxy;

import hello.proxy.config.AppV1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 스프링 부트 3.0부터는 `@Controller` , `@RestController` 를 사용했는데, 이렇게 하면 내부
 *  `@Component` 를 가지고 있어서 컴포넌트 스캔의 대상이 된다.
 *  따라서 스캔 대상의 범위를 "hello.proxy.app.v3" 과 같은 방식으로 변경해야한다.
 */
@Import(AppV1Config.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
