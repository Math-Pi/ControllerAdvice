package com.cjm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyGlobalExceptionHandler {
	/**
	 * ȫ���쳣����
	 * @return
	 */
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ModelAndView customException(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "�����±����");
        mav.setViewName("myerror");
        return mav;
    }
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView customException(ArithmeticException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.setViewName("myerror");
        return mav;
    }
    /**
     * 	ȫ�����ݰ�
     *  @ModelAttribute ע���Ǹ÷����ķ���������һ��ȫ������
     */
    @ModelAttribute(name = "user")
    public Map<String,Object> mydata() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", "23");
        return map;
    }
    /**
     *	  ȫ������Ԥ����
     */
    @InitBinder("s")
    public void student(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("s.");
    }
    @InitBinder("t")
    public void teacher(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("t.");
    }
}
