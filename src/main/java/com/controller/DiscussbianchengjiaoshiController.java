package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.DiscussbianchengjiaoshiEntity;
import com.entity.view.DiscussbianchengjiaoshiView;

import com.service.DiscussbianchengjiaoshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 编程教师评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:06
 */
@RestController
@RequestMapping("/discussbianchengjiaoshi")
public class DiscussbianchengjiaoshiController {
    @Autowired
    private DiscussbianchengjiaoshiService discussbianchengjiaoshiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussbianchengjiaoshiEntity discussbianchengjiaoshi,
		HttpServletRequest request){
        EntityWrapper<DiscussbianchengjiaoshiEntity> ew = new EntityWrapper<DiscussbianchengjiaoshiEntity>();



		PageUtils page = discussbianchengjiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussbianchengjiaoshi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussbianchengjiaoshiEntity discussbianchengjiaoshi, 
		HttpServletRequest request){
        EntityWrapper<DiscussbianchengjiaoshiEntity> ew = new EntityWrapper<DiscussbianchengjiaoshiEntity>();

		PageUtils page = discussbianchengjiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussbianchengjiaoshi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussbianchengjiaoshiEntity discussbianchengjiaoshi){
       	EntityWrapper<DiscussbianchengjiaoshiEntity> ew = new EntityWrapper<DiscussbianchengjiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussbianchengjiaoshi, "discussbianchengjiaoshi")); 
        return R.ok().put("data", discussbianchengjiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussbianchengjiaoshiEntity discussbianchengjiaoshi){
        EntityWrapper< DiscussbianchengjiaoshiEntity> ew = new EntityWrapper< DiscussbianchengjiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussbianchengjiaoshi, "discussbianchengjiaoshi")); 
		DiscussbianchengjiaoshiView discussbianchengjiaoshiView =  discussbianchengjiaoshiService.selectView(ew);
		return R.ok("查询编程教师评论表成功").put("data", discussbianchengjiaoshiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussbianchengjiaoshiEntity discussbianchengjiaoshi = discussbianchengjiaoshiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discussbianchengjiaoshi,deSens);
        return R.ok().put("data", discussbianchengjiaoshi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussbianchengjiaoshiEntity discussbianchengjiaoshi = discussbianchengjiaoshiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discussbianchengjiaoshi,deSens);
        return R.ok().put("data", discussbianchengjiaoshi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增编程教师评论表") 
    public R save(@RequestBody DiscussbianchengjiaoshiEntity discussbianchengjiaoshi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussbianchengjiaoshi);
        discussbianchengjiaoshiService.insert(discussbianchengjiaoshi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增编程教师评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussbianchengjiaoshiEntity discussbianchengjiaoshi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussbianchengjiaoshi);
        discussbianchengjiaoshiService.insert(discussbianchengjiaoshi);
        return R.ok().put("data",discussbianchengjiaoshi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussbianchengjiaoshiEntity discussbianchengjiaoshi = discussbianchengjiaoshiService.selectOne(new EntityWrapper<DiscussbianchengjiaoshiEntity>().eq("", username));
        return R.ok().put("data", discussbianchengjiaoshi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussbianchengjiaoshiEntity discussbianchengjiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussbianchengjiaoshi);
        //全部更新
        discussbianchengjiaoshiService.updateById(discussbianchengjiaoshi);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除编程教师评论表")
    public R delete(@RequestBody Long[] ids){
        discussbianchengjiaoshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussbianchengjiaoshiEntity discussbianchengjiaoshi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussbianchengjiaoshiEntity> ew = new EntityWrapper<DiscussbianchengjiaoshiEntity>();
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
		PageUtils page = discussbianchengjiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussbianchengjiaoshi), params), params));
        return R.ok().put("data", page);
    }











}
