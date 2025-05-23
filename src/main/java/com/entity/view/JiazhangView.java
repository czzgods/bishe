package com.entity.view;

import com.entity.JiazhangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 家长
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-12-20 17:38:04
 */
@TableName("jiazhang")
public class JiazhangView  extends JiazhangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiazhangView(){
	}
 
 	public JiazhangView(JiazhangEntity jiazhangEntity){
 	try {
			BeanUtils.copyProperties(this, jiazhangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
