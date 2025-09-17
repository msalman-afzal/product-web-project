package com.spring.mvc.project.productdao;

import java.time.LocalDateTime;

public class ProductDao {
	
	public int pid;
	public String pname;
	public String description;
	public Long price;
	public LocalDateTime time;
	
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
	@Override
	public String toString() {
		return "ProductDao [pid=" + pid + ", pname=" + pname + ", description=" + description + ", price=" + price
				+ ", time=" + time + "]";
	}

}
