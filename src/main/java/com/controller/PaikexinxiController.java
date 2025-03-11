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

import com.entity.PaikexinxiEntity;
import com.entity.view.PaikexinxiView;

import com.service.PaikexinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 排课信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:05
 */
@RestController
@RequestMapping("/paikexinxi")
public class PaikexinxiController {
    @Autowired
    private PaikexinxiService paikexinxiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PaikexinxiEntity paikexinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("bianchengjiaoshi")) {
			paikexinxi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<PaikexinxiEntity> ew = new EntityWrapper<PaikexinxiEntity>();



		PageUtils page = paikexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paikexinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PaikexinxiEntity paikexinxi, 
		HttpServletRequest request){
        EntityWrapper<PaikexinxiEntity> ew = new EntityWrapper<PaikexinxiEntity>();

		PageUtils page = paikexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paikexinxi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PaikexinxiEntity paikexinxi){
       	EntityWrapper<PaikexinxiEntity> ew = new EntityWrapper<PaikexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( paikexinxi, "paikexinxi")); 
        return R.ok().put("data", paikexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PaikexinxiEntity paikexinxi){
        EntityWrapper< PaikexinxiEntity> ew = new EntityWrapper< PaikexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( paikexinxi, "paikexinxi")); 
		PaikexinxiView paikexinxiView =  paikexinxiService.selectView(ew);
		return R.ok("查询排课信息成功").put("data", paikexinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PaikexinxiEntity paikexinxi = paikexinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(paikexinxi,deSens);
        return R.ok().put("data", paikexinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PaikexinxiEntity paikexinxi = paikexinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(paikexinxi,deSens);
        return R.ok().put("data", paikexinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增排课信息") 
    public R save(@RequestBody PaikexinxiEntity paikexinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(paikexinxi);
        paikexinxiService.insert(paikexinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增排课信息")
    @RequestMapping("/add")
    public R add(@RequestBody PaikexinxiEntity paikexinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(paikexinxi);
        paikexinxiService.insert(paikexinxi);
        return R.ok().put("data",paikexinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改排课信息")
    public R update(@RequestBody PaikexinxiEntity paikexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paikexinxi);
        //全部更新
        paikexinxiService.updateById(paikexinxi);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除排课信息")
    public R delete(@RequestBody Long[] ids){
        paikexinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
