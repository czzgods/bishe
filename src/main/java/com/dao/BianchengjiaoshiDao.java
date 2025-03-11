package com.dao;

import com.entity.BianchengjiaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BianchengjiaoshiVO;
import com.entity.view.BianchengjiaoshiView;


/**
 * 编程教师
 * 
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
public interface BianchengjiaoshiDao extends BaseMapper<BianchengjiaoshiEntity> {
	
	List<BianchengjiaoshiVO> selectListVO(@Param("ew") Wrapper<BianchengjiaoshiEntity> wrapper);
	
	BianchengjiaoshiVO selectVO(@Param("ew") Wrapper<BianchengjiaoshiEntity> wrapper);
	
	List<BianchengjiaoshiView> selectListView(@Param("ew") Wrapper<BianchengjiaoshiEntity> wrapper);

	List<BianchengjiaoshiView> selectListView(Pagination page,@Param("ew") Wrapper<BianchengjiaoshiEntity> wrapper);

	
	BianchengjiaoshiView selectView(@Param("ew") Wrapper<BianchengjiaoshiEntity> wrapper);
	

}
