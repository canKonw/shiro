package com.hh.Exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hh on 15-10-28.
 */
public class MyExceptionHandler implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        System.out.println("---------------i am resolveException");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", e);

        // 根据不同错误转向不同页面
      /*  if(ex instanceof 自己定义的异常类)*/
         return new ModelAndView("error", model);
    }

}
