package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.*;

@RequestMapping// 스프링은 @Controller 또는 @RequestMapping 이 있어야 Controller 로 인식한다
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
