package com.healthcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dto.DoctorDTO;
import com.healthcare.entity.Doctor;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.DoctorRepository;
import com.healthcare.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
		Doctor doctor = convertToEntity(doctorDTO);
		doctor = doctorRepository.save(doctor);
		return convertToDTO(doctor);
	}

	@Override
	public DoctorDTO getDoctorById(Long doctorId) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
		if (optionalDoctor.isPresent()) {
			return convertToDTO(optionalDoctor.get());
		} else {
			throw new NotFoundException("Doctor not found with id " + doctorId);
		}
	}

	@Override
	public List<DoctorDTO> getAllDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private DoctorDTO convertToDTO(Doctor doctor) {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setId(doctor.getId());
		doctorDTO.setName(doctor.getName());
		doctorDTO.setSpecialty(doctor.getSpecialty());
		return doctorDTO;
	}

	private Doctor convertToEntity(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor();
		doctor.setId(doctorDTO.getId());
		doctor.setName(doctorDTO.getName());
		doctor.setSpecialty(doctorDTO.getSpecialty());
		return doctor;
	}
}
