package com.dao;

import com.entity.XuexijinzhanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XuexijinzhanVO;
import com.entity.view.XuexijinzhanView;


/**
 * 学习进展
 * 
 * @author 
 * @email 
 * @date 2024-12-20 17:38:05
 */
public interface XuexijinzhanDao extends BaseMapper<XuexijinzhanEntity> {
	
	List<XuexijinzhanVO> selectListVO(@Param("ew") Wrapper<XuexijinzhanEntity> wrapper);
	
	XuexijinzhanVO selectVO(@Param("ew") Wrapper<XuexijinzhanEntity> wrapper);
	
	List<XuexijinzhanView> selectListView(@Param("ew") Wrapper<XuexijinzhanEntity> wrapper);

	List<XuexijinzhanView> selectListView(Pagination page,@Param("ew") Wrapper<XuexijinzhanEntity> wrapper);

	
	XuexijinzhanView selectView(@Param("ew") Wrapper<XuexijinzhanEntity> wrapper);
	

}
