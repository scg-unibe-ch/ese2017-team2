package ch.unibe.eseteam2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Animal;
import ch.unibe.eseteam2.model.dao.AnimalRepository;

@Service
public class AnimalService {
	@Autowired
	private AnimalRepository animalRepository;

	public Iterable<Animal> findAnimals() {
		return this.animalRepository.findAll();
	}

	public void save(Animal animal) {
		this.animalRepository.save(animal);
	}

}
