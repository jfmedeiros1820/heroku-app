package com.javaee.tests.services;

import java.util.List;

import com.javaee.tests.api.v1.model.VendorDTO;

public interface VendorService {
	
	VendorDTO getVendorById(String id);

    List<VendorDTO> getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(String id, VendorDTO vendorDTO);

    VendorDTO patchVendor(String id, VendorDTO vendorDTO);

    void deleteVendorById(String id);
}
