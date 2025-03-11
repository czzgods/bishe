package com.dao;

import com.entity.DiscussbianchengjiaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussbianchengjiaoshiVO;
import com.entity.view.DiscussbianchengjiaoshiView;


/**
 * 编程教师评论表
 * 
 * @author 
 * @email 
 * @date 2024-12-20 17:38:06
 */
public interface DiscussbianchengjiaoshiDao extends BaseMapper<DiscussbianchengjiaoshiEntity> {
	
	List<DiscussbianchengjiaoshiVO> selectListVO(@Param("ew") Wrapper<DiscussbianchengjiaoshiEntity> wrapper);
	
	DiscussbianchengjiaoshiVO selectVO(@Param("ew") Wrapper<DiscussbianchengjiaoshiEntity> wrapper);
	
	List<DiscussbianchengjiaoshiView> selectListView(@Param("ew") Wrapper<DiscussbianchengjiaoshiEntity> wrapper);

	List<DiscussbianchengjiaoshiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussbianchengjiaoshiEntity> wrapper);

	
	DiscussbianchengjiaoshiView selectView(@Param("ew") Wrapper<DiscussbianchengjiaoshiEntity> wrapper);
	

}
