package br.com.mol.molapi.payloads;

import br.com.mol.molapi.enums.Reports;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPayload {

	String json;
	Reports report = Reports.DEFAULT;
	
}
