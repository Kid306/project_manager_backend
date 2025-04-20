package com.mdp.err;

import com.mdp.core.err.BizException;
import com.mdp.core.err.QxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.mdp.core.entity.Result.error;

  @ControllerAdvice
  @ResponseBody
  public class WebExceptionHandle {
      private static Logger logger = LoggerFactory.getLogger(WebExceptionHandle.class);
      /**
       *  - Bad Request
       */
      @ResponseStatus(HttpStatus.BAD_REQUEST)
      @ExceptionHandler(HttpMessageNotReadableException.class)
     public Map<String,Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
         logger.error("参数解析失败", e);
         
         return error("could_not_read_json","参数解析失败");
     }
     
     /**
      *  - Method Not Allowed
      */
     @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
     @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
     public Map<String,Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
         logger.error("不支持当前请求方法", e);
         return error("request_method_not_supported","不支持当前请求方法");
     }
 
     /**
      *  - Unsupported Media Type
      */
     @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
     @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
     public Map<String,Object> handleHttpMediaTypeNotSupportedException(Exception e) {
         logger.error("不支持当前媒体类型", e);
         return error("content_type_not_supported","不支持当前媒体类型");
     }
 
     /**
      *  - 业务反馈
      */
     @ResponseStatus(HttpStatus.OK)
     @ExceptionHandler(BizException.class)
     public Map<String,Object> handleException(BizException e) {
    	 logger.error("业务检查不通过", e);
         return error(e);
     }
     
     /**
      *  - 业务反馈
      */
     @ResponseStatus(HttpStatus.OK)
     @ExceptionHandler(QxException.class)
     public Map<String,Object> handleException(QxException e) { 
    	 logger.error("权限检查不通过", e);
         return error(e);
     }

      @ExceptionHandler
      public Map<String,Object> handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
          logger.error("Http请求发生异常...",e);
          return error("err0","操作不成功。具体原因:%s",e.getMessage());
      }
 } 