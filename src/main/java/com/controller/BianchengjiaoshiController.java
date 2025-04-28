package com.controller;


import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.TokenEntity;
import com.utils.DeSensUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.BianchengjiaoshiEntity;
import com.entity.view.BianchengjiaoshiView;

import com.service.BianchengjiaoshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 编程教师
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
@RestController
@RequestMapping("/bianchengjiaoshi")
public class BianchengjiaoshiController {
    @Autowired
    private BianchengjiaoshiService bianchengjiaoshiService;

    @Autowired
    private StoreupService storeupService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
        Wrapper<BianchengjiaoshiEntity> jiaoshigonghao = new EntityWrapper<BianchengjiaoshiEntity>().eq("jiaoshigonghao", username);
        BianchengjiaoshiEntity u = bianchengjiaoshiService.selectOne(jiaoshigonghao );
        String md5Hex = DigestUtils.md5Hex(password);
        if(u==null || !u.getMima().equals(md5Hex )) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(u.getId(), username,"bianchengjiaoshi",  "编程教师" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody BianchengjiaoshiEntity bianchengjiaoshi){
    	//ValidatorUtils.validateEntity(bianchengjiaoshi);
    	BianchengjiaoshiEntity u = bianchengjiaoshiService.selectOne(new EntityWrapper<BianchengjiaoshiEntity>().eq("jiaoshigonghao", bianchengjiaoshi.getJiaoshigonghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		bianchengjiaoshi.setId(uId);
        // 密码加密
        String md5Password = DigestUtils.md5Hex(bianchengjiaoshi.getMima());

        bianchengjiaoshi.setMima(md5Password);

        bianchengjiaoshiService.insert(bianchengjiaoshi);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        BianchengjiaoshiEntity u = bianchengjiaoshiService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	BianchengjiaoshiEntity u = bianchengjiaoshiService.selectOne(new EntityWrapper<BianchengjiaoshiEntity>().eq("jiaoshigonghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima(DigestUtils.md5Hex("123456"));
        bianchengjiaoshiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BianchengjiaoshiEntity bianchengjiaoshi,
		HttpServletRequest request){
        EntityWrapper<BianchengjiaoshiEntity> ew = new EntityWrapper<BianchengjiaoshiEntity>();



		PageUtils page = bianchengjiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bianchengjiaoshi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BianchengjiaoshiEntity bianchengjiaoshi, 
		HttpServletRequest request){
        EntityWrapper<BianchengjiaoshiEntity> ew = new EntityWrapper<BianchengjiaoshiEntity>();

		PageUtils page = bianchengjiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bianchengjiaoshi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BianchengjiaoshiEntity bianchengjiaoshi){
       	EntityWrapper<BianchengjiaoshiEntity> ew = new EntityWrapper<BianchengjiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bianchengjiaoshi, "bianchengjiaoshi")); 
        return R.ok().put("data", bianchengjiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BianchengjiaoshiEntity bianchengjiaoshi){
        EntityWrapper< BianchengjiaoshiEntity> ew = new EntityWrapper< BianchengjiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bianchengjiaoshi, "bianchengjiaoshi")); 
		BianchengjiaoshiView bianchengjiaoshiView =  bianchengjiaoshiService.selectView(ew);
		return R.ok("查询编程教师成功").put("data", bianchengjiaoshiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BianchengjiaoshiEntity bianchengjiaoshi = bianchengjiaoshiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(bianchengjiaoshi,deSens);
        return R.ok().put("data", bianchengjiaoshi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BianchengjiaoshiEntity bianchengjiaoshi = bianchengjiaoshiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(bianchengjiaoshi,deSens);
        return R.ok().put("data", bianchengjiaoshi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        BianchengjiaoshiEntity bianchengjiaoshi = bianchengjiaoshiService.selectById(id);
        if(type.equals("1")) {
        	bianchengjiaoshi.setThumbsupnum(bianchengjiaoshi.getThumbsupnum()+1);
        } else {
        	bianchengjiaoshi.setCrazilynum(bianchengjiaoshi.getCrazilynum()+1);
        }
        bianchengjiaoshiService.updateById(bianchengjiaoshi);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增编程教师") 
    public R save(@RequestBody BianchengjiaoshiEntity bianchengjiaoshi, HttpServletRequest request){
        if(bianchengjiaoshiService.selectCount(new EntityWrapper<BianchengjiaoshiEntity>().eq("jiaoshigonghao", bianchengjiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
    	bianchengjiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bianchengjiaoshi);
    	BianchengjiaoshiEntity u = bianchengjiaoshiService.selectOne(new EntityWrapper<BianchengjiaoshiEntity>().eq("jiaoshigonghao", bianchengjiaoshi.getJiaoshigonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		bianchengjiaoshi.setId(new Date().getTime());
        bianchengjiaoshiService.insert(bianchengjiaoshi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增编程教师")
    @RequestMapping("/add")
    public R add(@RequestBody BianchengjiaoshiEntity bianchengjiaoshi, HttpServletRequest request){
        if(bianchengjiaoshiService.selectCount(new EntityWrapper<BianchengjiaoshiEntity>().eq("jiaoshigonghao", bianchengjiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
    	bianchengjiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bianchengjiaoshi);
    	BianchengjiaoshiEntity u = bianchengjiaoshiService.selectOne(new EntityWrapper<BianchengjiaoshiEntity>().eq("jiaoshigonghao", bianchengjiaoshi.getJiaoshigonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		bianchengjiaoshi.setId(new Date().getTime());
        bianchengjiaoshiService.insert(bianchengjiaoshi);
        return R.ok().put("data",bianchengjiaoshi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改编程教师")
    public R update(@RequestBody BianchengjiaoshiEntity bianchengjiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bianchengjiaoshi);
        if(bianchengjiaoshiService.selectCount(new EntityWrapper<BianchengjiaoshiEntity>().ne("id", bianchengjiaoshi.getId()).eq("jiaoshigonghao", bianchengjiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
        // 密码加密
        String md5Password = DigestUtils.md5Hex(bianchengjiaoshi.getMima());
        //全部更新
        bianchengjiaoshi.setMima(md5Password);
        bianchengjiaoshiService.updateById(bianchengjiaoshi);
    if(null!=bianchengjiaoshi.getJiaoshigonghao())
    {
        // 修改token
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUsername(bianchengjiaoshi.getJiaoshigonghao());
        tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", bianchengjiaoshi.getId()));
    }


        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除编程教师")
    public R delete(@RequestBody Long[] ids){
        bianchengjiaoshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,BianchengjiaoshiEntity bianchengjiaoshi, HttpServletRequest request,String pre){
        EntityWrapper<BianchengjiaoshiEntity> ew = new EntityWrapper<BianchengjiaoshiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = bianchengjiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bianchengjiaoshi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户收藏的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,BianchengjiaoshiEntity bianchengjiaoshi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "bianchengjiaoshi"));
        Map<String, Map<String, Double>> ratings = new HashMap<>();
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity storeup : storeups) {
                Map<String, Double> userRatings = null;
                if(ratings.containsKey(storeup.getUserid().toString())) {
                    userRatings = ratings.get(storeup.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(storeup.getUserid().toString(), userRatings);
                }

                if(userRatings.containsKey(storeup.getRefid().toString())) {
                    userRatings.put(storeup.getRefid().toString(), userRatings.get(storeup.getRefid().toString())+1.0);
                } else {
                    userRatings.put(storeup.getRefid().toString(), 1.0);
                }
            }
        }
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(targetUser, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<BianchengjiaoshiEntity> ew = new EntityWrapper<BianchengjiaoshiEntity>();
        ew.in("id", String.join(",", recommendations));
        if(recommendations!=null && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+"'"+String.join("','", recommendations)+"'"+")");
        }

        PageUtils page = bianchengjiaoshiService.queryPage(params, ew);
        List<BianchengjiaoshiEntity> pageList = (List<BianchengjiaoshiEntity>)page.getList();
        if(pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<BianchengjiaoshiEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(bianchengjiaoshiService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }









}
