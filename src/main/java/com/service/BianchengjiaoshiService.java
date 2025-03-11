package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BianchengjiaoshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BianchengjiaoshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BianchengjiaoshiView;


/**
 * 编程教师
 *
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
public interface BianchengjiaoshiService extends IService<BianchengjiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BianchengjiaoshiVO> selectListVO(Wrapper<BianchengjiaoshiEntity> wrapper);
   	
   	BianchengjiaoshiVO selectVO(@Param("ew") Wrapper<BianchengjiaoshiEntity> wrapper);
   	
   	List<BianchengjiaoshiView> selectListView(Wrapper<BianchengjiaoshiEntity> wrapper);
   	
   	BianchengjiaoshiView selectView(@Param("ew") Wrapper<BianchengjiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BianchengjiaoshiEntity> wrapper);

   	

}

