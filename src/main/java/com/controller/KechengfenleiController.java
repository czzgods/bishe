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

import com.entity.KechengfenleiEntity;
import com.entity.view.KechengfenleiView;

import com.service.KechengfenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程分类
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
@RestController
@RequestMapping("/kechengfenlei")
public class KechengfenleiController {
    @Autowired
    private KechengfenleiService kechengfenleiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengfenleiEntity kechengfenlei,
		HttpServletRequest request){
        EntityWrapper<KechengfenleiEntity> ew = new EntityWrapper<KechengfenleiEntity>();



		PageUtils page = kechengfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengfenlei), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KechengfenleiEntity kechengfenlei, 
		HttpServletRequest request){
        EntityWrapper<KechengfenleiEntity> ew = new EntityWrapper<KechengfenleiEntity>();

		PageUtils page = kechengfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengfenlei), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengfenleiEntity kechengfenlei){
       	EntityWrapper<KechengfenleiEntity> ew = new EntityWrapper<KechengfenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengfenlei, "kechengfenlei")); 
        return R.ok().put("data", kechengfenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengfenleiEntity kechengfenlei){
        EntityWrapper< KechengfenleiEntity> ew = new EntityWrapper< KechengfenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengfenlei, "kechengfenlei")); 
		KechengfenleiView kechengfenleiView =  kechengfenleiService.selectView(ew);
		return R.ok("查询课程分类成功").put("data", kechengfenleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengfenleiEntity kechengfenlei = kechengfenleiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(kechengfenlei,deSens);
        return R.ok().put("data", kechengfenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengfenleiEntity kechengfenlei = kechengfenleiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(kechengfenlei,deSens);
        return R.ok().put("data", kechengfenlei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增课程分类") 
    public R save(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kechengfenlei);
        kechengfenleiService.insert(kechengfenlei);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增课程分类")
    @RequestMapping("/add")
    public R add(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kechengfenlei);
        kechengfenleiService.insert(kechengfenlei);
        return R.ok().put("data",kechengfenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改课程分类")
    public R update(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengfenlei);
        //全部更新
        kechengfenleiService.updateById(kechengfenlei);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除课程分类")
    public R delete(@RequestBody Long[] ids){
        kechengfenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
