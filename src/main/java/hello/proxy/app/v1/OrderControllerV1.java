package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.*;

/**
 * 스프링 부트 3.0부터는 `@Controller` , `@RestController` 를 사용했는데, 이렇게 하면 내부
 *  `@Component` 를 가지고 있어서 컴포넌트 스캔의 대상이 된다.
 */
@RestController
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
