package br.com.mol.molapi.validators;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import br.com.mol.molapi.payloads.ReportPayload;
import br.com.mol.molapi.utils.JsonPathReader;

@Component
public class ReportValidator extends BaseValidator<ReportPayload> {

	public ReportValidator() {
		super(ReportPayload.class);
	}

	@Override
	public void validate(Errors errors) {
		String json = target.getJson();
		if (StringUtils.isBlank(json)) {
			errors.rejectValue("json", "json is missing!");
		} else {
			validateRequiredFields(json, errors);
		}
	}

	private void validateRequiredFields(String json, Errors errors) {
		ReadContext documentContext = JsonPath.parse(json);
		List<String> requiredFields = target.getReport().getRequiredFields();
		JsonPathReader pathReader = new JsonPathReader(documentContext);
		requiredFields.forEach(fieldPath -> {
			String field = pathReader.readFromContext(fieldPath);
			if (field == null) {
				errors.rejectValue("json", String.format("%s is missing on the json!", fieldPath));
			}
		});
	}

}
