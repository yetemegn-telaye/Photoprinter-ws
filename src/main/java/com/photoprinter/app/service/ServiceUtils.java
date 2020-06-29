package com.photoprinter.app.service;


import java.util.List;

import org.springframework.data.domain.Sort;

public class ServiceUtils {

	private ServiceUtils(){

	}

	public static Sort getSortCriteria(final List<String> sortAttributes) {
		Sort sort = null;
		int i = 0;
		if(sortAttributes != null && !sortAttributes.isEmpty()) {
			for(String sortAttribute: sortAttributes) {
				if (sortAttribute.startsWith("(") && sortAttribute.endsWith(")")) {
					if(i == 0){
						sort = new Sort(Sort.Direction.DESC, sortAttribute.substring(1, sortAttribute.length()-1));
					}else{
						sort = sort.and(new Sort(Sort.Direction.DESC, sortAttribute.substring(1, sortAttribute.length()-1)));
					}
				} else {
					if(i == 0){
						sort = new Sort(Sort.Direction.ASC, sortAttribute);
					}else{
						sort = sort.and(new Sort(Sort.Direction.ASC, sortAttribute));
					}
				}
				i ++;
			}
		}
		return sort;
	}

}
