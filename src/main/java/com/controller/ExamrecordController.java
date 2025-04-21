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

import com.entity.ExamquestionEntity;
import com.service.ExamquestionService;
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

import com.entity.ExamrecordEntity;
import com.entity.view.ExamrecordView;

import com.service.ExamrecordService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 测试记录表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-20 17:38:05
 */
@RestController
@RequestMapping("/examrecord")
public class ExamrecordController {
    @Autowired
    private ExamrecordService examrecordService;


    @Autowired
    private ExamquestionService questionService;



    /**
     * 考试记录接口
     */
    @RequestMapping("/groupby")
    public R page2(@RequestParam Map<String, Object> params,ExamrecordEntity examrecord, HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            examrecord.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        EntityWrapper<ExamrecordEntity> ew = new EntityWrapper<ExamrecordEntity>();
		PageUtils page = examrecordService.queryPageGroupBy(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, examrecord), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 选项统计接口
     */
    @RequestMapping("/options/num")
    public R optionsNum(@RequestParam Map<String, Object> params,ExamrecordEntity examrecord, HttpServletRequest request){
        EntityWrapper<ExamrecordEntity> ew = new EntityWrapper<ExamrecordEntity>();
        PageUtils page = examrecordService.queryPageOptionsNum(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, examrecord), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ExamrecordEntity examrecord,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            examrecord.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        EntityWrapper<ExamrecordEntity> ew = new EntityWrapper<ExamrecordEntity>();



		PageUtils page = examrecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, examrecord), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ExamrecordEntity examrecord,
		HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		examrecord.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
        EntityWrapper<ExamrecordEntity> ew = new EntityWrapper<ExamrecordEntity>();

		PageUtils page = examrecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, examrecord), params), params));

				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ExamrecordEntity examrecord){
       	EntityWrapper<ExamrecordEntity> ew = new EntityWrapper<ExamrecordEntity>();
      	ew.allEq(MPUtil.allEQMapPre( examrecord, "examrecord"));
        return R.ok().put("data", examrecordService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ExamrecordEntity examrecord){
        EntityWrapper< ExamrecordEntity> ew = new EntityWrapper< ExamrecordEntity>();
 		ew.allEq(MPUtil.allEQMapPre( examrecord, "examrecord"));
		ExamrecordView examrecordView =  examrecordService.selectView(ew);
		return R.ok("查询测试记录表成功").put("data", examrecordView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ExamrecordEntity examrecord = examrecordService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(examrecord,deSens);
        return R.ok().put("data", examrecord);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ExamrecordEntity examrecord = examrecordService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(examrecord,deSens);
        return R.ok().put("data", examrecord);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增测试记录表")
    public R save(@RequestBody ExamrecordEntity examrecord, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(examrecord);
    	examrecord.setUserid((Long)request.getSession().getAttribute("userId"));
        examrecordService.insert(examrecord);
        return R.ok();
    }

    /**
     * 前台保存
     * 做题后提交（支持单题/整卷提交）
     */
    @SysLog("新增测试记录表")
    @Transactional
    @RequestMapping("/add")
    public R add(@RequestBody ExamrecordEntity examrecord, HttpServletRequest request) {
        // 1.基础校验
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return R.error(401, "用户未登录");
        }
        if (examrecord.getPaperid() == null) {
            return R.error("试卷ID不能为空");
        }
        if (examrecord.getQuestionid() == null) {
            return R.error("试题ID不能为空");
        }
        if (StringUtils.isBlank(examrecord.getMyanswer())) {
            return R.error("考生答案不能为空");
        }
        // 2.设置系统字段
        examrecord.setUserid(userId);
        examrecord.setAddtime(new Date());
        // 3.关联数据校验（需注入对应Service）
        Wrapper<ExamquestionEntity> entityWrapper = new EntityWrapper<>();;
        entityWrapper.eq("id", examrecord.getQuestionid());
        if(questionService.selectOne(entityWrapper) == null) {
            return R.error("试题不存在或已下架");
        }
        try {
            if (!examrecordService.insert(examrecord)) {
                return R.error("提交失败，请稍后重试");
            }
        } catch (Exception e) {
            return R.error("系统处理异常：" + e.getMessage());
        }
        // 4.自动批改逻辑（根据试题类型）
        if(examrecord.getType() != null) {
            switch(examrecord.getType().intValue()) {
                case 0: // 单选题
                case 1: // 多选题
                case 2: // 判断题
                case 3: // 填空题
                    autoCheckObjective(examrecord);
                    break;
                case 4: // 主观题
                    examrecord.setIsmark(0L); // 需要人工批改
                    break;
                default:
                    return R.error("无效的试题类型");
            }
        }
        return R.ok()
                .put("data", examrecord.getId())
                .put("myscore", examrecord.getMyscore())
                .put("ismark", examrecord.getIsmark());
    }
    /**
     * 自动批改客观题
     */
    private void autoCheckObjective(ExamrecordEntity record) {
        // 对比正确答案（需根据实际业务完善）
        boolean isCorrect = record.getAnswer() != null &&
                record.getAnswer().equalsIgnoreCase(record.getMyanswer());
        record.setMyscore(isCorrect ? record.getScore() : 0L);
        record.setIsmark(1L); // 标记已批改
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        ExamrecordEntity examrecord = examrecordService.selectOne(new EntityWrapper<ExamrecordEntity>().eq("", username));
        return R.ok().put("data", examrecord);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody ExamrecordEntity examrecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(examrecord);
        //全部更新
        examrecordService.updateById(examrecord);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除测试记录表")
    public R delete(@RequestBody Long[] ids){
        examrecordService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ExamrecordEntity examrecord, HttpServletRequest request,String pre){
        EntityWrapper<ExamrecordEntity> ew = new EntityWrapper<ExamrecordEntity>();
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
		PageUtils page = examrecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, examrecord), params), params));
        return R.ok().put("data", page);
    }



    /**
     * 当重新考试时，删除考生的某个试卷的所有考试记录
     */
    @RequestMapping("/deleteRecords")
    public R deleteRecords(@RequestParam Long userid,@RequestParam Long paperid){
    	examrecordService.delete(new EntityWrapper<ExamrecordEntity>().eq("paperid", paperid).eq("userid", userid));
        return R.ok();
    }








}
