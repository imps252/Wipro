package com.healthcare.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<AppointmentDTO> createAppointment(@Validated @RequestBody AppointmentDTO appointmentDTO) {
		AppointmentDTO createdAppointment = appointmentService.createAppointment(appointmentDTO);
		return ResponseEntity.ok(createdAppointment);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<AppointmentDTO>> getUserAppointments(@PathVariable Long userId) {
		List<AppointmentDTO> appointments = appointmentService.getUserAppointments(userId);
		return ResponseEntity.ok(appointments);
	}

	@PutMapping("/{appointmentId}")
	public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long appointmentId,
			@Validated @RequestBody AppointmentDTO appointmentDTO) {
		AppointmentDTO updatedAppointment = appointmentService.updateAppointment(appointmentId, appointmentDTO);
		return ResponseEntity.ok(updatedAppointment);
	}

	@DeleteMapping("/{appointmentId}")
	public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId) {
		Boolean isDeleted = appointmentService.cancelAppointment(appointmentId);
		if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{appointmentId}")
	public ResponseEntity<AppointmentDTO> getAppointmentDetails(@PathVariable Long appointmentId) {
		AppointmentDTO appointmentDTO = appointmentService.getAppointmentDetails(appointmentId);
		return ResponseEntity.ok(appointmentDTO);
	}

	@PutMapping("/reschedule/{appointmentId}")
	public ResponseEntity<AppointmentDTO> rescheduleAppointment(@PathVariable Long appointmentId,
			@Validated @RequestBody AppointmentDTO appointmentDTO) {
		AppointmentDTO rescheduledAppointment = appointmentService.rescheduleAppointment(appointmentId, appointmentDTO);
		return ResponseEntity.ok(rescheduledAppointment);
	}

	@GetMapping("/status/{appointmentId}")
	public ResponseEntity<String> checkAppointmentStatus(@PathVariable Long appointmentId) {
		String status = appointmentService.checkAppointmentStatus(appointmentId);
		return ResponseEntity.ok(status);
	}

	@GetMapping("/date")
	public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDate(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDate(date);
		return ResponseEntity.ok(appointments);
	}

	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
		List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
		return ResponseEntity.ok(appointments);
	}

	@PutMapping("/complete/{appointmentId}")
	public ResponseEntity<AppointmentDTO> markAppointmentAsCompleted(@PathVariable Long appointmentId) {
		AppointmentDTO completedAppointment = appointmentService.markAppointmentAsCompleted(appointmentId);
		return ResponseEntity.ok(completedAppointment);
	}

	@GetMapping("/history/user/{userId}")
	public ResponseEntity<List<AppointmentDTO>> getAppointmentHistoryForUser(@PathVariable Long userId) {
		List<AppointmentDTO> appointmentHistory = appointmentService.getAppointmentHistoryForUser(userId);
		return ResponseEntity.ok(appointmentHistory);
	}
}
