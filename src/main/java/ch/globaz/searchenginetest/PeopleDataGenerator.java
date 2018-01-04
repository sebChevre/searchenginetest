package ch.globaz.searchenginetest;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PeopleDataGenerator {

	public static List<Personne> generateRandomPersonns() {

		Faker faker = new Faker();

		return IntStream.range(0,1000).asLongStream().mapToObj((index) -> {
			String nom = faker.name().firstName();
			String prenom = faker.name().lastName();

			String rue = faker.address().streetAddress();
			String localite = faker.address().cityName();

			String telFixe = faker.phoneNumber().cellPhone();
			String telMobile = faker.phoneNumber().phoneNumber();

			return Personne.from(nom, prenom, rue, localite, telFixe, telMobile);
		}).collect(Collectors.toList());








	}
}
