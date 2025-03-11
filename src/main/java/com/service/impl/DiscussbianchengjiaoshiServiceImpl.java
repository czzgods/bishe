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


import com.dao.DiscussbianchengjiaoshiDao;
import com.entity.DiscussbianchengjiaoshiEntity;
import com.service.DiscussbianchengjiaoshiService;
import com.entity.vo.DiscussbianchengjiaoshiVO;
import com.entity.view.DiscussbianchengjiaoshiView;

@Service("discussbianchengjiaoshiService")
public class DiscussbianchengjiaoshiServiceImpl extends ServiceImpl<DiscussbianchengjiaoshiDao, DiscussbianchengjiaoshiEntity> implements DiscussbianchengjiaoshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussbianchengjiaoshiEntity> page = this.selectPage(
                new Query<DiscussbianchengjiaoshiEntity>(params).getPage(),
                new EntityWrapper<DiscussbianchengjiaoshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussbianchengjiaoshiEntity> wrapper) {
		  Page<DiscussbianchengjiaoshiView> page =new Query<DiscussbianchengjiaoshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussbianchengjiaoshiVO> selectListVO(Wrapper<DiscussbianchengjiaoshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussbianchengjiaoshiVO selectVO(Wrapper<DiscussbianchengjiaoshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussbianchengjiaoshiView> selectListView(Wrapper<DiscussbianchengjiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussbianchengjiaoshiView selectView(Wrapper<DiscussbianchengjiaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
