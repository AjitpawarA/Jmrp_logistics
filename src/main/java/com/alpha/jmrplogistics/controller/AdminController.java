package com.alpha.jmrplogistics.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.jmrplogistics.dto.DriverDto;
import com.alpha.jmrplogistics.dto.LoadingDto;
import com.alpha.jmrplogistics.dto.OrderDto;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.dto.UnloadingDto;
import com.alpha.jmrplogistics.dto.UserDto;
import com.alpha.jmrplogistics.entity.Address;
import com.alpha.jmrplogistics.entity.Cargo;
import com.alpha.jmrplogistics.entity.Carrier;
import com.alpha.jmrplogistics.entity.Driver;
import com.alpha.jmrplogistics.entity.Loading;
import com.alpha.jmrplogistics.entity.Order;
import com.alpha.jmrplogistics.entity.Truck;
import com.alpha.jmrplogistics.entity.Unloading;
import com.alpha.jmrplogistics.entity.User;
import com.alpha.jmrplogistics.services.AddressService;
import com.alpha.jmrplogistics.services.CargoService;
import com.alpha.jmrplogistics.services.CarrierService;
import com.alpha.jmrplogistics.services.DriverService;
import com.alpha.jmrplogistics.services.LoadingService;
import com.alpha.jmrplogistics.services.OrderService;
import com.alpha.jmrplogistics.services.TruckService;
import com.alpha.jmrplogistics.services.UnloadingService;
import com.alpha.jmrplogistics.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private CargoService cargoService;

	@Autowired
	private TruckService truckService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CarrierService carrierService;

	@Autowired
	private LoadingService loadingService;

	@Autowired
	private UnloadingService unloadingService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	// Truck Endpoints
	@PostMapping("/truck")
	public ResponseEntity<ResponseStructure<Map<String, Object>>> createTruck(@Valid @RequestBody Truck truck,
			@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return truckService.addTruck(truck, userrole);
	}

	@GetMapping("/trucks/truckId/{id}")
	public ResponseEntity<ResponseStructure<Truck>> getTruckById(@PathVariable("id") int truckId) {
		return truckService.getTruckById(truckId);
	}

	@DeleteMapping("/trucks/truckId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeTruck(@PathVariable("id") int truckId) {
		return truckService.deleteTruck(truckId);
	}

	@GetMapping("/trucks")
	public ResponseEntity<ResponseStructure<List<Truck>>> listAllTrucks() {
		return truckService.getAllTrucks();
	}

	// Order Endpoints
	@PostMapping("/order")
	public ResponseEntity<ResponseStructure<Order>> createOrder(@Valid @RequestBody OrderDto orderDto,@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return orderService.addOrder(orderDto,userrole);
	}

	@GetMapping("/orders/orderId/{id}")
	public ResponseEntity<ResponseStructure<Order>> getOrderById(@PathVariable("id") int orderId,@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return orderService.getOrderById(orderId,userrole);
	}

	@DeleteMapping("/orders/orderId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeOrder(@PathVariable("id") int orderId,@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return orderService.deleteOrder(orderId, userrole);
	}

	@GetMapping("/orders")
	public ResponseEntity<ResponseStructure<List<Order>>> listAllOrders(@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return orderService.getAllOrders(userrole);
	}

	// Carrier Endpoints
	@PostMapping("/carrier")
	public ResponseEntity<ResponseStructure<Carrier>> createCarrier(@Valid @RequestBody Carrier carrier,
			@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return carrierService.addCarrier(carrier, userrole);
	}

	@GetMapping("/carriers/carrierId/{id}")
	public ResponseEntity<ResponseStructure<Carrier>> getCarrierById(@PathVariable("id") int carrierId) {
		return carrierService.getCarrierById(carrierId);
	}

	@DeleteMapping("/carriers/carrierId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeCarrier(@PathVariable("id") int carrierId) {
		return carrierService.deleteCarrier(carrierId);
	}

	@GetMapping("/carriers")
	public ResponseEntity<ResponseStructure<List<Carrier>>> listAllCarriers() {
		return carrierService.getAllCarriers();
	}

	// Loading Endpoints
	@PostMapping("/loading")
	public ResponseEntity<ResponseStructure<Map<String, Object>>> createLoading(
			@Valid @RequestBody LoadingDto loadingDto,
			@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return loadingService.addLoading(loadingDto, userrole);
	}

	@GetMapping("/loadings/loadingId/{id}")
	public ResponseEntity<ResponseStructure<Loading>> getLoadingById(@PathVariable("id") int loadingId) {
		return loadingService.getLoadingById(loadingId);
	}

	@DeleteMapping("/loadings/loadingId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeLoading(@PathVariable("id") int loadingId,
			@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return loadingService.deleteLoading(loadingId,userrole);
	}

	@GetMapping("/loadings")
	public ResponseEntity<ResponseStructure<List<Loading>>> listAllLoadings() {
		return loadingService.getAllLoadings();
	}

	// Unloading Endpoints
	@PostMapping("/unloading")
	public ResponseEntity<ResponseStructure<Map<String, Object>>> addUnloading(
			@Valid @RequestBody UnloadingDto unloadingDto,
			@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return unloadingService.addUnloading(unloadingDto, userrole);
	}

	@GetMapping("/unloadings/unloadingId/{id}")
	public ResponseEntity<ResponseStructure<Unloading>> getUnloadingById(@PathVariable("id") int unloadingId) {
		return unloadingService.getUnloadingById(unloadingId);
	}

	@DeleteMapping("/unloadings/unloadingId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeUnloading(@PathVariable("id") int unloadingId,
			@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return unloadingService.deleteUnloading(unloadingId,userrole);
	}

	@GetMapping("/unloadings")
	public ResponseEntity<ResponseStructure<List<Unloading>>> listAllUnloadings() {
		return unloadingService.getAllUnloadings();
	}
	
	

	// User Endpoints
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<Map<String, Object>>> createUser(@Valid @RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

	@GetMapping("/users/userId/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable("id") int userId) {
		return userService.getUserById(userId);
	}

	@PutMapping("/users/userId/{id}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable("id") int userId,
			@RequestBody UserDto userDto) {
		return userService.updateUser(userId, userDto);
	}

	@DeleteMapping("/users/userId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeUser(@PathVariable("id") int userId) {
		return userService.deleteUser(userId);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> listAllUsers() {
		return userService.getAllUsers();
	}

	
	
	// Cargo Endpoints
	@PostMapping("/cargo")
	public ResponseEntity<ResponseStructure<Map<String, Object>>> createCargo(@Valid @RequestBody Cargo cargo,
			@CookieValue(name = "role", defaultValue = "USER") String userrole) {
		return cargoService.addCargo(cargo, userrole);
	}

	@GetMapping("/cargos/cargoId/{id}")
	public ResponseEntity<ResponseStructure<Cargo>> getCargoById(@PathVariable("id") int cargoId) {
		return cargoService.getCargoById(cargoId);
	}

	@DeleteMapping("/cargos/cargoId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeCargo(@PathVariable("id") int cargoId) {
		return cargoService.deleteCargo(cargoId);
	}

	@GetMapping("/cargos")
	public ResponseEntity<ResponseStructure<List<Cargo>>> listAllCargo() {
		return cargoService.getAllCargo();
	}

	// Address Endpoints
	@PostMapping("/addresse")
	public ResponseEntity<ResponseStructure<Map<String, Object>>> createAddress(@Valid @RequestBody Address address) {
		return addressService.addAddress(address);
	}

	@GetMapping("/addresses/addressId/{id}")
	public ResponseEntity<ResponseStructure<Address>> getAddressById(@PathVariable("id") int addressId) {
		return addressService.getAddressById(addressId);
	}

	@DeleteMapping("/addresses/addressId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeAddress(@PathVariable("id") int addressId) {
		return addressService.deleteAddress(addressId);
	}

	@GetMapping("/addresses")
	public ResponseEntity<ResponseStructure<List<Address>>> listAllAddresses() {
		return addressService.getAllAddresses();
	}

	// Driver Endpoints
	@PostMapping("/driver")
	public ResponseEntity<ResponseStructure<Map<String, Object>>> createDriver(
			@Valid @RequestBody DriverDto driverDto) {
		return driverService.addDriver(driverDto);
	}

	@GetMapping("/drivers/driverId/{id}")
	public ResponseEntity<ResponseStructure<Driver>> getDriverById(@PathVariable("id") int driverId) {
		return driverService.getDriverById(driverId);
	}

	@DeleteMapping("/drivers/driverId/{id}")
	public ResponseEntity<ResponseStructure<String>> removeDriver(@PathVariable("id") int driverId) {
		return driverService.deleteDriver(driverId);
	}

	@GetMapping("/drivers")
	public ResponseEntity<ResponseStructure<List<Driver>>> listAllDrivers() {
		return driverService.getAllDrivers();
	}
}
