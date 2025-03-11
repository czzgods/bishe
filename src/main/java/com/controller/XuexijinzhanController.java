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

import com.entity.XuexijinzhanEntity;
import com.entity.view.XuexijinzhanView;

import com.service.XuexijinzhanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 学习进展
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:05
 */
@RestController
@RequestMapping("/xuexijinzhan")
public class XuexijinzhanController {
    @Autowired
    private XuexijinzhanService xuexijinzhanService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuexijinzhanEntity xuexijinzhan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("bianchengjiaoshi")) {
			xuexijinzhan.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiazhang")) {
			xuexijinzhan.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			xuexijinzhan.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<XuexijinzhanEntity> ew = new EntityWrapper<XuexijinzhanEntity>();



		PageUtils page = xuexijinzhanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexijinzhan), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XuexijinzhanEntity xuexijinzhan, 
		HttpServletRequest request){
        EntityWrapper<XuexijinzhanEntity> ew = new EntityWrapper<XuexijinzhanEntity>();

		PageUtils page = xuexijinzhanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexijinzhan), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuexijinzhanEntity xuexijinzhan){
       	EntityWrapper<XuexijinzhanEntity> ew = new EntityWrapper<XuexijinzhanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuexijinzhan, "xuexijinzhan")); 
        return R.ok().put("data", xuexijinzhanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuexijinzhanEntity xuexijinzhan){
        EntityWrapper< XuexijinzhanEntity> ew = new EntityWrapper< XuexijinzhanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuexijinzhan, "xuexijinzhan")); 
		XuexijinzhanView xuexijinzhanView =  xuexijinzhanService.selectView(ew);
		return R.ok("查询学习进展成功").put("data", xuexijinzhanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuexijinzhanEntity xuexijinzhan = xuexijinzhanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexijinzhan,deSens);
        return R.ok().put("data", xuexijinzhan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuexijinzhanEntity xuexijinzhan = xuexijinzhanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexijinzhan,deSens);
        return R.ok().put("data", xuexijinzhan);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增学习进展") 
    public R save(@RequestBody XuexijinzhanEntity xuexijinzhan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexijinzhan);
        xuexijinzhanService.insert(xuexijinzhan);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增学习进展")
    @RequestMapping("/add")
    public R add(@RequestBody XuexijinzhanEntity xuexijinzhan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexijinzhan);
        xuexijinzhanService.insert(xuexijinzhan);
        return R.ok().put("data",xuexijinzhan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改学习进展")
    public R update(@RequestBody XuexijinzhanEntity xuexijinzhan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexijinzhan);
        //全部更新
        xuexijinzhanService.updateById(xuexijinzhan);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除学习进展")
    public R delete(@RequestBody Long[] ids){
        xuexijinzhanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
