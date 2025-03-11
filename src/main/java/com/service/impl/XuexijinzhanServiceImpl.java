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


import com.dao.XuexijinzhanDao;
import com.entity.XuexijinzhanEntity;
import com.service.XuexijinzhanService;
import com.entity.vo.XuexijinzhanVO;
import com.entity.view.XuexijinzhanView;

@Service("xuexijinzhanService")
public class XuexijinzhanServiceImpl extends ServiceImpl<XuexijinzhanDao, XuexijinzhanEntity> implements XuexijinzhanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XuexijinzhanEntity> page = this.selectPage(
                new Query<XuexijinzhanEntity>(params).getPage(),
                new EntityWrapper<XuexijinzhanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XuexijinzhanEntity> wrapper) {
		  Page<XuexijinzhanView> page =new Query<XuexijinzhanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<XuexijinzhanVO> selectListVO(Wrapper<XuexijinzhanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XuexijinzhanVO selectVO(Wrapper<XuexijinzhanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XuexijinzhanView> selectListView(Wrapper<XuexijinzhanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XuexijinzhanView selectView(Wrapper<XuexijinzhanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
