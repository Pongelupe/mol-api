package br.com.mol.molapi.validators.enums;

public enum ValidatorsEnum {

	MISSING_FIELD("% is missing!");

	private ValidatorsEnum(String message) {
		this.message = message;
	}

	private final String message;
	private static final String WILD_CARD = "%";

	public String getFormattedMessage(String... wildcards) {
		String messageFormatted = message;
		int messageWildcards = message.split(WILD_CARD).length;
		int iterations = wildcards.length < messageWildcards ? wildcards.length : messageWildcards;
		for (int i = 0; i < iterations; i++) {
			messageFormatted = messageFormatted.replaceFirst(WILD_CARD, wildcards[i]);
		}

		return messageFormatted;
	}

}
