package br.com.mol.molapi.entity.enums;

public enum MedicineFrequencyPerDay {
	NONE(0), ONCE(24), TWICE(12), THREE_TIMES(8), FOUR_TIMES(6), SIX_TIMES(4), TWELVE_TIMES(2), TWENTY_FOUR_TIMES(1);

	private final int frequency;

	private MedicineFrequencyPerDay(int status) {
		this.frequency = status;
	}

	public int getFrequency() {
		return frequency;
	}
}
