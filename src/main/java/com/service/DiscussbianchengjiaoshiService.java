package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussbianchengjiaoshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussbianchengjiaoshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussbianchengjiaoshiView;


/**
 * 编程教师评论表
 *
 * @author 
 * @email 
 * @date 2024-12-20 17:38:06
 */
public interface DiscussbianchengjiaoshiService extends IService<DiscussbianchengjiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussbianchengjiaoshiVO> selectListVO(Wrapper<DiscussbianchengjiaoshiEntity> wrapper);
   	
   	DiscussbianchengjiaoshiVO selectVO(@Param("ew") Wrapper<DiscussbianchengjiaoshiEntity> wrapper);
   	
   	List<DiscussbianchengjiaoshiView> selectListView(Wrapper<DiscussbianchengjiaoshiEntity> wrapper);
   	
   	DiscussbianchengjiaoshiView selectView(@Param("ew") Wrapper<DiscussbianchengjiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussbianchengjiaoshiEntity> wrapper);

   	

}

