package com.ssafy.service;

import java.util.LinkedList;
import java.util.List;

import com.ssafy.dao.FoodDao;
import com.ssafy.dao.FoodDaoImpl;
import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;
import com.ssafy.util.FoodSaxParser;


public class FoodServiceImpl implements FoodService{
	private FoodDao dao;
	private String[] allergys={"대두","땅콩","우유","게","새우","참치","연어","쑥","소고기","닭고기","돼지고기","복숭아","민들레","계란흰자"};
	private List<Food> foods;
	
	public FoodServiceImpl() {
		 dao =new FoodDaoImpl();
	}
	public List<Food> searchAll(FoodPageBean bean) {
		return dao.searchAll(bean);
	}
	public Food search(int code) {

		//  code에  맞는 식품 정보를 검색하고, 검색된 식품의 원재료에 알레르기 성분이 있는지 확인하여 Food 정보에 입력한다.
		FoodSaxParser f = new FoodSaxParser();
		foods = f.getFoods();
				
		for(Food food : foods) {
			if(food.getCode()==code) {
				for(int i=0; i<allergys.length; i++) {
					if(!(food.getMaterial().contains(allergys[i])))
						return food;
				}
			}
		}
		
		return null;
	}
	public List<Food> searchBest() {
		return dao.searchBest();
	}
	public List<Food> searchBestIndex() {
		return dao.searchBestIndex();
	}
}
