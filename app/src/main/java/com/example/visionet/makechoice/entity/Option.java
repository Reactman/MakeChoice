/*
 * Copyright (c) 2017. Visionet and/or its affiliates. All right reserved.
 * VISIONET PROPRIETARY/CONFIDENTIAL.
 */
package com.example.visionet.makechoice.entity;

import java.sql.Timestamp;

/**
 * @author TC.Ubuntu
 * @since 2017/7/29.
 */
public class Option {

	private String name;

	private Integer weight;

	private Timestamp lastChoiceTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Timestamp getLastChoiceTime() {
		return lastChoiceTime;
	}

	public void setLastChoiceTime(Timestamp lastChoiceTime) {
		this.lastChoiceTime = lastChoiceTime;
	}
}
