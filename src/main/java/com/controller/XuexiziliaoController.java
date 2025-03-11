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
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.XuexiziliaoEntity;
import com.entity.view.XuexiziliaoView;

import com.service.XuexiziliaoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 学习资料
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
@RestController
@RequestMapping("/xuexiziliao")
public class XuexiziliaoController {
    @Autowired
    private XuexiziliaoService xuexiziliaoService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuexiziliaoEntity xuexiziliao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("bianchengjiaoshi")) {
			xuexiziliao.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<XuexiziliaoEntity> ew = new EntityWrapper<XuexiziliaoEntity>();



		PageUtils page = xuexiziliaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiziliao), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XuexiziliaoEntity xuexiziliao, 
		HttpServletRequest request){
        EntityWrapper<XuexiziliaoEntity> ew = new EntityWrapper<XuexiziliaoEntity>();

		PageUtils page = xuexiziliaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiziliao), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuexiziliaoEntity xuexiziliao){
       	EntityWrapper<XuexiziliaoEntity> ew = new EntityWrapper<XuexiziliaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuexiziliao, "xuexiziliao")); 
        return R.ok().put("data", xuexiziliaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuexiziliaoEntity xuexiziliao){
        EntityWrapper< XuexiziliaoEntity> ew = new EntityWrapper< XuexiziliaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuexiziliao, "xuexiziliao")); 
		XuexiziliaoView xuexiziliaoView =  xuexiziliaoService.selectView(ew);
		return R.ok("查询学习资料成功").put("data", xuexiziliaoView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuexiziliaoEntity xuexiziliao = xuexiziliaoService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexiziliao,deSens);
        return R.ok().put("data", xuexiziliao);
    }

    /**
     * 前台详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuexiziliaoEntity xuexiziliao = xuexiziliaoService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(xuexiziliao,deSens);
        return R.ok().put("data", xuexiziliao);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        XuexiziliaoEntity xuexiziliao = xuexiziliaoService.selectById(id);
        if(type.equals("1")) {
        	xuexiziliao.setThumbsupnum(xuexiziliao.getThumbsupnum()+1);
        } else {
        	xuexiziliao.setCrazilynum(xuexiziliao.getCrazilynum()+1);
        }
        xuexiziliaoService.updateById(xuexiziliao);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增学习资料") 
    public R save(@RequestBody XuexiziliaoEntity xuexiziliao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexiziliao);
        xuexiziliaoService.insert(xuexiziliao);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增学习资料")
    @RequestMapping("/add")
    public R add(@RequestBody XuexiziliaoEntity xuexiziliao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuexiziliao);
        xuexiziliaoService.insert(xuexiziliao);
        return R.ok().put("data",xuexiziliao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改学习资料")
    public R update(@RequestBody XuexiziliaoEntity xuexiziliao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexiziliao);
        //全部更新
        xuexiziliaoService.updateById(xuexiziliao);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除学习资料")
    public R delete(@RequestBody Long[] ids){
        xuexiziliaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,XuexiziliaoEntity xuexiziliao, HttpServletRequest request,String pre){
        EntityWrapper<XuexiziliaoEntity> ew = new EntityWrapper<XuexiziliaoEntity>();
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
		PageUtils page = xuexiziliaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexiziliao), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户收藏的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,XuexiziliaoEntity xuexiziliao, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "xuexiziliao"));
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

        EntityWrapper<XuexiziliaoEntity> ew = new EntityWrapper<XuexiziliaoEntity>();
        ew.in("id", String.join(",", recommendations));
        if(recommendations!=null && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+"'"+String.join("','", recommendations)+"'"+")");
        }

        PageUtils page = xuexiziliaoService.queryPage(params, ew);
        List<XuexiziliaoEntity> pageList = (List<XuexiziliaoEntity>)page.getList();
        if(pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<XuexiziliaoEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(xuexiziliaoService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }









}
