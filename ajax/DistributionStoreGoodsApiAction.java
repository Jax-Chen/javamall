package com.enation.app.distribution.component.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.enation.app.distribution.component.model.Distributors;
import com.enation.app.distribution.component.service.IDistributionGoodsManager;
import com.enation.app.distribution.component.service.IDistributionProductManager;
import com.enation.app.distribution.component.service.IDistributorGradeManager;
import com.enation.app.distribution.component.service.IDistributorManager;
import com.enation.app.shop.core.service.IBrandManager;
import com.enation.app.shop.core.service.ICartManager;
import com.enation.app.shop.core.service.IGoodsCatManager;
import com.enation.app.shop.core.service.IGoodsManager;
import com.enation.app.shop.core.service.IProductManager;
import com.enation.framework.action.WWAction;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.JsonMessageUtil;

/**
 * 分销商品Action
 * @author LiangLujiang
 * @copyright 唯品直通车实业（上海）有限公司
 * 2016年8月3日
 */
@ParentPackage("eop_default")
@Namespace("/api/shop")
@Action("distributionStoreGoodsApiAction")
@Scope("prototype")
@Results({
	@Result(name="distribution_Goods", type="freemarker", location="/themes/kaben/distribution/new/mydistributionGoods.html?type=distributionGoods") ,
	//@Result(name="distribution_Goods", type="freemarker", location="/com/enation/app/distribution/component/action/html/distributionGoodsforApi.html") ,
	@Result(name="listgoodsby_distrobutor", type="freemarker", location="/com/enation/app/distribution/component/action/html/distributionGoodsbyDistributorOfAll_list.html") ,
	@Result(name="listgoodsby_distrobutor_all", type="freemarker", location="/com/enation/app/distribution/component/action/html/distributionGoodsbyDistributorOfAllOnShell_list.html") ,
	//@Result(name="edit_distributonPrice", type="freemarker", location="/com/enation/app/distribution/component/action/html/distributonPrice_edit.html") ,
	@Result(name="listorderby_distrobutor", type="freemarker", location="/com/enation/app/distribution/component/action/html/distributor_order.html") ,
	
})
@Component
public class DistributionStoreGoodsApiAction extends WWAction{
	protected IGoodsCatManager goodsCatManager;
	protected IBrandManager brandManager;
	protected IGoodsManager goodsManager;
	private ICartManager cartManager;
	private IDistributionGoodsManager distributionGoodsManager;
	private IDistributorManager distributorManager;
	private IDistributorGradeManager distributorGradeManager;
	private IDistributionProductManager distributionProductManager;
	private Distributors distributors;
	private IProductManager productManager;
	
	private String pages;
	
	/**
	 * 展示某分销商的一页商品json
	 * @return
	 */
	public String listGoodsJsonforDistributorAll() {
		String d_sn = (String) ThreadContextHolder.getSessionContext().getAttribute("d_sn");
		distributors = this.distributorManager.getDistributorsBySn(d_sn);
		
		pages = (pages == null || pages.equals("")) ? "1" : pages;
		int pageSize = 10;
		
		Map searchMap = new HashMap<>();
		
		Page distributorGoodsPage = productManager.listProductforDistributor(searchMap,Integer.valueOf(pages), pageSize,true,distributors.getDistributor_id());
		//Page distributorGoodsPage = distributionGoodsManager.listforDistributor(searchMap,Integer.valueOf(page), pageSize);
		Long totalCount = distributorGoodsPage.getTotalCount();
		
		List goodsList = (List) distributorGoodsPage.getResult();
		goodsList = goodsList == null ? new ArrayList() : goodsList;
		
		
		this.json = JsonMessageUtil.getListJson(goodsList);
		
		return JSON_MESSAGE;
	}
	
	

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	public IBrandManager getBrandManager() {
		return brandManager;
	}

	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public ICartManager getCartManager() {
		return cartManager;
	}

	public void setCartManager(ICartManager cartManager) {
		this.cartManager = cartManager;
	}

	public IDistributionGoodsManager getDistributionGoodsManager() {
		return distributionGoodsManager;
	}

	public void setDistributionGoodsManager(IDistributionGoodsManager distributionGoodsManager) {
		this.distributionGoodsManager = distributionGoodsManager;
	}

	public IDistributorManager getDistributorManager() {
		return distributorManager;
	}

	public void setDistributorManager(IDistributorManager distributorManager) {
		this.distributorManager = distributorManager;
	}

	public IDistributorGradeManager getDistributorGradeManager() {
		return distributorGradeManager;
	}

	public void setDistributorGradeManager(IDistributorGradeManager distributorGradeManager) {
		this.distributorGradeManager = distributorGradeManager;
	}

	public IDistributionProductManager getDistributionProductManager() {
		return distributionProductManager;
	}

	public void setDistributionProductManager(IDistributionProductManager distributionProductManager) {
		this.distributionProductManager = distributionProductManager;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}



	public Distributors getDistributors() {
		return distributors;
	}



	public void setDistributors(Distributors distributors) {
		this.distributors = distributors;
	}



	public IProductManager getProductManager() {
		return productManager;
	}



	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}
}









