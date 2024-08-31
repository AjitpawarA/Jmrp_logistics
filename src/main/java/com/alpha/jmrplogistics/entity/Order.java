package com.alpha.jmrplogistics.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate dateoforder;
	private String orderstatus;
	private Double freightcost;
	private String additionalinfo;

	@ManyToOne
	@JoinColumn(name = "carrier_id", nullable = false)
	private Carrier carrier;

	@OneToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;

	@OneToOne
	@JoinColumn(name = "loading_id", nullable = false)
	private Loading loading;

	@OneToOne
	@JoinColumn(name = "unloading_id", nullable = false)
	private Unloading unloading;

	@ManyToMany
	@JoinTable(name = "order_loading_users", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> loadingusers;

	@ManyToMany
	@JoinTable(name = "order_unloading_users", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> unloadingusers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateOfOrder() {
		return dateoforder;
	}

	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateoforder = dateOfOrder;
	}

	public String getOrderStatus() {
		return orderstatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderstatus = orderStatus;
	}

	public Double getFreightCost() {
		return freightcost;
	}

	public void setFreightCost(Double freightCost) {
		this.freightcost = freightCost;
	}

	public String getAdditionalInfo() {
		return additionalinfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalinfo = additionalInfo;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Loading getLoading() {
		return loading;
	}

	public void setLoading(Loading loading) {
		this.loading = loading;
	}

	public Unloading getUnloading() {
		return unloading;
	}

	public void setUnloading(Unloading unloading) {
		this.unloading = unloading;
	}

	public List<User> getLoadingUsers() {
		return loadingusers;
	}

	public void setLoadingUsers(List<User> loadingUsers) {
		this.loadingusers = loadingUsers;
	}

	public List<User> getUnloadingUsers() {
		return unloadingusers;
	}

	public void setUnloadingUsers(List<User> unloadingUsers) {
		this.unloadingusers = unloadingUsers;
	}
}
