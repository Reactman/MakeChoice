/*
 * Copyright (c) 2017. Visionet and/or its affiliates. All right reserved.
 * VISIONET PROPRIETARY/CONFIDENTIAL.
 */
package com.example.visionet.makechoice.entity;

import java.util.List;
import java.util.Set;

/**
 * @author TC.Ubuntu
 * @since 2017/7/29.
 */
public class ChoiceOption {
	private String optionName;

	private Set<Option> options;

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}
}
