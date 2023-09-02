package com.nopcommerce.data;

import java.util.Locale;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

public class Faker_FakeData {

	public static void main(String[] args) {
		Faker faker = new Faker();
		System.out.println(faker.business().creditCardType());
		
		Faker fakerVi = new Faker(new Locale("vi"));
		System.out.println(fakerVi.address().city());
		System.out.println(fakerVi.finance().creditCard(CreditCardType.VISA));
	}

}
