package com.dao;

import com.entity.PaikexinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PaikexinxiVO;
import com.entity.view.PaikexinxiView;


/**
 * 排课信息
 * 
 * @author 
 * @email 
 * @date 2024-12-20 17:38:05
 */
public interface PaikexinxiDao extends BaseMapper<PaikexinxiEntity> {
	
	List<PaikexinxiVO> selectListVO(@Param("ew") Wrapper<PaikexinxiEntity> wrapper);
	
	PaikexinxiVO selectVO(@Param("ew") Wrapper<PaikexinxiEntity> wrapper);
	
	List<PaikexinxiView> selectListView(@Param("ew") Wrapper<PaikexinxiEntity> wrapper);

	List<PaikexinxiView> selectListView(Pagination page,@Param("ew") Wrapper<PaikexinxiEntity> wrapper);

	
	PaikexinxiView selectView(@Param("ew") Wrapper<PaikexinxiEntity> wrapper);
	

}
