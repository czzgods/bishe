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

import com.entity.BianchengjiaoshiEntity;
import com.entity.KechengxinxiEntity;
import com.service.BianchengjiaoshiService;
import com.service.KechengxinxiService;
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

import com.entity.KechengbaomingEntity;
import com.entity.view.KechengbaomingView;

import com.service.KechengbaomingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程报名
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
@RestController
@RequestMapping("/kechengbaoming")
public class KechengbaomingController {
    @Autowired
    private KechengbaomingService kechengbaomingService;


    @Autowired
    private BianchengjiaoshiService bianchengjiaoshiService;
    
    @Autowired
    private KechengxinxiService kechengxinxiService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengbaomingEntity kechengbaoming,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("bianchengjiaoshi")) {
			kechengbaoming.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiazhang")) {
			kechengbaoming.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			kechengbaoming.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();



		PageUtils page = kechengbaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengbaoming), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KechengbaomingEntity kechengbaoming, 
		HttpServletRequest request){
        EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();

		PageUtils page = kechengbaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengbaoming), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengbaomingEntity kechengbaoming){
       	EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengbaoming, "kechengbaoming")); 
        return R.ok().put("data", kechengbaomingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengbaomingEntity kechengbaoming){
        EntityWrapper< KechengbaomingEntity> ew = new EntityWrapper< KechengbaomingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengbaoming, "kechengbaoming")); 
		KechengbaomingView kechengbaomingView =  kechengbaomingService.selectView(ew);
		return R.ok("查询课程报名成功").put("data", kechengbaomingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengbaomingEntity kechengbaoming = kechengbaomingService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(kechengbaoming,deSens);
        return R.ok().put("data", kechengbaoming);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengbaomingEntity kechengbaoming = kechengbaomingService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(kechengbaoming,deSens);
        return R.ok().put("data", kechengbaoming);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增课程报名")
    @Transactional
    public R save(@RequestBody KechengbaomingEntity kechengbaoming, HttpServletRequest request){
        // 1.基础字段校验
        if(StringUtils.isBlank(kechengbaoming.getXueshengzhanghao())) {
            return R.error("学生账号不能为空");
        }
        if(StringUtils.isBlank(kechengbaoming.getKechengmingcheng())) {
            return R.error("课程名称不能为空");
        }
        if(StringUtils.isBlank(kechengbaoming.getJiaoshigonghao())) {
            return R.error("教师工号不能为空");
        }
        // 2.数值类型校验
        if(kechengbaoming.getKechengfeiyong() == null || kechengbaoming.getKechengfeiyong() < 0) {
            return R.error("课程费用不能为负数");
        }
        // 3.业务规则校验
        // 3.1 防止重复报名（根据学生+课程名称+教师工号）
        EntityWrapper<KechengbaomingEntity> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("xueshengzhanghao", kechengbaoming.getXueshengzhanghao())
                .eq("kechengmingcheng", kechengbaoming.getKechengmingcheng())
                .eq("jiaoshigonghao", kechengbaoming.getJiaoshigonghao());
        if(kechengbaomingService.selectCount(queryWrapper) > 0) {
            return R.error("该学生已报名此教师的同名课程");
        }
        // 4.设置系统字段
        kechengbaoming.setBaomingshijian(new Date()); // 自动设置报名时间
        kechengbaoming.setIspay("未支付");           // 默认支付状态
        // 5.关联数据校验（需要注入对应Service）
        // 验证教师有效性
        Wrapper<BianchengjiaoshiEntity> jiaoshigonghao = new EntityWrapper<BianchengjiaoshiEntity>()
                .eq("jiaoshigonghao", kechengbaoming.getJiaoshigonghao());
        if(bianchengjiaoshiService.selectOne(jiaoshigonghao) == null) {
         return R.error("教师信息不存在");
        }
        // 验证课程有效性
        EntityWrapper<KechengxinxiEntity> courseQuery = new EntityWrapper<>();
        courseQuery.eq("kechengmingcheng", kechengbaoming.getKechengmingcheng())
             .eq("jiaoshigonghao", kechengbaoming.getJiaoshigonghao());
        KechengxinxiEntity course = kechengxinxiService.selectOne(courseQuery);
        if(course == null) {
           return R.error("对应课程不存在");
        }
        // 6.保存数据
        if(!kechengbaomingService.insert(kechengbaoming)) {
            return R.error("报名信息保存失败");
        }
        return R.ok().put("data", kechengbaoming.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增课程报名")
    @RequestMapping("/add")
    public R add(@RequestBody KechengbaomingEntity kechengbaoming, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kechengbaoming);
        kechengbaomingService.insert(kechengbaoming);
        return R.ok().put("data",kechengbaoming.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改课程报名")
    public R update(@RequestBody KechengbaomingEntity kechengbaoming, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengbaoming);
        //全部更新
        kechengbaomingService.updateById(kechengbaoming);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除课程报名")
    public R delete(@RequestBody Long[] ids){
        kechengbaomingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








        /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get("value_kechengbaoming_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }else{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
                            if(tableName.equals("bianchengjiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
                            if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
        }
                    if(tableName.equals("xuesheng")) {
            ew.eq("xueshengzhanghao", (String)request.getSession().getAttribute("username"));
        }
                            List<Map<String, Object>> result = kechengbaomingService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            return Double.compare(total2.doubleValue(), total1.doubleValue());
        });
        return R.ok().put("data", result);
        }
    }
    
    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul,HttpServletRequest request)  throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get("value_kechengbaoming_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }else{
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();
String tableName = request.getSession().getAttribute("tableName").toString();
                            if(tableName.equals("bianchengjiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
                            if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
        }
                    if(tableName.equals("xuesheng")) {
            ew.eq("xueshengzhanghao", (String)request.getSession().getAttribute("username"));
        }
                        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = kechengbaomingService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
}
    
    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get("value_kechengbaoming_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }else{
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("xColumn", xColumnName);
            params.put("yColumn", yColumnName);
            params.put("timeStatType", timeStatType);
            EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();
    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("bianchengjiaoshi")) {
                ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
            }
                                                                    if(tableName.equals("jiazhang")) {
                ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
            }
                                            if(tableName.equals("xuesheng")) {
                ew.eq("xueshengzhanghao", (String)request.getSession().getAttribute("username"));
            }
                                                                    List<Map<String, Object>> result = kechengbaomingService.selectTimeStatValue(params, ew);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            return R.ok().put("data", result);
        }
    }
    
        /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) throws IOException
    {
        java.nio.file.Path path = java.nio.file.Paths.get("value_kechengbaoming_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }else{
            String[] yColumnNames = yColumnNameMul.split(",");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("xColumn", xColumnName);
            params.put("timeStatType", timeStatType);
            List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();
    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("bianchengjiaoshi")) {
                ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
            }
                                                                    if(tableName.equals("jiazhang")) {
                ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
            }
                                            if(tableName.equals("xuesheng")) {
                ew.eq("xueshengzhanghao", (String)request.getSession().getAttribute("username"));
            }
                                                            for(int i=0;i<yColumnNames.length;i++) {
                params.put("yColumn", yColumnNames[i]);
                List<Map<String, Object>> result = kechengbaomingService.selectTimeStatValue(params, ew);
                for(Map<String, Object> m : result) {
                    for(String k : m.keySet()) {
                        if(m.get(k) instanceof Date) {
                            m.put(k, sdf.format((Date)m.get(k)));
                        }
                    }
                }
                result2.add(result);
            }
            return R.ok().put("data", result2);
        }
    }
    
        /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get("group_kechengbaoming_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }else{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();
String tableName = request.getSession().getAttribute("tableName").toString();
                            if(tableName.equals("bianchengjiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
                            if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
        }
                    if(tableName.equals("xuesheng")) {
            ew.eq("xueshengzhanghao", (String)request.getSession().getAttribute("username"));
        }
                            List<Map<String, Object>> result = kechengbaomingService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
        }
    }    
    
    




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,KechengbaomingEntity kechengbaoming, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("bianchengjiaoshi")) {
            kechengbaoming.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("jiazhang")) {
            kechengbaoming.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("xuesheng")) {
            kechengbaoming.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<KechengbaomingEntity> ew = new EntityWrapper<KechengbaomingEntity>();
        int count = kechengbaomingService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengbaoming), params), params));
        return R.ok().put("data", count);
    }



}
