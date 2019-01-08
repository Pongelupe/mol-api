package br.com.mol.molapi.payloads;

import br.com.mol.molapi.enums.Reports;
import lombok.Data;

@Data
public class ReportPayload {

	String json;
	Reports report = Reports.DEFAULT;
	
}
