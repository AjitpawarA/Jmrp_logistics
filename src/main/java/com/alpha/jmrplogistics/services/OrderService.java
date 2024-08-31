package com.alpha.jmrplogistics.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.dao.OrderDao;
import com.alpha.jmrplogistics.dto.OrderDto;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Order;
import com.alpha.jmrplogistics.entity.User;
import com.alpha.jmrplogistics.exception.OrderNotAvailableException;
import com.alpha.jmrplogistics.exception.ResourceNotFoundException;
import com.alpha.jmrplogistics.repo.CargoRepository;
import com.alpha.jmrplogistics.repo.CarrierRepository;
import com.alpha.jmrplogistics.repo.LoadingRepository;
import com.alpha.jmrplogistics.repo.UnloadingRepository;
import com.alpha.jmrplogistics.repo.UserRepository;

import jakarta.validation.Valid;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private CarrierRepository carrierRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoadingRepository loadingRepository;

	@Autowired
	private UnloadingRepository unloadingRepository;

	public ResponseEntity<ResponseStructure<Order>> addOrder(@Valid OrderDto order, String userrole) {
		ResponseStructure<Order> responseStructure = new ResponseStructure<>();
		if (userrole.equals("ADMIN")) {

			Order orderObj = new Order();

			orderObj.setDateOfOrder(order.getDateoforder());
			orderObj.setOrderStatus(order.getOrderstatus());
			orderObj.setFreightCost(order.getFreightcost());
			orderObj.setAdditionalInfo(order.getAdditionalinfo());

			orderObj.setCargo(cargoRepository.findById((int) order.getCargoId())
					.orElseThrow(() -> new ResourceNotFoundException("Cargo not found for ID: " + order.getCargoId())));
			orderObj.setCarrier(carrierRepository.findById((int) order.getCarrierID()).orElseThrow(
					() -> new ResourceNotFoundException("Carrier not found for ID: " + order.getCarrierID())));

			orderObj.setLoading(loadingRepository.findById((int) order.getLoadingId()).orElseThrow(
					() -> new ResourceNotFoundException("Loading point not found for ID: " + order.getLoadingId())));
			orderObj.setUnloading(unloadingRepository.findById((int) order.getUnloadingId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Unloading point not found for ID: " + order.getUnloadingId())));

			List<User> loadingUsers = new ArrayList<>();
			loadingUsers.add(userRepository.findById(order.getLoadingUserid()).orElseThrow(
					() -> new ResourceNotFoundException("Loading User not found for ID: " + order.getLoadingUserid())));
			orderObj.setLoadingUsers(loadingUsers);

			List<User> unloadingUsers = new ArrayList<>();
			unloadingUsers.add(
					userRepository.findById(order.getUnloadingUserid()).orElseThrow(() -> new ResourceNotFoundException(
							"Unloading User not found for ID: " + order.getUnloadingUserid())));
			orderObj.setUnloadingUsers(unloadingUsers);

			Order savedOrder = orderDao.addOrder(orderObj);

			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Order created successfully");
			responseStructure.setData(savedOrder);
			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		} else {

			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Order created successfully");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<Order>> getOrderById(int id, String role) {
		ResponseStructure<Order> responseStructure = new ResponseStructure<>();
		
		if(role.equals("ADMIN")) {
			Order order = orderDao.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Order not found for ID: " + id));

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Order found");
			responseStructure.setData(order);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);}
		else {

			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("u r not a admin");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);}
		}
	
	public ResponseEntity<ResponseStructure<String>> deleteOrder(int id, String role) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		if (role.equals("ADMIN")) {
			Order order = orderDao.findById(id)
					.orElseThrow(() -> new OrderNotAvailableException("Order not found for ID: " + id));

			orderDao.deleteById(id);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Order deleted successfully");
			responseStructure.setData("Deleted order with ID: " + id);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("u r not a admin");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		}
	}

	public ResponseEntity<ResponseStructure<List<Order>>> getAllOrders(String userrole) {
		ResponseStructure<List<Order>> responseStructure = new ResponseStructure<>();
		if (userrole.equals("ADMIN")) {
			List<Order> orders = orderDao.findAll();

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Orders retrieved successfully");
			responseStructure.setData(orders);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {

			responseStructure.setStatuscode(HttpStatus.FORBIDDEN.value());
			responseStructure.setMessage("u r not a admin");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		}
	}

	public ResponseEntity<ResponseStructure<Order>> updateOrder(OrderDto order, int id) {
		Order orderObj = orderDao.findById(id).orElseThrow(() -> new OrderNotAvailableException("Order not found"));
		orderObj.setDateOfOrder(order.getDateoforder());
		orderObj.setOrderStatus(order.getOrderstatus());
		orderObj.setFreightCost(order.getFreightcost());
		orderObj.setAdditionalInfo(order.getAdditionalinfo());

		orderObj.setCargo(cargoRepository.findById((int) order.getCargoId())
				.orElseThrow(() -> new ResourceNotFoundException("Cargo not found for ID: " + order.getCargoId())));
		orderObj.setCarrier(carrierRepository.findById((int) order.getCarrierID())
				.orElseThrow(() -> new ResourceNotFoundException("Carrier not found for ID: " + order.getCarrierID())));

		orderObj.setLoading(loadingRepository.findById((int) order.getLoadingId()).orElseThrow(
				() -> new ResourceNotFoundException("Loading point not found for ID: " + order.getLoadingId())));
		orderObj.setUnloading(unloadingRepository.findById((int) order.getUnloadingId()).orElseThrow(
				() -> new ResourceNotFoundException("Unloading point not found for ID: " + order.getUnloadingId())));

		List<User> loadingUsers = new ArrayList<>();
		loadingUsers.add(userRepository.findById(order.getLoadingUserid()).orElseThrow(
				() -> new ResourceNotFoundException("Loading User not found for ID: " + order.getLoadingUserid())));
		orderObj.setLoadingUsers(loadingUsers);

		List<User> unloadingUsers = new ArrayList<>();
		unloadingUsers.add(userRepository.findById(order.getUnloadingUserid()).orElseThrow(
				() -> new ResourceNotFoundException("Unloading User not found for ID: " + order.getUnloadingUserid())));
		orderObj.setUnloadingUsers(unloadingUsers);
		Order savedOrder = orderDao.addOrder(orderObj);

		ResponseStructure<Order> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Order Updated successfully");
		responseStructure.setData(savedOrder);
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

}
