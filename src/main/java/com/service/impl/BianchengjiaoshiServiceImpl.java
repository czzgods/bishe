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


import com.dao.BianchengjiaoshiDao;
import com.entity.BianchengjiaoshiEntity;
import com.service.BianchengjiaoshiService;
import com.entity.vo.BianchengjiaoshiVO;
import com.entity.view.BianchengjiaoshiView;

@Service("bianchengjiaoshiService")
public class BianchengjiaoshiServiceImpl extends ServiceImpl<BianchengjiaoshiDao, BianchengjiaoshiEntity> implements BianchengjiaoshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BianchengjiaoshiEntity> page = this.selectPage(
                new Query<BianchengjiaoshiEntity>(params).getPage(),
                new EntityWrapper<BianchengjiaoshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BianchengjiaoshiEntity> wrapper) {
		  Page<BianchengjiaoshiView> page =new Query<BianchengjiaoshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BianchengjiaoshiVO> selectListVO(Wrapper<BianchengjiaoshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BianchengjiaoshiVO selectVO(Wrapper<BianchengjiaoshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BianchengjiaoshiView> selectListView(Wrapper<BianchengjiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BianchengjiaoshiView selectView(Wrapper<BianchengjiaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
