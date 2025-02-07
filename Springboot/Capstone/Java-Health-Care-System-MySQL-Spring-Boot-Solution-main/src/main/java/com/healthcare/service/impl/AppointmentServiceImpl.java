package com.healthcare.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entity.Appointment;
import com.healthcare.entity.Doctor;
import com.healthcare.entity.User;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.AppointmentRepository;
import com.healthcare.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = convertToEntity(appointmentDTO);
		appointment = appointmentRepository.save(appointment);
		return convertToDTO(appointment);
	}

	@Override
	public List<AppointmentDTO> getUserAppointments(Long userId) {
		List<Appointment> appointments = appointmentRepository.findByUserId(userId);
		return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDTO) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		if (optionalAppointment.isPresent()) {
			Appointment appointment = optionalAppointment.get();
			appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
			appointment.setStatus(appointmentDTO.getStatus());
			appointment = appointmentRepository.save(appointment);
			return convertToDTO(appointment);
		} else {
			throw new NotFoundException("Appointment not found with id " + appointmentId);
		}
	}

	@Override
	public Boolean cancelAppointment(Long appointmentId) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		if (optionalAppointment.isPresent()) {
			appointmentRepository.deleteById(appointmentId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AppointmentDTO getAppointmentDetails(Long appointmentId) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		if (optionalAppointment.isPresent()) {
			return convertToDTO(optionalAppointment.get());
		} else {
			throw new NotFoundException("Appointment not found with id " + appointmentId);
		}
	}

	@Override
	public AppointmentDTO rescheduleAppointment(Long appointmentId, AppointmentDTO appointmentDTO) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		if (optionalAppointment.isPresent()) {
			Appointment appointment = optionalAppointment.get();
			appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
			appointment = appointmentRepository.save(appointment);
			return convertToDTO(appointment);
		} else {
			throw new NotFoundException("Appointment not found with id " + appointmentId);
		}
	}

	@Override
	public String checkAppointmentStatus(Long appointmentId) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		if (optionalAppointment.isPresent()) {
			return optionalAppointment.get().getStatus();
		} else {
			throw new NotFoundException("Appointment not found with id " + appointmentId);
		}
	}

	@Override
	public List<AppointmentDTO> getAppointmentsByDate(LocalDate date) {
		LocalDateTime startOfDay = date.atStartOfDay();
		LocalDateTime endOfDay = date.atTime(23, 59, 59);
		List<Appointment> appointments = appointmentRepository.findAppointmentsByDateRange(null, startOfDay, endOfDay);
		return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId) {
		List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorAndDateRange(doctorId, null,
				null);
		return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public AppointmentDTO markAppointmentAsCompleted(Long appointmentId) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		if (optionalAppointment.isPresent()) {
			Appointment appointment = optionalAppointment.get();
			appointment.setStatus("COMPLETED");
			appointment = appointmentRepository.save(appointment);
			return convertToDTO(appointment);
		} else {
			throw new NotFoundException("Appointment not found with id " + appointmentId);
		}
	}

	@Override
	public List<AppointmentDTO> getAppointmentHistoryForUser(Long userId) {
		List<Appointment> appointments = appointmentRepository.findByUserId(userId);
		return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private AppointmentDTO convertToDTO(Appointment appointment) {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(appointment.getId());
		appointmentDTO.setUserId(appointment.getUser().getId());
		appointmentDTO.setDoctorId(appointment.getDoctor().getId());
		appointmentDTO.setAppointmentTime(appointment.getAppointmentTime());
		appointmentDTO.setStatus(appointment.getStatus());
		return appointmentDTO;
	}

	private Appointment convertToEntity(AppointmentDTO appointmentDTO) {
		Appointment appointment = new Appointment();
		appointment.setId(appointmentDTO.getId());
		User user = new User();
		user.setId(appointmentDTO.getUserId());
		appointment.setUser(user);
		Doctor doctor = new Doctor();
		doctor.setId(appointmentDTO.getDoctorId());
		appointment.setDoctor(doctor);
		appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
		appointment.setStatus(appointmentDTO.getStatus());
		return appointment;
	}
}
