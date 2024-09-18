package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("hello")
    // 주소 url을 지정
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; //  hello.html로 간다
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //  http에 바로 응답을 돌림 없다면? 맞는 html을 찾아서 돌림
    // 응답 바디 부분에 내가 직접 넣어주겠다
    // html 태그 사용 x
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    //  객체 생성 후 responseBody를 한다면 json 방식이 디폴트임
    public Hello helloapi(@RequestParam("name") String name) {
        Hello hello = new Hello();  //  hello 객체 생성
        hello.setName(name);
        return hello;
    }

    static class Hello {
        String name;

        //  프로퍼티 접근방식(자바 빈 규약)
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
