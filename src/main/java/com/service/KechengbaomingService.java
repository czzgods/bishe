package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.KechengbaomingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.KechengbaomingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.KechengbaomingView;


/**
 * 课程报名
 *
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
public interface KechengbaomingService extends IService<KechengbaomingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengbaomingVO> selectListVO(Wrapper<KechengbaomingEntity> wrapper);
   	
   	KechengbaomingVO selectVO(@Param("ew") Wrapper<KechengbaomingEntity> wrapper);
   	
   	List<KechengbaomingView> selectListView(Wrapper<KechengbaomingEntity> wrapper);
   	
   	KechengbaomingView selectView(@Param("ew") Wrapper<KechengbaomingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KechengbaomingEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<KechengbaomingEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<KechengbaomingEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<KechengbaomingEntity> wrapper);



}

