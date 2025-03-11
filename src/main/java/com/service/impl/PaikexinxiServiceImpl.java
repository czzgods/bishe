package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.PaikexinxiDao;
import com.entity.PaikexinxiEntity;
import com.service.PaikexinxiService;
import com.entity.vo.PaikexinxiVO;
import com.entity.view.PaikexinxiView;

@Service("paikexinxiService")
public class PaikexinxiServiceImpl extends ServiceImpl<PaikexinxiDao, PaikexinxiEntity> implements PaikexinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PaikexinxiEntity> page = this.selectPage(
                new Query<PaikexinxiEntity>(params).getPage(),
                new EntityWrapper<PaikexinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PaikexinxiEntity> wrapper) {
		  Page<PaikexinxiView> page =new Query<PaikexinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PaikexinxiVO> selectListVO(Wrapper<PaikexinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PaikexinxiVO selectVO(Wrapper<PaikexinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PaikexinxiView> selectListView(Wrapper<PaikexinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PaikexinxiView selectView(Wrapper<PaikexinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
