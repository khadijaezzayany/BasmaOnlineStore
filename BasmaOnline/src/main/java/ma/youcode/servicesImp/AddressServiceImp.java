package ma.youcode.servicesImp;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.youcode.entities.Address;
import ma.youcode.entities.User;
import ma.youcode.repository.AddressRepository;
import ma.youcode.repository.UserRepository;
import ma.youcode.services.AddressService;
import ma.youcode.shared.AddressDto;
import ma.youcode.shared.UserDto;
import ma.youcode.shared.Utils;

@Service
public class AddressServiceImp implements AddressService {

	@Autowired
	Utils utils;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<AddressDto> getAllAddresses(String email) {

		User currentUser = userRepository.findByEmail(email);
		List<Address> addresses = currentUser.getAdmin() == true ? (List<Address>) addressRepository.findAll()
				: addressRepository.findByUser(currentUser);

		Type listType = new TypeToken<List<AddressDto>>() {
		}.getType();
		List<AddressDto> addressDto = new ModelMapper().map(addresses, listType);

		return addressDto;
	}

	@Override
	public AddressDto createAddress(AddressDto address, String email) {

		User currentUser = userRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		address.setAddressId(utils.genereteStringId(30));
		address.setUser(userDto);

		Address addressEntity = modelMapper.map(address, Address.class);
		Address newAddress = addressRepository.save(addressEntity);
		AddressDto addressDto = modelMapper.map(newAddress, AddressDto.class);
		return addressDto;

	}

	@Override
	public AddressDto getAddress(String addressId) {
		Address addressEntity = addressRepository.findByAddressId(addressId);
		ModelMapper modelMapper = new ModelMapper();
		AddressDto addressDto = modelMapper.map(addressEntity, AddressDto.class);
		return addressDto;
	}

	@Override
	public void deleteAddress(String addressId) {
		Address address = addressRepository.findByAddressId(addressId);

		if (address == null)
			throw new RuntimeException("Address not found");
		addressRepository.delete(address);

	}

}
