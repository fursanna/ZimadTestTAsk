package framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

public class ValidatorJson {
    public static boolean validate(JsonNode data, JsonNode schemaNode) {
        JsonValidator VALIDATOR  = JsonSchemaFactory.byDefault().getValidator();

        ProcessingReport report = VALIDATOR.validateUnchecked(schemaNode, data);
        AllureLogger.logJsonValidationResult(report.toString());
        return report.isSuccess();
    }
}
