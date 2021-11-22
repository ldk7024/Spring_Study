package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-sting")
    @ResponseBody // body부에 직접 데이터를 넣어주겠다는 어노테이션
    public String hellostString(@RequestParam("name") String name){
        return "hello " + name; // "hello "name" "
    }

    @GetMapping ("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;
        // 자바 빈 규약 (자바 빈 표준 방식)
        // 프로퍼티 접근 방식
        // alt + insert로 게터 세터 생성가능
       public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }


}
