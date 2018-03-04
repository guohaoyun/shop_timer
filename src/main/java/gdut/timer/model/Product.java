package gdut.timer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ghy
 * @Description: 商品
 * @date 2017年11月24日 下午1:36:23 
 *
 */
@Entity
@Table(name = "t_product")
public class Product implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7633993768175351159L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "product_name")
	private String productName;	//商品名称
	
	@Column(name = "category_id")
	private Integer categoryId;	//类目id
	
	@Column(name = "picture_url")
	private String pictureUrl;	//展示图片地址
	
	@Column(name = "mid_picture_url")
	private String midPictureUrl;	//商品详情页展示图
	
	@Column(name = "detail_picture_url")
	private String detailPictureUrl;	//商品详情页放大镜图
	
	@Column(name = "price")
	private long price;	//单价
	
	@Column(name = "promotion_price")
	private long promotionPrice;	//促销价
	
	@Column(name = "score")
	private Double score;	//用户评分
	
	@Column(name = "sales_number")
	private Integer salesNumber;	//销量
	
	@Column(name = "month_sales_number")
	private Integer monthSalesNumber;	//月销量
	
	@Column(name = "inventory")
	private Integer inventory;	//库存
	
	@Column(name = "total_evaluate")
	private Integer totalEvaluate;	//累计评价
	
	@Column(name = "group_buy_number")
	private Integer groupBuyNumber;	//拼单需要的人数
	
	@Column(name = "group_buy_time")
	private long groupBuyTime;	//拼单持续时间，单位毫秒
	
	@Column(name = "fee")
	private Integer fee;		//运费
	
	@Column(name = "comprehensive_score")
	private Double comprehensiveScore;	//综合评分，用于综合排序
	
	
	public Product(){}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}


	

	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}


	public Integer getSalesNumber() {
		return salesNumber;
	}


	public void setSalesNumber(Integer salesNumber) {
		this.salesNumber = salesNumber;
	}



	public Integer getMonthSalesNumber() {
		return monthSalesNumber;
	}


	public void setMonthSalesNumber(Integer monthSalesNumber) {
		this.monthSalesNumber = monthSalesNumber;
	}


	public String getMidPictureUrl() {
		return midPictureUrl;
	}


	public void setMidPictureUrl(String midPictureUrl) {
		this.midPictureUrl = midPictureUrl;
	}


	public String getDetailPictureUrl() {
		return detailPictureUrl;
	}


	public void setDetailPictureUrl(String detailPictureUrl) {
		this.detailPictureUrl = detailPictureUrl;
	}


	public Integer getInventory() {
		return inventory;
	}


	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}


	public Integer getTotalEvaluate() {
		return totalEvaluate;
	}


	public void setTotalEvaluate(Integer totalEvaluate) {
		this.totalEvaluate = totalEvaluate;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}


	public long getPromotionPrice() {
		return promotionPrice;
	}


	public void setPromotionPrice(long promotionPrice) {
		this.promotionPrice = promotionPrice;
	}


	public Integer getGroupBuyNumber() {
		return groupBuyNumber;
	}


	public void setGroupBuyNumber(Integer groupBuyNumber) {
		this.groupBuyNumber = groupBuyNumber;
	}


	public long getGroupBuyTime() {
		return groupBuyTime;
	}


	public void setGroupBuyTime(long groupBuyTime) {
		this.groupBuyTime = groupBuyTime;
	}


	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Double getComprehensiveScore() {
		return comprehensiveScore;
	}

	public void setComprehensiveScore(Double comprehensiveScore) {
		this.comprehensiveScore = comprehensiveScore;
	}


	
	



	
}
