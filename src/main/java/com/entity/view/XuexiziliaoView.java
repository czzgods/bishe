package com.entity.view;

import com.entity.XuexiziliaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 学习资料
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
@TableName("xuexiziliao")
public class XuexiziliaoView  extends XuexiziliaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XuexiziliaoView(){
	}
 
 	public XuexiziliaoView(XuexiziliaoEntity xuexiziliaoEntity){
 	try {
			BeanUtils.copyProperties(this, xuexiziliaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
