package com.search.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 *
 *@author : mawei
 *@since : 2018/2/26
 *@description : TODO
 */
@Aspect
@Order(2000)
@Component
public class WebAspect {

    @Autowired
    private Tracer tracer;

    private static final Logger log = LoggerFactory.getLogger(WebAspect.class);

    @Pointcut(" @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCut(){};

    @After("pointCut()")
    public void doAfter()  {


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("TraceID",String.valueOf(Span.idToHex(tracer.getCurrentSpan().getTraceId())));
        response.setHeader("SpanID",String.valueOf(Span.idToHex(tracer.getCurrentSpan().getSpanId())));

    }

    @AfterThrowing("pointCut()")
    public void doAfterThrowing()  {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("TraceID",String.valueOf(Span.idToHex(tracer.getCurrentSpan().getTraceId())));
        response.setHeader("SpanID",String.valueOf(Span.idToHex(tracer.getCurrentSpan().getSpanId())));

    }





}
