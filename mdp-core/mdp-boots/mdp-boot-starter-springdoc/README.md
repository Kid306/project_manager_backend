## mdp对swagger的改造说明
1.swagger对于map\json的类型参数不友好；  
2.对于大量实体类参数，无法重用实体类的描述，无法进行实体类内部属性进行裁剪  
mdp对上述问题提供了解决方案  

### swagger的问题及mdp的解决办法
1 对map、JSON类型的参数无法进行有效描述，无法进行模拟请求,应答等  
解决办法：  
对于有现成的实体类，但是接口用了map、json的，可以使用[@ApiEntityParams](src/main/java/com/mdp/swagger/ApiEntityParams.java)注解进行对参数的描述  
对于没有现成实体类，但是接口用了map、json的，可以使用swagger 原生注解@ApiImplicitParam进行描述  
2 对于实体类型的参数，无法进行裁剪，导致全体属性暴露给外部  
解决办法：  
可以使用[@ApiEntityParams](src/main/java/com/mdp/swagger/ApiEntityParams.java)注解进行对参数的描述  

### 代码举例
```java
	@Operation( summary =  "查询互联网开放的任务的信息详情，免登录" )
	@ApiEntityParams(value = XmTask.class,props = {"id"}) 
	 
	@RequestMapping(value="/shareTaskDetail",method=RequestMethod.GET)
	public Result shareTaskDetail(  @RequestParam Map<String,Object> params){ 
		return Result.ok();
	}
```

### 参考文章
 参考  [springdoc与springboot集成](https://www.cnblogs.com/vic-tory/p/17690501.html)