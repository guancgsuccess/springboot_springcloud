package com.tensquare.user.controller;
import java.util.HashMap;
import java.util.Map;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 注册用户
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除 - jwt+token - 必须拥有管理权限才能够进行删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id, HttpServletRequest request){
		//获取请求的头信息
//		String authHeader = request.getHeader("Authorization");
//		System.out.println(authHeader);
//
//		//判断是否存在请求头信息
//		if(null == authHeader){
//			return new Result(false,StatusCode.ACCESSERROR,"权限不足!");
//		}
//
//		//判断是否和前端约定好的前缀Bearer+空格打头
//		if(!authHeader.startsWith("Bearer ")){
//			return new Result(false,StatusCode.ACCESSERROR,"权限不足!");
//		}
//
//		//提取token
//		String token = authHeader.substring(7);
//		System.out.println("token:"+token);
//		//调用jwt解析token
//		Claims claims = jwtUtil.parseJWT(token);
//
//		if(null==claims){
//			return new Result(false,StatusCode.ACCESSERROR,"权限不足!");
//		}
//
//		if(!"admin".equals(claims.get("roles"))){
//			return new Result(false,StatusCode.ACCESSERROR,"权限不足!");
//		}

		Claims claims = (Claims) request.getAttribute("admin_claims");
		if(null == claims){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足!");
		}


		//判断是否拥有管理员权限
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	/**
	 * 发送验证码
	 * @param mobile 手机号
	 * @return
	 */
	@PostMapping("/sendsms/{mobile}")
	public Result sendSms(@PathVariable String mobile){
		//发送验证码
		userService.sendSms(mobile);
		return new Result(true,StatusCode.OK,"发送成功");
	}

	/**
	 * 注册用户 - 等到用户接受到验证码之后,才能够进行验证
	 * @param code
	 * @param user
	 * @return
	 */
	@PostMapping("/register/{code}")
	public Result register(@PathVariable String code,@RequestBody User user){
		//从缓存中得到验证码
		String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode_"+user.getMobile());

		//判断是否接受到验证码
		if(checkcodeRedis == null){
			return new Result(false,StatusCode.ERROR,"请先获取手机验证码!");
		}
		if(!checkcodeRedis.equals(code)){
			return new Result(false,StatusCode.ERROR,"请输入正确的验证码!");
		}

		userService.add(user);
		return new Result(true,StatusCode.OK,"注册成功!");
	}


	/**
	 *
	 * @param u
	 * @return
	 */
	@PostMapping("/login")
	public Result login(@RequestBody User u){
		User user = userService.login(u);
		if(null == user){
			return new Result(false,StatusCode.ERROR,"登录失败");
		}

		//使得前后端可以通话的操作,采用jwt来实现
		System.out.println("controller -> user:"+user.getId());
		String token = jwtUtil.createJWT(user.getId(),u.getNickname(),"user");
		Map<String,Object> map =new HashMap<>();
		map.put("token",token);
		map.put("role","user");
		map.put("avatar",user.getAvatar());
		//map.put("name",user.getAvatar());
		return new Result(true,StatusCode.OK,"登录成功",map);
	}

	@PutMapping("/{userid}/{friendid}/{x}")
	public void updatefanscountandfollowcount(@PathVariable String userid,@PathVariable String friendid,@PathVariable int x){
		userService.updatefanscountandfollowcount(x,userid,friendid);
	}
}
