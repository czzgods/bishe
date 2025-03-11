package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PaikexinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PaikexinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PaikexinxiView;


/**
 * 排课信息
 *
 * @author 
 * @email 
 * @date 2024-12-20 17:38:05
 */
public interface PaikexinxiService extends IService<PaikexinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PaikexinxiVO> selectListVO(Wrapper<PaikexinxiEntity> wrapper);
   	
   	PaikexinxiVO selectVO(@Param("ew") Wrapper<PaikexinxiEntity> wrapper);
   	
   	List<PaikexinxiView> selectListView(Wrapper<PaikexinxiEntity> wrapper);
   	
   	PaikexinxiView selectView(@Param("ew") Wrapper<PaikexinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PaikexinxiEntity> wrapper);

   	

}

