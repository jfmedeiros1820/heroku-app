package com.javaee.tests.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.javaee.tests.api.v1.model.VendorDTO;
import com.javaee.tests.domain.Vendor;

@Component
public class VendorMapper {

	public VendorDTO vendorToVendorDTO(Vendor vendor) {
		final VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(vendor.getName());
		return vendorDTO;
	}

    public Vendor vendorDTOtoVendor(VendorDTO vendorDTO) {
    	final Vendor vendor = new Vendor();
    	vendor.setName(vendorDTO.getName());
		return vendor;
    }
}
