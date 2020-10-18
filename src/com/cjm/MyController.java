package com.cjm;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MyController {
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	int[] arr=new int[] {1,2,3};
	@RequestMapping("/test")
	public void test() {
		System.out.println(arr[3]);
	}
	@GetMapping("/user")
    public String user(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        int i = 1 / 0;
        return "数据绑定";
    }
	@GetMapping("/info")
	public String addBook(@ModelAttribute("s") Student student, @ModelAttribute("t") Teacher teacher) {
	    System.out.println(student);
	    System.out.println(teacher);
	    return "数据预处理";
	}
}
