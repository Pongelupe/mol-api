package br.com.mol.molapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.payloads.ReportPayload;
import br.com.mol.molapi.services.IReportService;
import br.com.mol.molapi.validators.ReportValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private IReportService reportService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ReportValidator());
	}

	@ApiOperation(value = "retrives the default prescription")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval of the default prescription"),
			@ApiResponse(code = 400, message = "Error validatind inputed json"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping
	public ResponseEntity<byte[]> generateReport(@ApiParam(required = true) @Valid @RequestBody ReportPayload payload)
			throws JRException {

		byte[] data = reportService.generateReport(payload);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}

}
