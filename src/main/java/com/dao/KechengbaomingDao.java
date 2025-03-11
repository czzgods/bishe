package com.dao;

import com.entity.KechengbaomingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.KechengbaomingVO;
import com.entity.view.KechengbaomingView;


/**
 * 课程报名
 * 
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
public interface KechengbaomingDao extends BaseMapper<KechengbaomingEntity> {
	
	List<KechengbaomingVO> selectListVO(@Param("ew") Wrapper<KechengbaomingEntity> wrapper);
	
	KechengbaomingVO selectVO(@Param("ew") Wrapper<KechengbaomingEntity> wrapper);
	
	List<KechengbaomingView> selectListView(@Param("ew") Wrapper<KechengbaomingEntity> wrapper);

	List<KechengbaomingView> selectListView(Pagination page,@Param("ew") Wrapper<KechengbaomingEntity> wrapper);

	
	KechengbaomingView selectView(@Param("ew") Wrapper<KechengbaomingEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KechengbaomingEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KechengbaomingEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KechengbaomingEntity> wrapper);



}
