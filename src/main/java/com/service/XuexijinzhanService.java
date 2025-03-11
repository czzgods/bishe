package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XuexijinzhanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XuexijinzhanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XuexijinzhanView;


/**
 * 学习进展
 *
 * @author 
 * @email 
 * @date 2024-12-20 17:38:05
 */
public interface XuexijinzhanService extends IService<XuexijinzhanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XuexijinzhanVO> selectListVO(Wrapper<XuexijinzhanEntity> wrapper);
   	
   	XuexijinzhanVO selectVO(@Param("ew") Wrapper<XuexijinzhanEntity> wrapper);
   	
   	List<XuexijinzhanView> selectListView(Wrapper<XuexijinzhanEntity> wrapper);
   	
   	XuexijinzhanView selectView(@Param("ew") Wrapper<XuexijinzhanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XuexijinzhanEntity> wrapper);

   	

}

