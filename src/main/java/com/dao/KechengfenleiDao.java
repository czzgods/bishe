package com.dao;

import com.entity.KechengfenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.KechengfenleiVO;
import com.entity.view.KechengfenleiView;


/**
 * 课程分类
 * 
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
public interface KechengfenleiDao extends BaseMapper<KechengfenleiEntity> {
	
	List<KechengfenleiVO> selectListVO(@Param("ew") Wrapper<KechengfenleiEntity> wrapper);
	
	KechengfenleiVO selectVO(@Param("ew") Wrapper<KechengfenleiEntity> wrapper);
	
	List<KechengfenleiView> selectListView(@Param("ew") Wrapper<KechengfenleiEntity> wrapper);

	List<KechengfenleiView> selectListView(Pagination page,@Param("ew") Wrapper<KechengfenleiEntity> wrapper);

	
	KechengfenleiView selectView(@Param("ew") Wrapper<KechengfenleiEntity> wrapper);
	

}
