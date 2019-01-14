package br.com.mol.molapi.utils;

import org.springframework.beans.BeanUtils;

public class DTOConverter {
	
	public static Object mapPropertiesTo(Object obj, Object obj2) {
		BeanUtils.copyProperties(obj, obj2);
		return obj2;
	}
}
